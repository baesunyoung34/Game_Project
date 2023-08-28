package edu.java.pro.login.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import edu.java.pro.login.controller.LoginDaoImpl;
import edu.java.pro.login.model.LoginUser;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUserCreate extends JFrame {

	private final LoginDaoImpl dao = LoginDaoImpl.getInstance();
	private JFrame frame;
	private JPanel panelName;
	private JButton btnSUCCESS;
	private JLabel lblNameSP;
	private JPanel panelbutton;
	private JPanel panel;
	private JButton btnCancel;
	private JLabel lblID;
	private JTextField textID;
	private JLabel lblPW;
	private JTextField textPW;
	private JLabel lblNAME;
	private JTextField textNAME;
	private JLabel lblPHONE;
	private JTextField textPHONE;
	private JLabel lblbit;
	private JTextField textbit;
	private Component parent;
	private LoginUserMain log;

	/**
	 * Launch the application.
	 */
	public static void showloginCreatFrame(Component parent, LoginUserMain log) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUserCreate window = new LoginUserCreate(parent, log);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginUserCreate(Component parent, LoginUserMain log) {
		this.parent = parent;
		this.log = log;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setTitle("회원가입"); // 제목
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		lblID.setBounds(67, 52, 102, 37);
		panel.add(lblID);
		
		textID = new JTextField();
		textID.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textID.setBounds(212, 52, 189, 37);
		panel.add(textID);
		textID.setColumns(10);
		
		lblPW = new JLabel("PW");
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		lblPW.setBounds(67, 111, 102, 37);
		panel.add(lblPW);
		
		textPW = new JTextField();
		textPW.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textPW.setColumns(10);
		textPW.setBounds(212, 111, 189, 37);
		panel.add(textPW);
		
		lblNAME = new JLabel("NAME");
		lblNAME.setHorizontalAlignment(SwingConstants.CENTER);
		lblNAME.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		lblNAME.setBounds(67, 170, 102, 37);
		panel.add(lblNAME);
		
		textNAME = new JTextField();
		textNAME.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textNAME.setColumns(10);
		textNAME.setBounds(212, 170, 189, 37);
		panel.add(textNAME);
		
		lblPHONE = new JLabel("PHONE");
		lblPHONE.setHorizontalAlignment(SwingConstants.CENTER);
		lblPHONE.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		lblPHONE.setBounds(67, 240, 102, 37);
		panel.add(lblPHONE);
		
		textPHONE = new JTextField();
		textPHONE.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textPHONE.setColumns(10);
		textPHONE.setBounds(212, 240, 189, 37);
		panel.add(textPHONE);
		
		lblbit = new JLabel("BIRTHDAY");
		lblbit.setHorizontalAlignment(SwingConstants.CENTER);
		lblbit.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		lblbit.setBounds(67, 310, 102, 37);
		panel.add(lblbit);
		
		textbit = new JTextField();
		textbit.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textbit.setColumns(10);
		textbit.setBounds(212, 310, 189, 37);
		panel.add(textbit);
		
		panelbutton = new JPanel();
		panelbutton.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelbutton, BorderLayout.SOUTH);
		
		btnSUCCESS = new JButton("SUCCESS");
		btnSUCCESS.setBackground(new Color(128, 128, 192));
		btnSUCCESS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewlogin();
			}
		});
		btnSUCCESS.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panelbutton.add(btnSUCCESS);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(new Color(128, 128, 192));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("휴먼매직체", Font.PLAIN, 16));
		panelbutton.add(btnCancel);
		
		panelName = new JPanel();
		panelName.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelName, BorderLayout.NORTH);
		
		lblNameSP = new JLabel("Sign up");
		lblNameSP.setFont(new Font("휴먼매직체", Font.PLAIN, 21));
		panelName.add(lblNameSP);
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 515, 578);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void createNewlogin() {
		String id = textID.getText();
		if(id.equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 꼭 입력해주세요!");
			return;
		} 
		String pw = textPW.getText();
		if(pw.equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호는 꼭 입력해주새요!");
			return;
		}
		String name = textNAME.getText();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름은 꼭 입력해주새요!");
			return;
		}
		String phone = textPHONE.getText();
		if(phone.equals("")) {
			JOptionPane.showMessageDialog(this, "전화번호는 꼭 입력해주새요!");
			return;
		}
		String birth = textbit.getText();
		if(birth.equals("")) {
			JOptionPane.showMessageDialog(this, "생일은 꼭 입력해주새요!");
			return;
		}
		
		// 리스트 타입 객체 생성.
		LoginUser loginuser = new LoginUser(id, pw, name, phone, birth);
		// 리스트에 추가 파일 업데이트 ->DAO
		int result = dao.create(loginuser);
		if (result == 1) {
			// 메인에 추가 됬다고 알려주기
			log.notifylogInSuccess();
			System.out.println("메인에 알려주기");
			
			// 현재 창 닫기
			frame.dispose();
			System.out.println("창 닫기");
		} else {
			JOptionPane.showMessageDialog(frame, "중복된 아이디 입니다!");
		}
		
		
		
		
		
		
	}
}
