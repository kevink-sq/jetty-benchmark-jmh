/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import okhttp3.UnixDomainSocketFactory;
import java.io.File;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Threads;

public class MyBenchmark {

    private static final OkHttpClient nativeClient = new OkHttpClient.Builder()
        .socketFactory(new UnixDomainSocketFactory(new File("/tmp/native-ingress.sock")))
        .build();
    private static final OkHttpClient jnrClient = new OkHttpClient.Builder()
        .socketFactory(new UnixDomainSocketFactory(new File("/tmp/jnr-ingress.sock")))
        .build();
    private static final OkHttpClient junixClient = new OkHttpClient.Builder()
        .socketFactory(new UnixDomainSocketFactory(new File("/tmp/junix-ingress.sock")))
        .build();
    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
        .build();

    private static final Request socketRequest = new Request.Builder().url("http://localhost/process").build();
    private static final Request httpRequest = new Request.Builder().url("http://localhost:8090/process").build();

    @Threads(Threads.MAX)
    @Benchmark
    public void nativeBenchmark() throws IOException {
        run(nativeClient, socketRequest);
    }

    @Threads(Threads.MAX)
    @Benchmark
    public void jnrBenchmark() throws IOException {
        run(jnrClient, socketRequest);
    }

    @Threads(Threads.MAX)
    @Benchmark
    public void junixBenchmark() throws IOException {
        run(junixClient, socketRequest);
    }

    @Threads(Threads.MAX)
    @Benchmark
    public void httpBenchmark() throws IOException {
        run(httpClient, httpRequest);
    }

    private static void run(OkHttpClient client, Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.body().string().contains("hello")){
                throw new RuntimeException("Didn't get expected output: " + response.body().string());
            }
        }
    }

}
