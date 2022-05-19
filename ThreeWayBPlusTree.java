package org.dfpl.lecture.database.assignment2.assignment18011675;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;

@SuppressWarnings("unused")
public class ThreeWayBPlusTree implements NavigableSet<Integer> {

	// Data Abstraction�� ������ �� �����Ӱ� B+ Tree�� ���� �ȿ��� ������� ��������
	private ThreeWayBPlusTreeNode root;
	private LinkedList<ThreeWayBPlusTreeNode> leafList;

	/**
	 * ���� Assignment4�� ���� �޼ҵ�:
	 * 
	 * key�� �˻��ϸ� root���� �����Ͽ�, key�� ������ �� �ִ� leaf node�� ã�� key�� ������ �����ϸ� �ش� Node��
	 * ��ȯ�ϰ�, �׷��� �ʴٸ� null�� ��ȯ�Ѵ�. �߰������� System.out.println(String) ���� ����ؾ� ��. 3 way
	 * B+ tree���� 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 �� �������
	 * add�Ǿ��ٰ� ���� ��,
	 * 
	 * ��: getNode(15)�� �����Ͽ��� �� 
	 * > start finding 15
	 * > larger than or equal to 9
	 * > larger than or equal to 13
	 * > larger than or equal to 15
	 * > less than 16
	 * > 15 found 
	 * ���� 6 ������
	 * �ֿܼ� ����ϰ� 15�� ������ ThreeWayBPlusTreeNode�� ��ȯ��
	 * 
	 * @param key
	 * @return
	 */
	
	public ThreeWayBPlusTree() {
		root = new ThreeWayBPlusTreeNode();
		leafList = new LinkedList();
		return;
	}
	
	public ThreeWayBPlusTreeNode getNode(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * ���� Assignment4�� ���� �޼ҵ�: 
	 * 
	 * inorder traversal�� �����Ͽ�, ���� ������������ ����Ѵ�. 
	 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 �� �������
	 * add�Ǿ��ٰ� ���� ��,
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 * 7
	 * 8
	 * 9
	 * 10
	 * 11
	 * 12
	 * 13
	 * 14
	 * 15
	 * 16
	 * 17
	 * ���� ���� ��µǾ�� ��. 
	 * Note: Bottom�� LinkedList ��ȸ�� �ϸ� �ȵ�
	 */
	public void inorderTraverse() {
		// TODO Auto-generated method stub
	}

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	public void add_switching(ThreeWayBPlusTreeNode sub) {
		if(sub.getParent() == null) {//�θ� ������
			//�θ����
			ThreeWayBPlusTreeNode parent = new ThreeWayBPlusTreeNode();
			sub.setParent(parent);
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode();
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //�ڽĵ� �θ���
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().poll()); //key���� �Ҵ��ϱ�
			parent.setKeyList(sub.getKeyList().peek());
			right.setKeyList(sub.getKeyList().poll());
			right.setKeyList(sub.getKeyList().poll());
			
			//�ڽļ���
			parent.setChildren(left, right);
			
			root = parent;
		}
		else {
			
			
		}
	}
	@Override
	public boolean add(Integer e) {
		ThreeWayBPlusTreeNode sub = root;
		if(sub.getKeyList().isEmpty()) {
			sub.getKeyList().add(e);
		}
		else {
			while(!sub.getChildren().isEmpty()) {
				List<ThreeWayBPlusTreeNode> child = sub.getChildren();
				int a = child.get(1).getKeyList().peek();
				
				if(e >= a) {
					sub = child.get(1);
				}
				else {
					sub = child.get(0);
				}
			}
			sub.setKeyList(e);
			if(sub.getKeyList().size()>=3) {
				add_switching(sub);
			}
		}
		
		System.out.println(root.getKeyList());
		
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

}