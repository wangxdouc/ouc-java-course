package sample.string;

/**
 * 配套讲解String +操作符的源代码
 * 
 * 1. 单步调试<br>
 * 2. 反编译代码：javap -c StringConcatSample
 */
public class StringConcatSample {
	public static void main(String[] args) {
		String s = "Java";
		String a = "Java";
		String ss = "Hello, " + s + ". I love you.";
		System.out.println(ss);
	}
}
