package core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Rahimi on 12/22/18.
 */
public class Transfer {

  public Transfer() {}

  String getFileName(StringBuilder req) {
    String t = req.toString();
    String[] lines = t.split("\\r?\\n");
    if (lines.length == 2 && lines[0].equals("File Request:")) {
      return lines[1];
    }
    return "NOFILE";
  }

  static final int MSG_LEN = 8;

  // public void send(String file, String path) {
  //   System.out.println("init 0");
  //   try {
  //     System.out.println("init1");
  //     DatagramSocket socket = new DatagramSocket(
  //       8027,
  //       InetAddress.getByName("0.0.0.0")
  //     );
  //     byte[] buf = new byte[100];
  //     DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
  //     System.out.println("init2");
  //     try {
  //       socket.receive(packet);
  //     } catch (IOException e) {
  //       System.out.println("err0");
  //     }
  //     System.out.println("init");
  //     String req_file = getFileName(data(buf));
  //     System.out.println("Request:" + req_file);
  //     if (file.equals(req_file)) {
  //       System.out.println("init");
  //       DatagramSocket ssocker = new DatagramSocket(5000);
  //       FileReader f = new FileReader(path);
  //       BufferedReader br = new BufferedReader(f);
  //       File ff = new File(path);
  //       long l = ff.length();
  //       for (int i = 0; i < Math.ceil(l / MSG_LEN) + 1; i++) {
  //         System.out.println("reached " + i);
  //         char[] chr = new char[MSG_LEN];
  //         int n = br.read(chr, 0, MSG_LEN);
  //         String line = "OFFSET=" + (i) + "=" + String.valueOf(chr);
  //         byte[] buf2 = line.getBytes();
  //         DatagramPacket ppacket = new DatagramPacket(
  //           buf2,
  //           0,
  //           buf2.length,
  //           packet.getAddress(),
  //           packet.getPort()
  //         );
  //         ssocker.send(ppacket);
  //       }
  //       String line = "#END";
  //       byte[] buf2 = line.getBytes();
  //       DatagramPacket ppacket = new DatagramPacket(
  //         buf2,
  //         0,
  //         buf2.length,
  //         packet.getAddress(),
  //         packet.getPort()
  //       );
  //       ssocker.send(ppacket);
  //       ssocker.send(ppacket);
  //       System.out.println("reached here");
  //       br.close();
  //       f.close();
  //     } else {
  //       System.out.println("Invalid Requested File");
  //     }
  //   } catch (IOException e) {
  //     System.out.println("error");
  //     e.printStackTrace();
  //   }
  // }

  public void send(String file, int portNumber) throws Exception {
    byte[] checksum = digestCheckSum(file);
    DatagramSocket ds = new DatagramSocket();
    InetAddress ip = InetAddress.getByName("127.0.0.1");

    DatagramPacket dp = new DatagramPacket(
      checksum,
      checksum.length,
      ip,
      portNumber
    );

    // getAddress() method
    System.out.println("Address : " + dp.getAddress());

    // getPort() method
    System.out.println("Port : " + dp.getPort());

    // getLength() method
    System.out.println("Length : " + dp.getLength());

    // getData() method
    System.out.println("Data : " + Arrays.toString(dp.getData()));

    // getSocketAddress() method
    System.out.println("Socket Address : " + dp.getSocketAddress());

    // getOffset() method
    System.out.println("Offset : " + dp.getOffset());
    ds.send(dp);
    System.out.println("Sent " + Arrays.toString(checksum) + " to " + ip);
    ds.setReuseAddress(true);
    ds.close();
  }

  public byte[] digestCheckSum(String file) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");

      return md.digest();
    } catch (NoSuchAlgorithmException nSAE) {
      nSAE.printStackTrace();
    }
    return null;
  }
}
