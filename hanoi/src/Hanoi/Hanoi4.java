package Hanoi;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Zzm
 */

public class Hanoi4 extends JFrame implements Runnable, KeyListener {

	private int m_pegs;
	private int m_plates;
	private LinkedList<Integer>[] InitialPegStatus;
	private Var var;
	int count=0;
	public void Rearrange() {
		m_plates = 0;
		for (int i = 0; i < m_pegs; i++)
			m_plates += InitialPegStatus[i].size();
		for (int i = 1; i < m_plates; i++) {
			MoveToContainer(i);
		}
//		System.out.println(count);
	}

	// ȫ���Ƶ�һ��������

	public void MoveToContainer(int plate) {
		int owningIndex = -1, containingIndex = -1;
		int[] bufferIndex = new int[] { -1, -1 };
		int current = 0;
		for (int i = 0; i < m_pegs; i++) {
			boolean flag = InitialPegStatus[i].contains(plate);
			if (flag == false)
				continue;
			else {
				owningIndex = i;// �ҳ�ֵΪplate���������ڵ����ӱ��
				break;
			}
		} // for����
		if (owningIndex >= 0) {
			for (int i = 0; i < m_pegs; i++) {
				boolean flag = InitialPegStatus[i].contains(plate + 1);// �ҳ���Ҫ���õ����ӱ��
				if (flag == false)
					continue;
				else {
					containingIndex = i;
					break;
				}
			} // for����
			for (int i = 0; i < m_pegs; i++)
				if (i != owningIndex && i != containingIndex) {// Ѱ���м仺����
					bufferIndex[current++] = i;
					if (current == 2)
						break;
					continue;
				}
			if (containingIndex >= 0 && owningIndex != containingIndex) {
				hanoi_four(plate, owningIndex, bufferIndex[0], bufferIndex[1], containingIndex);
			}
		} // owingIndex if����
	}

	public void Move() {
		Random r = new Random();
		int numberOfPeg = 0;
		for (int i = 1; i <= var.n; i++) {// �������������
			int j = r.nextInt(4);
			InitialPegStatus[j].add(i);
			numberOfPeg = j;
		}
		for (int i = 0; i < 4; i++) {
			Collections.sort(InitialPegStatus[i], new IntegetCompare());// С��������
			System.out.println(InitialPegStatus[i]);
		}
		var.t_one = InitialPegStatus[0].size() - 1;
		var.t_two = InitialPegStatus[1].size() - 1;
		var.t_three = InitialPegStatus[2].size() - 1;
		var.t_four = InitialPegStatus[3].size() - 1;
		for (int i = 0; i < 4; i++) {
			int tem = InitialPegStatus[i].size() - 1;
			for (int j = 0; j < InitialPegStatus[i].size(); j++) {
				if (i == 0)
					var.tower_one[tem--] = var.n - InitialPegStatus[i].get(j);
				if (i == 1)
					var.tower_two[tem--] = var.n - InitialPegStatus[i].get(j);
				if (i == 2)
					var.tower_three[tem--] = var.n - InitialPegStatus[i].get(j);
				if (i == 3)
					var.tower_four[tem--] = var.n - InitialPegStatus[i].get(j);
			}
		}
		Rearrange();
		if (numberOfPeg < 3) {
			int current = 0;
			int[] tempNumber = new int[3];
			for (int i = 0; i < 4; i++)
				if (i != numberOfPeg)
					tempNumber[current++] = i;
			hanoi_four(var.n, numberOfPeg, tempNumber[0], tempNumber[1], 3);
			
		}
		System.out.print(count);
	}

	public Thread thread1 = new Thread(this);

	// ���캯��
	public Hanoi4() {
		init();
		Init_K();
		Image icon = Toolkit.getDefaultToolkit().getImage("rec\\hanoi.jpg");
		setIconImage(icon);
		add(new Paints());
		input();
		this.addKeyListener(this);
		thread1.start();
	}


	public void Init_K() {
		int i, k;
		long temp;
		for (int j = 0; j <= var.Max; j++) {
			var.m[j] = 0;
			var.K[j] = 0;
		}
		for (i = 1; i <= var.Max; i++) {
			var.m[i] = var.INT_MAX;
			for (k = 1; k <= i; k++) {
				temp = 2 * var.m[i - k] + (long) Math.pow(2, k) - 1;
				if (temp < var.m[i]) {
					var.m[i] = temp;
					var.K[i] = k;
				}
			}
		}
	}

	public void init() {
		var = new Var();
		for (int i = 0; i < 30; i++) {
			var.tower_one[i] = i;
			var.tower_two[i] = i;
			var.tower_three[i] = i;
		}
		InitialPegStatus = new LinkedList[4];
		m_pegs = 4;// ��������
		for (int i = 0; i < 4; i++) {
			InitialPegStatus[i] = new LinkedList<Integer>();
		}
	}

	public void input() {
		String str = JOptionPane.showInputDialog("�����뺺ŵ������������13��������");
		int x = (int) Float.parseFloat(str);
		if (!(x >= 1 && x <= 13)) {
			JOptionPane.showMessageDialog(null, "�����뺺ŵ������������13��������");
			System.exit(0);
		} else {
			var.n = x;
		}
	}

