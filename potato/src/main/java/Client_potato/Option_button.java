package Client_potato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Random;


public class Option_button extends AbstractAction{
	ArrayList<String> array = new ArrayList<String>();
	static String word;
	static boolean Pa = true ;
	int pertime=0;
	static JFrame frame_menu,frame1,frame2;
	boolean isResetProcess=true;
	Random random;
	Graphics2D g2;
	JButton buttonRun;
	JButton buttonOp;
	JPanel panel;
	int w;int h;
	public Option_button(JFrame frame_menu) {
		this.frame_menu=frame_menu;
//		String text = "countdownR ";
//		DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length, Datagram_Send_C.sendAddress);//Datagram_Send_S.address_Port
//		try {
//			Datagram_Send_C.socket.send(sendPacket);
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		isResetProcess = true;
		frame_menu.setVisible(false);
        

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
					Client_Menu.body_color = selectCo[i];
					String word = "body_Color " + selectCo[i];
					DatagramPacket sendPacket=new DatagramPacket(word.getBytes(),word.getBytes().length, Datagram_Send_C.sendAddress);//Datagram_Send_S.address_Port
					try {
						Datagram_Send_C.socket.send(sendPacket);
						System.out.println("potato_color申請を送りました");
					} catch (IOException ex) {
						ex.printStackTrace();
					}
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
					Datagram_receive_C.lifenum = i+1 ;
					String word = "lifenum " + i+1 + " ";
					DatagramPacket sendPacket=new DatagramPacket(word.getBytes(),word.getBytes().length, Datagram_Send_C.sendAddress);//Datagram_Send_S.address_Port
					try {
						Datagram_Send_C.socket.send(sendPacket);
						System.out.println("potato_lifenum申請を送りました");
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					if(DrawMethod.field.equals("online")){
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
			Client_Menu.M_Potato.reDrawing();
			frame2.setVisible(false);
			frame_menu.setVisible(true);
		}
	}
	static void setPa(){
		Pa = false;
	}
	static void setword(String text){
		word = text;
	}
}
