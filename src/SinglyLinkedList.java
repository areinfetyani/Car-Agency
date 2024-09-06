public class SinglyLinkedList {
	Node first, last;
	private int count = 0;

	public Object getFirst() {
		if (count == 0)
			return null;
		else
			return first.getElement();
	}

	public Object getLast() {
		if (count == 0)
			return null;
		else
			return last.getElement();
	}

	public void addFirst(Object element) {
		if (count == 0)
			first = last = new Node(element);
		else {
			Node temp = new Node(element);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	public void addLast(Object element) {
		if (count == 0)
			first = last = new Node(element);
		else {
			Node temp = new Node(element);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	public void add(Object obj, int index) {
		if (index == 0)
			addFirst(obj);
		else {
			if (index >= count)
				addLast(obj);
			else {
				Node temp = new Node(obj);
				Node current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.getNext();
				}
				temp.setNext(current.getNext());
				current.setNext(temp);
				count++;
			}
		}
	}

	public void add(Object obj) {
		Node curr = first;
		int i = 0;
		while (obj instanceof Car && curr != null && (((Car) (curr.getElement())).compareTo((Car) obj) < 0)) {
			curr = curr.getNext();
			i++;
		}
		add(obj, i);
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else {
			if (count == 1)
				first = last = null;
			else
				first = first.getNext();
			count--;
			return true;
		}
	}

	public boolean removeLast() {
		if (count == 0)
			return false;
		else {
			if (count == 1)
				last = first = null;
			else {
				Node current = first;
				for (int i = 0; i < count - 2; i++) {
					current = current.getNext();
				}
				last = current;
				current.setNext(null);
				;
			}
			count--;
			return true;
		}
	}

	public boolean remove(int index) {
		if (count == 0)
			return false;
		else {
			if (index == 0)
				return removeFirst();
			else {
				if (index == count - 1)
					return removeLast();
				if (index < 0 || index >= count)
					return false;
				else {
					Node current = first;
					for (int i = 0; i < index - 1; i++)
						current = current.getNext();
					current.setNext((current.getNext()).getNext());
					count--;
					return true;
				}
			}
		}
	}

	public boolean remove(Object element) {
		Node current = null;
		if (count != 0) {
			if (element.equals(first.getElement())) {
				return removeFirst();
			} else if (element.equals(last.getElement())) {
				return removeLast();
			} else {
				current = first;
				for (int i = 0; i < count - 1; i++) {
					if (element.equals(current.getElement())) {
						return remove(i);
					}
					current = current.getNext();
				}
			}
		}
		return false;
	}

	public Node getNode(Car car) {
		Node curr = first;
		for (int i = 0; i < count; i++) {
			if (((Car) curr.getElement()).getModel().equals(car.getModel())
			        && ((Car) curr.getElement()).getYear() == (car.getYear())
			        && ((Car) curr.getElement()).getColor().equals(car.getColor())
			        && ((Car) curr.getElement()).getPrice() == (car.getPrice())) {
				return curr;
			}
			curr = curr.getNext();
		}
		return null;
	}

	public Node getNode(int index) {
		Node curr = first;
		for (int i = 0; i < index && curr != null; i++) {
			curr = curr.getNext();
		}
		return curr;
	}

	public String toString() {
		Node current = first;
		String str = "";
		if (count == 0)
			return "";
		else {
			for (int i = 0; i < count; i++) {
				str += current.toString() + " ";
				current = current.getNext();
			}
			return str;
		}
	}

	public void printList() {
		Node current = first;
		if (count == 0)
			return;
		for (int i = 0; i < count; i++) {
			System.out.println(current.toString());
			current = current.getNext();
		}
	}

	public int size() {
		return count;
	}

}