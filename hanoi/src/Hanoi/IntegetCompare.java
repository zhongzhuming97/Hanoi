package Hanoi;

import java.util.Comparator;

public class IntegetCompare implements Comparator<Integer> {
	@Override
	public int compare(Integer a, Integer b) {
		return a - b;
	}
}
