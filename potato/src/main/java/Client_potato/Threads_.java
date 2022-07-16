package Client_potato;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Threads_ extends Thread implements ActionListener{
	String word;
	static Client_Menu menu;
	int num ;
	JFrame frame;
	Graphics2D g;
//	Act act;
	public Threads_() {
	}
	public Threads_(String word){
		this.word=word;
	}
	public Threads_(JFrame frame, String word) {
		this.word=word;
		this.frame=frame;
	}
	  public void run() {
	    	if(word.equals("gui")) {
//	    		gui=new BattleGui();
//	    		System.out.println("終了？");
	    	}
	    	if(word.equals("Client_Menu")) {
				menu=new Client_Menu();
			}
	    	if(word.equals("menu2")) {
				menu.addComponent();
			}
	    	if(word.equals("key")) {
	    		new KeyActCatch(frame);
				System.out.println("keyAct起動");
	    	}
			if( word.equals("Datagram_Send_C")){
				try {
					new Datagram_Send_C();
					System.out.println(" new Datagram_Send_C()");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			  if(word.equals("Datagram_Receive_C")) {
				  new Datagram_receive_C();
			  }
			  if(word.equals("Datagram_Receive_C_1")) {

				   new Datagram_receive_C(1);

				   System.out.println("Threads 終了 Datagram_Receive_C_1");
			  }
			  if(word.equals("visible")) {
				  Client_Menu.menuframe.setVisible(true);
			  }

//Datagram_Receive_C_1
	    	
	    }
	  public void actionPerformed(ActionEvent e) {	  
		  
		}
//	  public BattleGui getBattleGui() {
//		  return gui;
//	  }
}
