package netpro.pokemon2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Threads_ extends Thread implements ActionListener{
	String word;
	static BattleGui gui;
	static Menu menu;
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
	    	if(word.equals("menu")) {
				menu=new Menu();
			}
	    	if(word.equals("menu2")) {
				menu.addComponent();
			}
	    	if(word.equals("key")) {
	    		new KeyActCatch(frame);
	    	}
	    }
	  public void actionPerformed(ActionEvent e) {	  
		  
		}
	  public BattleGui getBattleGui() {
		  return gui;
	  }
}
