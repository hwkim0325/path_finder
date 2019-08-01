package Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Database.Data;
import Database.DatabaseDAO;
import Database.UserDTO;
import Event.StateEvent;

public class AdminWindow extends JFrame {
	
	DatabaseDAO databaseDAO = DatabaseDAO.getInstance();

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	ArrayList<Data> datas = new ArrayList<>();
	
	public AdminWindow() {
		
		setLocationRelativeTo(null);
		setSize(500,510);
		setResizable(false);
		setTitle("DatabaseWindow");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		
		String colNames[] = {"아이디","비밀번호"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
		}};	
		
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(200,200);
		
		DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();
		tableCell.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cellMode = table.getColumnModel();
		
		for(int i = 0; i < cellMode.getColumnCount(); i++)
		{
			cellMode.getColumn(i).setCellRenderer(tableCell);
		}
		
		JLabel label = new JLabel("수정할 암호");
		JTextField textField= new JTextField(5);
		JButton updatetButton = new JButton("암호수정");
		JButton banButton = new JButton("회원추방");
		JButton resetButton = new JButton("회원 경로 리셋");

		contentPane.add(scrollPane);
		contentPane.add(label);
		contentPane.add(textField);
		contentPane.add(updatetButton);
		contentPane.add(banButton);
		contentPane.add(resetButton);
		
		

		datas = databaseDAO.getUser();
			
			for(Data data : datas)
			{
				Vector<String> value = new Vector<>();
				value.add(data.getId());
				value.add(data.getPassword());
				model.addRow(value);
			}
			
			updatetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = null;
				String password = null;
				if(table.getValueAt(row, 1) != null)
					id = (String) table.getValueAt(row, 0);
					
				if(textField.getText() != null)
					password = textField.getText();
				
				

				if(id != null && password != null)
				databaseDAO.updateUser(id, password);
				JOptionPane.showMessageDialog(null, "회원정보를 수정하였습니다.");
				
				model.setRowCount(0);
				
				datas = databaseDAO.getUser();
				
				for(Data data : datas)
				{
					Vector<String> value = new Vector<>();
					value.add(data.getId());
					value.add(data.getPassword());
					model.addRow(value);
				}
				
				table.updateUI();
			}
		});
		
		banButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				String id = null;
				if(table.getValueAt(row, 1) != null)
					id = (String) table.getValueAt(row, 0);
				model.removeRow(row);
				System.out.println(id);
				
				if(id != null)
				databaseDAO.deleteUser(id);
				JOptionPane.showMessageDialog(null, "회원을 추방하였습니다.");
				table.updateUI();
			}
		});
		
		resetButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = null;
				if(table.getValueAt(row, 1) != null)
					id = (String) table.getValueAt(row, 0);
				
				if(id != null)
				databaseDAO.resetPath(id);
				JOptionPane.showMessageDialog(null, "경로 리셋 성공");
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
			public void windowDeactivated(WindowEvent e) { }
			
			@Override
			public void windowClosing(WindowEvent e) {
				StateEvent.resetPathWindow();
			}
			
			@Override
			public void windowClosed(WindowEvent e) { }
			
			@Override
			public void windowActivated(WindowEvent e) { }
		});
		
		setVisible(true);
	}
}