package com.ligh.spring3.chapter3;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

//æ≤Ã¨π§≥ß¿‡
public class DependencyInjectByStaticFactory {
	
	public static HelloApi newInstance(String message, int index) {
		return new HelloImpl3(message, index);
	}
	
}
