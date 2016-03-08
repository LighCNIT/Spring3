package com.ligh.spring3.chapter4;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

public class ResourceLoaderTest {
    
    @Test
    public void testResourceLoad() {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:com/ligh/spring3/chapter4/test1.txt");
        //验证返回的是ClassPathResource
        Assert.assertEquals(ClassPathResource.class, resource.getClass());
        
        Resource resource2 = loader.getResource("file:com/ligh/spring3/chapter4/test1.txt");
        //验证返回的是ClassPathResource
        Assert.assertEquals(UrlResource.class, resource2.getClass());
        
        Resource resource3 = loader.getResource("com/ligh/spring3/chapter4/test1.txt");
        
        Assert.assertTrue(resource3 instanceof ClassPathResource);
        
    }
}
