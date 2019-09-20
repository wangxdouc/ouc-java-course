package sample.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

/**
 * 通过反射构建JFrame测试窗口
 * 
 * @author xiaodong
 *
 */
public class ReflectSample {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> clazz = Class.forName("javax.swing.JFrame");
		Constructor<?> ctor = clazz.getConstructor(String.class);
		Object obj = ctor.newInstance("测试JFrame");
		JFrame jf = (JFrame) obj;
		jf.setSize(200, 200);
		jf.setVisible(true);
	}

}
