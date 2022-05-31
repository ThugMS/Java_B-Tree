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
	
	public LinkedList<ThreeWayBPlusTreeNode> get_LeafList(){
		return this.leafList;
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
//		ThreeWayBPlusTreeNode sub = root;
//		while(!sub.getChildren().isEmpty()) {
//			sub = sub.getChildren().get(0);
//		}
//		
//		return sub.getKeyList().get(0);
		return leafList.getFirst().getKeyList().get(0);
	}

	@Override
	public Integer last() {
//		ThreeWayBPlusTreeNode sub = root;
//		while(!sub.getChildren().isEmpty()) {
//			sub = sub.getChildren().get(sub.getChildren().size()-1);
//		}
//		
//		return sub.getKeyList().get(sub.getKeyList().size()-1);
		
		return leafList.getLast().getKeyList().get(leafList.getLast().getKeyList().size()-1);
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
	
	public boolean checking(int a) {
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
		boolean check = true;
		
		for(int i=0;i<sub.getKeyList().size();i++) {
			if(a == sub.getKeyList().get(i))
				check = false;
		}
		
		return check;
		
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
			parent.getKeyList().sort(Comparator.naturalOrder());
			
			right.setKeyList(sub.getKeyList().get(2));
			
			sub.getChildren().sort(new childrenComparator());
			
			left.setChildren(sub.getChildren().get(0), sub.getChildren().get(1));
			sub.getChildren().get(0).setParent(left);
			sub.getChildren().get(1).setParent(left);
			right.setChildren(sub.getChildren().get(2), sub.getChildren().get(3));
			sub.getChildren().get(2).setParent(right);
			sub.getChildren().get(3).setParent(right);
			
			parent.setChildren(left, right);
			parent.getChildren().sort(new childrenComparator());
			
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
			
			right.setKeyList(sub.getKeyList().get(2));
			
			sub.getChildren().sort(new childrenComparator());
			
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
			
			int index = leafList.indexOf(sub);
			leafList.remove(index);
			
			left.setParent(parent); //자식들 부모설정
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key값들 할당하기
			parent.setKeyList(sub.getKeyList().get(1));
			parent.getKeyList().sort(Comparator.naturalOrder());
			
			right.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(2));
			
			//자식설정
			parent.setChildren(left, right);
			
			parent.getChildren().sort(new childrenComparator());
			
			leafList.add(index, left);
			leafList.add(index + 1, right);
			
			root = parent;
		}
		else {
			ThreeWayBPlusTreeNode parent = sub.getParent();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //자식들 생성
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			int index = leafList.indexOf(sub);
			leafList.remove(index);
			
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
			
			parent.getChildren().sort(new childrenComparator());
			
			leafList.add(index, left);
			leafList.add(index + 1, right);
			
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
		if(!checking(e))
			return false;
		
		ThreeWayBPlusTreeNode sub = root;
		
		if(sub.getKeyList().isEmpty()) {
			sub.getKeyList().add(e);
			leafList.add(sub);
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
	public  ThreeWayBPlusTreeNode updateNode(ThreeWayBPlusTreeNode sub, int remove, int update) {
		sub = sub.getParent();
		
		while(sub!=null) {
			for(int i=0;i<sub.getKeyList().size();i++) {
				if(sub.getKeyList().get(i) == remove) {
					sub.getKeyList().remove(i);
					
					if(update != -1) {
						sub.getKeyList().add(i, update);
					}
					return sub;
				}
			}
			sub = sub.getParent();
		}
		return null;
	}
	@Override
	public boolean remove(Object o) {
		int e = (int)o;
		
		ThreeWayBPlusTreeNode sub = search(e);
		int index = -1;
		for(int i=0;i<sub.getKeyList().size();i++) {
			if(e == sub.getKeyList().get(i))
				index = i;
		}
		if(index  == -1)
			return false;
		//우선 키 값들 삭제
		sub.getKeyList().remove(index);
		
		//하나밖에 없을 때 삭제할때 모든것을 초기 세팅으로 돌려줌
		if(sub.getParent() == null) {
			if(sub.getKeyList().size() == 0) {
				root = null;
				leafList.remove(sub);
			}
			return true;
		}
		
		ThreeWayBPlusTreeNode update = new ThreeWayBPlusTreeNode();
		
		//가장 왼쪽에 있는 노드가 아니면서 노드에서 가장 처음의 값이라면 무조건 상위노드에 자신과 똑같은 값이 있으므로 없데이트작업이 필요 
		if(index == 0 && leafList.indexOf(sub) != 0) {
			//안비어있다면 바꿔주고 끝
			if(sub.getKeyList().size() != 0) {
				updateNode(sub, e, sub.getKeyList().get(index));
				return true;
			} //남은게 없다면 다른 작업이 필요
			else if(sub.getKeyList().size() == 0) {
				update = updateNode(sub, e, -1);
			}
		}
		
		
		//최소키 조건 만족하므로 끝
		if(sub.getKeyList().size() != 0) {
			return true;
		}
		//부모 없을때 끝
		if(sub.getParent() == null) {
			root = null;
			leafList.remove(sub);
			return true;
		}
		
		//빌려오거나 병합을 위한 사전 작업
		
		//지워진 노드가 부모의 몇번째 자식인지 체크
		int subIndex = sub.getParent().getChildren().indexOf(sub);
		
		ThreeWayBPlusTreeNode left = null;
		//왼쪽에 자식이 있는경우
		if(subIndex -1 >= 0) {
			left = sub.getParent().getChildren().get(subIndex-1);
		}
		
		ThreeWayBPlusTreeNode right = null;
		//오른쪽에 자식이 있는경우
		if(subIndex + 1 < sub.getParent().getChildren().size()) {
			right = sub.getParent().getChildren().get(subIndex+1);
		}
		//왼쪽이 있으면서 왼쪽에서 빌릴 수 있을때
		if(left != null && left.getKeyList().size() >= 2) {
			//왼쪽에서 가장 우측에 있는 값을 가져오는 과정
			int LV = left.getKeyList().remove(left.getKeyList().size() - 1);
			//값을 넣어주고 정렬
//			sub.getKeyList().add(LV);
//			sub.getKeyList().sort(Comparator.naturalOrder());
			sub.addKeyList(LV);
//			sub.getParent().getKeyList().add(LV);
//			sub.getParent().getKeyList().sort(Comparator.naturalOrder());
			sub.getParent().addKeyList(LV);
		}
		else if(right != null && right.getKeyList().size() >= 2) {
			int PRV = sub.getParent().getChildren().indexOf(right)-1;
			int RV = right.getKeyList().remove(0);
			
//			sub.getKeyList().add(RV);
//			sub.getKeyList().sort(Comparator.naturalOrder());
			sub.addKeyList(RV);
			if(sub.getKeyList().get(0) == RV && update != null) {
//				update.getKeyList().add(RV);
//				update.getKeyList().sort(Comparator.naturalOrder());
				update.addKeyList(RV);
			}
			
			if(PRV == 0) {
				sub.getParent().getKeyList().remove(0);
			}
//			sub.getParent().getKeyList().add(right.getKeyList().get(0));
//			sub.getParent().getKeyList().sort(Comparator.naturalOrder());
			sub.getParent().addKeyList(right.getKeyList().get(0));
		}
		//빌려올 수가 없다면 병합을 해야됨
		else {
			if(left != null);
			else if(right != null) {
				int indexPRV = sub.getParent().getChildren().indexOf(right)-1;
				int PRV = sub.getParent().getKeyList().remove(indexPRV);
				if(update != null) {
//					update.getKeyList().add(PRV);
//					update.getKeyList().sort(Comparator.naturalOrder());
					update.addKeyList(PRV);
				}
//				right.getKeyList().add(PRV);
//				right.getKeyList().sort(Comparator.naturalOrder());
				right.addKeyList(PRV);
			}
			leafList.remove(leafList.indexOf(sub));
			ThreeWayBPlusTreeNode parent = sub.getParent();
			sub.getParent().getChildren().remove(sub);
			sub.setParent(null);
			merge(parent);
		}
		return true;
	}
	
	public void merge(ThreeWayBPlusTreeNode sub) {
		if(sub.getKeyList().size() != 0) {
			return;
		}
		//부모거 없는 경우
		ThreeWayBPlusTreeNode parent = sub.getParent();
		if(parent == null) {
			if(sub.getKeyList().size() == 0) {
				root = sub.getChildren().get(0);
			}
			else {
				root = sub;
			}
			root.setParent(null);
			return;
		}
		
		int subIndex = parent.getChildren().indexOf(sub);

		ThreeWayBPlusTreeNode left = null;
		//왼쪽에 자식이 있는경우
		if(subIndex -1 >= 0) {
			left = sub.getParent().getChildren().get(subIndex-1);
		}
		
		ThreeWayBPlusTreeNode right = null;
		//오른쪽에 자식이 있는경우
		if(subIndex + 1 < sub.getParent().getChildren().size()) {
			right = sub.getParent().getChildren().get(subIndex+1);
		}
		
		if(left != null && left.getKeyList().size() >= 2) {
			int LV = left.getKeyList().remove(left.getKeyList().size()-1);
			int indexPLV = parent.getChildren().indexOf(left);
			int PLV = parent.getKeyList().get(indexPLV);
			
//			sub.getKeyList().add(PLV);
//			sub.getKeyList().sort(Comparator.naturalOrder());
			sub.addKeyList(PLV);
			
			sub.getChildren().add(0,left.getChildren().remove(left.getChildren().size()-1));
			sub.getChildren().get(0).setParent(sub);
			
			sub.addKeyList(parent.getKeyList().remove(indexPLV));

			
			parent.addKeyList(LV);
//			parent.getKeyList().add(LV);
//			parent.getKeyList().sort(Comparator.naturalOrder());
		}
		
		else if(right != null && right.getKeyList().size() >= 2) {
			int RV = right.getKeyList().remove(0);
			int indexPRV = parent.getChildren().indexOf(right) - 1;
			int PRV = parent.getKeyList().get(indexPRV);
			
			sub.addKeyList(PRV);
//			sub.getKeyList().add(PRV);
//			sub.getKeyList().sort(Comparator.naturalOrder());
			
			sub.getChildren().add(right.getChildren().remove(0));
			sub.getChildren().get(sub.getChildren().size() - 1).setParent(sub);
			
			sub.addKeyList(parent.getKeyList().remove(indexPRV));

			parent.addKeyList(RV);
//			parent.getKeyList().add(RV);
//			parent.getKeyList().sort(Comparator.naturalOrder());
		}
		else { //둘 다 안되는경우 다시 머지
			if(left != null) {
				int indexPLV = sub.getParent().getChildren().indexOf(left);
				int PLV = sub.getParent().getKeyList().remove(indexPLV);
				
//				left.getKeyList().add(PLV);
//				left.getKeyList().sort(Comparator.naturalOrder());
				left.addKeyList(PLV);
				
				left.getChildren().add(sub.getChildren().get(0));
				
				sub.getChildren().get(0).setParent(left);
			}
			else if(right != null) {
				int indexPRV = sub.getParent().getChildren().indexOf(right)-1;
				int PRV = sub.getParent().getKeyList().remove(indexPRV);
				
//				right.getKeyList().add(PRV);
//				right.getKeyList().sort(Comparator.naturalOrder());
				right.addKeyList(PRV);
				
				right.getChildren().add(0, sub.getChildren().get(0));
				
				sub.getChildren().get(0).setParent(right);
			}
			parent.getChildren().remove(sub);
			merge(parent);
		}
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
	
	public LinkedList<Integer> makeIntList(){
		LinkedList<Integer> intList = new LinkedList<Integer>();
		
		for(ThreeWayBPlusTreeNode i : leafList) {
			for(int j=0;j<i.getKeyList().size();j++) {
				intList.add(i.getKeyList().get(j));
			}
		}
		
		return intList;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		LinkedList<Integer> intList = makeIntList();
		
		
		return intList.iterator();
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