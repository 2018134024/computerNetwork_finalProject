package finalProject;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerFrame extends Frame implements WindowListener, ActionListener {
	
	String serverIP = null;
	int serverPort = 0;
	boolean start = false;
	
	Button startbtn = null;
	
	TextArea clientList = null;
	TextField txt1 = null;
	TextField txt2 = null;
	
	static ArrayList<String> idList = new ArrayList<String>();
	static ArrayList<String> disconnectedIdList = new ArrayList<String>();
	
	
	
	public ServerFrame() {

		super("Server");
		
		Panel p = new Panel();
		p.setBackground(Color.GRAY);
		
		Label l1 = new Label("IP : ");
		Label l2 = new Label("Port number : ");
		Label l3 = new Label("Clients List");
		
		txt1 = new TextField(20);
		txt2 = new TextField(20);
		
		clientList = new TextArea(10,20);
		clientList.setEditable(false);
		
		startbtn = new Button("Start");
		startbtn.setBackground(Color.RED);
		
		/*Button btn2 = new Button("End server");
		btn2.setBackground(Color.BLUE);
		btn2.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					start = false;
					st.setVisible(false);
					fi.setVisible(true);
					
				}
		});*/
		
		p.add(l1);
		p.add(txt1);
		p.add(l2);
		p.add(txt2);
		p.add(startbtn);
		p.add(l3);
		p.add(clientList);
		//p.add(btn2);
		add(p);
		setSize(200,400);
		setVisible(true);
		
	}
	public boolean getStart() {
		return start;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void acceptNewClient(String name) {
		clientList.append(name);
		clientList.append("\n");
		idList.add(name);
		System.out.println("accept");
		System.out.println(idList);
		System.out.println(disconnectedIdList);
	}
	public Button getStartBtn() {
		return startbtn;
	}
	public boolean isEmptyLabel() {
		if(txt1.getText().equals("") || txt2.getText().equals(""))
			return true;
		else
			return false;
	}
	public void start() {
		this.serverIP = txt1.getText();
		this.serverPort = Integer.parseInt(txt2.getText());
		start = true;
	}
	public void setClientList(int i) {
		clientList.setText("");
		for (int k=0;k<idList.size();k++) {
			clientList.append(idList.get(k));
			clientList.append("\n");
		}
	}
	public void disconnect(int i) {
		disconnectedIdList.add(idList.get(i));
		idList.remove(i);
		setClientList(i);
		System.out.println("disconnect");
		System.out.println(idList);
		System.out.println(disconnectedIdList);
	}
	public void connectAgain(int i) {
		clientList.append(disconnectedIdList.get(i));
		clientList.append("\n");
		idList.add(disconnectedIdList.get(i));
		disconnectedIdList.remove(i);
		System.out.println("connect again");
		System.out.println(idList);
		System.out.println(disconnectedIdList);
	}
	
	/*public void startServer() {
		try {
				
			int socketPort = getServerPort();
			serverSocket = new ServerSocket(socketPort);
		 
			System.out.println("port# : " + socketPort + "으로 서버가 열렸습니다.");
			 
			if(true) {
				Socket socketUser = serverSocket.accept();
				Thread thd = new Server(socketUser);
				thd.start();
				//if(!getStart()) break;
			}
			
		} catch(IOException e) {
			 e.printStackTrace();
		}
	}
	
	public void endServer(){
		try {
			serverSocket.close();
			serverSocket = null;
			System.out.println("서버가 끝났습니다.");
		} catch(IOException e) {
			 e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		ServerFrame sf = new ServerFrame();
		
	}
	*/
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
