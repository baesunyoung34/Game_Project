package edu.java.pro.login.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import edu.java.pro.RSPgame.GameMain;
import edu.java.pro.login.controller.LoginDaoImpl;
import edu.java.pro.login.model.LoginUser;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginUserMain {
	
	private JFrame frame;
	private JTextField textID;
	private JTextField textPassword;
	private JButton logBtn;
	private JButton logBtnDelete;
	private JPanel panel_button;
	private JPanel panel;
	private JButton btnSignUp;
	private DefaultTableModel model;
	private List<LoginUser> loginlist;
	GameMain app = new GameMain(); 
	private final LoginDaoImpl dao = LoginDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	ImageIcon[] imgIcons = {new ImageIcon("")};
	private JLabel lblPassword;
	private JLabel lblID;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUserMain window = new LoginUserMain();
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
	public LoginUserMain() {
		initialize();
	}
	
	// 회원가입 로그인 완료시 호출할 메서드.
	public void notifylogInSuccess() {
		JOptionPane.showMessageDialog(frame, "회원가입 성공!");
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LOG_IN");
		frame.setBounds(100, 100, 558, 309);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textID = new JTextField(10);
		textID.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textID.setBounds(234, 40, 234, 55);
		panel.add(textID);
		
		JPasswordField textPassword  = new JPasswordField(10);
		textPassword.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		textPassword.setBounds(234, 133, 234, 55);
		panel.add(textPassword);
		
		lblID = new JLabel("ID");
		lblID.setFont(new Font("휴먼매직체", Font.PLAIN, 18));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(79, 40, 109, 55);
		panel.add(lblID);
		
		lblPassword = new JLabel("PassWord");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("휴먼매직체", Font.PLAIN, 18));
		lblPassword.setBounds(79, 133, 109, 55);
		panel.add(lblPassword);
		
		panel_button = new JPanel();
		panel_button.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel_button, BorderLayout.SOUTH);
		
		logBtnDelete = new JButton("Cancel");
		logBtnDelete.setBackground(new Color(128, 128, 192));
		logBtnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginUserCreate.showloginCreatFrame(frame, LoginUserMain.this);
			}
		});
		btnSignUp.setBackground(new Color(128, 128, 192));
		btnSignUp.setFont(new Font("휴먼매직체", Font.PLAIN, 16));
		panel_button.add(btnSignUp);
		logBtnDelete.setFont(new Font("휴먼매직체", Font.PLAIN, 16));
		panel_button.add(logBtnDelete);
		

		
		logBtn = new JButton("Log In");
		
		logBtn.setBackground(new Color(128, 128, 192));
		logBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			boolean result = dao.login(textID.getText(), textPassword.getText());
				if (result == true) {
				JOptionPane.showMessageDialog(frame, "로그인에 성공 하였습니다.");
				// 게임 메인화면 부르기
				// 현재 화면을 닫습니다.
				frame.setVisible(false);
			    // 새로운 화면을 생성하고 보여줍니다.
			    app.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "로그인에 실패하였습니다");
				}
			}
		});
		logBtn.setFont(new Font("휴먼매직체", Font.PLAIN, 16));
		panel_button.add(logBtn);
	}
}
