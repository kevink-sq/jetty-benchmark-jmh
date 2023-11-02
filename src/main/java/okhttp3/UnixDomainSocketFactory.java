package okhttp3;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import org.newsclub.net.unix.AFUNIXSelectorProvider;
import org.newsclub.net.unix.AFUNIXSocketAddress;

public final class UnixDomainSocketFactory extends SocketFactory {
  private final File path;

  public UnixDomainSocketFactory(File path) {
    this.path = path;
  }

  @Override public Socket createSocket() throws IOException {
    AFUNIXSelectorProvider provider = AFUNIXSelectorProvider.provider();
    try {
      return new TunnelingUnixSocket(AFUNIXSocketAddress.of(path), provider.openSocketChannel());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override public Socket createSocket(String host, int port) throws IOException {
    Socket result = createSocket();
    result.connect(new InetSocketAddress(host, port));
    return result;
  }

  @Override public Socket createSocket(
      String host, int port, InetAddress localHost, int localPort) throws IOException {
    return createSocket(host, port);
  }

  @Override public Socket createSocket(InetAddress host, int port) throws IOException {
    Socket result = createSocket();
    result.connect(new InetSocketAddress(host, port));
    return result;
  }

  @Override public Socket createSocket(
      InetAddress host, int port, InetAddress localAddress, int localPort) throws IOException {
    return createSocket(host, port);
  }

  public File getPath() {
    return path;
  }
}
