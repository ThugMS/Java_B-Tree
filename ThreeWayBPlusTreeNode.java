package org.dfpl.lecture.database.assignment2.assignment18011675;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction�� ������ �� �����Ӱ� B+ Tree�� ���� �ȿ��� ������� ��������
	private ThreeWayBPlusTreeNode parent;
	private PriorityQueue<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children;
	
	ThreeWayBPlusTreeNode(){
		keyList = new PriorityQueue<>();
		children = new ArrayList<>();
	}
	
	ThreeWayBPlusTreeNode getParent() {
		return parent;
	}
	
	PriorityQueue<Integer> getKeyList(){
		return keyList;
	}
	
	List<ThreeWayBPlusTreeNode> getChildren(){
		return children;
	}
	
	void setParent(ThreeWayBPlusTreeNode parent) {
		this.parent = parent;
	}

	void setKeyList(int e) {
		this.keyList.add(e);
	}
	
	void setChildren(ThreeWayBPlusTreeNode left, ThreeWayBPlusTreeNode right) {
		this.children.add(left);
		this.children.add(right);
	}
}