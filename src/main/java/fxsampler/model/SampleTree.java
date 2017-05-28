package fxsampler.model;

import fxsampler.Sample;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class SampleTree
{
  private TreeNode root;
  private int count = 0;
  
  public SampleTree(Sample rootSample)
  {
    this.root = new TreeNode(null, null, rootSample);
  }
  
  public TreeNode getRoot()
  {
    return this.root;
  }
  
  public Object size()
  {
    return Integer.valueOf(this.count);
  }
  
  public void addSample(String[] packages, Sample sample)
  {
    if (packages.length == 0)
    {
      this.root.addSample(sample);
      return;
    }
    TreeNode n = this.root;
    for (String packageName : packages) {
      if (n.containsChild(packageName))
      {
        n = n.getChild(packageName);
      }
      else
      {
        TreeNode newNode = new TreeNode(packageName);
        n.addNode(newNode);
        n = newNode;
      }
    }
    if (n.packageName.equals(packages[(packages.length - 1)]))
    {
      n.addSample(sample);
      this.count += 1;
    }
  }
  
  public String toString()
  {
    return this.root.toString();
  }
  
  public static class TreeNode
  {
    private final Sample sample;
    private final String packageName;
    private final TreeNode parent;
    private List<TreeNode> children;
    
    public TreeNode()
    {
      this(null, null, null);
    }
    
    public TreeNode(String packageName)
    {
      this(null, packageName, null);
    }
    
    public TreeNode(TreeNode parent, String packageName, Sample sample)
    {
      this.children = new ArrayList();
      this.sample = sample;
      this.parent = parent;
      this.packageName = packageName;
    }
    
    public boolean containsChild(String packageName)
    {
      if (packageName == null) {
        return false;
      }
      for (TreeNode n : this.children) {
        if (packageName.equals(n.packageName)) {
          return true;
        }
      }
      return false;
    }
    
    public TreeNode getChild(String packageName)
    {
      if (packageName == null) {
        return null;
      }
      for (TreeNode n : this.children) {
        if (packageName.equals(n.packageName)) {
          return n;
        }
      }
      return null;
    }
    
    public void addSample(Sample sample)
    {
      this.children.add(new TreeNode(this, null, sample));
    }
    
    public void addNode(TreeNode n)
    {
      this.children.add(n);
    }
    
    public Sample getSample()
    {
      return this.sample;
    }
    
    public String getPackageName()
    {
      return this.packageName;
    }
    
    public TreeItem<Sample> createTreeItem()
    {
      TreeItem<Sample> treeItem = null;
      if (this.sample != null) {
        treeItem = new TreeItem(this.sample);
      } else if (this.packageName != null) {
        treeItem = new TreeItem(new EmptySample(this.packageName));
      }
      treeItem.setExpanded(true);
      for (TreeNode n : this.children) {
        treeItem.getChildren().add(n.createTreeItem());
         treeItem.getChildren().add(n.createTreeItem());
      }
      return treeItem;
    }
    
    public String toString()
    {
      if (this.sample != null) {
        return " Sample [ sampleName: " + this.sample.getSampleName() + ", children: " + this.children + " ]";
      }
      return " Sample [ packageName: " + this.packageName + ", children: " + this.children + " ]";
    }
  }
}