package finalProject;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class ClientFrame extends Frame implements WindowListener, ActionListener {
	
	String serverIP = null;
	int serverPort = 0;
	String id = null;
	String password = null;
	
	//boolean start = false;
	Button login = null;	
	Button cancel = null;
	Button loginbtn = null;
	Button sendbtn = null;
	Button filebtn = null;
	
	JTextArea ta1 = null;    
	JTextArea ta2 = null;
	
	TextField txt1;
	TextField txt2;
	TextField txt3;
	TextField txt4;
	
	Panel p;
	
	
	public ClientFrame() {

		super("Client");
		
		p = new Panel();
		p.setBackground(Color.GRAY);
		//labels
		Label l1 = new Label("  Server Ip  ");
		Label l2 = new Label("  Port  ");
		Label l3 = new Label("  ID  ");
		Label l4 = new Label("  Password  ");
		//text fields
		txt1 = new TextField(20);
		txt2 = new TextField(20);
		txt3 = new TextField(20);
		txt4 = new TextField(20);
		
		//login button - execute startChat()
		loginbtn = new Button("Login");
		loginbtn.setBackground(Color.RED);
		loginbtn.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					//get info
					serverIP = txt1.getText();
					String port = txt2.getText();
					id = txt3.getText();
					password = txt4.getText();
					//if all filled startChat()
					if(serverIP.equals("") || port.equals("") || id.equals("") || password.equals(""))
						JOptionPane.showMessageDialog(null, "ip와 port번호를 입력하세요.");
					else {
						//start = true;
						serverPort = Integer.parseInt(port);
						remove(p);
						startChat();
					}
					
					
				}
			
		});
		//cancel button - clear
		Button cancelbtn = new Button("Cancel");
		cancelbtn.setBackground(Color.BLUE);
		cancelbtn.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt4.setText("");
				}
		});
		//add component
		p.add(l1);
		p.add(txt1);
		p.add(l2);
		p.add(txt2);
		p.add(l3);
		p.add(txt3);
		p.add(l4);
		p.add(txt4);
		p.add(loginbtn);
		p.add(cancelbtn);
		add(p);
		setSize(200,400);
		setVisible(true);
		
	}
	
	public void startChat() {
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		//chat field
		ta1 = new JTextArea(20,30);
		ta1.setEditable(false);
		ta1.setBackground(Color.GRAY);
		ta1.setLineWrap(true);
		JScrollPane scroll1 = new JScrollPane(ta1);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//input field
		ta2= new JTextArea(3,30);
		ta2.setBackground(Color.PINK);
		ta2.setLineWrap(true);
		JScrollPane scroll2 = new JScrollPane(ta2);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//buttons
		filebtn = new Button("File");
		filebtn.setForeground(Color.RED);
		sendbtn = new Button("Send");
		sendbtn.setForeground(Color.BLUE);
		//add components
		p1.add(scroll1);
		p2.add(scroll2);
		p3.add(filebtn);
		p3.add(sendbtn);
		add("North",p1);
		add("Center",p2);
		add("South",p3);
		setSize(400,450);
		
	}
	
	public void write(String str) {
		ta1.append(str);
		ta1.append("\n");
	}
	
	
	/*public boolean getStart() {
		return start;
	}*/
	public int getServerPort() {
		return serverPort;
	}
	public String getServerIP() {
		return serverIP;
	}
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Button getSendButton() {
		return sendbtn;
	}
	public String getMessage() {
		return ta2.getText();
	}
	public Button getFileButton() {
		return filebtn;
	}
	public Button getLoginButton() {
		return loginbtn;
	}
	public boolean isEmptyLabel() {
		if(txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") || txt4.getText().equals(""))
			return true;
		else
			return false;
	}
	public void start() {
		this.serverIP = txt1.getText();
		this.serverPort = Integer.parseInt(txt2.getText());
		this.id = txt3.getText();
		this.password = txt4.getText();
		this.remove(p);
		startChat();
	}
	//public String getDirectory() {
		
	//}
	
	
	/*public static void main(String[] args) {
		
		ClientFrame sf = new ClientFrame();
		
	}*/
	
	

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

