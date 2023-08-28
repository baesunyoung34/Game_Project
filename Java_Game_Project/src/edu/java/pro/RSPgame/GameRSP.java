package edu.java.pro.RSPgame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.java.pro.RSP.music.BGM;





public class GameRSP extends JFrame {

	ImageIcon[] imgIcons = {new ImageIcon("game/kawi.jpg"), new ImageIcon("game/bawi.jpg"),new ImageIcon("game/bo.jpg")}; // 가위 바위 보 이미지
	GameMain app = new GameMain();
	private Random rand = new Random(); // 컴퓨터가 랜덤으로 가위 , 바위 보를 냄
	
	
	
	private JFrame frame;
	private JLabel lblS;
	private JLabel lblP;
	private JLabel lblR;
	private JButton btnButtonP;
	private JButton btnButtonS;
	private JButton btnButtonR;
	private JTextArea textAreaResult;
	private JPanel panelresult;
	private Component parent;
    private GameMain gamemain;
    private static final int ROCK = 0; // 주먹은 0
    private static final int SCISSORS = 1; // 가위는 1
    private static final int PAPER = 2; // 보는 2
    private JTextArea lblResult;
    private BGM bgm;
    private JButton btnButtonBack;
    
    
	
	/**
	 * Launch the application.
	 */
	
	
	
	
	
	public static void showRSPGame(Component parent, GameMain gamemain) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameRSP window = new GameRSP(parent, gamemain);
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
	public GameRSP(Component parent, GameMain gamemain) {
		this.parent = parent;
		this.gamemain = gamemain;
		initialize();
		bgm = new BGM();
				
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void exit() {
		
        // BGM 종료
        bgm.stop();
        
        // 게임 창 닫기
        frame.dispose();
        frame.setVisible(false);

        // 이전 창으로 돌아가기
        app.frame.setVisible(true);
    }
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 489);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("가위 바위 보 게임!");
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblS = new JLabel(imgIcons[0]);
		lblS.setForeground(new Color(0, 0, 0));
		lblS.setBounds(31, 31, 95, 93);
		panel.add(lblS);
		
		lblR = new JLabel(imgIcons[1]);
		lblR.setForeground(Color.BLACK);
		lblR.setBounds(184, 31, 95, 93);
		panel.add(lblR);
		
		lblP = new JLabel(imgIcons[2]);
		lblP.setForeground(Color.BLACK);
		lblP.setBounds(340, 31, 95, 93);
		panel.add(lblP);
		
		btnButtonP = new JButton("Rock");
		btnButtonP.setBackground(new Color(128, 128, 192));
		btnButtonP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGame(ROCK);
				
			}
		});
		btnButtonP.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		btnButtonP.setBounds(340, 160, 97, 23);
		panel.add(btnButtonP);
		
		btnButtonS = new JButton("Scissors");
		btnButtonS.setBackground(new Color(128, 128, 192));
		btnButtonS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGame(SCISSORS);
			}
		});
		btnButtonS.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		btnButtonS.setBounds(29, 160, 97, 23);
		panel.add(btnButtonS);
		
		btnButtonR = new JButton("Paper");
		btnButtonR.setBackground(new Color(128, 128, 192));
		btnButtonR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGame(PAPER);
			}
		});
		btnButtonR.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		btnButtonR.setBounds(182, 160, 97, 23);
		panel.add(btnButtonR);
		
		
		panelresult = new JPanel();
		panelresult.setBackground(new Color(128, 128, 192));
		panelresult.setBounds(23, 242, 412, 172);
		panel.add(panelresult);
		panelresult.setLayout(null);
		
		lblResult = new JTextArea("");
		lblResult.setBounds(12, 10, 388, 152);
		panelresult.add(lblResult);
		
		JButton btnresult = new JButton("ReStart");
		btnresult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 결과 초기화 코드 작성
		        lblResult.setText("");
			}
		});
		btnresult.setBackground(new Color(128, 128, 192));
		btnresult.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		btnresult.setBounds(71, 209, 315, 23);
		panel.add(btnresult);
		
		btnButtonBack = new JButton("<--");
		btnButtonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		btnButtonBack.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		btnButtonBack.setBackground(new Color(128, 128, 192));
		btnButtonBack.setBounds(12, 419, 67, 23);
		panel.add(btnButtonBack);
	}

	private void playGame(int playerChoice) {
		String result = new String();
		int computerChoice = rand.nextInt(3); // 0~2중 랜덤으로 선택!
		// 컴퓨터
		 switch (computerChoice) {
		 case ROCK: 
			 result = "주먹";
	            break;
	        case SCISSORS:	
	        	result = "가위";
	            break;
	        case PAPER:	  	
	        	result = "보";
	            break;
	    }
		 // 플레이어
	    switch (playerChoice) {
	        case ROCK: 
	        	result = "주먹";
	            break;
	        case SCISSORS:  
	        	result = "가위";
	            break;
	        case PAPER:
	        	result = "보";
	            break;
	    }
		
	    
	 // 가위, 바위, 보 게임 승패 결정
	    if (playerChoice == computerChoice) {
	    	lblResult.append("컴퓨터 값 : " +  result+" \n" +"유저값 : " + result +" \n"+ "비겼습니다 아까비...\n");
	    } else if ((playerChoice == ROCK && computerChoice == SCISSORS) ||
	               (playerChoice == SCISSORS && computerChoice == PAPER) ||
	               (playerChoice == PAPER && computerChoice == ROCK)) {
	    	lblResult.append("컴퓨터 값 : " +  result+" \n" +
	               "유저값 : " + result + " \n" + "유저가 이겼다..우씨...ㅠㅠ\n");
	    } else {
	    	lblResult.append("컴퓨터 값 : " +  result+" \n" +"유저값 : " +result+ " \n"+ "유저가 졌습니다ㅋ 울어요? ㅋ\n");
	    }
		
		
	}
}

	
	

