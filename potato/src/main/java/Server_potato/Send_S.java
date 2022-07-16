package Server_potato;
import java.io.IOException;
import java.net.*;

public class Send_S {

    public static final int SENDBACK_PORT = 10008;

    public static final int PACKET_SIZE = 1024;
    static DatagramSocket socket_Client_10008;
    static DatagramSocket socket_Client_10009;

    static {
        try {
            socket_Client_10008 = new DatagramSocket();
            socket_Client_10009 = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

//    static InetSocketAddress address_Port;
        Send_S(){
            //framespeed
            String text = "framespeed " + WorkGui.framespeed;
            DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP,10009));//Datagram_Send_S.address_Port
            try {
                Send_S.socket_Client_10009.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //color_count
            text = "color_count " + WorkGui.color_count;
            sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP,10009));//Datagram_Send_S.address_Port
            try {
                Send_S.socket_Client_10009.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

//        try {
//            address_Port =
//                    new InetSocketAddress(ipname, SENDBACK_PORT);//受け取ったアドレスと決め打ちしたポート番号を設定

//            sendback_socket = new DatagramSocket();//ソケットを生成（Threadで分けてないと混乱するため、サーバー側は送る方をいちいちクローズしている）
//            byte[] sendbackbuf = new byte[PACKET_SIZE];//送る内容のバイト数を決める
//            sendbackbuf=("ok").getBytes();//送るものをバイト化

//                DatagramPacket sendback_packet =
//                        new DatagramPacket(sendbackbuf, sendbackbuf.length, address_Port);//送るパケットを準備
//                socket_Client_10008.send(sendback_packet);//送る
//            System.out.println("DatagramReceiverがアクセスしました port= 10008 ");

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }//main end

}//class end