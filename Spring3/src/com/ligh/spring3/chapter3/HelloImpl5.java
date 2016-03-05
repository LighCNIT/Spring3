package com.ligh.spring3.chapter3;


import com.ligh.spring3.chapter2.helloworld.HelloApi;
import com.ligh.spring3.chapter3.bean.Printer;

public abstract class HelloImpl5 implements HelloApi {
    
    private Printer printer;
    
    @Override
    public void sayHello() {
        printer.print("setter");
        createPrototypePrinter().print("prototype");
        createSingletonPrinter().print("singleton");
    }
    
    public abstract Printer createPrototypePrinter();
        

    public Printer createSingletonPrinter() {
        System.out.println("");
        return new Printer();
    }
    
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
