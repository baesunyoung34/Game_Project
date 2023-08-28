package edu.java.pro.RSPgame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.java.pro.RSP.music.BGM;
import edu.java.pro.buble.game.BubbleFrame;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMain extends JFrame {
	ImageIcon[] imageIcons = {new ImageIcon("gameMain/RSP.png"), new ImageIcon("gameMain/bobbles.png")};// 게임 메인 화면
	public JFrame frame;
	private JButton btn1;
	private JButton btn2;
	private JLabel lblgame1;
	private JLabel lblgame2;
	private JLabel lblTITle;
	private JPanel panelTitle;
	private JPanel panel;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMain window = new GameMain();
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
	public GameMain() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 628, 403);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("게임 메인 화면");
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblgame1 = new JLabel(imageIcons[0]);
		lblgame1.setBounds(86, 20, 190, 216);
		panel.add(lblgame1);
		
		lblgame2 = new JLabel(imageIcons[1]);
		lblgame2.setBounds(342, 20, 190, 216);
		panel.add(lblgame2);
		
		btn2 = new JButton("click");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);	
				BubbleFrame.showBubbleGame(frame, GameMain.this);
			}
		});
		btn2.setBackground(new Color(128, 128, 192));
		btn2.setFont(new Font("휴먼매직체", Font.PLAIN, 22));
		btn2.setBounds(381, 259, 123, 37);
		panel.add(btn2);
		
		btn1 = new JButton("click");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 현재 화면을 닫음
				frame.setVisible(false);
				GameRSP.showRSPGame(frame, GameMain.this);
			}
		});
		btn1.setBackground(new Color(128, 128, 192));
		btn1.setFont(new Font("휴먼매직체", Font.PLAIN, 22));
		btn1.setBounds(109, 259, 123, 37);
		panel.add(btn1);
		
		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelTitle, BorderLayout.NORTH);
		
		lblTITle = new JLabel("GAME MAIN");
		lblTITle.setFont(new Font("새굴림", Font.BOLD, 32));
		panelTitle.add(lblTITle);
	}

}
