package sample.classloader;

import java.io.IOException;
import java.lang.ClassLoader;
import java.net.URL;
import java.util.Enumeration;

/**
 * 类加载器示例
 * 
 * @author xiaodong
 *
 */
public class ClassLoaderSample {

	public static void main(String[] args) throws IOException {

		/*
		 * 取得系统类加载器 系统类加载器的加载路径通常由 CLASSPATH 环境变量指定，如果操作系统没有指定 CLASSPATH
		 * 环境变量，默认以当前􏳳路径作为系统类加载器的加载路径
		 */
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器为 " + systemLoader);
		System.out.println("系统类加载器的加载路径为 ");

		Enumeration<URL> eml = systemLoader.getResources("");
		while (eml.hasMoreElements()) {
			System.out.println(eml.nextElement());
		}

		ClassLoader extensionLoader = systemLoader.getParent();
		System.out.println("扩展类加载器为 " + extensionLoader);
		System.out.println("扩展类加载器的加载路径为 " + System.getProperty("java.ext.dirs"));

		System.out.println("扩展类加载器的Parent为 " + extensionLoader.getParent());
	}
}
