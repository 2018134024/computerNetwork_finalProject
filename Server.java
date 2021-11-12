package finalProject;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends Thread{
	
	static ArrayList<Socket> socketList = new ArrayList<Socket>();
	static ArrayList<Socket> disconnectSocketList = new ArrayList<Socket>();
	static ServerFrame sf = null;
	Socket socket = null;

	boolean isSocketConnected;
	boolean isSocketAlive;
	//boolean echoArrived;
	long currTime;
	long lastCheckTime;
	
	public Server(Socket socket, ServerFrame sf) {
		this.socket = socket;
		socketList.add(socket);
		this.sf = sf;
		isSocketConnected = socket.isConnected();
		isSocketAlive = true;
		//echoArrived = false;
	}
	
	public void run() { 
		try {
			
			System.out.println("서버와 클라이트가 연결되었습니다.");
			//System.out.println(socket);
			//System.out.println(isSocketConnected);
			
			//server thread's InputStream
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			//server thread's OutputStream
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);
			
			//연결됐다는 메세지 보내기
			//writer.println("서버에 연결되었습니다!id를 입력해주세요!");
			
			String readValue ;
			String name = null;
			boolean identify = false;
			
			
			ServerEcho echo = new ServerEcho(socket);
			echo.start();
			
			while(true) {
			//check input
			try {
				while( (readValue = reader.readLine())  != null) {
				
					//첫입장 
					if(!identify) {
						name = readValue;
						identify = true;
						//서버프레임에 새로운 client 추
						sf.acceptNewClient(name);
						
						//새로 들어왔다고 알림 
						for(int i=0; i<socketList.size(); i++) {
							out = socketList.get(i).getOutputStream();
							writer = new PrintWriter(out, true);
							writer.println("                 "+name+"님이 접속하셨습니다.");
						}
						continue;
					}
					//"echo" 메세지 - connect 확인 
					if(readValue.equals("echo")) {
						socket.setSoTimeout(2000);
					}
					//정상적인 메세지 - 전체에게 메세지 전
					else {
						for(int i=0; i<socketList.size(); i++) {
							//System.out.println(recv(socket, Buffer buf));
							out = socketList.get(i).getOutputStream();
							writer = new PrintWriter(out, true);
							writer.println(" [ " + name + " ] " + " : " + readValue);
						}
					}
				} 
			
			}catch(Exception e) {
				try {
					socket.setSoTimeout(0);
					System.out.println("server disconnect1");
					//add to disconnect
					disconnectSocketList.add(socket);
					//remove from connect
					int i = socketList.indexOf(socket);
					socketList.remove(socket);
					//inform to frame
					sf.disconnect(i);
					System.out.println(socketList);
					System.out.println(disconnectSocketList);
					System.out.println(socket.getSoTimeout());
					if((readValue=reader.readLine()).equals("echo")) {
						
						socketList.add(socket);
						i=disconnectSocketList.indexOf(socket);
						disconnectSocketList.remove(socket);
						sf.connectAgain(i);
						continue;
					}
				} catch (IOException e1) {
					System.out.println("server disconnect2");
				}
				
			}
			
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			System.out.println("server disconnect3");
		}
	}
	
	
	
	public static void main(String[] args) {
		
		ServerFrame sf = new ServerFrame();
		boolean start = false;
		//ServerSocket serverSocket = null;
		
		/*while(!sf.getStart() || sf.getServerPort()==0) {
			System.out.print("");
		}*/
		//button에 event추
		sf.getStartBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				if(sf.isEmptyLabel())
					JOptionPane.showMessageDialog(null, "ip와 port번호를 입력하세요.");
				else {
					sf.start();
				}
			}
		
		});
		
		//정보가 입력되었을때, 서버 활성
		while(true) {
			start = sf.getStart();
			
			if(start) {
				try {
				
					int socketPort = sf.getServerPort();
					ServerSocket serverSocket = new ServerSocket(socketPort);
				 
					System.out.println("port# : " + socketPort + "으로 서버가 열렸습니다.");
					 
					while(true) {
						Socket socketUser = serverSocket.accept();
						Thread thd = new Server(socketUser,sf);
						thd.start();
					}
				} catch(IOException e) {
					 e.printStackTrace();
				}
			}
			
			System.out.print("");
			
		}
			
		
	} 
	
	
}
