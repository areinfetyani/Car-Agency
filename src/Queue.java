
public class Queue {
	private int front,rear;
	private int size=0;
	private SinglyLinkedList queue;
	
	public Queue() {
		queue = new SinglyLinkedList();
		front =rear=0;
	}


	public int size() {
		return size;
	}


	public SinglyLinkedList getQueue() {
		return queue;
	}


	public boolean isEmpty() {
		return rear==front;
	}
	
	public void enQueue(Object x) {
		queue.addLast(x);
		rear++;
		size++;
	}
	
	public Object deQueue() {
		if(!isEmpty()) {
			Object temp = queue.getFirst();
			front++;
			queue.removeFirst();
			size--;
			return temp;
		}
		return null;
	}
	
	
	
	public Node getFront() {
		return queue.getNode(0);
	}

	public Node getNode(int i) {
		return queue.getNode(i);
	}

	public Node getRear() {
		return queue.getNode(size()-1);
	}


	public void printQueue() {
		if(!isEmpty()) {
		for(int i = 0 ; i < queue.size() ; i++)
			System.out.println(queue.getNode(i).getElement().toString());
		}else {
			System.out.println("Empty Queue");
		}
	}

}
