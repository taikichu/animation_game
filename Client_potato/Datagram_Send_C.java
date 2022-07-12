package Client_potato;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Datagram_Send_C {

    public static final int SERVER_PORT = 10007;
    //パケットサイズを1024byteに設定
    static String serveripaddress = "";
    static InetSocketAddress sendAddress;
    static DatagramSocket socket;
    static Threads_ receive_C_1;
    static {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static final int PACKET_SIZE = 1024;
//    public static void main(String args[]) throws InterruptedException {
    Datagram_Send_C() throws InterruptedException {

        new Threads_("Datagram_Receive_C").start();//receiveの10008ポート
        new Threads_("Datagram_Receive_C_1").start();//receiveの10009ポート

        Thread.sleep(500);//1秒間隔をあける

        String message = "menu ";

        System.out.println("port10007:アクセス");
        sendAddress = new InetSocketAddress(serveripaddress, SERVER_PORT);
        try {
            byte[] buf = message.getBytes("MS932");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, sendAddress);
            socket.send(packet);//ここで送る
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//main end
}//class end