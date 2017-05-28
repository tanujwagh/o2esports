package com.spool.model;

import java.util.HashSet;
import java.util.Set;

public class Level
{
  private int level1;
  private int level2;
  private int level3;
  private int level4;
  private int level5;
  private int level6;
  private int level7;
  private int level8;
  private int level9;
  private int level10;
  
  public int getlevel1()
  {
    return this.level1;
  }
  
  public void setlevel1(int level1)
  {
    this.level1 = level1;
  }
  
  public int getlevel2()
  {
    return this.level2;
  }
  
  public void setlevel2(int level2)
  {
    this.level2 = level2;
  }
  
  public int getlevel3()
  {
    return this.level3;
  }
  
  public void setlevel3(int level3)
  {
    this.level3 = level3;
  }
  
  public int getlevel4()
  {
    return this.level4;
  }
  
  public void setlevel4(int level4)
  {
    this.level4 = level4;
  }
  
  public int getlevel5()
  {
    return this.level5;
  }
  
  public void setlevel5(int level5)
  {
    this.level5 = level5;
  }
  
  public int getlevel6()
  {
    return this.level6;
  }
  
  public void setlevel6(int level6)
  {
    this.level6 = level6;
  }
  
  public int getlevel7()
  {
    return this.level7;
  }
  
  public void setlevel7(int level7)
  {
    this.level7 = level7;
  }
  
  public int getlevel8()
  {
    return this.level8;
  }
  
  public void setlevel8(int level8)
  {
    this.level8 = level8;
  }
  
  public int getlevel9()
  {
    return this.level9;
  }
  
  public void setlevel9(int level9)
  {
    this.level9 = level9;
  }
  
  public int getlevel10()
  {
    return this.level10;
  }
  
  public void setlevel10(int level10)
  {
    this.level10 = level10;
  }
  
  public Set<Integer> getAlllevel()
  {
    HashSet<Integer> set = new HashSet();
    set.add(Integer.valueOf(this.level1));
    set.add(Integer.valueOf(this.level2));
    set.add(Integer.valueOf(this.level3));
    set.add(Integer.valueOf(this.level4));
    set.add(Integer.valueOf(this.level5));
    set.add(Integer.valueOf(this.level6));
    set.add(Integer.valueOf(this.level7));
    set.add(Integer.valueOf(this.level8));
    set.add(Integer.valueOf(this.level9));
    set.add(Integer.valueOf(this.level10));
    return set;
  }
}


/* Location:           C:\Users\PROCORNER EDUFLEX 60\Desktop\new project\com\spool\model.zip
 * Qualified Name:     model.Level
 * JD-Core Version:    0.7.0.1
 */