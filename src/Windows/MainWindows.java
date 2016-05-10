package Windows;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.MouseInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Checkbox;

import Windows.MyCanvas;
import Maze.Maze;
import Maze.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class MainWindows extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	Maze MyMaze;
	Point StartPoint;
	Point EndPoint;
	
	int SetPointMode = 0;
	
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindows frame = new MainWindows();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindows() {
		setTitle("3.4\u8FF7\u5BAB\u95EE\u9898");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5730\u56FE\u5BBD:");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5730\u56FE\u9AD8:");
		label_1.setBounds(10, 35, 54, 15);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(62, 7, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(62, 32, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		final MyCanvas canvas = new MyCanvas();
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//canvas.setCursor(Cursor.getPredefinedCursor(DEFAULT_CURSOR));
				/*
				System.out.println(arg0.getModifiers());
				System.out.println(MouseEvent.BUTTON1);
				System.out.println(MouseEvent.BUTTON2);
				System.out.println(MouseEvent.BUTTON3);
				*/
				if (SetPointMode != 0){
					if (arg0.getModifiers() == 16){//Left
						int x = arg0.getX() / canvas.GetSize(),y = arg0.getY() / canvas.GetSize();
						//System.out.println(x + "," + y);
						if (x >= 0 && x < MyMaze.GetWidth() && y >= 0 && y < MyMaze.GetHeight() && !MyMaze.GetMazeData(y, x)){
							if (SetPointMode == 1){
								StartPoint = new Point(x,y);
								MyMaze.SetStartPoint(StartPoint);
								textField_2.setText(String.valueOf(x));
								textField_3.setText(String.valueOf(y));
							}
							else if (SetPointMode == 2){
								EndPoint = new Point(x,y);
								MyMaze.SetEndPoint(EndPoint);
								textField_4.setText(String.valueOf(x));
								textField_5.setText(String.valueOf(y));
							}
							//canvas.SendMaze(MyMaze);
							canvas.repaint();
						}
						else{
							JOptionPane.showMessageDialog(null, "不符合规则！");
						}
						SetPointMode = 0;
						canvas.setCursor(Cursor.getPredefinedCursor(DEFAULT_CURSOR));
					}
					else if (arg0.getModifiers() == 4){//Right
						canvas.setCursor(Cursor.getPredefinedCursor(DEFAULT_CURSOR));
						SetPointMode = 0;
					}
				}	
			}
		});
		
		canvas.setBackground(Color.DARK_GRAY);
		canvas.setBounds(146, 10, 481, 425);
		contentPane.add(canvas);
		
		JButton button_1 = new JButton("\u8BA1\u7B97\u6700\u77ED\u901A\u8DEF");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MyMaze.GetShortestPath() == null){
					JOptionPane.showMessageDialog(null, "不能找到通路！");
				}
				else{
					//canvas.SendMaze(MyMaze);
					canvas.repaint();
				}
				
			}
		});
		button_1.setBounds(10, 122, 118, 23);
		contentPane.add(button_1);
		
		JButton button = new JButton("\u968F\u673A\u751F\u6210\u5730\u56FE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int w = Integer.parseInt(textField.getText());
				int h = Integer.parseInt(textField_1.getText());
				int per = Integer.parseInt(textField_6.getText());
				if (w > 0 && w <= 50 && h > 0 && h <= 50 && per >= 0 && per <= 100){
					MyMaze = new Maze(w,h,per);
					canvas.SendMaze(MyMaze);
					canvas.repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "输入数据不符合规则！");
				}
			}
		});
		button.setBounds(10, 89, 118, 23);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("\u663E\u793A\u500D\u6570:");
		label_2.setBounds(10, 155, 54, 15);
		//contentPane.add(label_2);
		
		final JRadioButton rdbtnX = new JRadioButton("X1");
		rdbtnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.ChangeSize(1);
				canvas.repaint();
			}
		});
		rdbtnX.setBounds(10, 176, 121, 23);
		contentPane.add(rdbtnX);
		
		JRadioButton rdbtnX_1 = new JRadioButton("X2");
		rdbtnX_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.ChangeSize(2);
				canvas.repaint();
			}
		});
		rdbtnX_1.setBounds(10, 201, 121, 23);
		contentPane.add(rdbtnX_1);
		
		JRadioButton rdbtnX_2 = new JRadioButton("X4");
		rdbtnX_2.setSelected(true);
		rdbtnX_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.ChangeSize(4);
				canvas.repaint();
			}
		});
		rdbtnX_2.setBounds(10, 226, 121, 23);
		contentPane.add(rdbtnX_2);
		
		JRadioButton rdbtnX_3 = new JRadioButton("X8");
		rdbtnX_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.ChangeSize(8);
				canvas.repaint();
			}
		});
		rdbtnX_3.setBounds(10, 251, 121, 23);
		contentPane.add(rdbtnX_3);
		
		ButtonGroup RadioButtonGroup = new ButtonGroup();
		RadioButtonGroup.add(rdbtnX);
		RadioButtonGroup.add(rdbtnX_1);
		RadioButtonGroup.add(rdbtnX_2);
		RadioButtonGroup.add(rdbtnX_3);
		
		JLabel label_3 = new JLabel("\u5730\u56FE\u663E\u793A\u500D\u6570:");
		label_3.setBounds(10, 155, 101, 15);
		contentPane.add(label_3);
		
		JLabel lblx = new JLabel("\u8D77\u70B9\u5750\u6807X:");
		lblx.setBounds(10, 279, 83, 15);
		contentPane.add(lblx);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(74, 280, 54, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lbly = new JLabel("\u8D77\u70B9\u5750\u6807Y:");
		lbly.setBounds(10, 304, 72, 15);
		contentPane.add(lbly);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(74, 304, 54, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblx_1 = new JLabel("\u7EC8\u70B9\u5750\u6807X:");
		lblx_1.setBounds(10, 358, 83, 15);
		contentPane.add(lblx_1);
		
		JLabel lbly_1 = new JLabel("\u7EC8\u70B9\u5750\u6807Y:");
		lbly_1.setBounds(10, 383, 83, 15);
		contentPane.add(lbly_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(74, 355, 54, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(74, 383, 54, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton button_2 = new JButton("\u8BBE\u7F6E\u8D77\u70B9");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setCursor(Cursor.getPredefinedCursor(CROSSHAIR_CURSOR));
				SetPointMode = 1;//Set Start Point
				/*
				int x = Integer.parseInt(textField_2.getText());
				int y = Integer.parseInt(textField_3.getText());
				if (x >= 0 && x < MyMaze.GetWidth() && y >= 0 && y < MyMaze.GetHeight() && !MyMaze.GetMazeData(y, x)){
					StartPoint = new Point(x,y);
					MyMaze.SetStartPoint(StartPoint);
					//canvas.SendMaze(MyMaze);
					canvas.repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "输入的点坐标不符合规则！");
				}
				*/
			}
		});
		button_2.setBounds(10, 329, 93, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u8BBE\u7F6E\u7EC8\u70B9");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setCursor(Cursor.getPredefinedCursor(CROSSHAIR_CURSOR));
				SetPointMode = 2;
				/*
				int x = Integer.parseInt(textField_4.getText());
				int y = Integer.parseInt(textField_5.getText());
				if (x >= 0 && x < MyMaze.GetWidth() && y >= 0 && y < MyMaze.GetHeight() && !MyMaze.GetMazeData(y, x)){
					EndPoint = new Point(x,y);
					MyMaze.SetEndPoint(EndPoint);
					//canvas.SendMaze(MyMaze);
					canvas.repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "输入的点坐标不符合规则！");
				}
				*/
			}
		});
		button_3.setBounds(10, 412, 93, 23);
		contentPane.add(button_3);
		
		JLabel label_4 = new JLabel("\u7EA2\u70B9\u8868\u793A\u8D77\u70B9\uFF0C\u7EFF\u70B9\u8868\u793A\u7EC8\u70B9\uFF0C\u84DD\u8272\u8868\u793A\u884C\u8D70\u7684\u8DEF\u5F84\u3002");
		label_4.setBounds(156, 441, 403, 15);
		contentPane.add(label_4);
		
		JLabel lblNewLabel = new JLabel("\u4F5C\u8005\uFF1A20135301");
		lblNewLabel.setBounds(476, 441, 118, 15);
		contentPane.add(lblNewLabel);
		
		textField_6 = new JTextField();
		textField_6.setBounds(62, 60, 66, 21);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_5 = new JLabel("\u6BD4\u4F8B\uFF1A");
		label_5.setBounds(10, 60, 54, 15);
		contentPane.add(label_5);
		
		
	}
}



