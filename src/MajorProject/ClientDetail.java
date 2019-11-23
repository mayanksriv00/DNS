package MajorProject;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import javax.swing.*; 

class ClientDetail extends JFrame implements ActionListener
{
	Button Send,Clear,Exit;
	TextField ReceiverName,ReceiverIp,SenderName;
	JPasswordField SenderPassword;
	Label SenderNameLabel,SenderPasswordLabel,RecieverNameLabel;

	ClientDetail()
	{
		super("Sender Details"); 
		Container c = getContentPane();
		setSize(400,300);
		setResizable(false);
		JPanel jp = new JPanel();
		jp.setLayout(null);
		SenderName= new TextField(20);
		SenderPassword = new JPasswordField(20);
		ReceiverName=new TextField(20);
		SenderNameLabel= new Label("SENDERNAME    :",Label.LEFT);
		SenderPasswordLabel= new Label("SENDERPASSWORD:",Label.LEFT);
		RecieverNameLabel= new Label("RECEIVERNAME  :",Label.LEFT);
		Send=new Button("Send");
		Clear=new Button("Clear");
		Exit=new Button("Exit");
		jp.add(SenderNameLabel);
		jp.add(SenderName);
		jp.add(SenderPasswordLabel);
		jp.add(SenderPassword);
		jp.add(RecieverNameLabel);
		jp.add(ReceiverName);
		jp.add(Send);
		jp.add(Clear);
		jp.add(Exit);
		c.add(jp);
		SenderNameLabel.setBounds(40,25,120,25);
		SenderName.setBounds(200,25,120,25);
		SenderPasswordLabel.setBounds(40,70,140,25);
		SenderPassword.setBounds(200,70,120,25);
		RecieverNameLabel.setBounds(40,115,120,25);
		ReceiverName.setBounds(200,115,120,25);
		Send.setBounds(80,200,50,25);
		Clear.setBounds(140,200,50,25);
		Exit.setBounds(200,200,50,25);
		Send.addActionListener(this);
		Clear.addActionListener(this);
		Exit.addActionListener(this);
                //setVisible(true);
	}


	public void actionPerformed(ActionEvent ae)
	{
		String st;
		st=ae.getActionCommand();
		if(st.equals("Send"))
                {
			try
			{
                            SenderMessage sm = new SenderMessage(this);
                            sm.setVisible(true);
                            setVisible(false);
			}
			catch(Exception e)
			{
                            //System.out.println(e);
			}
		}
		else if(st.equals("Clear"))
		{
			SenderName.setText("");
			SenderPassword.setText("");
			ReceiverName.setText("");
		}
		else if(st.equals("Exit"))
		{
			System.exit(0);
		}
	}
       /* public static void main(String args[])
        {
            ClientDetail client = new ClientDetail();
        }*/

}

