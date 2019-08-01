package Window;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Database.DatabaseDAO;

public class JoinWindow extends JFrame{
	
	DatabaseDAO databaseDAO = DatabaseDAO.getInstance();
	boolean isChecked = false;
	String checkedID = null;
	
	JoinWindow(){
		setLocationRelativeTo(null);
		setTitle("회원가입");
		setSize(300,300);
		setResizable(false);
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(0, 2));
		
		JLabel id_label = new JLabel("아이디");
		JTextField id_textField = new JTextField(15);
		JLabel password_label = new JLabel("비밀번호");
		JTextField password_textField = new JTextField(15);
		JButton check_btn = new JButton("중복확인");
		JButton join_btn = new JButton("회원가입");
		
		id_label.setHorizontalAlignment(0);
		password_label.setHorizontalAlignment(0);
		
		container.add(id_label);
		container.add(id_textField);
		container.add(password_label);
		container.add(password_textField);
		container.add(check_btn);
		container.add(join_btn);
		
		check_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!id_textField.getText().equals("")) 
				{
					boolean result = false;
					result = databaseDAO.checkUser(id_textField.getText());
					checkedID = id_textField.getText();
					if(result == true){
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "경고", JOptionPane.WARNING_MESSAGE);
						isChecked = false;					
					}else {
						JOptionPane.showMessageDialog(null, "가입 가능한 아이디 입니다.");
						isChecked = true;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
					isChecked = false;
				}
			}
		});
		
		join_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result;
				String id = null;
				if(!id_textField.getText().equals(""))
					id = id_textField.getText();
				if(checkedID != null && checkedID.equals(id) && isChecked == true)
				{	
					if(!id_textField.getText().equals("") && !password_textField.getText().equals("")) 
					{
						result = databaseDAO.join(id_textField.getText(), password_textField.getText());
						if(result == -1)
						{
							JOptionPane.showMessageDialog(null, "데이터베이스 오류", "경고", JOptionPane.WARNING_MESSAGE);
						}else if(result == 0){
							JOptionPane.showMessageDialog(null, "회원가입 불가한 아이디입니다.", "경고", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "회원가입 성공");
							setVisible(false);
						}
					}else {
						JOptionPane.showMessageDialog(null, "아이디와 패스워드 모두 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "중복확인 해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		setVisible(true);
	}

}
