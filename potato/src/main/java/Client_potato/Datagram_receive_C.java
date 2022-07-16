package Client_potato;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Datagram_receive_C extends JPanel {
    boolean boo = false ;
    static int lifenum = 3 ;
    static Color heart_color=Color.blue;
    protected static int color_count  ;


    //パケットサイズを1024byteに設定

    public static final int SENDBACK_PORT = 10008;
//    public static final int SENDBACK_PORT+1 = 10008+1;//10009
    static int w = 700 , h = 550 ;
    int o ;

    String[] color = new String[3];

    public static final int PACKET_SIZE = 1024;
    DatagramSocket backsocket;
    DatagramPacket receivePacket;
    static String message;
    byte[] sendbackbuf;
    static JFrame battleframe;
    ArrayList<String> array = new ArrayList<String>();

//public static void main(String[] args){
        Datagram_receive_C(){
           battleframe =new JFrame("C_run");
            battleframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            battleframe.add(new Datagram_receive_C(0));
            battleframe.setResizable(false);
            battleframe.setSize( w , h );
            battleframe.setLocationRelativeTo(null);
            battleframe.setVisible(false);//--------------------現在false 実行するときはtrue?
            new Threads_(battleframe,"key").start();
    }//コンストラクタ end
    Datagram_receive_C(int o){
            this.o = o ;
        try {
            sendbackbuf = new byte[PACKET_SIZE];
            receivePacket = new DatagramPacket(sendbackbuf, sendbackbuf.length);
            //Threadで分けてないとサーバー側の口とクライアント側で混乱するため、//クライアント側ではサーバーをいちいちクローズしている
            //o==1の時ポートを1つ追加する
            backsocket = new DatagramSocket(SENDBACK_PORT+o);//
            if(o == 1){
                while(true) {
                    backsocket.receive(receivePacket);
                    message = new String(sendbackbuf, 0, receivePacket.getLength());
                    String[] words = message.split(" ");//wards.size() >= 2を想定

                    switch (words[0]){
                        case "color_count" :
                            color_count = Integer.parseInt(words[1]);
                            System.out.println("color_count:"+color_count);
                            break;
                        case "heart_color" :
                            words[1]=(words[1]
                                    .replace("java.awt.Color[","")
                                    .replace("]","")
                                    .replace("r","")
                                    .replace("g","")
                                    .replace("b","")
                                    .replace("=",""));
                            String[] heart=words[1].split(",");
                            heart_color = new Color(Integer.parseInt(color[0]),
                                    Integer.parseInt(color[1]),
                                    Integer.parseInt(color[2]));
                            break;
                        case "lifenum" :
                            lifenum = Integer.parseInt(words[1]);
                            break;
                        case "boo" :
                            if(words[1].equals("true")){
                                boo = true ;
                            }else{
                                boo = false ;
                            }
                            break;
                        case "menu":
                            for(int i = 1;i < words.length;i++){
                                switch (i){
                                    case 1:
                                        Client_Menu.potato_b_x = Integer.parseInt(words[i]);
                                        break;
                                    case 2:
                                        Client_Menu.potato_b_y = Integer.parseInt(words[i]);
                                        break;
                                    case 3:
                                        Client_Menu.potato_b_w = Integer.parseInt(words[i]);
                                        break;
                                    case 4:
                                        Client_Menu.potato_b_h = Integer.parseInt(words[i]);
                                        break;
                                    case 5:
                                        Client_Menu.potato_b_aw = Integer.parseInt(words[i]);
                                        break;
                                    case 6:
                                        Client_Menu.potato_b_ah = Integer.parseInt(words[i]);
                                        break;
                                    case 7:
                                        words[i]=words[i].replace("java.awt.Color[","")
                                                .replace("]","")
                                                .replace("r","")
                                                .replace("g","")
                                                .replace("b","")
                                                .replace("=","");
                                        color=words[i].split(",");
                                        Client_Menu.body_color = new Color(Integer.parseInt(color[0]),
                                                Integer.parseInt(color[1]),
                                                Integer.parseInt(color[2]));
                                        break;
                                }
                            }
                            break;
                    }
                    if(words[0].equals("gameover")){
                        Datagram_receive_C.battleframe.setVisible(false);
                        System.out.println("gameover後 : setVisible");
                        System.exit(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2.setRenderingHints(rh);

            try {
                while(true) {
                    backsocket.receive(receivePacket);
                    message = new String(sendbackbuf, 0, receivePacket.getLength());
                    String word = message.split(" ")[0];
                    if(word.equals("null") == false) {
                        if(word.equals("false")){
                            break;
                        }
                        array.add(word);
                    }else if(array !=null && array.size() > 0){
                        array.set(1, array.get(1).replace("java.awt.Color[","")
                                .replace("]","")
                                .replace("r","")
                                .replace("g","")
                                .replace("b","")
                                .replace("=",""));

                        color=array.get(1).split(",");
                        g2.setColor(new Color(Integer.parseInt(color[0]),
                                Integer.parseInt(color[1]),
                                Integer.parseInt(color[2])));
                        switch (array.get(0)){
                            case "drawLine" :
                                DrawMethod.drawLine(
                                        Integer.parseInt(array.get(2)),
                                        Integer.parseInt(array.get(3)),
                                        Integer.parseInt(array.get(4)),
                                        Integer.parseInt(array.get(5)), g2);
                                break;
                            case "drawString":
                                g2.setFont(new Font("Serif", Font.PLAIN, 16+ Integer.parseInt(array.get(5))));
                                DrawMethod.drawString(
                                        array.get(2),
                                        Integer.parseInt(array.get(3)),
                                        Integer.parseInt(array.get(4)), g2);
                                break;
                            case "drawRect" :
                                DrawMethod.drawRect(
                                        Integer.parseInt(array.get(2)),
                                        Integer.parseInt(array.get(3)),
                                        Integer.parseInt(array.get(4)),
                                        Integer.parseInt(array.get(5)), g2);
                                break;
                            case "drawArc":
                                DrawMethod.drawArc(
                                        Integer.parseInt(array.get(2)),
                                        Integer.parseInt(array.get(3)),
                                        Integer.parseInt(array.get(4)),
                                        Integer.parseInt(array.get(5)),
                                        Integer.parseInt(array.get(6)),
                                        Integer.parseInt(array.get(7)), g2);
                                break;
                            case "fillRoundRect" :
                                DrawMethod.fillRoundRect(
                                        Integer.parseInt(array.get(2)),
                                        Integer.parseInt(array.get(3)),
                                        Integer.parseInt(array.get(4)),
                                        Integer.parseInt(array.get(5)),
                                        Integer.parseInt(array.get(6)),
                                        Integer.parseInt(array.get(7)), g2);
                                break;
                            case "fillArc":
                                DrawMethod.fillArc(
                                        Integer.parseInt(array.get(2)),
                                        Integer.parseInt(array.get(3)),
                                        Integer.parseInt(array.get(4)),
                                        Integer.parseInt(array.get(5)),
                                        Integer.parseInt(array.get(6)),
                                        Integer.parseInt(array.get(7)), g2);
                                break;
                        }
                        array.clear();
                    }
                    else{
                        System.out.println("continue");
                    }
                }
                makeHeart(g2);
                repaint();
            } catch (Exception e) {
                array.clear();
                repaint();
            }

    }
    void makeHeart(Graphics2D g) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        int y0 = 0 , y180 = 0 ;
        double t0 = 0 ;double t180 = 0;
        double c = 1.5 ;//ハートの大きさ
        for(int i = 0 ; i < 180 ; i++ ) {
            t0= Math.toRadians(i);
            t180= Math.toRadians(i+180);//色を満たすため180度ずらす
            for(int j = 0 ; j <lifenum ; j++ ) {
                array.add((int) ((16 * Math.sin(t0) *Math.sin(t0) * Math.sin(t0))*c)+50+(w*(8-j))/10);
                array.add((int) ((16 * Math.sin(t180) *Math.sin(t180) * Math.sin(t180))*c)+50+(w*(8-j))/10);

            }
            y0=(int) -((13 * Math.cos(t0) - 5 * Math.cos(2 * t0) - 2 * Math.cos(3 * t0) - Math.cos(4 * t0))*c)+50;
            y180=(int) -((13 * Math.cos(t180) - 5 * Math.cos(2 * (t180)) - 2 * Math.cos(3 * (t180)) - Math.cos(4 * (t180)))*c)+50;
            if(color_count>0 && heart_color != null ) {//color_countが増えていたらその数だけハートを青くする
                g.setColor(heart_color);

                for(int j = 0 ; j < array.size()/2 ; j++ ) {

                    if((j+1) <= color_count) {
                        DrawMethod.drawLine(array.get(2*j), y0, array.get(2*j+1) , y180 , g);
                    }else {
                        g.setColor(new Color(255,20,30));
                        for(int k = j ; k < array.size()/2 ; k++) {
                            DrawMethod.drawLine(array.get(2*k), y0, array.get(2*k+1) , y180 , g);
                        }
                        break;
                    }
                }
            }else {
                g.setColor(new Color(255,20,30));
                for(int j = 0 ; j < array.size()/2 ; j++) {
                    DrawMethod.drawLine(array.get(2*j), y0, array.get(2*j+1) , y180 , g);
                }
            }
            array.clear();
        }
        if(lifenum<=color_count) {
            if(boo == false){
                String word = "boo ";
                DatagramPacket sendPacket=new DatagramPacket(word.getBytes(),word.getBytes().length, Datagram_Send_C.sendAddress);//Datagram_Send_S.address_Port
                try {
                    Datagram_Send_C.socket.send(sendPacket);
                    System.out.println("booを送りました");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            boo = true ;

        }
    }
}//class end