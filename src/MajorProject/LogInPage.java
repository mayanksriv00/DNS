package MajorProject;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class LogInPage implements ActionListener
{
	JFrame frame;
	JButton Submit,Reset;
	JTextField UserName;
	JPasswordField Password;

	LogInPage()
	{
			String inf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			try
			{
				UIManager.setLookAndFeel(inf);
			}
			catch(Exception e){}
	}

	public void LogInPageMethod()// Main Method of LogIn Page
	{

		JLabel UNameLabel = new JLabel("UserName :",JLabel.RIGHT);
		JLabel PassLabel = new JLabel("PassWord :",JLabel.RIGHT);
		UserName = new JTextField(10);
		Password = new JPasswordField(10);
		UNameLabel.setLabelFor(UserName);
		PassLabel.setLabelFor(Password);
		Submit = new JButton("Submit");
		Reset = new JButton("Reset");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2,5,5));
		panel.add(UNameLabel);
		panel.add(UserName);
		panel.add(PassLabel);
		panel.add(Password);
		panel.add(Submit);
		panel.add(Reset);
		Submit.addActionListener(this);
		Reset.addActionListener(this);
		frame = new JFrame("Login Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setBounds(200,200,250,130);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event)
	{

			if(Submit==(JButton) event.getSource())
			{
				if(((UserName.getText()).equals("admin")) && ((Password.getText()).equals ("admin")))
				{
					ClientDetail c1 = new ClientDetail();
		 			c1.setVisible(true);
		 			frame.setVisible(false);               
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invaild UserName and PassWord","Alert", JOptionPane.ERROR_MESSAGE);
					UserName.setText("");
					Password.setText("");
					frame.setVisible(true);
				}
		  	}
			else if(Reset == (JButton)event.getSource())
			{
				UserName.setText("");
				Password.setText("");

			}
	}
	public static void main(String arg[])throws Exception
	{
            LogInPage s1 = new LogInPage();
            s1.LogInPageMethod();
	}
}