package com.ligh.spring3.chapter3.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResourceBean {
    
    private FileOutputStream fos;
    
    private File file;
    
    //初始化方法
    public void init() {
        System.out.println("ResourceBean:========初始化");
        
        System.out.println("ResourceBean:========加载资源，执行一些预操作");
        try {
            this.fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    public void destroy() {
        System.out.println("ResourceBean:=======销毁");
        
        System.out.println("ResourceBean:========释放资源");
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public FileOutputStream getFos() {
        return fos;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
}
