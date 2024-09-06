
public class Stack {
	private SinglyLinkedList stack;
	private int top = -1;
	private int size = 0;

	public Stack() {
		stack = new SinglyLinkedList();
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public void push(Object order) {
		stack.addFirst(order);
		top++;
		size++;
	}
	public Object peek() {
		if (!isEmpty()) {
			return stack.getFirst();
		}
		return null;
	}
	public Object pop() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Stack is Empty");
		} else {
			Object temp = stack.getFirst();
			stack.removeFirst();
			top--;
			size--;
			return temp;
		}
	}
	public int size() {
		return size;
	}

	public void printStack() {
		for(int i = 0 ; i <= top ; i++) {
			System.out.println(stack.getNode(i).getElement().toString() +" ");
		}
	}
	
}
