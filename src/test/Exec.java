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
		String strJarPath = "lib/Menu.jar";
		String strExecPath = "menu.test.Exec";
		String strExecTitlePath = "menu.test.Exec$Title";
		String strFieldPath = "common.Field";
		String strLinePath = "common.Line";
		String strMenuManagerPath = "common.MenuManager";
		String strPrintManagerPath = "common.PrintManager";
		
		menu.test.Exec.main(args);
		
		JarLoader jarLoader = new JarLoader(JarLoader.class.getClassLoader());
		jarLoader.loadJar(strJarPath);
		Class classExec = jarLoader.load(strExecPath);
//		jarLoader.load(strFieldPath);
//		jarLoader.load(strLinePath);
//		jarLoader.load(strMenuManagerPath);
//		jarLoader.load(strPrintManagerPath);
		jarLoader.load(strExecTitlePath);
		
		invokeMenu(classExec);
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("press any key when you ready...");
		scanner.next();
		
		jarLoader = new JarLoader(JarLoader.class.getClassLoader());
		jarLoader.loadJar(strJarPath);
		classExec = jarLoader.load(strExecPath);
		jarLoader.load(strExecTitlePath);
		invokeMenu(classExec);
	}
	
	/*
	private static URLClassLoader getMenuClassLoader() throws Exception
	{
		JarLoader jarLoader = new JarLoader();
		return jarLoader.getClassLoader("./lib/Menu.jar");
	}
	*/
	
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
