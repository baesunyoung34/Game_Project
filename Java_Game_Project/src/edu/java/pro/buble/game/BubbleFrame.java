package edu.java.pro.buble.game;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.java.pro.RSPgame.GameMain;
import edu.java.pro.buble.component.Enemy;
import edu.java.pro.buble.component.Player;
import edu.java.pro.buble.music.BGM;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class BubbleFrame extends JFrame {
	
	private BubbleFrame mContext = this;
	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;
	private Component parent;
    private GameMain gamemain;
    private BGM bgm;
    GameMain app = new GameMain();
	
	// getter setter
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public BubbleFrame getmContext() {
		return mContext;
	}

	public void setmContext(BubbleFrame mContext) {
		this.mContext = mContext;
	}

	public JLabel getBackgroundMap() {
		return backgroundMap;
	}

	public void setBackgroundMap(JLabel backgroundMap) {
		this.backgroundMap = backgroundMap;
	}
	public void exit() {
		
        // BGM 종료
        bgm.stop();
        
        // 게임 창 닫기
        dispose();
        setVisible(false);

        // 이전 창으로 돌아가기
        app.frame.setVisible(true);
    }
	

	public BubbleFrame(Component parent, GameMain gamemain) {
	this.parent = parent;
    this.gamemain = gamemain;
	initObject();
	initSetting();
	initListener();
	setVisible(true);	
	bgm = new BGM();
	}	
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player(mContext);
		getContentPane().add(player);
		enemy = new Enemy(mContext);
		getContentPane().add(enemy);
		
	}
	
	
	private void initSetting() {
		setSize(1000,640);
		getContentPane().setLayout(null); // absoulte 레이아웃 (자유롭게 그림을 그릴 수 있다)
		setLocationRelativeTo(null); // JFrame 가운데 배치하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 종료시 JVM 같이 종료.
		setTitle("보글보글 게임");
	}
	
	private void initListener () {
		addKeyListener(new KeyAdapter() {
			
			// 키보드 클릭 이벤트
			@Override
			public void keyPressed(KeyEvent e) {
			//	System.out.println(e.getKeyCode());
				
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_LEFT:
					if(!player.isLeft() && !player.isLeftWallCrash()) {
					player.left();
					}
					break;
				
				case KeyEvent.VK_RIGHT:
					if(!player.isRight() && !player.isRightWallCrash()) {
						player.right();	
					}
					break;
			
				case KeyEvent.VK_UP:
					if(!player.isUp() && !player.isDown()) {
					player.up();
					}
					break;
				
				case KeyEvent.VK_SPACE:
					player.attack();
					break;
				case KeyEvent.VK_0:
					exit();
					break;
				}
				
					
			}
			
			// 키보드 해제 이벤트
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
				
			}
		});
	}
	public static void showBubbleGame(Component parent, GameMain gamemain) {
		new BubbleFrame(parent,gamemain);

	}
}
