package BNC;
import java.io.*;
import java.util.Vector;
import jxl.*;

class NBCFramework {
	private String filename;
	private Vector<Instance> data;
	private Vector<Statistic> sta;
	private int attribute_num;
	
	NBCFramework(String filename) {
		this.filename = filename;
		data = new Vector<Instance>();
		sta = new Vector<Statistic>();
		readXLS();
	}
	
	public void classify(Instance e) {
		Vector<String> target = new Vector<String>();
		String s = new String();
		
		for (int i = 0 ;i < data.size(); i++) {
			s = data.elementAt(i).attribute.lastElement().toString();
			if (!target.contains(s)) {
				target.addElement(s);
				sta.addElement(new Statistic(s, attribute_num - 1, data.size()));
			}else {
				sta.elementAt(target.indexOf(s)).targetCount++;
			}
		}
		String status = new String();
		for (int i = 0; i < data.size(); i++) {
			for (int j =0; j < data.elementAt(0).attribute.size()-1; j++) {
				s = data.elementAt(i).attribute.elementAt(j);
				status = data.elementAt(i).attribute.lastElement();
				if (s.equals(e.attribute.elementAt(j))) {
					sta.elementAt(target.indexOf(status)).increment(j);
				}
			}
		}
		report();
	}
	
	private void report() {
		int max_index = 0;
		double max_prob = 0.0;
		for (int i = 0; i < sta.size(); i++) {
			double temp = sta.elementAt(i).getProbability();
			if (temp > max_prob) {
				max_prob = temp;
				max_index = i;
			}
			System.out.println();
		}
		System.out.print(sta.elementAt(max_index).target);
	}
	
	private void readXLS() {
		Workbook book = null;
		Sheet sheet = null;
		try {
			FileInputStream in = new FileInputStream(filename);
			book = Workbook.getWorkbook(in);
			sheet = book.getSheet(0); // get first sheet.

			Cell cell = null;
			attribute_num =sheet.getColumns();
			for (int i = 1; i < sheet.getRows()-1; i++) {   //caution
				data.addElement(new Instance());
				for (int j = 0; j < attribute_num; j++) {
					cell = sheet.getCell(j, i);
					data.lastElement().attribute.addElement(cell.getContents());	
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sheet != null) {
				sheet = null;
			}
			if (book != null) {
				book = null;
			}
		}
	}
}
