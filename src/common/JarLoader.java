package common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.jar.JarFile;

public class JarLoader extends ClassLoader
{
	private File file;
	private URL url;
	
	public JarLoader(ClassLoader classLoaderParent)
	{
		super(classLoaderParent);
	}
	
	public String loadJar(String strJarPath) throws Exception
	{
		this.file = new File(strJarPath);
		if (!this.file.exists()) return "load jar failed!";
		
		//JarFile jarFile = new JarFile(strJarPath);
		
		this.url = this.file.toURI().toURL();
		System.out.println("load jar url : " + this.url);
		return null;
	}
	
	public Class load(String strClassName) throws Exception
	{
		String strClassPath = strClassName.replace('.', '/');
		URL url = new URL("jar:" + this.url + "!/" + strClassPath + ".class");
		URLConnection urlConnection = url.openConnection();
		InputStream input = urlConnection.getInputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		int nReadData = 0;
		while((nReadData = input.read()) != -1)
		{
			byteArrayOutputStream.write(nReadData);
		}
		
		input.close();
		
		byte[] bsClassData = byteArrayOutputStream.toByteArray();
		
		Class classLoad = this.defineClass(strClassName, bsClassData, 0, bsClassData.length);
		return classLoad;
	}
}
