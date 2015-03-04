package alda.skiplist;

public class SkipList<T extends Comparable<? super T>> {

	private int maxLevel;
	Node<T>[] head;

	public SkipList(int maxLevel){
		this.maxLevel = maxLevel;
		head = new Node[maxLevel];
		for(int i = 0; i < maxLevel; i++)
			head[i] = null;
	}

	private class Node<T>{
		T data;
		int level;
		Node<T>[] next;

		protected Node(int level, T data){
			this.data = data;
			this.level = level;
			next = new Node[level];

			for(int i = 0; i < level; i++){
				next[i] = null;
			}
		}
	}

	public void insert(T data){
		Node<T>[] current = new Node[maxLevel];
		Node<T>[] previous = new Node[maxLevel];

		current[maxLevel - 1] = head[maxLevel - 1];
		previous[maxLevel - 1] = null;

		for(int i = maxLevel - 1; i >= 0; i--){
			while(current[i] != null && current[i].data.compareTo(data) < 0){
				previous[i] = current[i];
				current[i] = current[i].next[i];
			}

			if(i > 0){
				if(previous[i] == null){
					current[i - 1] = head[i - 1];
					previous[i - 1] = null;
				}else{
					current[i - 1] = previous[i].next[i - 1];
					previous[i - 1] = previous[i]; 
				}
			}
		}

		int newLevel = generateLevel();
		System.out.print(newLevel+" ");
		Node<T> newNode = new Node<T>(newLevel, data);

		for(int i = 0; i <= newLevel - 1; i++){
			newNode.next[i] = current[i];
			if(previous[i] == null){
				head[i] = newNode;
			}else{
				previous[i].next[i] = newNode;
			}
		}
	}
	
	public boolean remove(T data){
		
		Node<T>[] current = new Node[maxLevel];
		Node<T>[] previous = new Node[maxLevel];

		current[maxLevel - 1] = head[maxLevel - 1];
		previous[maxLevel - 1] = null;

		for(int i = maxLevel - 1; i >= 0; i--){
			while(current[i] != null && current[i].data.compareTo(data) < 0 ){

				previous[i] = current[i];
				current[i] = current[i].next[i];
			}

			if(i > 0){
				if(previous[i] == null){
					current[i - 1] = head[i - 1];
					previous[i - 1] = null;
				}else{
					current[i - 1] = previous[i].next[i - 1];
					previous[i - 1] = previous[i]; 
				}
			}
		}
		
			for(int i = 0; i <= current.length-1; i++){

				if(previous[i]!=null){	
					if(previous[i].next[i] != null && current[i].data.equals(data)){
						previous[i].next[i] = current[i].next[i];
					}
				}
			}
		

		
		
		return false;
		
	}

	public T remove(int index){
		return null;
	}


	public T get(int index){
		if(index < 0)
			return null;

		Node<T> node = head[0];

		for(int i = 0; i < index; i++){
			if(node.next[0] != null){
				node = node.next[0];
			}else
				return null;	
		}
		return node.data;
	}

	private int generateLevel(){
		int level = 1;

		while((int)(Math.random()*2) == 1 && level < maxLevel){
			level++;
		}
		return level;
	}

	public String toString(){
		Node<T> n = head[0];
		String result = "SkipList: [";

		while(n != null){
			result += "'" + n.data.toString() + (n.next[0] != null ? "', " : "'");
			n = n.next[0];
		}

		result += "]";

		return result;
	}

	public static void main(String [] args){

		SkipList<String> mylist = new SkipList<String>(4);

		mylist.insert("hello");
		mylist.insert("how");
		mylist.insert("are");
		mylist.insert("you");
		mylist.insert("doing");
		mylist.insert("today?");

		System.out.println(" ");
		System.out.println(mylist);
		//System.out.println(mylist.get(-1));
		mylist.remove("you");

		System.out.println(mylist);
		
		mylist.remove("hello");
		System.out.println(mylist);
		
		mylist.remove("how");
		System.out.println(mylist);
		
		mylist.remove("today?");
		System.out.println(mylist);
		
		mylist.remove("hi");
		System.out.println(mylist);
	}
}