	public void moves(int a, int b) {
		count++;
		switch (a) {
		case 0:
			var.rectmid = 155;
			var.tower = var.tower_one;
			var.t = var.t_one;// tΪ��������������¼��
			var.t_one -= 1;
			break;
		case 1:
			var.rectmid = 315;
			var.tower = var.tower_two;
			var.t = var.t_two;
			var.t_two -= 1;
			break;
		case 2:
			var.rectmid = 475;
			var.tower = var.tower_three;
			var.t = var.t_three;
			var.t_three -= 1;
			break;
		case 3:
			var.rectmid = 635;
			var.tower = var.tower_four;
			var.t = var.t_four;
			var.t_four -= 1;
			break;
		}
		switch (b) {
		case 0:
			var.rectmidt = 155;
			var.t2 = var.t_one;
			break;// t2��¼����������-1Ϊû������0Ϊ1��
		case 1:
			var.rectmidt = 315;
			var.t2 = var.t_two;
			break;
		case 2:
			var.rectmidt = 475;
			var.t2 = var.t_three;
			break;
		case 3:
			var.rectmidt = 635;
			var.t2 = var.t_four;
			break;
		}
		if (a >= b)
			var.bool = 1;
		else
			var.bool = 0;
		var.rectx1 = var.rectmid - (14 - var.tower[var.t]) * 5;
		var.rectx2 = var.rectmid + (14 - var.tower[var.t]) * 5;
		var.recty = 460 - var.t * 10;
		while (var.recty >= 200)// �����׶�
		{

			var.recty -= 10;
			repaint();
			try {
				Thread.sleep(var.delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (var.rectmid != var.rectmidt)// rectmidt��ʾ�м����м��λ��
		{
			if (var.bool == 1)// a>=b,���ұ������
			{
				var.rectmid -= 5;
				var.rectx1 = var.rectmid - (14 - var.tower[var.t]) * 5;// �ƶ��ĺ����꣬��5��ά�ֳ��Ȳ��䣬x2Ҫ-5
				var.rectx2 = var.rectmid + (14 - var.tower[var.t]) * 5;// rectx2-rectx1 ���ֲ��䣬Ϊ��˵����ӳ���
				repaint();
				try {
					Thread.sleep(var.delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else // ��������ұ�
			{
				var.rectmid += 5;
				var.rectx1 = var.rectmid - (14 - var.tower[var.t]) * 5;
				var.rectx2 = var.rectmid + (14 - var.tower[var.t]) * 5;
				repaint();
				try {
					Thread.sleep(var.delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		while (var.recty <= (430 - (var.t2) * 10))// �������ƶ�
		{
			var.recty += 10;
			try {
				Thread.sleep(var.delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
		switch (b)// �޸�
		{
		case 0:
			var.t_one += 1;
			var.tower_one[var.t_one] = var.tower[var.t];
			break;// b��������˱��Ϊa�������ƶ��ı��
		case 1:
			var.t_two += 1;
			var.tower_two[var.t_two] = var.tower[var.t];
			break;
		case 2:
			var.t_three += 1;
			var.tower_three[var.t_three] = var.tower[var.t];
			break;
		case 3:
			var.t_four += 1;
			var.tower_four[var.t_four] = var.tower[var.t];
			break;
		}

	}// moves����

	public void hanoi_four(int n, int one, int two, int three, int four) {
		if (n == 1) {
			InitialPegStatus[one].remove(new Integer(n));
			InitialPegStatus[four].addFirst(n);
			moves(one, four);
		} else {
			int kn = var.K[n];
			hanoi_four(n - kn, one, three, four, two);
			hanoi_three(kn, one, three, four);
			hanoi_four(n - kn, two, one, three, four);
		}
	}

	public void hanoi_three(int n, int one, int two, int three) {
		if (n == 1) {
			moves(one, three);
			InitialPegStatus[one].remove(new Integer(n));
			InitialPegStatus[three].addFirst(n);

		
		} else {
			hanoi_three(n - 1, one, three, two);
			InitialPegStatus[one].remove(new Integer(n));
			InitialPegStatus[three].addFirst(n);
			moves(one, three);
			hanoi_three(n - 1, two, one, three);
		}
	}

	public void run() {
		Move();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			try {
				System.out.println("��������" + e.getKeyChar());
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	class Paints extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			int i;
			g.drawRect(80, 450, 150, 5);
			g.drawRect(240, 450, 150, 5);
			g.drawRect(400, 450, 150, 5);
			g.drawRect(560, 450, 150, 5);
			int recty1 = 440;
			g.drawRect(var.rectx1, var.recty - 10, var.rectx2 - var.rectx1, 10);
			// g.drawString(String.valueOf(var.t), var.rectx1+(var.rectx2-var.rectx1)/2,
			// var.recty);
			// System.out.println(var.recty+" "+var.t2+" "+(var.rectx2-var.rectx1));
			for (i = 0; i <= var.t_one; i++)
				g.drawRect(155 - (14 - var.tower_one[i]) * 5, recty1 - 10 * i, (14 - var.tower_one[i]) * 10, 10);
			for (i = 0; i <= var.t_two; i++)
				g.drawRect(315 - (14 - var.tower_two[i]) * 5, recty1 - 10 * i, (14 - var.tower_two[i]) * 10, 10);
			for (i = 0; i <= var.t_three; i++)
				g.drawRect(475 - (14 - var.tower_three[i]) * 5, recty1 - 10 * i, (14 - var.tower_three[i]) * 10, 10);
			for (i = 0; i <= var.t_four; i++)
				g.drawRect(635 - (14 - var.tower_four[i]) * 5, recty1 - 10 * i, (14 - var.tower_four[i]) * 10, 10);
			
		}

	}// �ඨ�����

	public static void main(String args[]) {

		Hanoi4 frame = new Hanoi4();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(820, 550);
		frame.setResizable(false);
		frame.setTitle("��ŵ������");
		frame.setVisible(true);

	}

}