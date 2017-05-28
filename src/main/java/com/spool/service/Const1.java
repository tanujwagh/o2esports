package com.spool.service;

import java.util.HashMap;
import java.util.Map;

public class Const1
{
  private int coins;
  private double uid;
  private int updated;
  
  public double getUid()
  {
    return this.uid;
  }
  
  public void setUid(double uid)
  {
    this.uid = uid;
  }
  
  public int getUpdated()
  {
    return this.updated;
  }
  
  public void setUpdated(int updated)
  {
    this.updated = updated;
  }
  
  public int getCoins()
  {
    return this.coins;
  }
  
  public void setCoins(int coins)
  {
    this.coins = coins;
  }
  
  private Map<Integer, String> result = new HashMap();
}