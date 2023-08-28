package edu.java.pro.buble.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import edu.java.pro.buble.game.BubbleFrame;
import edu.java.pro.buble.game.Moveable;
import edu.java.pro.buble.service.BackGroundBubbleService;

public class Bubble extends JLabel implements Moveable{
	
	// 의존성 콤포지션
	private BubbleFrame mContext;
	private Player player;
	private Enemy enemy;
	private BackGroundBubbleService backGroundBubbleService;

	
	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적군을 맞춘 상태
	private int state; // 0(물방울), 1(적을 가둔 물방울)
	// getter setter
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}


	
	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
	}
	
	

	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backGroundBubbleService = new  BackGroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0;
	}
	
	

	@Override
	public void left() {
		left = true;  // 왼쪽으로 가니까 TRUE
		for(int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);
			
			if(backGroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			// 아군과 적군의 거리가 10
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50 ) {
				System.out.println("물방울이 적군과 충돌하였습니다.");
				if(enemy.getState() == 0) {
					attack();
					break;
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up(); // 버블이 400 정도 가다가 위로 올라간다는 것. 
		
	}

	@Override
	public void right() {
		right = true;
		for(int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);
			if(backGroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			// 아군과 적군의 거리가 10
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50 ) {
				System.out.println("물방울이 적군과 충돌하였습니다.");
				if(enemy.getState() == 0) {
					attack();
					break;
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up(); // 버블이 400 정도 가다가 위로 올라간다는 것. 	
	}

	@Override
	public void up() {
		up = true;
		while(true) {
			y--;
			setLocation(x, y);
			if(backGroundBubbleService.topWall()) {
				up = false;
				break;
			}
			try {
			if(state == 0) { // 기본 물방울
				Thread.sleep(1);
			}else { // 적을 가둔 물방울
				Thread.sleep(10);
			}
			
		}catch (InterruptedException e) {
				e.printStackTrace();
		}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		if(state == 0) {
		clearBubble(); // 천장에 버블이 도착하고 나서 3초 후에 메모리에서 소멸.
		}
	}

	@Override
	public void attack() {
		state = 1;
		enemy.setState(1); // 적군의 상태도 바꿔줘야함.
		setIcon(bubbled);
		mContext.remove(enemy); // 메모리에서 사라지게 한다. (가비지 컬렉션->즉시 발동하지 않음)
		mContext.repaint(); // 화면 갱신
	}


	private void clearBubble() {
		try {
			Thread.sleep(3000); // 3초
			setIcon(bomb); // 어택과 동시에 충격을 받아서 이미지 아이콘이 바뀜
			Thread.sleep(5000); // 터진 버블이 화면에서 사라져야함
			mContext.remove(this); // BubbleFrame의 bubble이 메모리에서 소멸 된다. 
			mContext.repaint(); // BubbleFrame의 전체를 다시 그림 즉 mContext.remove(this);에서 버블 메모리가 사라져서 repaint 다시 그린다 메모리에 남아있는 것들만 다시 그림
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void clearBubbled() {
		new Thread(()->{
			System.out.println("clearBubbled");
			try {
				up = false;
				setIcon(bomb);
				Thread.sleep(10);
				mContext.remove(this);
				mContext.repaint();
			} catch (Exception e) {
			}
		}).start();
		
	}



	
}
