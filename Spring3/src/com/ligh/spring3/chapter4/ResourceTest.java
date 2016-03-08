package com.ligh.spring3.chapter4;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;

import junit.framework.Assert;

import org.jboss.vfs.TempFileProvider;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.spi.RealFileSystem;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.VfsResource;

public class ResourceTest {
    
    
    @Test
    public void testByteArrayResource() {
        Resource resource = new ByteArrayResource("Hello World!".getBytes());
        if(resource.exists()) {
            dumpStream(resource);
        }
        //ByteArrayResource可多次读取数组资源，即isOpen ()永远返回false。
        Assert.assertEquals(false, resource.isOpen());
    }
    
    @Test
    public void testInputStreamResource() {
         Resource resource = new InputStreamResource(
        		 new ByteArrayInputStream("Hello World!".getBytes()));
        if(resource.exists()) {
            dumpStream(resource);
        }
        // InputStreamResource代表java.io.InputStream字节流，对于“getInputStream ”
        //操作将直接返回该字节流，因此只能读取一次该字节流，即“isOpen”永远返回true。
        Assert.assertEquals(true, resource.isOpen());
    }
    
    @Test
    public void testFileResource() {
        File file = new File("d:/test.txt");
        Resource resource = new FileSystemResource(file);
        if(resource.exists()) {
            dumpStream(resource);
        }
        if(resource.exists()) {
            dumpStream(resource);
        }
        // FileSystemResource代表java.io.File资源，对于“getInputStream ”
        //操作将返回底层文件的字节流，“isOpen”将永远返回false，从而表示可多次读取底层文件的字节流
        Assert.assertEquals(false, resource.isOpen());
    }
    
    @Test
    public void testClasspathResourceByDefaultClassLoader() throws IOException {
        Resource resource = new ClassPathResource("com/ligh/spring3/chapter4/test1.properties");
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        System.out.println("path:" + resource.getFile().getPath());
        //可多次加载资源
        Assert.assertEquals(false, resource.isOpen());
    }
    
    //使用指定的ClassLoader进行加载资源
    @Test
    public void testClasspathResourceByClassLoader() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        Resource resource = new ClassPathResource("com/ligh/spring3/chapter4/test1.properties" , cl);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
    }
    
    /**
     * 使用指定的类进行加载资源，将尝试加载相对于当前类的路径的资源
     * “resource1”将加载com/ligh/spring3/chapter4/cn/javass/spring/chapter4/test1.properties资源
     * “resource2”将加载“com/ligh/spring3/chapter4/test1.properties”
     * @throws IOException
     */
    @Test
    public void testClasspathResourceByClass() throws IOException {
        Class clazz = this.getClass();
        Resource resource1 = new ClassPathResource("com/ligh/spring3/chapter4/test1.properties" , clazz);
        if(resource1.exists()) {
            dumpStream(resource1);
        }
        System.out.println("path:" + resource1.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource1.isOpen());
        
        Resource resource2 = new ClassPathResource("test1.properties" , this.getClass());
        if(resource2.exists()) {
            dumpStream(resource2);
        }
        System.out.println("path:" + resource2.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource2.isOpen());

    }
    
    /**
     * 加载jar包里的资源，首先在当前类路径下找不到，最后才到Jar包里找，而且在第一个Jar包里找到的将被返回：
     * @throws IOException
     */
    @Test
    public void testClasspathResourceFromJar() throws IOException {
        Resource resource = new ClassPathResource("overview.html");
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getURL().getPath());
        Assert.assertEquals(false, resource.isOpen());

    }

    
    @Test
    public void testUrlResource() throws IOException {
        Resource resource = new UrlResource("file:d:/test.txt");
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getURL().getPath());
        Assert.assertEquals(false, resource.isOpen());

        Resource resource2 = new UrlResource("http://www.springsource.org");
        if(resource2.exists()) {
            dumpStream(resource2);
        }
        System.out.println("path:" + resource2.getURL().getPath());
        Assert.assertEquals(false, resource2.isOpen());

    }

    
    @Test
    public void testVfsResource1() throws IOException {
        VirtualFile virtualFile = VFS.getChild("d:/test.txt");
        Resource resource = new VfsResource(virtualFile);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getURL().getPath());
        Assert.assertEquals(false, resource.isOpen());
        
    }

    @Test
    public void testVfsResourceForRealFileSystem() throws IOException {
        //1.创建一个虚拟路径
        VirtualFile home = VFS.getChild("/home");
        //2.将虚拟目录映射到物理的目录
        VFS.mount(home, new RealFileSystem(new File("d:")));
        //3.通过虚拟目录获取文件资源
        VirtualFile testFile = home.getChild("test.txt");
        //4.通过一致的接口访问
        Resource resource = new VfsResource(testFile);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
        
    }

    @Test
    public void testVfsResourceForJar() throws IOException {
    	//1.首先获取jar包路径
        File realFile = new File("F:/跟我学spring3-源码/spring/lib/org.springframework.beans-3.0.5.RELEASE.jar");
        //2.创建虚拟路径
        VirtualFile home = VFS.getChild("/home2");
        //3.将虚拟目录映射到物理的目录
        VFS.mountZipExpanded(realFile, home, TempFileProvider.create("tmp", Executors.newScheduledThreadPool(1)));
        //4.通过虚拟目录获取文件资源
        VirtualFile testFile = home.getChild("META-INF/spring.handlers");
        
        Resource resource = new VfsResource(testFile);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
        
    }


    /**
     * 通用访问资源的方法，可抽象出来
     * @param resource
     */
    private void dumpStream(Resource resource) {
        InputStream is = null;
        try {
            //1.获取文件资源
            is = resource.getInputStream();
            //2.读取资源
            byte[] descBytes = new byte[is.available()]; 
            is.read(descBytes);
            System.out.println(new String(descBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                
                is.close();
            } catch (IOException e) {
            }
        }
    }
    
}
