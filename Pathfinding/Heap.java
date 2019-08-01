package Pathfinding;

import Node_Frame.Node;

public class Heap {
	Node[] items;
	int currentItemCount;
	
	public Heap(int maxHeapSize) {
		items = new Node[maxHeapSize];
	}
	
	public void Add(Node item) {
		item.setHeapIndex(currentItemCount);
		items[currentItemCount] = item;
		SortUp(item);
		currentItemCount++;
	}
	
	public Node RemoveFirst() {
		Node firstItem = items[0];
		currentItemCount--;
		items[0] = items[currentItemCount];
		items[0].setHeapIndex(0);
		SortDown(items[0]);
		return firstItem;
	}
	
	public void UpdateItem(Node item)
	{
		SortUp(item);
	}
	
	public int Count(){
		return currentItemCount;
	}
	
	public boolean Contains(Node item) {
		return item.equals(items[item.getHeapIndex()]);
	}
	
	public void SortUp(Node item){
		int parentIndex = (item.getHeapIndex() -1 ) / 2;
		
		while(true) {
			Node parentItem = items[parentIndex];
			if(item.CompareTo(parentItem) < 0) {
				Swap(item, parentItem);
			}
			else {
				break;
			}
			parentIndex = (item.getHeapIndex() - 1) / 2;
		}
	}
	
	public void SortDown(Node item) {
		while(true){
			int childIndexLeft = item.getHeapIndex() * 2 + 1;
			int childIndexRight = item.getHeapIndex() * 2 + 2;
			int swapIndex = 0;
			
			if(childIndexLeft < currentItemCount) {
				swapIndex = childIndexLeft;
				
				if(childIndexRight < currentItemCount) {
					if(items[childIndexLeft].CompareTo(items[childIndexRight]) > 0) {
						swapIndex = childIndexRight;
					}
				}
				
				if(item.CompareTo(items[swapIndex]) > 0) {
					Swap(item, items[swapIndex]);
				}
				else 
				{
					return;
				}
			}
			else
			{
				return;
			}
		}
	}
	
	public void Swap(Node itemA, Node itemB) {
		items[itemA.getHeapIndex()] = itemB;
		items[itemB.getHeapIndex()] = itemA;
		int itemAIndex = itemA.getHeapIndex();
		itemA.setHeapIndex(itemB.getHeapIndex()); 
		itemB.setHeapIndex(itemAIndex);
	}	
}
