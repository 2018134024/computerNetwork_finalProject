package finalProject;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Client {
	
	ClientFrame cf;
	
	public Client(ClientFrame cf) {
		this.cf = cf;
	}
	
	public void startChat() {
		try {	
			System.out.println(cf.getServerIP());
			Socket socket = null;
			
			socket = new Socket(cf.getServerIP(),cf.getServerPort()); //ip,port
			System.out.println("서버 접속");
			
			ListeningThread t1 = new ListeningThread(socket, cf);
			WritingThread t2 = new WritingThread(socket, cf);
			
			t1.start();
			t2.start();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
		ClientFrame cf = new ClientFrame();
		Client client= new Client(cf);
		
		//wait until start
		/*while(!cf.getStart() || cf.getServerIP().equals(null) || cf.getServerPort() == 0
				|| cf.getId().equals(null) || cf.getPassword().equals(null)) {
			System.out.print(""); 
		}*/
		cf.getLoginButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//get info
				
				//if all filled startChat()
				if(cf.isEmptyLabel())
					JOptionPane.showMessageDialog(null, "클라이언트의 정보를 입력하세요.");
				else {
					//start = true;
					cf.start();
					client.startChat();
					
				}
			}
		});
		//if start
		
	}

}
