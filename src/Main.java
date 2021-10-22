

/**
 * Created by Rahimi on 12/22/18.
 */

import java.util.Scanner;

import core.Receiver;
import core.Transfer;

public class Main {

  public static void main(String[] args) {
    Receiver receiver = new Receiver();
    Transfer transfer = new Transfer();
    // System.out.print("Enter your port number: ");
    //        if (args.length < 2) {
    //            showHelp();
    //        } else {
    //            if (args[0].equals("-receive")) {
    //                System.out.println("RECEIVE: " + args[1]);
    //		receiver.receive();
    //            } else if (args.length == 5 && args[0].equals("-serve")) {
    //                System.out.println("SEND: " + args[2] + " " + args[4]);
    // transfer.send(
    //   "data.txt",
    //   "/home/mark/projects/P2PFileTransfer-master/data.txt"
    // );
    // byte[] checkSum = transfer.digestCheckSum(
    //   "/home/mark/projects/P2PFileTransfer-master/data.txt"
    // );
    int opt = 0;
    Scanner sc = new Scanner(System.in);
    while (opt != 3) {

      System.out.println("Enter port number: ");
      int myPort = sc.nextInt();
      System.out.println("Enter port number: ");
      int otherPort = sc.nextInt();
      System.out.println("1. Send\n2. Receive");
      opt = sc.nextInt();
      switch (opt) {
        case 1:
          try {
            transfer.send(
              "/home/mark/projects/P2PFileTransfer-master/data.txt", otherPort
            );
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case 2:
          try {
            receiver.receive();
          } catch (Exception e) {
            e.printStackTrace();
          }
      }
      // receiver.receive();
    }
    sc.close();
    // while(true) {
    // receiver.receive();
    // }

    //            } else {
    //                showHelp();
    //            }
    //        }
  }
}
