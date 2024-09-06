import java.util.Date;

public class Car implements Comparable<Car> {
	private String model;
	private int year;
	private String color;
	private double price;

	public Car(String model, int year, String color, double price) {
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(Car o) {
		if (this.getYear() > o.getYear())
			return 1;
		else if (this.getYear() < o.getYear())
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return model + ", " + year + ", " + color + ", " + price;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Car && getModel().equals(((Car) obj).getModel()) && getColor().equals(((Car) obj).getColor())
		        && getYear() == (((Car) obj).getYear()) && getPrice() == (((Car) obj).getPrice())) {
			return true;
		}
		return false;
	}

}
