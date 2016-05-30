package vitvaror;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JButton;

public class Inlogg extends JFrame {

	private JPanel contentPane;
	private JTextField textField;



	public Inlogg() {
	
		setBounds(100, 100, 443, 80);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUserid = new JLabel("KundID");
		contentPane.add(lblUserid);
		
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
	
		
		JButton btnInlogg = new JButton("Inlogg");
		contentPane.add(btnInlogg);
		btnInlogg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int userID = Integer.parseInt(textField.getText());
				try {
					
					new Controller(2,userID);
					close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}

}
