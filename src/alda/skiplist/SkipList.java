package alda.skiplist;

public class SkipList<T extends Comparable<? super T>> {

	private int maxLevel;
	Node<T> head;

	public SkipList(int maxLevel){
		this.maxLevel = maxLevel;
		head = new Node<T>(maxLevel, null);	//Startnod
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
		Node<T> current= head;
		int newLevel=generateLevel();
		Node<T> newNode= new Node<T>(newLevel, data);

		if(head.next[0]==null){ //Om tom skipList, lägg till ny först
			for(int i = 0; i<=newLevel-1; i++){
				head.next[i]=newNode;
			}	
		}else{ //Annars, hitta vart den ska vara
			for(int i =newLevel-1; i>=0; i--){
				while(current.next[i]!=null && current.next[i].data.compareTo(data)<0){
					current=current.next[i];

				}	
				newNode.next[i]=current.next[i];//Stoppar in den nya noden.
				current.next[i]=newNode;
			}

		}
	}

	public void remove(T data){
		Node<T> current= head;
		for(int i =maxLevel-1; i>=0; i--){
			while(current.next[i]!=null && current.next[i].data.compareTo(data)<=0){
				if(current.next[i].data.equals(data)){ //Om datan är hittad, riktas pekarna om.
					current.next[i]=current.next[i].next[i];
				}else{
					current=current.next[i];//om inte, hoppa till nästa 
				}
			}		
		}
	}


	public T remove(int index){
		return null;
	}

	private int generateLevel(){
		int level = 1;
		while((int)(Math.random()*2) == 1 && level < maxLevel){
			level++;
		}
		return level;
	}

	public T get(int index){
		if(index < 0)
			return null;

		Node<T> node = head.next[0];

		for(int i = 0; i < index; i++){
			if(node.next[0] != null){
				node = node.next[0];
			}else
				return null;	
		}
		return node.data;
	}

	public static void main(String [] args){
		SkipList<String> mylist = new SkipList<String>(4);

		mylist.insert("hello");
		mylist.insert("how");
		mylist.insert("are");
		mylist.insert("you");
		mylist.insert("doing");
		mylist.insert("today?");
		System.out.println("Fullständig lista:");
		System.out.print(mylist.get(0)+" ");
		System.out.print(mylist.get(1)+" ");
		System.out.print(mylist.get(2)+" ");
		System.out.print(mylist.get(3)+" ");
		System.out.print(mylist.get(4)+" ");
		System.out.println(mylist.get(5)+" ");

		mylist.remove("you");
		mylist.remove("hello");
		mylist.remove("hi");
		System.out.println("Efter remove");
		System.out.print(mylist.get(0)+" ");
		System.out.print(mylist.get(1)+" ");
		System.out.print(mylist.get(2)+" ");
		System.out.print(mylist.get(3)+" ");
		System.out.print(mylist.get(4)+" ");
		System.out.print(mylist.get(5)+" ");

	}
}