package finalProject;

import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;

public class WritingThread extends Thread{
	
	Socket socket = null;
	Scanner scanner = new Scanner(System.in);
	ClientFrame frame = null;
	String message = null;
	String path = null;
	OutputStream out;
	PrintWriter writer;
	DataOutputStream dos;
	
	
	public WritingThread(Socket socket, ClientFrame frame) {
		this.socket = socket;
		this.frame = frame;
		frame.getSendButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				writer.print(frame.getMessage());
				writer.println("");
			}
		});
		frame.getFileButton().addActionListener(new ActionListener()  {
			
			@Override
			public void actionPerformed(ActionEvent event){
				
				FileDialog dialog = new FileDialog(frame, "파일 열기", FileDialog.LOAD);
				dialog.setVisible(true);
				if(dialog.getFile() != null) {
					path = dialog.getDirectory()+dialog.getFile();
					File file = new File(path);
					sendFile(file);
					
				}
			}
		});
	}
	
	public void run() {
		try {
			out = socket.getOutputStream();
			writer = new PrintWriter(out, true);
			writer.println(frame.id);
			System.out.println(frame.id);
			/*while(true) {
				writer.println(scanner.nextLine());
			}*/
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendFile(File f) {
		 /* try {
				FileInputStream fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis);
				int len;
				int size = 10000;
				byte[] data = new byte[size];
				while((len=bis.read(data))!=-1) {
					dos.write(data,0,len);
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}*/
					
	}
}
