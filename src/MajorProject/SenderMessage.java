package MajorProject;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.util.zip.*;
import java.security.*;
import java.security.SecureRandom.*;
import java.security.spec.*;
import javax.swing.*;

public class SenderMessage extends JFrame implements ActionListener
{
	JTabbedPane TabbedPane ;
	JButton send;
	JTextArea MessageTextArea;
	JScrollPane MessageScrollPane;
	String g1=" ";
	byte str[];
	byte realSig[];
	
	String y="";
	String st="";
	ClientDetail ct;

	public SenderMessage(ClientDetail ct)
	{
		super("Transaction");
		this.ct=ct;
		setSize(500,500);
		Container c = getContentPane();
		JPanel panel = new JPanel(new FlowLayout());
		JLabel MessageLabel = new JLabel("Enter the Text: ");
		MessageTextArea = new JTextArea(20,40);
		MessageScrollPane = new JScrollPane(MessageTextArea);
		send= new JButton("Send");
		panel.add(MessageLabel);
		panel.add(MessageScrollPane);
		panel.add(send);
		panel.setVisible(true);         
                send.addActionListener(this);                          
		TabbedPane = new JTabbedPane();
		TabbedPane.addTab("Message",null,panel,"Enter the Message here");
		c.add(TabbedPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	/*         MESSAGE DIGEST ALOGORITHM          */
	private byte[] content;

	public byte[] digestValue(byte[] in_text1)
	{
		byte[] in_text=in_text1;
		content=in_text;
		MessageDigest mg1=null;
		try
		{
			mg1=MessageDigest.getInstance("SHA1");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		mg1.update(content);
		byte[] digest1=mg1.digest();
		System.out.println("Message Digest:"+digest1);
		return digest1;
	}

	public void keys()
	{
		try
		{
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA","SUN");
		 /* cryptographically strong pseudo-random number generator (PRNG) */
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
			keygen.initialize(1024,random);
			KeyPair keypair = keygen.generateKeyPair();
			PrivateKey privatekey = keypair.getPrivate();
			PublicKey publickey = keypair.getPublic();
			FileOutputStream pubkeyfos = new FileOutputStream("public.txt");
			pubkeyfos.write(publickey.getEncoded());
			System.out.println("PublicKey"+publickey.getEncoded());
			pubkeyfos.close();
			FileOutputStream prikeyfos = new FileOutputStream("private.txt");
			prikeyfos.write(privatekey.getEncoded());
			System.out.println("PrivateKey:"+privatekey);
			prikeyfos.close();
			JOptionPane.showMessageDialog(null, "keys are created","Keys", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void genSig(String PublicKeyName)
	{
		try
		{
			byte[] md;
			String args=PublicKeyName;
			FileInputStream fin=new FileInputStream(args);
			byte[] in_text=new byte[fin.available()];
			fin.read(in_text);
			fin.close();
			//SHA sha=new SHA(in_text);
			md=digestValue(in_text);
			FileInputStream fins=new FileInputStream("private.txt");
			byte[] enc_priv=new byte[fins.available()];
			fins.read(enc_priv);
			fins.close();
			Signature dsa=Signature.getInstance("SHA1withDSA","SUN");
			PKCS8EncodedKeySpec privKeySpec=new PKCS8EncodedKeySpec(enc_priv);
			KeyFactory keyFactory=KeyFactory.getInstance("DSA","SUN");
			PrivateKey priv=(PrivateKey)keyFactory.generatePrivate(privKeySpec);
			dsa.initSign(priv);
			dsa.update(md);
			realSig=dsa.sign();
			System.out.println("Generated Signature :"+realSig);
			FileOutputStream sigfos = new FileOutputStream("realSign.txt");
			sigfos.write(realSig);
			sigfos.close();
			JOptionPane.showMessageDialog(null, "Signature are Generated","Signature", JOptionPane.INFORMATION_MESSAGE);
			//Thread.wait(8000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == send)
		{
                    // problem in this authentication
                    if (MessageTextArea.getText() == "")
				JOptionPane.showMessageDialog(null,"Enter the Message","Invalid Data",JOptionPane.ERROR_MESSAGE);
                    else
                    {
			int i;
			int b[]=new int[5000];
			int ArrayofOriginalMessage[]=new int[5000];
			String g=" ";
			char x[]=new char[50];
			char d,s;
			String OriginalMessage=MessageTextArea.getText();
			System.out.println("Message Entered:"+OriginalMessage);
			int len=OriginalMessage.length();
			System.out.println("StringLength:"+len); 
			System.out.print("HASH-CODE:");
                        //It convert each character of original string into ASCII value and store it in Different Array
			for(i=0;i<len;i++)
			{
                            int ASCIIvalue= OriginalMessage.charAt(i);
                            System.out.print("\t"+ ASCIIvalue);
                            ArrayofOriginalMessage[i]=ASCIIvalue;
			}
			System.out.print("\n");
			System.out.print("HEX-CODE:");
			for(i=0;i<len;i++)
			{
                            g=" ";
                            b[i]=ArrayofOriginalMessage[i]%16;
                            ArrayofOriginalMessage[i]=ArrayofOriginalMessage[i]/16;
                            if(ArrayofOriginalMessage[i]==10)
                            {
				s='A';
				g=g+s;
                            }
                            else if(ArrayofOriginalMessage[i]==11)
                            {
				s='B';
				g=g+s;
                            }
                            else if(ArrayofOriginalMessage[i]==12)
                            {
				s='C';
				g=g+s;
                            }
                            else if(ArrayofOriginalMessage[i]==13)
                            {
				s='D';
				g=g+s;
                            }
                            else if(ArrayofOriginalMessage[i]==14)
                            {
				s='E';
				g=g+s;
                            }
                            else if(ArrayofOriginalMessage[i]==15)
                            {
				s='F';
                            	g=g+s;
                            }
                            else
                            {
				s=(char) ArrayofOriginalMessage[i];
				g=g+ArrayofOriginalMessage[i];
                            }
                            System.out.println("S is "+s);
                            if(b[i]==10)
                            {
				s='A';
				g=g+s;
				System.out.print(g.trim());
                            }
                            else if(b[i]==11)
                            {
				s='B';
				g=g+s;
				System.out.print(g.trim());
                            }
                            else if(b[i]==12)
                            {
                            	s='C';
				g=g+s;
				System.out.print(g.trim());
                            }
                            else if(b[i]==13)
                            {
				s='D';
				g=g+s;
				System.out.print(g.trim());
                            }
                            else if(b[i]==14)
                            {
				s='E';
				g=g+s;
				System.out.print(g.trim());
                            }
                            else if(b[i]==15)
                            {
				s='F';
				g=g+s;
				System.out.print(g.trim());						
                            }
                            else
                            {
                             	s=(char) b[i];
                            	g=g+b[i];
				System.out.print(g.trim());
                            }
                            g1=g1+g.trim();
			}
			System.out.println("Encoded text " + g1);
			str=g1.getBytes();
			System.out.println("This may be a File   : " +  str);
			st=new String(str);
			digestValue(g1.getBytes());//Here Digest Algo is applied
			keys();
			genSig("public.txt");
			String inputValue = JOptionPane.showInputDialog("Enter the System Name for Domain1");
			try
			{
				Socket s1 = new Socket(InetAddress.getByName(inputValue),9999);
				PrintStream ps = new PrintStream(s1.getOutputStream());
                                ps.println(ct.SenderName.getText());
				System.out.println("SenderDomain  :"+ct.SenderName.getText());
				ps.println(ct.ReceiverName.getText());
				System.out.println("ReceiverDomain:"+ct.ReceiverName.getText());
				ps.println(ct.SenderName.getText());
				System.out.println("SenderName  :"+ct.SenderName.getText());
				ps.println(ct.ReceiverName.getText());
				System.out.println("ReceiverName:"+ct.ReceiverName.getText());
				System.out.println("File:"+ new String(str));
				ps.println(new String(str));
				ps.println("public.txt");
				ps.println("realSign.txt");
				ps.flush();
				BufferedReader br=new BufferedReader(new InputStreamReader(s1.getInputStream()));
				String tr = br.readLine();
				if(tr .equals ("correct"))
				{
					JOptionPane.showMessageDialog(null,"Valid Password","Password",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"InValid Password","Password",JOptionPane.ERROR_MESSAGE);
					}
				s1.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
	}
	}
        public static void main(String[] args) {
        ClientDetail ObjClientDetail1 = new ClientDetail();
        SenderMessage objSenderMessage = new SenderMessage( ObjClientDetail1);
        }    
}