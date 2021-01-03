package FoodMenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// 음식 메뉴 생성자
class Food{
	private String name;
	private int price;
	
	public Food(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
}

// 주문 이미지 버튼
class MyButton extends Button{
	private Image img;
	
	public MyButton(Image img) {
		this.img=img;
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 5, 5, this.getWidth()-10 , this.getHeight()-10, this);
	}
}

class MyFrame extends JFrame implements ActionListener{
	private String[] name = new String[] { "짜 장 면", "짬     뽕", "순 대 국", "뼈해장국", "소불고기", 
			"제육볶음", "소갈비찜", "카레가스","청 국 장" };
	private int price[] = new int[] { 5000, 6000, 7000, 8000, 8000, 8000, 10000, 7000, 7000 };
	private String str = "--------주 문 내 역---------";
	private JPanel center_p = new JPanel();
	private JPanel east_p = new JPanel();
	private MyButton bt[] = new MyButton[9];
	private JTextArea ta = new JTextArea();
	private JScrollPane scroll = new JScrollPane(ta);
	private ArrayList<Food> list = new ArrayList<>();
	int sum = 0;
	
	// 초기 버튼 세팅
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("Center",center_p);
		east_p.setPreferredSize(new Dimension(200,300));
		this.add("East",east_p);
		east_p.setLayout(new BorderLayout());
		east_p.add("Center",scroll);
		center_p.setLayout(new GridLayout(3,3));
		ta.setText(str);
		
		for(int i=0; i<bt.length;i++) {
			// 이미지 파일 명은 1부터 시작하니까 (i+1) 해줘야 함
			Image img = Toolkit.getDefaultToolkit().getImage("food"+(i+1)+".png"); //이미지 가져오기
			bt[i] = new MyButton(img); // 이미지 버튼 생성
			center_p.add(bt[i]); //버튼 배치
		}
	}
	
	// 버튼 클릭 시 반응하도록 만들어주기
	public void start() {
		for(int i = 0;i<bt.length;i++) {
			bt[i].addActionListener(this);
		}
	}
	
	public MyFrame(String title) {
		super(title);
		this.init(); 
		this.start();
		
		this.setSize(550,300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2) - this.getWidth()/2;
		int ypos = (int) (screen.getHeight() - this.getHeight()) / 2;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int sum = 0;
		String str2 = ""; // 메뉴 내역 나올 필드
		String str3 = "---------------------------\n";
		
		for(int i=0; i<name.length; i++) {
			if(e.getSource()==bt[i]) {
				Food food = new Food(name[i],price[i]);
				list.add(food);
			}
		}
		Iterator<Food> itr = list.iterator();
		while(itr.hasNext()) {
			Food food=itr.next();
			str2+=food.getName()+"\t"+food.getPrice()+"\n";
			sum=sum+food.getPrice();
		}
		ta.setText(str+"\n"+str2+str3+"합계: "+sum);
		
	}
	
}



public class FoodMenu {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame("메뉴판");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
