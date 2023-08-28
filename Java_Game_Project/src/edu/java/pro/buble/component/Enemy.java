package edu.java.pro.buble.component;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import edu.java.pro.buble.game.BubbleFrame;
import edu.java.pro.buble.game.Moveable;
import edu.java.pro.buble.service.BackGroundEnemyService;
import edu.java.pro.buble.state.EnemyWay;


// class Player -> new 가능한 애들!! 게임에 존재할 수 있음 (추상메서드를 가질 수 없음)
public class Enemy extends JLabel implements Moveable{

	private BubbleFrame mContext;

	// 위치 상태
	private int x;
	private int y;
	
	//적군 뱡향
	private EnemyWay enemyway;
	
	
	// 움직인 상태 
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state; // 0 이 (살아있는 상태) 1(물방울에 갇힌 상태)
	
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
	
	public EnemyWay getEnemyway() {
		return enemyway;
	}
	public void setEnemyway(EnemyWay enemyway) {
		this.enemyway = enemyway;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public ImageIcon getEnemyR() {
		return enemyR;
	}
	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}
	public ImageIcon getEnemyL() {
		return enemyL;
	}
	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
	}
	public int getSPEED() {
		return SPEED;
	}
	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	// Enemy 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1; // up , down 스피드
	
	// 적의 상태 오른쪽 왼쪽 이미지
	private ImageIcon enemyR, enemyL;
	
	
	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackGroundEnemySerivice();
		right();
		
	}

	private void initBackGroundEnemySerivice() {
		new Thread(new BackGroundEnemyService(this)).start();
		
	}
	@Override
	public void attack() {
		new Thread(() -> {
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			if(enemyway == EnemyWay.LEFT) {
				bubble.left();
			}else {
				bubble.right();
			}	
		}).start();
		
	}
	
	private void initSetting() { // 적 위치 시작
		x = 480;
		y = 170;
		
		left = false;
		right = false;
		up = false;
		down = false;
	
		state = 0;
		
		enemyway = EnemyWay.RIGHT; // 시작시 플레이어는 항상 오른쪽을 보고 있음.
		setIcon(enemyR);
		setSize(50,50);
		setLocation(x, y);
		
		
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
		
	}

	@Override
	public void left() {
		//System.out.println("left");
		enemyway = EnemyWay.LEFT; // 버블의 방향 플레이어 기준
		left = true;
		new Thread(()-> {
			while (left){
				setIcon(enemyL);
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
		enemyway = EnemyWay.RIGHT; // 플레이어 뱡향의 기준에 따라 버블의 방향도 결정이 되야함
		right = true;
		new Thread(()-> {
			while(right) {
				setIcon(enemyR);
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
