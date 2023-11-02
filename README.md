# Description

[jmh](https://github.com/openjdk/jmh) benchmarking against [jetty-poc](https://github.com/kevink-sq/jetty-concurrency-issue-poc)

## To use

start jetty-concurrency-issue-poc server via:

```
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

in this repo

```
mvn clean verify
```

then run benchmark

```
java -jar target/benchmarks.jar

```

Modify code to target different endpoints as necessary.

## Benchmark (Nov 2 31 2023)

```
JUNX
----
Result "org.sample.MyBenchmark.testMethod":
  26026.928 ±(99.9%) 199.467 ops/s [Average]
  (min, avg, max) = (25446.878, 26026.928, 26341.407), stdev = 266.283
  CI (99.9%): [25827.460, 26226.395] (assumes normal distribution)

Benchmark                Mode  Cnt      Score     Error  Units
MyBenchmark.testMethod  thrpt   25  26026.928 ± 199.467  ops/s

NATIVE
------
Result "org.sample.MyBenchmark.testMethod":
  29355.454 ±(99.9%) 109.610 ops/s [Average]
  (min, avg, max) = (28953.738, 29355.454, 29530.573), stdev = 146.326
  CI (99.9%): [29245.844, 29465.063] (assumes normal distribution)

Benchmark                Mode  Cnt      Score     Error  Units
MyBenchmark.testMethod  thrpt   25  29355.454 ± 109.610  ops/s

HTTP
-----
Result "org.sample.MyBenchmark.testMethod":
  24814.379 ±(99.9%) 196.266 ops/s [Average]
  (min, avg, max) = (24413.903, 24814.379, 25246.640), stdev = 262.010
  CI (99.9%): [24618.112, 25010.645] (assumes normal distribution)

Benchmark                Mode  Cnt      Score     Error  Units
MyBenchmark.testMethod  thrpt   25  24814.379 ± 196.266  ops/s
```

