package org.dfpl.lecture.database.assignment2.assignment18011675;

import java.util.List;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode parent;
	private PriorityQueue<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children;
	
	ThreeWayBPlusTreeNode(){
		keyList = new PriorityQueue<>();
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

	void setKeyList(PriorityQueue<Integer> keyList) {
		this.keyList = keyList;
	}
	
	void setChildren(List<ThreeWayBPlusTreeNode> children) {
		this.children = children;
	}
}
