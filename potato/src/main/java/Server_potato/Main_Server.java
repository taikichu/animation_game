package Server_potato;

public class Main_Server {
    public static void main( String[] args ){
//    	new Threads_("menu").start();//menu画面
    	new Threads_("Datagram_Receive_S").start();//サーバー
    }
}
