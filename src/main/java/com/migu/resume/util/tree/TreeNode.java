package com.migu.resume.util.tree;

import java.util.List;
import java.util.Map;

/**
 * treeNode model
 * @version: V1.0
 */
public class TreeNode {
    //主键
    private String pkId;
	//节点id
	private String id; 
	//父节点id
	private String parentId; 
	//文本
	private String text;	
	//节点状态
	private String state = "open";
	//是否选中
	private Boolean checked = false;
	//子节点集合
	private List<TreeNode> children;
	//自定义属性
	private Map<String, Object> attributes;
	
	public TreeNode(){}
	
	public TreeNode(String parentId,String id,String text){
		this.id = id;
		this.parentId = parentId;
		this.text = text;
	}
	
	public TreeNode(String pkId, String id, String parentId, String text) {
	    this.pkId = pkId;
	    this.id = id;
        this.parentId = parentId;
        this.text = text;
	}
		
	public TreeNode(String id,String parentId,String text,String state, Boolean checked){
		this.id = id;
		this.parentId = parentId;
		this.text = text;
		this.state = state;
		this.checked = checked;
	}
		
		
	//get set
	public String getId() {
		return id;
	}
	public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
}
