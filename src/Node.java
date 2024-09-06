
public class Node {
	private Object element;
	private Node next;

	public Node(Object element) {
		this.element = element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}


	@Override
	public String toString() {
		return element.toString();
	}

}