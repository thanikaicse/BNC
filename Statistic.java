package BNC;
import java.util.*;

class Statistic {
	public String target;
	public Vector<Integer> count;
	public int targetCount;
	private int instnum;
	private double tarprob;
	
	Statistic(String target, int attrnum, int instnum) {
		count = new Vector<Integer>();
		for (int i = 0; i < attrnum; i++) count.addElement(new Integer(0));
		this.target = target;
		targetCount = 1;   //if the target doesn't emerge, the class will not be instantiated, 
						   //otherwise, count from 1
		this.instnum = instnum;
	}
	
	public void increment(int index) {
		int orig = count.elementAt(index);
		count.setElementAt(new Integer(orig + 1), index);
	}
	
	public double getProbability() {
		tarprob = 1.0;
		for (int i = 0; i < count.size(); i++) {
			System.out.print(count.elementAt(i) + " ");
			tarprob = tarprob * ((double)count.elementAt(i) / (double) targetCount);
		}
		System.out.print("targetCount: " + targetCount);
		return tarprob * ((double)targetCount / (double) instnum);
	}
}
