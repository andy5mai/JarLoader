package test;

import java.lang.reflect.Method;
import java.net.URLClassLoader;

import common.JarLoader;

public class Exec {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		JarLoader jarLoader = new JarLoader();
		URLClassLoader urlClassLoader = jarLoader.getClassLoader("./lib/Menu.jar");
		Class klass = urlClassLoader.loadClass("menu.test.Exec");
		for(Class classArg : klass.getDeclaredMethod("main", String[].class).getParameterTypes())
		{
			System.out.println(classArg.toString());
		}
		
		klass.getMethod("main", String[].class).invoke(null, new Object[]{new String[0]});
	}

}
