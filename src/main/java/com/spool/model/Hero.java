package com.spool.model;

import java.util.HashSet;
import java.util.Set;

public class Hero
{
  private int hero1;
  private int hero2;
  private int hero3;
  private int hero4;
  private int hero5;
  private int hero6;
  private int hero7;
  private int hero8;
  private int hero9;
  private int hero10;
  
  public int getHero1()
  {
    return this.hero1;
  }
  
  public void setHero1(int hero1)
  {
    this.hero1 = hero1;
  }
  
  public int getHero2()
  {
    return this.hero2;
  }
  
  public void setHero2(int hero2)
  {
    this.hero2 = hero2;
  }
  
  public int getHero3()
  {
    return this.hero3;
  }
  
  public void setHero3(int hero3)
  {
    this.hero3 = hero3;
  }
  
  public int getHero4()
  {
    return this.hero4;
  }
  
  public void setHero4(int hero4)
  {
    this.hero4 = hero4;
  }
  
  public int getHero5()
  {
    return this.hero5;
  }
  
  public void setHero5(int hero5)
  {
    this.hero5 = hero5;
  }
  
  public int getHero6()
  {
    return this.hero6;
  }
  
  public void setHero6(int hero6)
  {
    this.hero6 = hero6;
  }
  
  public int getHero7()
  {
    return this.hero7;
  }
  
  public void setHero7(int hero7)
  {
    this.hero7 = hero7;
  }
  
  public int getHero8()
  {
    return this.hero8;
  }
  
  public void setHero8(int hero8)
  {
    this.hero8 = hero8;
  }
  
  public int getHero9()
  {
    return this.hero9;
  }
  
  public void setHero9(int hero9)
  {
    this.hero9 = hero9;
  }
  
  public int getHero10()
  {
    return this.hero10;
  }
  
  public void setHero10(int hero10)
  {
    this.hero10 = hero10;
  }
  
  public Set<Integer> getAllHero()
  {
    HashSet<Integer> set = new HashSet();
    set.add(Integer.valueOf(this.hero1));
    set.add(Integer.valueOf(this.hero2));
    set.add(Integer.valueOf(this.hero3));
    set.add(Integer.valueOf(this.hero4));
    set.add(Integer.valueOf(this.hero5));
    set.add(Integer.valueOf(this.hero6));
    set.add(Integer.valueOf(this.hero7));
    set.add(Integer.valueOf(this.hero8));
    set.add(Integer.valueOf(this.hero9));
    set.add(Integer.valueOf(this.hero10));
    return set;
  }
}


/* Location:           C:\Users\PROCORNER EDUFLEX 60\Desktop\new project\com\spool\model.zip
 * Qualified Name:     model.Hero
 * JD-Core Version:    0.7.0.1
 */