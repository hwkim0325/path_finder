package Pathfinding;

import java.util.ArrayList;

public class ManagerQueue {
	private ArrayList<PathRequest> queue= new ArrayList<>();
	
	public void enqueue(PathRequest request) {queue.add(request);}
	
	public PathRequest dequeue() {
		if(queue.isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return queue.remove(0);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	
}
