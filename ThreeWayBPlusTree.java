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

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode root;
	private LinkedList<ThreeWayBPlusTreeNode> leafList;

	/**
	 * 과제 Assignment4를 위한 메소드:
	 * 
	 * key로 검색하면 root부터 시작하여, key를 포함할 수 있는 leaf node를 찾고 key가 실제로 존재하면 해당 Node를
	 * 반환하고, 그렇지 않다면 null을 반환한다. 중간과정을 System.out.println(String) 으로 출력해야 함. 3 way
	 * B+ tree에서 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
	 * add되었다고 했을 때,
	 * 
	 * 예: getNode(15)을 수행하였을 때 
	 * > start finding 15
	 * > larger than or equal to 9
	 * > larger than or equal to 13
	 * > larger than or equal to 15
	 * > less than 16
	 * > 15 found 
	 * 위의 6 문장을
	 * 콘솔에 출력하고 15를 포함한 ThreeWayBPlusTreeNode를 반환함
	 * 
	 * @param key
	 * @return
	 */
	
	public ThreeWayBPlusTree() {
		root = new ThreeWayBPlusTreeNode();
		leafList = new LinkedList();
		return;
	}
	
	public void show(ThreeWayBPlusTreeNode sub, int cnt) {
		if(sub == null)
			return;
		cnt++;
		System.out.println(cnt + " " + sub.getKeyList());
		for(int i=0;i<sub.getChildren().size();i++) {
			show(sub.getChildren().get(i), cnt);
		}
	}
	
	public void getNode(Integer a) {
		ThreeWayBPlusTreeNode sub = root;
		System.out.println("start finding " + a);
		while(!sub.getChildren().isEmpty()) {
			List<ThreeWayBPlusTreeNode> child = sub.getChildren();
			if(sub.getKeyList().size() == 2) { //자식이 3개일때
				if(a>=sub.getKeyList().get(1)) {
					System.out.println("larger than or equal to " + sub.getKeyList().get(1));
					sub = child.get(2); //가장오른쪽 가기
				}
				else if(a>=sub.getKeyList().get(0)) {
					System.out.println("larger than or equal to " + sub.getKeyList().get(0));
					sub = child.get(1); //가운데 자식으로 가기
				}
				else {
					System.out.println("less than " + sub.getKeyList().get(0));
					sub = child.get(0); //가장 왼쪽 자식
				}
			}
			else {
				if(a>=sub.getKeyList().get(0)) { //자식이 2개일때
					System.out.println("larger than or equal to " + sub.getKeyList().get(0));
					sub = child.get(1); //오른쪽 자식
				}
				else {
					System.out.println("less than " + sub.getKeyList().get(0));
					sub = child.get(0); //왼쪽 자식
				}
			}
		}
		int tmp;
		if(sub.getKeyList().size() == 2) {
			if(sub.getKeyList().get(0) == a) {
				System.out.println(a +" found");
			}
			else if(sub.getKeyList().get(1) == a) {
				System.out.println(a +" found");
			}
			else {
				if(a < sub.getKeyList().get(0)) {
					System.out.println("less than " + sub.getKeyList().get(0));
				}
				else {
					System.out.println("larger than " + sub.getKeyList().get(0));
				}
				System.out.println(a +" not found");
			}
		}
		else {
			if(sub.getKeyList().get(0) == a) {
				System.out.println(a +" found");
			}
			else {
				if(a < sub.getKeyList().get(0)) {
					System.out.println("less than " + sub.getKeyList().get(0));
				}
				else {
					System.out.println("larger than " + sub.getKeyList().get(0));
				}
				System.out.println(a +" not found");
			}
		}
	}
	
	/**
	 * 과제 Assignment4를 위한 메소드: 
	 * 
	 * inorder traversal을 수행하여, 값을 오름차순으로 출력한다. 
	 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
	 * add되었다고 했을 때,
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
	 * 위와 같이 출력되어야 함. 
	 * Note: Bottom의 LinkedList 순회를 하면 안됨
	 */
	public void inorderTraverse() {
		inorder(root);
	}
	
	public void inorder(ThreeWayBPlusTreeNode sub) {
		if(sub == null)
			return;
		//중위순회를 하기위해서 나 보다 왼쪽에 있는 자식들에 가기위해 아래의 자식이 나보다 작은 값일때만 먼저 호출
		for(int i=0;i<sub.getChildren().size();i++) {
			if(sub.getChildren().get(i).getKeyList().get(0) < sub.getKeyList().get(0)) {
				inorder(sub.getChildren().get(i));
			}
		}
		//나보다 왼쪾에 있는 자식들은 모두 방문했으니 나의 값을 호출
		if(sub.getChildren().isEmpty()) {
			for(int i=0;i<sub.getKeyList().size();i++) {
				System.out.println(sub.getKeyList().get(i));
			}
		}
		//나보다 오른쪽에 있는 자식들에 방문하기 위해 호출
		for(int i=0;i<sub.getChildren().size();i++) {
			if(sub.getChildren().get(i).getKeyList().get(0) >= sub.getKeyList().get(0)) {
				inorder(sub.getChildren().get(i));
			}
		}
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
	public ThreeWayBPlusTreeNode search(int a) {
		ThreeWayBPlusTreeNode sub = root;
		
		while(!sub.getChildren().isEmpty()) {
			List<ThreeWayBPlusTreeNode> child = sub.getChildren();
			if(sub.getKeyList().size() == 2) { //자식이 3개일때
				if(a>=sub.getKeyList().get(1)) {
					sub = child.get(2); //가장오른쪽 가기
				}
				else if(a>=sub.getKeyList().get(0)) {
					sub = child.get(1); //가운데 자식으로 가기
				}
				else {
					sub = child.get(0); //가장 왼쪽 자식
				}
			}
			else{
				if(a>=sub.getKeyList().get(0)) { //자식이 2개일때
					sub = child.get(1); //오른쪽 자식
				}
				else {
					sub = child.get(0); //왼쪽 자식
				}
			}
		}
		
		return sub;
	}
	public void parent_switching(ThreeWayBPlusTreeNode sub) {
		if(sub.getParent() == null) {
			ThreeWayBPlusTreeNode parent = new ThreeWayBPlusTreeNode();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //자식들 생성
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //자식들 부모설정
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key값들 할당하기
			parent.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(2));
			
			left.setChildren(sub.getChildren().get(0), sub.getChildren().get(1));
			sub.getChildren().get(0).setParent(left);
			sub.getChildren().get(1).setParent(left);
			right.setChildren(sub.getChildren().get(2), sub.getChildren().get(3));
			sub.getChildren().get(2).setParent(right);
			sub.getChildren().get(3).setParent(right);
			
			parent.setChildren(left, right);
			
			root = parent;
		}
		else {
			ThreeWayBPlusTreeNode parent = sub.getParent();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //자식들 생성
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //자식들 부모설정
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key값들 할당하기
			parent.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(2));
			
			left.setChildren(sub.getChildren().get(0), sub.getChildren().get(1));
			sub.getChildren().get(0).setParent(left);
			sub.getChildren().get(1).setParent(left);
			
			right.setChildren(sub.getChildren().get(2), sub.getChildren().get(3));
			sub.getChildren().get(2).setParent(right);
			sub.getChildren().get(3).setParent(right);
			
			for(int i=0;i<parent.getChildren().size();i++) {
				if(parent.getChildren().get(i) == sub) {
					parent.getChildren().remove(i);
					break;
				}
			}
			
			parent.setChildren(left, right);
			//부모의 자식들을 뒤에 넣었으니 순서 조정
			parent.getChildren().sort(new childrenComparator());
			
			if(parent.getKeyList().size()>=3) {
				parent_switching(parent);
			}
		}
	}
	
	class childrenComparator implements Comparator<ThreeWayBPlusTreeNode>{

		@Override
		public int compare(ThreeWayBPlusTreeNode o1, ThreeWayBPlusTreeNode o2) {
			if(o1.getKeyList().get(0) > o2.getKeyList().get(0)) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
	}
	
	public void add_switching(ThreeWayBPlusTreeNode sub) {
		if(sub.getParent() == null) {//부모가 없을시
			//부모생성
			ThreeWayBPlusTreeNode parent = new ThreeWayBPlusTreeNode();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //자식들 생성
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //자식들 부모설정
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key값들 할당하기
			parent.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(2));
			
			//자식설정
			parent.setChildren(left, right);
			
			root = parent;
		}
		else {
			ThreeWayBPlusTreeNode parent = sub.getParent();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //자식들 생성
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //자식들 부모설정
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key값들 할당하기
			parent.setKeyList(sub.getKeyList().get(1));
			parent.getKeyList().sort(Comparator.naturalOrder());
			
			right.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(2));
			
			for(int i=0;i<parent.getChildren().size();i++) {
				if(parent.getChildren().get(i) == sub) {
//					System.out.println("remove" + parent.getChildren().get(i).getKeyList());
					parent.getChildren().remove(i);
					
					break;
				}
			}
			
			parent.setChildren(left, right);
			
			
			if(parent.getKeyList().size()>=3) {
				parent_switching(parent);
			}
			
//			for(int i=0;i<parent.getChildren().size();i++) {
//				System.out.println("add" + parent.getChildren().get(i).getKeyList());
//			}
		}
	}
	@Override
	public boolean add(Integer e) {
		ThreeWayBPlusTreeNode sub = root;
		
		if(sub.getKeyList().isEmpty()) {
			sub.getKeyList().add(e);
		}
		else {
			sub = search(e);
			sub.setKeyList(e);
			sub.getKeyList().sort(Comparator.naturalOrder());
			
			if(sub.getKeyList().size()>=3) {
				add_switching(sub);
			}
		}
		
//		show(root, 0);
//		System.out.println(" ");
		
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