package Hanoi;

public class Var {
	public int t_one = -1, t_two = -1, t_three = -1, t_four = -1; // ��ʾÿ�����ĸ���
	// ��ʾÿ���������Ĳ���
	public int tower_one[] = new int[30], tower_two[] = new int[30], tower_three[] = new int[30],
			tower_four[] = new int[30];
	public int n; // ��������
	public int rectx1, rectx2, recty = 440, rectmid, rectmidt;// ��ʾҪ���ľ�����������
	public int t = 0, t2 = 0;//
	public int tower[] = tower_one;// towerΪָ��Ҫ�ƶ�������ָ��
	public int bool = 1;// ��ͼ����������a���ƶ���b��
	public final int Max = 100;
	public final long INT_MAX = 0xfffffffffffl;
	public long[] m = new long[Max + 1];
	public int[] K = new int[Max + 1];
	public final int delay = 5;
}