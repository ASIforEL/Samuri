package logic;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CaptainAmerica extends Samurai{
	
	
	public CaptainAmerica() {
		
		setSide(0);
		setWeapon(0);
		setLifeSpan(2);
		//��ʼ��ʱ ����Ӣ�۵�ͼƬ���ؽ�ȥ
		try {
			samuraiImg = ImageIO.read(this.getClass().getResource(""));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	
	//����Ѫ��
	public void drawBlood(Graphics g,JPanel i){
		initBlood_X = 150;	//ÿ��Ӣ�۵ĳ�ʼλ�ò�һ��
		initBlood_Y = 150;	
		//����ͼƬ��С���ü�࣬yֵ����
		for (int j = 0; j < this.getLifeSpan(); j++) {
			g.drawImage(bloodImg, initBlood_X+j*50, initBlood_Y, (ImageObserver)i);
		}
	}

	//����Ӣ��
	public void drawSamurai(Graphics g, JPanel i) {
		// TODO �Զ����ɵķ������
		//ÿ��Ӣ�ۼ��ز�ͬ��ͼƬ
		
	}

	//��������
	public void drawPower(Graphics g, JPanel i) {
		//ͬ����Ѫ��һ��
	}
}
