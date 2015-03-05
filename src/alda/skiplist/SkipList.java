package alda.skiplist;

public class SkipList<T extends Comparable<? super T>> {

	private int maxLevel;
	Node<T> head;

	public SkipList(int maxLevel){
		this.maxLevel = maxLevel;
		head = new Node<T>(maxLevel, null);	//Startnod
	}

	private class Node<T>{//Nodklass
		T data;
		int level;
		Node<T>[] next;//Noderna innehåller en array av pekare

		protected Node(int level, T data){
			this.data = data;
			this.level = level;
			next = new Node[level];
			for(int i = 0; i < level; i++){
				next[i] = null;
			}
		}
	}

	public void insert(T data){//Insättning
		Node<T> current= head;
		int newLevel=generateLevel();
		Node<T> newNode= new Node<T>(newLevel, data);

			for(int i =newLevel-1; i>=0; i--){
				while(current.next[i]!=null && current.next[i].data.compareTo(data)<0){
					current=current.next[i];
				}	
				newNode.next[i]=current.next[i];//Stoppar in den nya noden.
				current.next[i]=newNode;
			}
	}

	public void remove(T data){ //Borttag
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
	
	public T get(T data){ //Sökning
		Node<T> node=new Node<T>(maxLevel, null);
		Node<T> current= head;
		for(int i =maxLevel-1; i>=0; i--){
			while(current.next[i]!=null && current.next[i].data.compareTo(data)<=0){
				if(current.next[i].data.equals(data)){ //Om datan är hittad, riktas pekarna om.
					node=current.next[i];
					break;
				}else{
					current=current.next[i];//om inte, hoppa till nästa nod 
				}
			}		
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
		String string="";
		Node<T> current=head;
		while(current.next[0]!=null){
			string += current.next[0].data.toString() +" ";
			current=current.next[0];
		}
		return string;
		
	}

	public static void main(String [] args){
		SkipList<String> mylist = new SkipList<String>(4);

		mylist.insert("hello");
		mylist.insert("how");
		mylist.insert("are");
		mylist.insert("you");
		mylist.insert("doing");
		mylist.insert("today?");
		System.out.println(mylist.toString());

		mylist.remove("you");
		mylist.remove("hello");
		mylist.remove("hi");
		System.out.println("Efter remove");
		System.out.println(mylist.toString());
		
		System.out.println(mylist.get("are"));
		System.out.println(mylist.get("hi"));
		
		SkipList<Integer> intlist = new SkipList<Integer>(3);
		intlist.insert(4);
		intlist.insert(1);
		intlist.insert(3);
		System.out.println(intlist.toString());
		intlist.remove(2);
		intlist.remove(1);
		System.out.println(intlist.toString());
		
	}
}