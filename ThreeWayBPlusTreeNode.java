package org.dfpl.lecture.database.assignment2.assignment18011675;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction�� ������ �� �����Ӱ� B+ Tree�� ���� �ȿ��� ������� ��������
	private ThreeWayBPlusTreeNode parent;
	private ArrayList<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children;
	public Object getKeyList;
	
	ThreeWayBPlusTreeNode(){
		keyList = new ArrayList<Integer>();
		children = new ArrayList<>();
		parent = null;
	}
	
	ThreeWayBPlusTreeNode getParent() {
		return parent;
	}
	
	ArrayList<Integer> getKeyList(){
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
	
	public int addKeyList(int e) {
		for (int i = 0; i < keyList.size(); i++) {
			//�̹� Ʈ���� �������
			if (e == keyList.get(i)) {
				return -1;
			}
			if (e < keyList.get(i)) {
				keyList.add(i, e);
				return i;
			}
		}
		int index = keyList.size();
		keyList.add(index, e);
		return index;
	}
}