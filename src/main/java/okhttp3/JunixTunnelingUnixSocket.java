package okhttp3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.nio.channels.SocketChannel;
import java.util.Set;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.newsclub.net.unix.AFUNIXSocketChannel;

final class JunixTunnelingUnixSocket extends Socket {
  private final AFUNIXSocketAddress socketAddress;
  private final AFUNIXSocketChannel socketChannel;
  private InetSocketAddress inetAddress;

  JunixTunnelingUnixSocket(AFUNIXSocketAddress socketAddress, AFUNIXSocketChannel socketChannel) {
    this.socketAddress = socketAddress;
    this.socketChannel = socketChannel;
  }

  @Override public void connect(SocketAddress endpoint) throws IOException {
    this.inetAddress = (InetSocketAddress) endpoint;
    socketChannel.socket().connect(socketAddress);
  }

  @Override public void connect(SocketAddress endpoint, int timeout) throws IOException {
    this.inetAddress = (InetSocketAddress) endpoint;
    socketChannel.socket().connect(socketAddress, timeout);
  }

  @Override public InetAddress getInetAddress() {
    return inetAddress.getAddress();
  }

  @Override
  public void bind(SocketAddress bindpoint) throws IOException {
    socketChannel.socket().bind(bindpoint);
  }

  @Override
  public InetAddress getLocalAddress() {
    return socketChannel.socket().getLocalAddress();
  }

  @Override
  public int getPort() {
    return socketChannel.socket().getPort();
  }

  @Override
  public int getLocalPort() {
    return socketChannel.socket().getLocalPort();
  }

  @Override
  public SocketAddress getRemoteSocketAddress() {
    SocketAddress socketAddress =  socketChannel.socket().getRemoteSocketAddress();
    return (socketAddress != null) ? socketAddress : new InetSocketAddress(getInetAddress(), getPort());
  }

  @Override
  public SocketAddress getLocalSocketAddress() {
    return socketChannel.socket().getLocalSocketAddress();
  }

  @Override
  public SocketChannel getChannel() {
    return socketChannel.socket().getChannel();
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return socketChannel.socket().getInputStream();
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    return socketChannel.socket().getOutputStream();
  }

  @Override
  public void setTcpNoDelay(boolean on) throws SocketException {
    socketChannel.socket().setTcpNoDelay(on);
  }

  @Override
  public boolean getTcpNoDelay() throws SocketException {
    return socketChannel.socket().getTcpNoDelay();
  }

  @Override
  public void setSoLinger(boolean on, int linger) throws SocketException {
    socketChannel.socket().setSoLinger(on, linger);
  }

  @Override
  public int getSoLinger() throws SocketException {
    return socketChannel.socket().getSoLinger();
  }

  @Override
  public void sendUrgentData(int data) throws IOException {
    socketChannel.socket().sendUrgentData(data);
  }

  @Override
  public void setOOBInline(boolean on) throws SocketException {
    socketChannel.socket().setOOBInline(on);
  }

  @Override
  public boolean getOOBInline() throws SocketException {
    return socketChannel.socket().getOOBInline();
  }

  @Override
  public synchronized void setSoTimeout(int timeout) throws SocketException {
    socketChannel.socket().setSoTimeout(timeout);
  }

  @Override
  public synchronized int getSoTimeout() throws SocketException {
    return socketChannel.socket().getSoTimeout();
  }

  @Override
  public synchronized void setSendBufferSize(int size) throws SocketException {
    socketChannel.socket().setSendBufferSize(size);
  }

  @Override
  public synchronized int getSendBufferSize() throws SocketException {
    return socketChannel.socket().getSendBufferSize();
  }

  @Override
  public synchronized void setReceiveBufferSize(int size) throws SocketException {
    socketChannel.socket().setReceiveBufferSize(size);
  }

  @Override
  public synchronized int getReceiveBufferSize() throws SocketException {
    return socketChannel.socket().getReceiveBufferSize();
  }

  @Override
  public void setKeepAlive(boolean on) throws SocketException {
    socketChannel.socket().setKeepAlive(on);
  }

  @Override
  public boolean getKeepAlive() throws SocketException {
    return socketChannel.socket().getKeepAlive();
  }

  @Override
  public void setTrafficClass(int tc) throws SocketException {
    socketChannel.socket().setTrafficClass(tc);
  }

  @Override
  public int getTrafficClass() throws SocketException {
    return socketChannel.socket().getTrafficClass();
  }

  @Override
  public void setReuseAddress(boolean on) throws SocketException {
    socketChannel.socket().setReuseAddress(on);
  }

  @Override
  public boolean getReuseAddress() throws SocketException {
    return socketChannel.socket().getReuseAddress();
  }

  @Override
  public synchronized void close() throws IOException {
    socketChannel.socket().close();
  }

  @Override
  public void shutdownInput() throws IOException {
    socketChannel.socket().shutdownInput();
  }

  @Override
  public void shutdownOutput() throws IOException {
    socketChannel.socket().shutdownOutput();
  }

  @Override
  public String toString() {
    return socketChannel.socket().toString();
  }

  @Override
  public boolean isConnected() {
    return socketChannel.socket().isConnected();
  }

  @Override
  public boolean isBound() {
    return socketChannel.socket().isBound();
  }

  @Override
  public boolean isClosed() {
    return socketChannel.socket().isClosed();
  }

  @Override
  public boolean isInputShutdown() {
    return socketChannel.socket().isInputShutdown();
  }

  @Override
  public boolean isOutputShutdown() {
    return socketChannel.socket().isOutputShutdown();
  }

  @Override
  public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
    socketChannel.socket().setPerformancePreferences(connectionTime, latency, bandwidth);
  }

  @Override
  public <T> Socket setOption(SocketOption<T> name, T value) throws IOException {
    return socketChannel.socket().setOption(name, value);
  }

  @Override
  public <T> T getOption(SocketOption<T> name) throws IOException {
    return socketChannel.socket().getOption(name);
  }

  @Override
  public Set<SocketOption<?>> supportedOptions() {
    return socketChannel.socket().supportedOptions();
  }

  @Override
  public int hashCode() {
    return socketChannel.socket().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return socketChannel.socket().equals(obj);
  }

}
