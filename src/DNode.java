
public class DNode implements Comparable<DNode> {
	private SinglyLinkedList list = new SinglyLinkedList();
	private String brand;
	private DNode prev, next;
	
	public DNode(String brand) {
		this.brand = brand;
	}

	public SinglyLinkedList getList() {
		return list;
	}

	public void setList(SinglyLinkedList list,Car m) {
		list.add(m);
		this.list = list;
	}
	public void setList(SinglyLinkedList list) {
		this.list = list;
	}
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}

	@Override
	public int compareTo(DNode o) {
		return this.getBrand().compareTo(o.getBrand());
	}

	@Override
	public String toString() {
		return "[brand=" + brand + "]";
	}
	
	
	
}
