package sample.reflect;

import java.lang.reflect.Array;

public class ReflectHandleArraySample {

	public static void main(String[] args) {
		
		Class<?> componentType = String.class;
		int length = 10;
		
		Array.newInstance(componentType, length);
	}

}
