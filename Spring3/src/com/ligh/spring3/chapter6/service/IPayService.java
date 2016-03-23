package com.ligh.spring3.chapter6.service;

public interface IPayService {
	public boolean pay(long userId, long money);
    
    public boolean isEnough(long userId, long money);
    
    public long query(long userId);
}
