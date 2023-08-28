package edu.java.pro.buble.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import edu.java.pro.buble.game.BubbleFrame;
import edu.java.pro.buble.game.Moveable;
import edu.java.pro.buble.service.BackGroundPlayerService;
import edu.java.pro.buble.state.PlayerWay;


// class Player -> new 가능한 애들!! 게임에 존재할 수 있음 (추상메서드를 가질 수 없음)
public class Player extends JLabel implements Moveable{

	private BubbleFrame mContext;
	private List<Bubble> bubbleList;

	// 위치 상태
	private int x;
	private int y;
	
	//플레이어 뱡향
	private PlayerWay playerway;
	
	
	// 움직인 상태 
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// GETTER, SETTER
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isLeft() {
		return left;
	}
	public boolean isRight() {
		return right;
	}
	
	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}
	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}
	public boolean isRightWallCrash() {
		return rightWallCrash;
	}
	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}
	
	public PlayerWay getPlayerway() {
		return playerway;
	}
	public void setPlayerway(PlayerWay playerway) {
		this.playerway = playerway;
	}
	public List<Bubble> getBubbleList() {
		return bubbleList;
	}
	public void setBubbleList(List<Bubble> bubbleList) {
		this.bubbleList = bubbleList;
	}
	public BubbleFrame getmContext() {
		return mContext;
	}
	public void setmContext(BubbleFrame mContext) {
		this.mContext = mContext;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ImageIcon getPlayerR() {
		return playerR;
	}
	public void setPlayerR(ImageIcon playerR) {
		this.playerR = playerR;
	}
	public ImageIcon getPlayerL() {
		return playerL;
	}
	public void setPlayerL(ImageIcon playerL) {
		this.playerL = playerL;
	}


	// player 속도 상태
	private final int SPEED = 5;
	private final int JUMPSPEED = 3; // up , down 스피드
	
	// 플레이어의 상태 오른쪽 왼쪽  이미지
	private ImageIcon playerR, playerL;
	
	
	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackGroundPlayerSerivice();
		
	}

	private void initBackGroundPlayerSerivice() {
		new Thread(new BackGroundPlayerService(this)).start();
		
	}
	
	// 플레이어의 버블로 공격 
	@Override
	public void attack() {
		new Thread(() -> {
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			bubbleList.add(bubble);
			if(playerway == PlayerWay.LEFT) {
				bubble.left();
			}else {
				bubble.right();
			}	
		}).start();
		
	}
	
	private void initSetting() { // 플레이어의 위치
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		leftWallCrash = false;
		rightWallCrash = false;
		
		playerway = PlayerWay.RIGHT; // 시작시 플레이어는 항상 오른쪽을 보고 있음.
		setIcon(playerR);
		setSize(50,50);
		setLocation(x, y);
		
		
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
		bubbleList = new ArrayList<>();
		
	}

	@Override
	public void left() {
		//System.out.println("left");
		playerway = PlayerWay.LEFT; // 버블의 방향 플레이어 기준
		left = true;
		new Thread(()-> {
			while (left){
				setIcon(playerL);
				x = x -SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}			
		}).start(); // 람다식 표현
		
		
	}

	@Override
	public void right() {
		//System.out.println("right");
		playerway = PlayerWay.RIGHT; // 플레이어 뱡향의 기준에 따라 버블의 방향도 결정이 되야함
		right = true;
		new Thread(()-> {
			while(right) {
				setIcon(playerR);
				x = x +SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}).start(); // 람다식 표현
		
		
	}

	@Override
	public void up() {
		//System.out.println("UP");
		up = true;
		new Thread(()-> {
			for(int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y -JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;
			down();
		}).start(); // 람다식 표현		
		
	}

	@Override
	public void down() {
		//System.out.println("down");
		down = true;
		new Thread(()-> {
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();	
		
	}
	
	

}
