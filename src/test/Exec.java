package test;

import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Scanner;

import common.JarLoader;

public class Exec {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		URLClassLoader urlClassLoader = getMenuClassLoader();
		invokeMenu(loadMenu(urlClassLoader));
		urlClassLoader.close();
		Scanner scanner = new Scanner(System.in);
		System.out.println("press any key when you ready...");
		scanner.next();
		
		invokeMenu(loadMenu(urlClassLoader));
	}
	
	private static URLClassLoader getMenuClassLoader() throws Exception
	{
		JarLoader jarLoader = new JarLoader();
		return jarLoader.getClassLoader("./lib/Menu.jar");
	}
	
	private static Class loadMenu(URLClassLoader urlClassLoader) throws Exception
	{
//		JarLoader jarLoader = new JarLoader();
//		urlClassLoader = jarLoader.getClassLoader("./lib/Menu.jar");
		return urlClassLoader.loadClass("menu.test.Exec");
//		for(Class classArg : klass.getDeclaredMethod("main", String[].class).getParameterTypes())
//		{
//			System.out.println(classArg.toString());
//		}
		
		
	}
	
	private static void invokeMenu(Class classMenu) throws Exception
	{
		classMenu.getMethod("main", String[].class).invoke(null, new Object[]{new String[0]});
	}
}
