package BNC;

public class Naive_Bayesian_Classifier {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NBCFramework nbc = new NBCFramework("data\\data.xls");
		Instance test = new Instance();
		test.attribute.addElement("Rain");
		test.attribute.addElement("Cool");
		test.attribute.addElement("High");
		test.attribute.addElement("One");
		test.attribute.addElement("Strong");
		nbc.classify(test);
	}
}
