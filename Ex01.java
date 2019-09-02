import java.awt.*;
import javax.swing.*;

public class Ex01 extends JFrame implements Runnable {
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image miro = tk.getImage("src/1.png");
	Graphics buffg = null;
	Image buffImage;
	boolean up, down, left, right;
	int x = 1, y = 2;
	int char_x = 0, char_y = 100;
	boolean exit;
	int[][] tile = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		   ,{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}
		   ,{1, 2, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1}
		   ,{1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 3}
		   ,{1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1}
		   ,{1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}
		   ,{1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1}
		   ,{1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1}
		   ,{1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1}
		   ,{1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1}
		   ,{1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1}
		   ,{1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1}
		   ,{1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1}
		   ,{1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1}
		   ,{1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1}
		   ,{1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1}
		   ,{1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1}
		   ,{1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1}
		   ,{1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}
		   ,{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	/*
	 * 타일 번호
	 * 0	- 기본 땅
	 * 1	- 물
	 * 2	- 출발지
	 * 3	- 도착지
	 * 4	- 지나온 타일
	 * 5	-2번 지나온 타일
	 */
	Ex01() {
		Dimension dim = new Dimension(1000, 1000);
		setPreferredSize(dim);
		pack();
		setTitle("미로찾기");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		new Thread(this).start();
	}
	public void paint(Graphics g) {
		buffImage = createImage(1000, 1000);
		buffg = buffImage.getGraphics();
		update(g);
	}
	public void update(Graphics g) {
		tile[2][0] = 2;
		for(int x=0; x<20; x++) {
			for(int y=0; y<20; y++) {
				if(tile[y][x] == 0 || tile[y][x] >= 4) {
					miro = tk.getImage("src/castleCenter.png");
					buffg.drawImage(miro, x*50, y*50, this);
				} else if(tile[y][x] == 1) {
					miro = tk.getImage("src/liquidWater.png");
					buffg.drawImage(miro, x*50, y*50, this);
				} else if(tile[y][x] == 2) {
					miro = tk.getImage("src/castleCenter.png");
					buffg.drawImage(miro, x*50, y*50, this);
					miro = tk.getImage("src/window.png");
					buffg.drawImage(miro, x*50, y*50, this);
				} else if(tile[y][x] == 3) {
					miro = tk.getImage("src/castleCenter.png");
					buffg.drawImage(miro, x*50, y*50, this);
					miro = tk.getImage("src/signExit.png");
					buffg.drawImage(miro, x*50, y*50, this);
				}
				miro = tk.getImage("src/p1_stand.png");
				buffg.drawImage(miro, char_x, char_y, this);
			}
		}
		g.drawImage(buffImage, 0, 0, this);
	}
	public static void main(String[] args) {
		new Ex01();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				repaint();
				if(exit == false) {
					Check();
					move();
				}
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void move() {
		if(right == true) {
			char_x += 5;
			if(char_x % 50 == 0) {
				x++;
				right = false;
			}
		} else if(left == true) {
			char_x -= 5;
			if(char_x % 50 == 0) {
				x--;
				left = false;
			}
		} else if(up == true) {
			char_y -= 5;
			if(char_y % 50 == 0) {
				y--;
				up = false;
			}
		} else if(down == true) {
			char_y += 5;
			if(char_y % 50 == 0) {
				y++;
				down = false;
			}
		}
	}
	public void Check() {
		try {
			if(tile[y][x-1] == 0) {
				left = true;
			} else if(tile[y+1][x] == 0) {
				down = true;
			} else if(tile[y][x+1] == 0) {
				right = true;		
			} else if(tile[y-1][x] == 0) {
				up = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
