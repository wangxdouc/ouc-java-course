package sample.string;

import java.util.Formatter;

public class FormatterSample {
	
	public static void main(String[] args) {
		Formatter formatter = new Formatter(System.err);
		String id = "2011022";
		String name = "王晓东";
		int age = 38; 
		formatter.format("User information is [%s, %s, %d]", id, name, age);

		formatter.close();
	}
}
