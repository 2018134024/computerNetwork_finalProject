package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerEcho extends Thread{
	
	Socket socket;
	boolean run; //to check socket is not closed
	//boolean isSoAlive; //is socket alive
	//boolean received;
	
	ServerEcho(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			
			
			while(run = !socket.isClosed()) {
				
				writer.println("echo");
				sleep(1000);
			} 
				
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
