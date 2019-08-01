package Window;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Database.DatabaseDAO;
import Database.UserDTO;
import Event.StateEvent;

public class LoginWindow extends JFrame{
	
	DatabaseDAO databaseDAO = DatabaseDAO.getInstance();
	UserDTO userDTO = UserDTO.getInstance();
	
	public LoginWindow(){
		setLocationRelativeTo(null);
		setSize(200,150);
		setResizable(false);
		setTitle("로그인");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		JLabel id_label = new JLabel("    아이디 : ");
		JTextField id_textField = new JTextField(10);
		JLabel password_label = new JLabel("비밀번호 : ");
		JTextField password_textField = new JTextField(10);
		JButton login_btn = new JButton("로그인");
		JButton join_btn = new JButton("회원가입");
		
		container.add(id_label);
		container.add(id_textField);
		container.add(password_label);
		container.add(password_textField);
		container.add(login_btn);
		container.add(join_btn);
		
		login_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!id_textField.getText().equals("") && !password_textField.getText().equals("")) 
				{
					boolean result;
					result = databaseDAO.login(id_textField.getText(), password_textField.getText());
					if(result == false)
					{
						JOptionPane.showMessageDialog(null, "아이디와 패스워드를 정확하게 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
					} else {
						setVisible(false);
						userDTO.setId(id_textField.getText());
						userDTO.setPassword(password_textField.getText());
						StateEvent.resetLoginWindow();
					}
				}else {
					JOptionPane.showMessageDialog(null, "아이디와 패스워드 모두 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
				}										
			}
		});
		
		join_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoinWindow();
			}
		});
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) { }
			
			@Override
			public void windowDeiconified(WindowEvent e) { }
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				StateEvent.resetLoginWindow();
			}
			
			@Override
			public void windowClosed(WindowEvent e) { }
			
			@Override
			public void windowActivated(WindowEvent e) { }
		});
		
		setVisible(true);
	}
}
