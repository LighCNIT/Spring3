package com.ligh.spring3.chapter3;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class DependencyCheckTest {
    
    @Test(expected = UnsatisfiedDependencyException.class)
    public void testDependencyCheckByObject() throws IOException {
        
        new ClassPathXmlApplicationContext("chapter3/dependency-check-object.xml");
    }

    @Test(expected = UnsatisfiedDependencyException.class)
    public void testDependencyCheckBySimple() throws IOException {
        
        new ClassPathXmlApplicationContext("chapter3/dependency-check-simple.xml");
    }
   
    @Test(expected = UnsatisfiedDependencyException.class)
    public void testDependencyCheckByAll() throws IOException {
        
        new ClassPathXmlApplicationContext("chapter3/dependency-check-all.xml");
    }
    
    @Test
    public void testDependencyCheckByNone() throws IOException {
        
        new ClassPathXmlApplicationContext("chapter3/dependency-check-none.xml");
    }
    
}

