package Window;

import static Event.CheckInput.*;
import static Node_Frame.Grid.SetColor;
import static Node_Frame.Grid.grid;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Database.Data;
import Database.DatabaseDAO;
import Database.UserDTO;
import Event.StateEvent;
import Node_Frame.Grid;
import Node_Frame.NodeType;
import Pathfinding.WayPoints;

public class PathWindow extends JFrame {

	DatabaseDAO databaseDAO = DatabaseDAO.getInstance();
	
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private String pathText = null;
	
	public PathWindow() {
		setLocationRelativeTo(null);
		setSize(500,510);
		setResizable(false);
		setTitle("DatabaseWindow");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		
		String colNames[] = {"시작","도착", "경로길이","날짜"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
		}};		
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(200,200);
		
		ArrayList<Data> datas = new ArrayList<>();
		if(UserDTO.getInstance().getId() != null)
		{
			datas = databaseDAO.getData(UserDTO.getInstance().getId());
			
			for(Data data : datas)
			{
				Vector<String> value = new Vector<>();
				value.add(data.getStartPoint());
				value.add(data.getEndPoint());
				value.add(data.getDistance());
				value.add(data.getSearchDate().toString());
				value.add(data.getStartPoint());
				model.addRow(value);
			}		
		}
		
		DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();
		tableCell.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cellMode = table.getColumnModel();
		
		for(int i = 0; i < cellMode.getColumnCount(); i++)
		{
			cellMode.getColumn(i).setCellRenderer(tableCell);
		}
			
		JButton showButton = new JButton("보여주기");
		JButton deleteButton = new JButton("삭제하기");
		JButton adminButton = new JButton("관리페이지");

		contentPane.add(scrollPane);
		contentPane.add(showButton);
		contentPane.add(deleteButton);
		contentPane.add(adminButton);
		
		showButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String startPoint = (String) table.getValueAt(row,0);
				String endPoint = (String) table.getValueAt(row, 1);
				String id=null;
				ArrayList<WayPoints> path = new ArrayList<WayPoints>();
				
				if(UserDTO.getInstance().getId() != null)
				{
					id = UserDTO.getInstance().getId();
				}
				
				pathText = databaseDAO.getPath(startPoint, endPoint, id);
				int x = 0;
				int y = 0;
				
				String[] str = pathText.split(",");
				
				for(String t : str)
				{
					if(x == 0)
					{
						x = Integer.parseInt(t);
					}
					else if(y == 0)
					{
						y = Integer.parseInt(t);
						path.add(new WayPoints(x,y));
						x=y=0;
					}			
				}
				
				Grid.reset();
				
				int pathX;
				int pathY;
				
				for(WayPoints way : path) {
					pathX = way.x;
					pathY = way.y;
						SetColor(pathX, pathY, PathColor[0], PathColor[1], PathColor[2], NodeType.PATH, true);
				}
				
				String start[] = startPoint.split(",");
				String end[] = endPoint.split(",");
				
				SetColor(Integer.parseInt(start[0]), Integer.parseInt(start[1]), StartColor[0], StartColor[1], StartColor[2], NodeType.PATH, true);
				SetColor(Integer.parseInt(end[0]), Integer.parseInt(end[1]), GoalColor[0], GoalColor[1], GoalColor[2], NodeType.PATH, true);
				
				path.clear();
				
				setVisible(false);
				StateEvent.resetPathWindow();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();				
				String startPoint = (String) table.getValueAt(row,0);
				String endPoint = (String) table.getValueAt(row, 1);
				String id=null;
				model.removeRow(row);
				if(UserDTO.getInstance().getId() != null)
				{
					id = UserDTO.getInstance().getId();
				}
				
				if(startPoint != null && endPoint != null && id != null)
				databaseDAO.deletePath(startPoint, endPoint, id);
				JOptionPane.showMessageDialog(null, "경로 삭제에 성공하였습니다.");
				

			}
		});
		
		adminButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserDTO.getInstance().getId()!= null && UserDTO.getInstance().getId().equals("admin"))
				{
					new AdminWindow();
					setVisible(false);
				}
				else if(UserDTO.getInstance().getId()!= null && !UserDTO.getInstance().getId().equals("admin"))
				{
					JOptionPane.showMessageDialog(null, "admin으로 접속해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}
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
