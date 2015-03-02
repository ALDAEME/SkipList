package alda.skiplist;

public class SkipList<T> {

	private int maxLevel;
	
	public SkipList(int maxLevel){
		this.maxLevel = maxLevel;
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
	
	public boolean add(T data){
		return false;
	}
	
	public T remove(int index){
		return null;
	}
	
	public T get(int index){
		return null;
	}
	
	private int generateLevel(){
		int level = 1;
		
		while((int)(Math.random()*2) == 1 && level < maxLevel){
			level++;
		}
		
		return level;
	}
}
