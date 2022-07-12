package Server_potato;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

//初めの接続のプログラム + keyアクションを受け取る
//受け取ったパケットからアドレスを保持しておく
public class Datagram_Receive_S {

    public static final int RECEIVE_PORT = 10007;

    public static final int PACKET_SIZE = 1024;
    public static InetAddress IpAddress;
    static String RehostIP;
//    public static void main(String[] args) {
    public Datagram_Receive_S(){


        DatagramSocket socket_server10007 = null;
        byte[] buf = new byte[PACKET_SIZE];
        //格納用パケット
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        packet.setSocketAddress(new InetSocketAddress("172.20.10.5",10007));
        String word = "no" ;
        try {
            socket_server10007 = new DatagramSocket(RECEIVE_PORT);//サーバー?的な
            while(true){
                socket_server10007.receive(packet);//receiveしたら次に進む
                System.out.println("packet.getAddress()" + packet.getAddress());
                RehostIP =  packet.getAddress().getHostAddress();//変数に送られてきたもののアドレスを保持
                System.out.println(RehostIP);
                word = new String(buf, 0, packet.getLength());//受け取った物を文字列化
                String[] words = word.split(" ");
                if(words[0].equals("keyTyped")){
                    BattleGui.actFlag2++;
                    BattleGui.actFlag = words[1];
                }
                else if(words[0].equals("menu")){
                    String text = "menu "+ Menu.Menu_Potato.potato_b_x+" "+Menu.Menu_Potato.potato_b_y+" "+Potato_1.body_w_L+" "+Potato_1.body_h_L+" "+Potato_1.body_aw_L+" "+Potato_1.body_ah_L+" "
                            +Potato_1.body_color+" ";
                    DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(RehostIP,10009));//Datagram_Send_S.address_Port
                    try {
                        Datagram_Send_S.socket_Client_10009.send(sendPacket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(words[0].equals("battle")){
//                    Menu.frame.setVisible(false);
                    new BattleGui();
                }
                else if(words[0].equals("countdownR")){
                    BattleGui.countdownR = 0 ;
                }
                else if(words[0].equals("boo")){
                    BattleGui.boo = true ;
                }
                else if(words[0].equals("lifenum")){
                    BattleGui.lifenum = Integer.parseInt(words[1]);
                }
                else if(words[0].equals("body_Color")){
                    System.out.println("body_colorの申請を受けました:"+words[1]);
                    words[1]=words[1].replace("java.awt.Color[","")
                            .replace("]","")
                            .replace("r","")
                            .replace("g","")
                            .replace("b","")
                            .replace("=","");
                    String[] color=words[1].split(",");
                    Potato_1.body_color = new Color(Integer.parseInt(color[0]),
                            Integer.parseInt(color[1]),
                            Integer.parseInt(color[2]));
                }

                System.out.println(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket_server10007 != null) {
                socket_server10007.close();
            }
        }//finall end
    }//main end

}//class end