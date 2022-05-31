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
			if(sub.getKeyList().size() == 2) { //�ڽ��� 3���϶�
				if(a>=sub.getKeyList().get(1)) {
					System.out.println("larger than or equal to " + sub.getKeyList().get(1));
					sub = child.get(2); //��������� ����
				}
				else if(a>=sub.getKeyList().get(0)) {
					System.out.println("larger than or equal to " + sub.getKeyList().get(0));
					sub = child.get(1); //��� �ڽ����� ����
				}
				else {
					System.out.println("less than " + sub.getKeyList().get(0));
					sub = child.get(0); //���� ���� �ڽ�
				}
			}
			else {
				if(a>=sub.getKeyList().get(0)) { //�ڽ��� 2���϶�
					System.out.println("larger than or equal to " + sub.getKeyList().get(0));
					sub = child.get(1); //������ �ڽ�
				}
				else {
					System.out.println("less than " + sub.getKeyList().get(0));
					sub = child.get(0); //���� �ڽ�
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
		inorder(root);
	}
	
	public void inorder(ThreeWayBPlusTreeNode sub) {
		if(sub == null)
			return;
		//������ȸ�� �ϱ����ؼ� �� ���� ���ʿ� �ִ� �ڽĵ鿡 �������� �Ʒ��� �ڽ��� ������ ���� ���϶��� ���� ȣ��
		for(int i=0;i<sub.getChildren().size();i++) {
			if(sub.getChildren().get(i).getKeyList().get(0) < sub.getKeyList().get(0)) {
				inorder(sub.getChildren().get(i));
			}
		}
		//������ �ަU�� �ִ� �ڽĵ��� ��� �湮������ ���� ���� ȣ��
		if(sub.getChildren().isEmpty()) {
			for(int i=0;i<sub.getKeyList().size();i++) {
				System.out.println(sub.getKeyList().get(i));
			}
		}
		//������ �����ʿ� �ִ� �ڽĵ鿡 �湮�ϱ� ���� ȣ��
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
			if(sub.getKeyList().size() == 2) { //�ڽ��� 3���϶�
				if(a>=sub.getKeyList().get(1)) {
					sub = child.get(2); //��������� ����
				}
				else if(a>=sub.getKeyList().get(0)) {
					sub = child.get(1); //��� �ڽ����� ����
				}
				else {
					sub = child.get(0); //���� ���� �ڽ�
				}
			}
			else{
				if(a>=sub.getKeyList().get(0)) { //�ڽ��� 2���϶�
					sub = child.get(1); //������ �ڽ�
				}
				else {
					sub = child.get(0); //���� �ڽ�
				}
			}
		}
		
		return sub;
	}
	
	public boolean checking(int a) {
		ThreeWayBPlusTreeNode sub = root;
		
		while(!sub.getChildren().isEmpty()) {
			List<ThreeWayBPlusTreeNode> child = sub.getChildren();
			if(sub.getKeyList().size() == 2) { //�ڽ��� 3���϶�
				if(a>=sub.getKeyList().get(1)) {
					sub = child.get(2); //��������� ����
				}
				else if(a>=sub.getKeyList().get(0)) {
					sub = child.get(1); //��� �ڽ����� ����
				}
				else {
					sub = child.get(0); //���� ���� �ڽ�
				}
			}
			else{
				if(a>=sub.getKeyList().get(0)) { //�ڽ��� 2���϶�
					sub = child.get(1); //������ �ڽ�
				}
				else {
					sub = child.get(0); //���� �ڽ�
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
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //�ڽĵ� ����
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //�ڽĵ� �θ���
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key���� �Ҵ��ϱ�
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
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //�ڽĵ� ����
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			left.setParent(parent); //�ڽĵ� �θ���
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key���� �Ҵ��ϱ�
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
			//�θ��� �ڽĵ��� �ڿ� �־����� ���� ����
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
		if(sub.getParent() == null) {//�θ� ������
			//�θ����
			ThreeWayBPlusTreeNode parent = new ThreeWayBPlusTreeNode();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //�ڽĵ� ����
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			int index = leafList.indexOf(sub);
			leafList.remove(index);
			
			left.setParent(parent); //�ڽĵ� �θ���
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key���� �Ҵ��ϱ�
			parent.setKeyList(sub.getKeyList().get(1));
			parent.getKeyList().sort(Comparator.naturalOrder());
			
			right.setKeyList(sub.getKeyList().get(1));
			right.setKeyList(sub.getKeyList().get(2));
			
			//�ڽļ���
			parent.setChildren(left, right);
			
			parent.getChildren().sort(new childrenComparator());
			
			leafList.add(index, left);
			leafList.add(index + 1, right);
			
			root = parent;
		}
		else {
			ThreeWayBPlusTreeNode parent = sub.getParent();
			
			ThreeWayBPlusTreeNode left = new ThreeWayBPlusTreeNode(); //�ڽĵ� ����
			ThreeWayBPlusTreeNode right = new ThreeWayBPlusTreeNode();
			
			int index = leafList.indexOf(sub);
			leafList.remove(index);
			
			left.setParent(parent); //�ڽĵ� �θ���
			right.setParent(parent);
			
			left.setKeyList(sub.getKeyList().get(0)); //key���� �Ҵ��ϱ�
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
		//�켱 Ű ���� ����
		sub.getKeyList().remove(index);
		
		//�ϳ��ۿ� ���� �� �����Ҷ� ������ �ʱ� �������� ������
		if(sub.getParent() == null) {
			if(sub.getKeyList().size() == 0) {
				root = null;
				leafList.remove(sub);
			}
			return true;
		}
		
		ThreeWayBPlusTreeNode update = new ThreeWayBPlusTreeNode();
		
		//���� ���ʿ� �ִ� ��尡 �ƴϸ鼭 ��忡�� ���� ó���� ���̶�� ������ ������忡 �ڽŰ� �Ȱ��� ���� �����Ƿ� ������Ʈ�۾��� �ʿ� 
		if(index == 0 && leafList.indexOf(sub) != 0) {
			//�Ⱥ���ִٸ� �ٲ��ְ� ��
			if(sub.getKeyList().size() != 0) {
				updateNode(sub, e, sub.getKeyList().get(index));
				return true;
			} //������ ���ٸ� �ٸ� �۾��� �ʿ�
			else if(sub.getKeyList().size() == 0) {
				update = updateNode(sub, e, -1);
			}
		}
		
		
		//�ּ�Ű ���� �����ϹǷ� ��
		if(sub.getKeyList().size() != 0) {
			return true;
		}
		//�θ� ������ ��
		if(sub.getParent() == null) {
			root = null;
			leafList.remove(sub);
			return true;
		}
		
		//�������ų� ������ ���� ���� �۾�
		
		//������ ��尡 �θ��� ���° �ڽ����� üũ
		int subIndex = sub.getParent().getChildren().indexOf(sub);
		
		ThreeWayBPlusTreeNode left = null;
		//���ʿ� �ڽ��� �ִ°��
		if(subIndex -1 >= 0) {
			left = sub.getParent().getChildren().get(subIndex-1);
		}
		
		ThreeWayBPlusTreeNode right = null;
		//�����ʿ� �ڽ��� �ִ°��
		if(subIndex + 1 < sub.getParent().getChildren().size()) {
			right = sub.getParent().getChildren().get(subIndex+1);
		}
		//������ �����鼭 ���ʿ��� ���� �� ������
		if(left != null && left.getKeyList().size() >= 2) {
			//���ʿ��� ���� ������ �ִ� ���� �������� ����
			int LV = left.getKeyList().remove(left.getKeyList().size() - 1);
			//���� �־��ְ� ����
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
		//������ ���� ���ٸ� ������ �ؾߵ�
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
		//�θ�� ���� ���
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
		//���ʿ� �ڽ��� �ִ°��
		if(subIndex -1 >= 0) {
			left = sub.getParent().getChildren().get(subIndex-1);
		}
		
		ThreeWayBPlusTreeNode right = null;
		//�����ʿ� �ڽ��� �ִ°��
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
		else { //�� �� �ȵǴ°�� �ٽ� ����
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