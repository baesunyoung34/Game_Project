package edu.java.pro.buble.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import edu.java.pro.buble.component.Enemy;

// 메인스레드 바쁨 - 키보드 이벤트 처리하기 때문에 바쁨
// 백그라운드에서 계속 관찰
public class BackGroundEnemyService implements Runnable{

	
	private BufferedImage image;
	private Enemy enemy;

	
				
	public BackGroundEnemyService (Enemy enemy) {
		this.enemy =  enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void run() {
		while(enemy.getState() == 0) {
			
		// 색상 확인
		Color leftcolor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
		Color rightcolor = new Color(image.getRGB(enemy.getX() + 50 + 15, enemy.getY()+ 25));
		// 가장 왼쪽과 가장 오른쪽부분의 하단
		// -2 가 나온다는 것은 바닥에 색깔이 없이 흰색
		int bottomColor = image.getRGB(enemy.getX() + 10 , enemy.getY()+ 50 + 5) 
				+ image.getRGB(enemy.getX()+ 50 , enemy.getY()+ 50 + 5); 
		// System.out.println("바텀 컬러");
//		System.out.println("left" + leftcolor);
//		System.out.println("right" + rightcolor);
		
		// 바닥 충돌 확인
		if(bottomColor != -2) {
			//System.out.println("바텀 컬러" + bottomColor);
			//System.out.println("바닥에 충돌함");
			enemy.setDown(false);
		} else { // -2일때 실행됨 => 내 바닥색깔이 하얀색이라는 것
			if(!enemy.isUp() && !enemy.isDown()) {
//			System.out.println("다운 실행함");
				enemy.down();
			}
			
		}
		
		
		
		
		// 외벽 충돌 확인
		if(leftcolor.getRed()== 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0 ) {
			enemy.setLeft(false);
			if(!enemy.isRight()) {
				enemy.right();
			}
		} else if (rightcolor.getRed()== 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0) {
			enemy.setRight(false);
			if(!enemy.isLeft()) {
				enemy.left();	
			}
		} 
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
