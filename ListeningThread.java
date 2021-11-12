package finalProject;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;

public class ListeningThread extends Thread{
	
	Socket socket = null;
	ClientFrame frame = null;
	long currentTime;
	boolean slept=false;
	
	public ListeningThread(Socket socket, ClientFrame frame) {
		this.socket = socket;
		this.frame = frame;
		this.currentTime = System.currentTimeMillis();
	}
	
	public void run() {
		try{
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);
			
			String readValue;
			while((readValue=reader.readLine())!=null) {
				System.out.println("recieved" + readValue);
				if(readValue.equals("echo")) {
					writer.println("echo");
					System.out.println("send echo");
				}
				else
					frame.write(readValue);
				//System.out.println(reader.readLine());
				if((System.currentTimeMillis() - currentTime > 10000) && !slept) {
					slept = true;
					sleep(7000);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
