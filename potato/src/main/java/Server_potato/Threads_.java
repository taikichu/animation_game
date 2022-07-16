package Server_potato;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Threads_ extends Thread implements ActionListener{
	String word;
	static WorkGui gui;
	static Menu menu;
	JFrame frame;



	Graphics g;

	String line;
	int x1;
	int x2;
	int y1;
	int y2;
	int w;
	int h;
	int sa;
	int aa;

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




	public Threads_(String word,int x1, int y1, int x2 , int y2 , Graphics g ){//drawline
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.g = g;
		this.word = word;
	}
	public Threads_(String word,String line , int x1, int y1, Graphics g ){//drawString,drawRect
		this.line = line;
		this.x1 = x1;
		this.y1 = y1;
		this.g = g;
		this.word = word;
	}

	public Threads_(String word,int x1, int y1, int w , int h , int sa , int aa , Graphics g ){//drawArc+fillRoundRect+fillArc
		this.x1 = x1;
		this.w = w;
		this.y1 = y1;
		this.h = h;
		this.g = g;
		this.sa = sa;
		this.aa = aa;
		this.word = word;
	}



	  public void run() {
//	    	if(word.equals("battle")) {
//	    		new BattleGui();
////	    		System.out.println("終了？");
//	    	}
	    	if(word.equals("menu")) {
				menu=new Menu();
			}
	    	if(word.equals("menu2")) {
				menu.addComponent();
			}
	    	if(word.equals("key")) {
	    		new KeyActCatch(frame);
	    	}
	    	if(word.equals("Datagram_Send_S")) {
	    		new Send_S();
	    	}
			if(word.equals("Datagram_Receive_S")) {
				  new Receive_S();
			}


		  if(word.equals("drawLine")){
			  DrawMethod.drawLine(x1,y1,x2,y2,g);
		  }
		  if(word.equals("drawString")){
			  DrawMethod.drawString(line,x1,y1,16,g);
		  }
		  if(word.equals("drawRect")){
			  DrawMethod.drawRect(x1,y1,x2,y2,g);
		  }
		  if(word.equals("drawArc")){
			  DrawMethod.drawArc(x1,y1,w,h,sa,aa,g);
		  }
		  if(word.equals("fillRoundRect")){
			  DrawMethod.fillRoundRect(x1,y1,w,h,sa,aa,g);
		  }
		  if(word.equals("fillArc")){
			  DrawMethod.fillArc(x1,y1,w,h,sa,aa,g);
		  }


	    }
	  public void actionPerformed(ActionEvent e) {	  
		  
		}
	  public WorkGui getBattleGui() {
		  return gui;
	  }
}
