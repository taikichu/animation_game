package Client_potato;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.*;


public class Client_Menu {
    static int potato_b_x;
    static int potato_b_y;
    static int potato_b_h;
    static int potato_b_w;
    static int potato_b_ah = -1;
    static int potato_b_aw ;

    static Color body_color = Color.gray;

    static Menu_Potato M_Potato;
    static JFrame menuframe;
    static JButton buttonRun;
    static JButton buttonOp;
    static Graphics2D g2;
    int h=550;
    int w=550;

    public static void main(String[] args) throws InterruptedException {
//    public Main_Client() throws InterruptedException {//接続をインスタンスを生成
        //接続が成功したら、キャラクターの主要部位を受け取ってパネルを描画するメソッドを呼び出す


        int count = 0 ;
        JPanel pane = new JPanel();
        Datagram_Send_C.serveripaddress = JOptionPane.showInputDialog( pane, "サーバーのipアドレスを入力して下さい", "ユーザーからの文字列を受け付けます",
                JOptionPane.INFORMATION_MESSAGE );

        new Threads_ ("Datagram_Send_C").start();
        while(potato_b_ah < 0 && count < 10){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
        if(count >= 10){
            JOptionPane.showMessageDialog( pane, "サーバーを見つけられなかったため終了します" );
            System.exit(0);
        }
        new Threads_("Client_Menu").start();
    }
    public Client_Menu() {
        menuframe = new JFrame("pane_Client");
        System.out.println("Main_Clientに入っています");
        menuframe.setSize(w,h);
        menuframe.setLocationRelativeTo(null);
        menuframe.setResizable(false);
        menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        M_Potato= new Menu_Potato();
        M_Potato.setLayout(null);
        menuframe.add(M_Potato);
        menuframe.setResizable(false);
        menuframe.setVisible(true);
    }
    public void addComponent() {

        buttonRun = new JButton(new Run_button());//paint→コンポーネントの順で生成?
        buttonRun.setText("走る");
        buttonRun.setBounds(w/6,(h*2)/3,w/2,h/5);

        buttonOp = new JButton(new Option_button(menuframe));
        buttonOp.setText("option");
        buttonOp.setBounds((w*3)/4,(h*2)/3,w/5,h/5);

        M_Potato.add(buttonOp);
        M_Potato.add(buttonRun);
    }
    class Run_button extends AbstractAction{
        Run_button(){
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            menuframe.setVisible(false);//false
            DatagramPacket sendPacket=new DatagramPacket("battle ".getBytes(),"battle".getBytes().length, Datagram_Send_C.sendAddress);//Datagram_Send_S.address_Port
            try {
                Datagram_Send_C.socket.send(sendPacket);
                System.out.println("battleを送りました");
                Datagram_receive_C.battleframe.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    class Menu_Potato extends JPanel{

        public Menu_Potato() {
            System.out.println("Menu_Potatoのコンストラクタに入っています。");
        }

        public void paint(Graphics g) {
            g2=(Graphics2D)g;
            System.out.println("Menu_Potatoのpaintに入っています");
            g2.setColor(Color.black);
            DrawMethod.drawRect( w/6 , (h*2)/3 , w/2 , h/5 , g2 );
            DrawMethod.drawRect( (w*3)/4 , (h*2)/3 , w/5 , h/5 , g2 );

            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHints(rh);

            System.out.println("Client_Menu paint を通りました");

            full(g2);
            new Threads_("menu2").start();
            menuframe.setVisible(true);
        }

        void reDrawing(){
            repaint();
        }
        //横たわる芋を描画
        public void full(Graphics g) {
            makearmL(g2);
            makebody(g2);
            makearmR(g2);
            makeeye(g2);
        }


        public void makeeye(Graphics g) {
            g.setColor(Color.black);
            DrawMethod.drawArc( potato_b_x , potato_b_y*9/10 , potato_b_w , potato_b_h , 0 , 15 , g );
        }
        public void makebody(Graphics g) {
            g.setColor(body_color);
            DrawMethod.fillRoundRect( potato_b_x , potato_b_y , potato_b_h , potato_b_w , potato_b_ah , potato_b_aw , g );
        }
        public void makearmR(Graphics g) {
            g.setColor(Color.black);
            DrawMethod.drawLine( potato_b_x+(potato_b_h)/10*6 , potato_b_y+(potato_b_w*2)/3 , potato_b_x-10 , potato_b_y+potato_b_w , g);
            g.setColor(Color.white);
            DrawMethod.fillArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w-10/2 , 10 , 10 , 0 , 360 , g );
            g.setColor(Color.black);
            DrawMethod.drawArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w-10/2 , 10 , 10 , 0 , 360 , g );
        }
        public void makearmL(Graphics g) {
            g.setColor(Color.black);
            DrawMethod.drawLine( potato_b_x+(potato_b_h)/10*4 , potato_b_y+potato_b_w/3 , potato_b_x-10 ,  potato_b_y+potato_b_w*2/3 , g );
            g.setColor(Color.white);
            DrawMethod.fillArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w*2/3-10/2 , 10 , 10 , 0 , 360 , g );
            g.setColor(Color.black);
            DrawMethod.drawArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w*2/3-10/2 , 10 , 10 , 0 , 360 , g );
        }
    }
}

