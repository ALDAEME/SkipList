/**
 * ALDA - Algoritmdesigntekniker
 * 
 * @author Elise Edette (tero0337)
 * @author Aframyeos Rohoum (afro0793)
 * @author Emma Persson (empe5691)
 */

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
	
	public boolean contains(T data){ //Sökning
		boolean exists=false;
		Node<T> current= head;
		for(int i =maxLevel-1; i>=0; i--){
			while(current.next[i]!=null && current.next[i].data.compareTo(data)<=0){
				if(current.next[i].data.equals(data)){ //Om datan är hittad
					exists=true;
					break;
				}else{
					current=current.next[i];//om inte, hoppa till nästa nod 
				}
			}		
		}
		return exists;
	}

	private int generateLevel(){
		int level = 1;
		while((int)(Math.random()*2) == 1 && level < maxLevel){
			level++;
		}
		return level;
	}
	
}