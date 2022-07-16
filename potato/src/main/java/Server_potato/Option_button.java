package Server_potato;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Option_button extends AbstractAction{
	int pertime=0;
	JFrame frame_menu,frame1,frame2;
	boolean isResetProcess=true;
	Move moving;
	Random random;
	Graphics2D g2;
	JButton buttonRun;
	JButton buttonOp;
	JPanel panel;
	int w;int h;
	Potato_1 potato ;
	public Option_button(JFrame frame_menu) {
		this.frame_menu=frame_menu;
		WorkGui.countdownR=0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		isResetProcess = true;
		
		frame_menu.setVisible(false);
		frame1 = new JFrame();
		frame1.setResizable(false);
        frame1.setSize(w = 300 , h = 300 );
        frame1.setLocation(w, h);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        moving = new Move();
        frame1.add(moving);
        moving.setLayout(null);
        
        frame1.setVisible(true);
        

        frame2 = new JFrame();
		frame2.setResizable(false);
        frame2.setSize(w = 550 , h = 550 );
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JButton button1 = new JButton(new ColorClass());
        button1.setText("Body:color");
        button1.setPreferredSize(new Dimension(w,h/5));
        
        JButton button2 = new JButton(new Heart());
        button2.setText("Heart:have");
        button2.setPreferredSize(new Dimension(w,h/5));
        
        JButton button3 = new JButton(new Ret());
        button3.setText("メニューに戻る");
        button3.setPreferredSize(new Dimension(w,h/5));
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        
        
        frame2.add(panel);
        frame2.setVisible(true);
	}
	class Move extends JPanel implements ActionListener{
		public Move() {
			Timer timer=new Timer(WorkGui.framespeed,this);
			timer.setInitialDelay(500);
	        timer.start();
		}
		@Override
		public void paint(Graphics g) {
			super.paintComponent(g);
			 g2 = (Graphics2D) g;
			 
		        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);

		        rh.put(RenderingHints.KEY_RENDERING,
		                RenderingHints.VALUE_RENDER_QUALITY);

		        g2.setRenderingHints(rh);
		        Dimension windowSize = getSize();//現在あるコンポーネントの状態を返す　//Dimensionはguiにあるように調整してくれる
		        
		        if (isResetProcess) {
		            one(windowSize.width, windowSize.height);
		            isResetProcess = false;
		        }
		        this.nextPosition(windowSize.width, windowSize.height,g);
		        potato.fullset(g2);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
		public void nextPosition(int w,int h,Graphics g) {//moveと描画の間でアクション動作を完了させる必要がある?
			move(g);
		}
		//A//一度だけ呼び出される-----------------------------------------------------------------------------------------------new Potato_1();
		public void one(int w, int h) {

			potato = new Potato_1( w/4 , (h*9)/10 );
			random=new Random();			
		}
		void move(Graphics g) {
			pertime++;
		}
	}
	/**オプションクラスのColorの項目を定義*/
	class ColorClass extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o;
			String[] selectSt = {"red","blue","yellow","black","white","glay"};
			Color[] selectCo = {Color.red,Color.blue,Color.yellow,Color.black,Color.white,Color.gray};
			o=JOptionPane.showInputDialog(panel, "色を選択してください","Color", JOptionPane.INFORMATION_MESSAGE,null, selectSt, selectSt[0]);
			for(int i = 0 ; i < selectSt.length ; i++ ) {
				if(selectSt[i].equals(o.toString())) {
					Potato_1.body_color=selectCo[i];
					break;
				}
			}
		}
	}
	class Heart extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o;
			String[] selectSt = {"１つ","２つ","３つ","４つ","５つ","６つ","７つ","８つ"};
			o=JOptionPane.showInputDialog(panel, "ハートの数を選択してください","LIFE", JOptionPane.INFORMATION_MESSAGE,null, selectSt, selectSt[0]);
			for(int i = 0 ; i < selectSt.length ; i++ ) {
				if(selectSt[i].equals(o.toString())) {
					WorkGui.lifenum=i+1;
					if(DrawMethod.field.equals("online")){
						DatagramPacket sendPacket=new DatagramPacket(("online "+i+1+" ").getBytes(),("online "+i+1+" ").getBytes().length,new InetSocketAddress("localhost",10009));//Datagram_Send_S.address_Port
//						sendPacket.setPort(Datagram_Send_S.SENDBACK_PORT+1);//1つ隣のポート
						try {
							Send_S.socket_Client_10009.send(sendPacket);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					break;
				}
			}
		}
		
	}
	/**メニューに戻る*/
	class Ret extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			frame1.setVisible(false);
			frame2.setVisible(false);
			frame_menu.setVisible(true);
		}
		
	}
}
