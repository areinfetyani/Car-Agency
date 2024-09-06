import java.util.Date;

public class Order {
	private String brand;
	private Car car;
	private String customerName;
	private int customerMobile;
	private Date orderDate;
	private String orderStatus;

	public Order(Car car, String brand, String customerName, int customerMobile, Date orderDate, String orderStatus) {
		this.car = car;
		this.brand = brand;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(int customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [brand=" + brand + ", car=" + car + ", customerName=" + customerName + ", customerMobile="
		        + customerMobile + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Order && getBrand().equals(((Order) obj).getBrand())
		        && getCar().equals(((Order) obj).getCar()) && getCustomerName().equals(((Order) obj).getCustomerName())
		        && getOrderStatus().equals(((Order) obj).getOrderStatus())
		        && getOrderDate().equals(((Order) obj).getOrderDate())
		        && getCustomerMobile() == (((Order) obj).getCustomerMobile())) {
			return true;

		}
		return false;
	}

}
