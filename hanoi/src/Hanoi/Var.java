package Hanoi;

public class Var {
	public int t_one = -1, t_two = -1, t_three = -1, t_four = -1; // 表示每层塔的个数
	// 表示每个塔有塔的层数
	public int tower_one[] = new int[30], tower_two[] = new int[30], tower_three[] = new int[30],
			tower_four[] = new int[30];
	public int n; // 塔的数量
	public int rectx1, rectx2, recty = 440, rectmid, rectmidt;// 表示要画的矩形左右坐标
	public int t = 0, t2 = 0;//
	public int tower[] = tower_one;// tower为指向要移动的塔的指针
	public int bool = 1;// 画图函数，画出a塔移动到b塔
	public final int Max = 100;
	public final long INT_MAX = 0xfffffffffffl;
	public long[] m = new long[Max + 1];
	public int[] K = new int[Max + 1];
	public final int delay = 5;
}