package com.ligh.spring3.chapter4;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.spi.RealFileSystem;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResourcePatternTest {
    
    @Test
    public void testClasspathPrefix() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        //ֻ����һ������ƥ��Resource����ͨ��ResourceLoader.getResource���м��� 
        Resource[] resources = resolver.getResources("classpath:META-INF/INDEX.LIST");
        Assert.assertEquals(1, resources.length);

        ////ֻ����һ��ƥ���Resource����ͨ��ResourceLoader.getResource���м���
        resources = resolver.getResources("classpath:META-INF/*.LIST");
        Assert.assertTrue(resources.length == 1);
        

        
        resources = resolver.getResources("classpath:META-INF/MANIFEST.MF");
        Assert.assertEquals(1, resources.length);
    }
    

    @Test
    public void testClasspathAsteriskPrefix() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        //�����ض������ƥ�������Resource  
        //������ͨ��ClassLoader.getResources("META-INF")���ط�ģʽ·������  
        //Ȼ����б���ģʽƥ��
        Resource[] resources = resolver.getResources("classpath*:META-INF/INDEX.LIST");
        Assert.assertTrue(resources.length > 1);
        
        
        resources = resolver.getResources("classpath*:META-INF/*.LIST");
        Assert.assertTrue(resources.length > 1);

        
    }

    @Test
    public void testClasspathAsteriskPrefixLimit() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //������ͨ��ClassLoader.getResources("")����Ŀ¼��  
        //����nameΪ�����������ֻ�����ļ�ϵͳ����·��
        //��ֻ�����ļ�ϵͳ����·��������jar�ĸ�·��  
        //Ȼ����б���ģʽƥ��         
        Resource[] resources = resolver.getResources("classpath*:asm-*.txt");
        Assert.assertTrue(resources.length == 0);
     	//��ͨ��ClassLoader.getResources("asm-license.txt")����  
        //asm-license.txt������com.springsource.net.sf.cglib-2.2.0.jar         
        resources = resolver.getResources("classpath*:asm-license.txt");
        Assert.assertTrue(resources.length > 0);
        
        
        resources = resolver.getResources("classpath*:LICENS*");
        Assert.assertTrue(resources.length == 1);
    }

    @Test
    public void testFilekPrefix() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("file:D:/*.txt");
        Assert.assertTrue(resources.length > 0);
    }

    @Test
    public void testVfsPrefix() throws IOException {
        
        VirtualFile home = VFS.getChild("/home");
        
        VFS.mount(home, new RealFileSystem(new File("d:")));
        
        VirtualFile testFile = home.getChild("test.txt");
        
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("/home/test.txt");
        Assert.assertTrue(resources.length > 0);
        System.out.println(resources[0].getClass());
        
    }
    
}
