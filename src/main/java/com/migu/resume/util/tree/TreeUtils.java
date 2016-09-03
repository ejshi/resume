package com.migu.resume.util.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.migu.resume.util.StringUtils;

/**
 * 树转换工具类
 * @version: V1.0
 */
public class TreeUtils {
	
	/**
	 * list<TreeNode>转换成tree.
	 * @param treeNodeList
	 * @return  树形展示tree {"id":"", "pId":"" children:[...]}
	 * @throws IOException
	 */
	public static List<TreeNode> getTree(List<TreeNode> treeNodeList) throws IOException{
		List<TreeNode> pNodeList = new ArrayList<TreeNode>();  //存放父节点的list
		
		for(TreeNode node1 : treeNodeList){  
		      boolean isChildMark = false;  //标志该节点是否是子节点，isChildMark为false表示不是子节点
		      for(TreeNode node2 : treeNodeList){  
		          if(StringUtils.isNotBlank(node1.getParentId()) && node1.getParentId().equals(node2.getId())){  
		        	  isChildMark = true;  
		              if(node2.getChildren() == null){
		            	  node2.setChildren(new ArrayList<TreeNode>()); 
		              }
		              node2.getChildren().add(node1); 
		              //node2.setState("closed");
		              break;  
		          } 
		      }  
		      if(!isChildMark){  
		          pNodeList.add(node1);   
		      }  
		  }
		
		return pNodeList;
	}
	
}
