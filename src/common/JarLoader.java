package common;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoader
{
	private File file;
	private URL[] urls;
	
	public JarLoader()
	{
		this.urls = new URL[1];
	}
	
	public URLClassLoader getClassLoader(String strJarPath) throws Exception
	{
		this.file = new File(strJarPath);
		if (!this.file.exists()) return null;
		
		System.out.println("load file success!");
		this.urls[0] = this.file.toURI().toURL();
		return new URLClassLoader(this.urls);
	}
}
