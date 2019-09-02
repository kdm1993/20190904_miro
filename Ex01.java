import java.awt.*;
import javax.swing.*;

public class Ex01 extends JFrame implements Runnable {
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image left_img, right_img, short_img;
	Graphics buffg = null;
	Image buffImage;
	boolean left_up, left_down, left_left, left_right;
	boolean right_up, right_down, right_left, right_right;
	int left_x = 1, left_y = 2;
	int right_x = 1, right_y = 2;
	int left_char_x = 50, left_char_y = 100;
	int right_char_x = 50, right_char_y = 100;
	boolean left_exit, left_move;
	boolean right_exit, right_move;
	int[][] tile = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		   ,{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1}
		   ,{1, 2, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1}
		   ,{1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1}
		   ,{1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1}
		   ,{1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1}
		   ,{1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1}
		   ,{1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1}
		   ,{1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1}
		   ,{1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1}
		   ,{1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1}
		   ,{1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1}
		   ,{1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1}
		   ,{1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1}
		   ,{1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1}
		   ,{1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1}
		   ,{1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1}
		   ,{1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1}
		   ,{1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 1}
		   ,{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	/*
	 * 타일	- 기본 땅 번호
	 * 0
	 * 1	- 물
	 * 2	- 출발지
	 * 3	- 도착지
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
		for(int x=0; x<20; x++) {
			for(int y=0; y<20; y++) {
				if(tile[y][x] == 0 || tile[y][x] >= 4) {
					left_img = tk.getImage("src/castleCenter.png");
					buffg.drawImage(left_img, x*50, y*50, this);
				} else if(tile[y][x] == 1) {
					left_img = tk.getImage("src/liquidWater.png");
					buffg.drawImage(left_img, x*50, y*50, this);
				} else if(tile[y][x] == 2) {
					left_img = tk.getImage("src/castleCenter.png");
					buffg.drawImage(left_img, x*50, y*50, this);
					left_img = tk.getImage("src/window.png");
					buffg.drawImage(left_img, x*50, y*50, this);
				} else if(tile[y][x] == 3) {
					left_img = tk.getImage("src/castleCenter.png");
					buffg.drawImage(left_img, x*50, y*50, this);
					left_img = tk.getImage("src/signExit.png");
					buffg.drawImage(left_img, x*50, y*50, this);
				}
				buffg.setFont(new Font("돋움", Font.BOLD, 30));
				if(left_exit == false) {
					left_img = tk.getImage("src/p1_right.png");
					if(left_left == true) {
						left_img = tk.getImage("src/p1_left.png");
					} else if(left_up == true) {
						left_img = tk.getImage("src/p1_up.png");
					} else if(left_down == true) {
						left_img = tk.getImage("src/p1_down.png");
					}
					buffg.drawImage(left_img, left_char_x, left_char_y, this);
					buffg.drawString("좌선법", left_char_x-25, left_char_y);
				}
				if(right_exit == false) {
					right_img = tk.getImage("src/p2_right.png");
					if(right_left == true) {
						right_img = tk.getImage("src/p2_left.png");
					} else if(left_up == true) {
						right_img = tk.getImage("src/p2_up.png");
					} else if(left_down == true) {
						right_img = tk.getImage("src/p2_down.png");
					}
					buffg.drawImage(right_img, right_char_x, right_char_y, this);
					buffg.drawString("우선법", right_char_x-25, right_char_y);
				}
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
				if(left_exit == false) {
					left_Check();
					left_move();
				}
				if(right_exit == false) {
					right_Check();
					right_move();
				}
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void left_move() {
		if(left_right == true) {
			left_char_x += 5;
			if(left_char_x % 50 == 0) {
				left_x++;
				left_move = false;
			}
		}
		if(left_left == true) {
			left_char_x -= 5;
			if(left_char_x % 50 == 0) {
				left_x--;
				left_move = false;			
			}
		}
		if(left_up == true) {
			left_char_y -= 5;
			if(left_char_y % 50 == 0) {
				left_y--;
				left_move = false;			
			}
		}
		if(left_down == true) {
			left_char_y += 5;
			if(left_char_y % 50 == 0) {
				left_y++;
				left_move = false;
			}
		}
		if(tile[left_x][left_y] == 3) {
			left_exit = true;
			left_move = true;
		}
		
	}
	public void left_Check() {
		
		if(left_move == false) {
			
			boolean Check_left, Check_right, Check_up, Check_down;
			
			Check_up = tile[left_y-1][left_x] == 1 ? true : false;
			Check_down = tile[left_y+1][left_x] == 1 ? true : false;
			Check_left = tile[left_y][left_x-1] == 1 ? true : false;
			Check_right = tile[left_y][left_x+1] == 1 ? true : false;
			
			if(tile[left_y][left_x+1] == 3) {
				left_right = true;
				left_up = false;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(tile[left_y][left_x-1] == 3) {
				left_left = true;
				left_right = false;
				left_up = false;
				left_down = false;
				left_move = true;
			} else if(tile[left_y+1][left_x] == 3) {
				left_down = true;
				left_right = false;
				left_up = false;
				left_left = false;
				left_move = true;
			} else if(tile[left_y-1][left_x] == 3) {
				left_up = true;
				left_right = false;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(tile[left_y+1][left_x] == 0 && left_left == true) {
				left_right = false;
				left_up = false;
				left_down = true;
				left_left = false;
				left_move = true;
			} else if(tile[left_y][left_x+1] == 0 && left_down == true) {
				left_right = true;
				left_up = false;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(tile[left_y][left_x-1] == 0 && left_up == true) {
				left_right = false;
				left_up = false;
				left_down = false;
				left_left = true;
				left_move = true;
			} else if(tile[left_y-1][left_x] == 0 && left_right == true) {
				left_right = false;
				left_up = true;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(Check_left == true && Check_down == true && tile[left_y-1][left_x] == 0 && left_left == true) {
				left_right = false;
				left_up = true;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(Check_down == true && Check_right == true && tile[left_y][left_x-1] == 0 && left_down == true) {
				left_right = false;
				left_up = false;
				left_down = false;
				left_left = true;
				left_move = true;
			} else if(Check_up == true && Check_left == true && tile[left_y][left_x+1] == 0 && left_up == true) {
				left_right = true;
				left_up = false;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(Check_right == true && Check_up == true && tile[left_y+1][left_x] == 0 && left_right == true) {
				left_right = false;
				left_up = false;
				left_down = true;
				left_left = false;
				left_move = true;
			} else if(Check_right == true && Check_up == true && Check_left == true) {
				left_right = false;
				left_up = false;
				left_down = true;
				left_left = false;
				left_move = true;
			} else if(Check_down == true && Check_up == true && Check_left == true) {
				left_right = true;
				left_up = false;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(Check_down == true && Check_right == true && Check_left == true) {
				left_right = false;
				left_up = true;
				left_down = false;
				left_left = false;
				left_move = true;
			} else if(Check_right == true && Check_up == true && Check_down == true) {
				left_right = false;
				left_up = false;
				left_down = false;
				left_left = true;
				left_move = true;
			}
		}
	}
	public void right_move() {
		if(right_right == true) {
			right_char_x += 5;
			if(right_char_x % 50 == 0) {
				right_x++;
				right_move = false;				
			}
		}
		if(right_left == true) {
			right_char_x -= 5;
			if(right_char_x % 50 == 0) {
				right_x--;
				right_move = false;							
			}
		}
		if(right_up == true) {
			right_char_y -= 5;
			if(right_char_y % 50 == 0) {
				right_y--;
				right_move = false;							
			}
		}
		if(right_down == true) {
			right_char_y += 5;
			if(right_char_y % 50 == 0) {
				right_y++;
				right_move = false;		
			}
		}
		if(tile[right_x][right_y] == 3) {
			right_exit = true;
		}		
	}
	public void right_Check() {
		
		if(right_move == false) {
			
			boolean Check_left, Check_right, Check_up, Check_down;
			
			Check_up = tile[right_y-1][right_x] == 1 ? true : false;
			Check_down = tile[right_y+1][right_x] == 1 ? true : false;
			Check_left = tile[right_y][right_x-1] == 1 ? true : false;
			Check_right = tile[right_y][right_x+1] == 1 ? true : false;
			
			if(tile[right_y][right_x+1] == 3) {
				right_right = true;
				right_up = false;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(tile[right_y][right_x-1] == 3) {
				right_left = true;
				right_right = false;
				right_up = false;
				right_down = false;
				right_move = true;
			} else if(tile[right_y+1][right_x] == 3) {
				right_down = true;
				right_right = false;
				right_up = false;
				right_left = false;
				right_move = true;
			} else if(tile[right_y-1][right_x] == 3) {
				right_up = true;
				right_right = false;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(tile[right_y-1][right_x] == 0 && right_left == true) {
				right_right = false;
				right_up = true;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(tile[right_y][right_x-1] == 0 && right_down == true) {
				right_right = false;
				right_up = false;
				right_down = false;
				right_left = true;
				right_move = true;
			} else if(tile[right_y][right_x+1] == 0 && right_up == true) {
				right_right = true;
				right_up = false;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(tile[right_y+1][right_x] == 0 && right_right == true) {
				right_right = false;
				right_up = false;
				right_down = true;
				right_left = false;
				right_move = true;
			} else if(Check_left == true && Check_up == true && tile[right_y+1][right_x] == 0 && right_left == true) {
				right_right = false;
				right_up = false;
				right_down = true;
				right_left = false;
				right_move = true;
			} else if(Check_down == true && Check_left == true && tile[right_y][right_x+1] == 0 && right_down == true) {
				right_right = true;
				right_up = false;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(Check_up == true && Check_right == true && tile[right_y][right_x-1] == 0 && right_up == true) {
				right_right = false;
				right_up = false;
				right_down = false;
				right_left = true;
				right_move = true;
			} else if(Check_right == true && Check_down == true && tile[right_y-1][right_x] == 0 && right_right == true) {
				right_right = false;
				right_up = true;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(Check_right == true && Check_up == true && Check_left == true) {
				right_right = false;
				right_up = false;
				right_down = true;
				right_left = false;
				right_move = true;
			} else if(Check_down == true && Check_up == true && Check_left == true) {
				right_right = true;
				right_up = false;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(Check_down == true && Check_right == true && Check_left == true) {
				right_right = false;
				right_up = true;
				right_down = false;
				right_left = false;
				right_move = true;
			} else if(Check_right == true && Check_up == true && Check_down == true) {
				right_right = false;
				right_up = false;
				right_down = false;
				right_left = true;
				right_move = true;
			}
		}
	}
}
