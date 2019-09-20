package sample.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 键盘输入类
 * @author xiaodong
 *
 */
public class KeyboardInput {

	/**
	 * 从键盘获得输入的方式1，使用流对象
	 * 
	 * @throws IOException
	 */
	public static void InputFromKeyboardM1() throws IOException {
		int num1, num2;
		String str1, str2;
		InputStreamReader in;
		in = new InputStreamReader(System.in);
		BufferedReader buf;
		buf = new BufferedReader(in);
		System.out.print("请输入第一个数:");
		str1 = buf.readLine(); // 将输入的内容􏰅值􏰆字符串变量 str1
		num1 = Integer.parseInt(str1); // 将 str1 转成 int 类型后􏰅􏰆 num1
		System.out.print("请输入第二个数:");
		str2 = buf.readLine(); // 将输入的内容􏰅值􏰆字符串变量 str2
		num2 = Integer.parseInt(str2); // 将 str2 转成 int 类型后􏰅􏰆 num2
		System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
	}

	/**
	 * 从键盘获得输入的方式2，使用Scanner
	 */
	public static void InputFromKeyboardM2() {
		System.out.print("请输入double样式字符串:");
		Scanner reader = new Scanner(System.in);
		double num;
		num = reader.nextDouble(); // 按照 double 类型􏰇取键盘输入
		System.out.println(num);

		System.out.print("请输入字符串:");
		String str;
		str = reader.next(); // 按照 double 类型􏰇取键盘输入
		System.out.println(str);
		reader.close();
	}

	public static void main(String[] args) throws IOException {
		// 调用方法 ...
		KeyboardInput.InputFromKeyboardM1();
		KeyboardInput.InputFromKeyboardM2();
	}
}