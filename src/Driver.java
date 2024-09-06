import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	static DoublyLinkedList doublylist = new DoublyLinkedList();
	static Stack stack = new Stack();
	static Queue queue = new Queue();
	static Queue tempQueue = new Queue();
	Label title, brandName, namelbl, mobilelbl;
	TextField nametf, mobiletf;
	Button readCars, readOrders, client, admin, save, next, prev, cart, saveorders;
	Stage clientStage, adminStage;
	Font buttonfont, titlefont;
	ComboBox<String> cb;
	RadioButton gray, black, white, green, blue, red, metagray, metasilver, l2010, l2020, c200, c300, x, l3, x3, x5, x6,
	        mustang, rio, optima, range1, range2, range3, range4;

	@Override
	public void start(Stage stage) throws Exception {
		buttonfont = Font.font("Comic Sans MS", FontWeight.MEDIUM, FontPosture.REGULAR, 19);
		titlefont = Font.font("Lucida Sans Unicode", FontWeight.BOLD, FontPosture.REGULAR, 23);
		Color lightPurple = Color.rgb(160, 120, 120);
		title = new Label("The Car Agency");
		title.setFont(titlefont);
		title.setStyle("-fx-text-fill:WHITE;");
		Image logo = new Image("result.png");
		ImageView logoiv = new ImageView(logo);
		logoiv.setFitHeight(320);
		logoiv.setFitWidth(690);
		readCars = new Button("Upload cars file");
		readOrders = new Button("Upload orders file");
		client = new Button("Client");
		admin = new Button("Admin");
		save = new Button("Save cars to file");
		saveorders = new Button("Save orders to file");
		readCars.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		readCars.setFont(buttonfont);
		readCars.setOnMouseEntered(e -> {
			readCars.setStyle("-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;");
			readCars.setTextFill(lightPurple);
		});
		readCars.setOnMouseExited(e -> {
			readCars.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		readCars.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(stage);
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(",");
					doublylist.add(parts[0]);
				}
			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
//			doublylist.printList();
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(", ", -1);
					String brand = parts[0];
					String model = parts[1];
					int year = Integer.parseInt(parts[2]);
					String color = parts[3];
					double price = Double.parseDouble(parts[4].substring(0, parts[4].length() - 1)) * 1000;
					doublylist.getNode(brand).setList(doublylist.getNode(brand).getList(),
					        (new Car(model, year, color, price)));

				}
			} catch (IOException ex) {
				System.out.println("Error");
				ex.printStackTrace();
			}
//			doublylist.getNode(3).getList().printList();
		});
		readOrders.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		readOrders.setFont(buttonfont);
		readOrders.setOnMouseEntered(e -> {
			readOrders.setStyle("-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;");
			readOrders.setTextFill(lightPurple);
		});
		readOrders.setOnMouseExited(e -> {
			readOrders.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		readOrders.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(stage);
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(", ", -1);
					if (parts[8].equals("Finished") || parts[8].equals("Finished ")) {
						String customerName = parts[0];
						int customerMobile = Integer.parseInt(parts[1]);
						String brand = parts[2];
						String model = parts[3];
						int year = Integer.parseInt(parts[4]);
						String color = parts[5];
						double price = Double.parseDouble(parts[6].substring(0, parts[6].length() - 1)) * 1000;
						String[] dateDetails = parts[7].split("/");
						Date orderDate = new Date(dateDetails[1] + "/" + dateDetails[0] + "/" + dateDetails[2]);
						String orderStatus = parts[8];
						stack.push(new Order(new Car(model, year, color, price), brand, customerName, customerMobile,
						        orderDate, orderStatus));

					}
				}
			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
//			stack.printStack();
//			System.out.println("*******************");
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(", ", -1);
					if (parts[8].equals("InProcess") || parts[8].equals("InProcess ")) {
						String customerName = parts[0];
						int customerMobile = Integer.parseInt(parts[1]);
						String brand = parts[2];
						String model = parts[3];
						int year = Integer.parseInt(parts[4]);
						String color = parts[5];
						double price = Double.parseDouble(parts[6].substring(0, parts[6].length() - 1)) * 1000;
						String[] dateDetails = parts[7].split("/");
						Date orderDate = new Date(dateDetails[1] + "/" + dateDetails[0] + "/" + dateDetails[2]);
						String orderStatus = parts[8];
						queue.enQueue(new Order(new Car(model, year, color, price), brand, customerName, customerMobile,
						        orderDate, orderStatus));
					}
				}
			} catch (IOException ex) {
				System.out.println("Error");
				ex.printStackTrace();
			}
//			queue.printQueue();
//			System.out.println("**************");
//			System.out.println(queue.getFront());
//			System.out.println("**************");
//			System.out.println(queue.getRear());
//			System.out.println("**************");
//			queue.deQueue();
//			queue.printQueue();
		});
		client.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		client.setFont(buttonfont);
		client.setOnMouseEntered(e -> {
			client.setStyle("-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;");
			client.setTextFill(lightPurple);
		});
		client.setOnMouseExited(e -> {
			client.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		client.setOnAction(e -> {
			clientStage = new Stage();
			brandName = new Label(doublylist.getNode(0).getBrand());
			brandName.setFont(titlefont);
			next = new Button("next>");
			next.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			next.setFont(buttonfont);
			next.setOnMouseEntered(a -> {
				next.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:red;");

			});
			next.setOnMouseExited(a -> {
				next.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			});
			prev = new Button("<previous");
			prev.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			prev.setFont(buttonfont);
			prev.setOnMouseEntered(a -> {
				prev.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:red;");

			});
			prev.setOnMouseExited(a -> {
				prev.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			});
			cart = new Button("Add to cart");
			Font cartfont = Font.font("Comic Sans MS", FontWeight.MEDIUM, FontPosture.REGULAR, 14);
			cart.setFont(cartfont);
			Image cartim = new Image("cart.png");
			ImageView cariv = new ImageView(cartim);
			cariv.setFitHeight(25);
			cariv.setFitWidth(25);
			cart.setGraphic(cariv);
			namelbl = new Label("Customer Name:");
			namelbl.setFont(cartfont);
			nametf = new TextField();
			mobilelbl = new Label("Customer Mobile:");
			mobilelbl.setFont(cartfont);
			mobiletf = new TextField();
			HBox hb = new HBox(20);
			TableView<Car> table = new TableView<>();
			ObservableList<Car> data = FXCollections.observableArrayList();
			table.setItems(data);
			TableColumn modelCol = new TableColumn("Model");
			modelCol.setMinWidth(100);
			modelCol.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
			TableColumn yearCol = new TableColumn("Year");
			yearCol.setMinWidth(100);
			yearCol.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
			TableColumn colorCol = new TableColumn("Color");
			colorCol.setMinWidth(100);
			colorCol.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
			TableColumn priceCol = new TableColumn("Price");
			priceCol.setMinWidth(100);
			priceCol.setCellValueFactory(new PropertyValueFactory<Car, Double>("price"));
			table.getColumns().addAll(modelCol, yearCol, colorCol, priceCol);

			for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
				Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
				data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
			}

			cart.setOnAction(a -> {

				Car car = table.getSelectionModel().getSelectedItem();
				if (car != null && nametf != null && mobiletf != null) {
					Order order = new Order(car, brandName.getText(), nametf.getText(),
					        Integer.parseInt(mobiletf.getText()), new Date(), "InProcess");
					queue.enQueue(order);
					nametf.setText("");
					mobiletf.setText("");
				}
//				queue.printQueue();
			});
			VBox cbvb = new VBox(5);
			cb = new ComboBox<String>();
			cb.getItems().addAll("Year", "Color", "Model", "Price");
			cb.setPromptText("Filter according to");
			hb.getChildren().addAll(namelbl, nametf, mobilelbl, mobiletf);
			cbvb.getChildren().addAll(cb);
			ToggleGroup group = new ToggleGroup();
			gray = new RadioButton("Gray");
			gray.setToggleGroup(group);
			gray.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("gray")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			black = new RadioButton("Black");
			black.setToggleGroup(group);
			black.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("black")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			white = new RadioButton("White");
			white.setToggleGroup(group);
			white.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("white")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			green = new RadioButton("Green");
			green.setToggleGroup(group);
			green.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("green")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			blue = new RadioButton("Blue");
			blue.setToggleGroup(group);
			blue.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("blue")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			red = new RadioButton("Red");
			red.setToggleGroup(group);
			red.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("red")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			metagray = new RadioButton("Metallic Gray");
			metagray.setToggleGroup(group);
			metagray.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("metallic gray")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			metasilver = new RadioButton("Metallic Silver");
			metasilver.setToggleGroup(group);
			metasilver.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getColor().toLowerCase().trim().equals("metallic silver")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			l2010 = new RadioButton("<2020");
			l2010.setToggleGroup(group);
			l2010.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getYear() < 2020) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			l2020 = new RadioButton(">=2020");
			l2020.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getYear() >= 2020) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			l2020.setToggleGroup(group);
			c200 = new RadioButton("C200");
			c200.setToggleGroup(group);
			c200.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("c200")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			c300 = new RadioButton("C300");
			c300.setToggleGroup(group);
			c300.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("c300")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			x = new RadioButton("X");
			x.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("x")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			x.setToggleGroup(group);
			l3 = new RadioButton("3");
			l3.setToggleGroup(group);
			l3.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("3")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			x3 = new RadioButton("X3");
			x3.setToggleGroup(group);
			x3.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("x3")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			x5 = new RadioButton("X5");
			x5.setToggleGroup(group);
			x5.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("x5")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			x6 = new RadioButton("X6");
			x6.setToggleGroup(group);
			x6.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("x6")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			mustang = new RadioButton("MUSTANG");
			mustang.setToggleGroup(group);
			mustang.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("mustang")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			rio = new RadioButton("RIO");
			rio.setToggleGroup(group);
			rio.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("rio")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			optima = new RadioButton("OPTIMA");
			optima.setToggleGroup(group);
			optima.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getModel().toLowerCase().trim().equals("optima")) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			range1 = new RadioButton("price<=100K");
			range1.setToggleGroup(group);
			range1.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getPrice() <= 100000.0) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			range2 = new RadioButton("100K<price<=150K");
			range2.setToggleGroup(group);
			range2.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getPrice() > 100000.0 && car.getPrice() <= 150000.0) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			range3 = new RadioButton("150K<price<=200K");
			range3.setToggleGroup(group);
			range3.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getPrice() <= 200000.0 && car.getPrice() > 150000.0) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			range4 = new RadioButton("price>200K");
			range4.setToggleGroup(group);
			range4.setOnAction(a -> {
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					if (car.getPrice() > 200000.0) {
						data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
					}
				}
				table.setItems(data);
			});
			next.setOnAction(a -> {
				brandName.setText(doublylist.getNode(brandName.getText()).getNext().getBrand());
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
				if (group.getSelectedToggle() != null) {
					group.getSelectedToggle().setSelected(false);
				}
			});
			prev.setOnAction(a -> {
				brandName.setText(doublylist.getNode(brandName.getText()).getPrev().getBrand());
				data.clear();
				for (int i = 0; i < doublylist.getNode(brandName.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(brandName.getText()).getList().getNode(i).getElement();
					data.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
				if (group.getSelectedToggle() != null) {
					group.getSelectedToggle().setSelected(false);
				}
			});
			cb.setOnAction(a -> {
				if (cb.getSelectionModel().getSelectedIndex() == 0) {
					cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, c200, c300,
					        x, l3, x3, x5, x6, mustang, rio, optima, range1, range2, range3, range4);
					cbvb.getChildren().addAll(l2010, l2020);
				} else if (cb.getSelectionModel().getSelectedIndex() == 1) {
					cbvb.getChildren().removeAll(l2010, l2020, c200, c300, x, l3, x3, x5, x6, mustang, rio, optima,
					        range1, range2, range3, range4);
					cbvb.getChildren().addAll(gray, black, white, green, blue, red, metagray, metasilver);
				} else if (cb.getSelectionModel().getSelectedIndex() == 2) {
					if (brandName.getText().equals("BMW")) {
						cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, l2010,
						        l2020, c200, c300, x, l3, mustang, rio, optima, range1, range2, range3, range4);
						cbvb.getChildren().addAll(x3, x5, x6);
					} else if (brandName.getText().equals("MERCEDES")) {
						cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, l2010,
						        l2020, x, l3, x3, x5, x6, mustang, rio, optima, range1, range2, range3, range4);
						cbvb.getChildren().addAll(c200, c300);
					} else if (brandName.getText().equals("TESLA")) {
						cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, l2010,
						        l2020, c200, c300, x3, x5, x6, mustang, rio, optima, range1, range2, range3, range4);
						cbvb.getChildren().addAll(x, l3);
					} else if (brandName.getText().equals("FORD")) {
						cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, l2010,
						        l2020, c200, c300, x, l3, x3, x5, x6, rio, optima, range1, range2, range3, range4);
						cbvb.getChildren().addAll(mustang);
					} else if (brandName.getText().equals("KIA")) {
						cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, l2010,
						        l2020, c200, c300, x, l3, x3, x5, x6, mustang, range1, range2, range3, range4);
						cbvb.getChildren().addAll(rio, optima);
					}
				} else if (cb.getSelectionModel().getSelectedIndex() == 3) {
					cbvb.getChildren().removeAll(gray, black, white, green, blue, red, metagray, metasilver, l2010,
					        l2020, c200, c300, x, l3, x3, x5, x6, mustang, rio, optima);
					cbvb.getChildren().addAll(range1, range2, range3, range4);
				}
			});
			GridPane gp = new GridPane();
			gp.setHgap(15);
			gp.setVgap(20);
			gp.setPadding(new Insets(10, 10, 10, 10));
			gp.add(prev, 0, 1);
			gp.add(brandName, 1, 0);
			gp.add(table, 1, 1);
			gp.add(hb, 1, 2);
			gp.add(cbvb, 2, 1);
			gp.add(next, 3, 1);
			gp.add(cart, 2, 2);
			gp.setAlignment(Pos.CENTER);
			gp.setHalignment(brandName, HPos.CENTER);
			gp.setValignment(cb, VPos.TOP);
			hb.setAlignment(Pos.CENTER);
			Scene s = new Scene(gp, 1000, 700);
			clientStage.setScene(s);
			clientStage.setTitle("Client Screen");
			clientStage.show();
		});
		admin.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		admin.setFont(buttonfont);
		admin.setOnMouseEntered(e -> {
			admin.setTextFill(lightPurple);
			admin.setStyle("-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;");
		});
		admin.setOnMouseExited(e -> {
			admin.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		admin.setOnAction(e -> {
			adminStage = new Stage();
			TabPane tabPane = new TabPane();
			Tab tab1 = new Tab("Orders Info");
			Tab tab2 = new Tab("Edit brand");
			Tab tab3 = new Tab("Edit car");
			Tab tab4 = new Tab("Summary Report");
			TableView<Order> tv = new TableView<>();
			ObservableList<Order> data = FXCollections.observableArrayList();
			TableColumn brandCol = new TableColumn("Brand");
			brandCol.setMinWidth(100);
			brandCol.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));
			TableColumn carCol = new TableColumn("Car");
			carCol.setMinWidth(200);
			carCol.setCellValueFactory(new PropertyValueFactory<Order, Car>("car"));
			TableColumn nameCol = new TableColumn("Customer Name");
			nameCol.setMinWidth(150);
			nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
			TableColumn mobileCol = new TableColumn("Customer Mobile");
			mobileCol.setMinWidth(100);
			mobileCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("customerMobile"));
			TableColumn dateCol = new TableColumn("Order Date");
			dateCol.setMinWidth(200);
			dateCol.setCellValueFactory(new PropertyValueFactory<Order, Date>("orderDate"));
			TableColumn statusCol = new TableColumn("Order Status");
			statusCol.setMinWidth(100);
			statusCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
			tv.getColumns().addAll(brandCol, carCol, nameCol, mobileCol, dateCol, statusCol);
			for (int i = 0; i < queue.size(); i++) {
				data.add((Order) queue.getNode(i).getElement());
			}
			tv.setItems(data);
			BorderPane bp = new BorderPane();
			bp.setPadding(new Insets(10, 10, 10, 10));
			Button show = new Button("Show last 10 sold Cars");
			Button process = new Button("Process Order");
			HBox hb = new HBox(15);
			hb.getChildren().addAll(show, process);
			hb.setAlignment(Pos.CENTER);
			Font button = Font.font("Comic Sans MS", FontWeight.MEDIUM, FontPosture.REGULAR, 14);
			show.setFont(button);
			process.setFont(button);
			process.setOnAction(a -> {

				Order firstOrder = data.get(0);
				SinglyLinkedList list = doublylist.getNode(firstOrder.getBrand()).getList();
				if (list.getNode(firstOrder.getCar()) != null) {
					list.remove(list.getNode((Car) firstOrder.getCar()).getElement());
					stack.push(queue.deQueue());
					((Order) stack.peek()).setOrderStatus("Finished");
					tv.getItems().clear();
					for (int i = 0; i < queue.size(); i++) {
						data.add((Order) queue.getNode(i).getElement());
					}

				} else {
					Stage choose = new Stage();
					Label unavailable = new Label("Order currently not available");
					unavailable.setWrapText(true);
					unavailable.setAlignment(Pos.CENTER);
					unavailable.setFont(buttonfont);
					Button cancel = new Button("Cancel Order");
					cancel.setFont(button);
					cancel.setOnAction(z -> {
						queue.deQueue();
						tv.getItems().clear();
						for (int i = 0; i < queue.size(); i++) {
							data.add((Order) queue.getNode(i).getElement());
						}
						choose.close();
					});
					Button postpone = new Button("Postpone Order");
					postpone.setFont(button);
					postpone.setOnAction(z -> {
						Order o = (Order) queue.deQueue();
						queue.enQueue(o);
						tempQueue.enQueue(o);
						tv.getItems().clear();
						for (int i = 0; i < queue.size(); i++) {
							data.add((Order) queue.getNode(i).getElement());
						}
						choose.close();
					});
					HBox choosehb = new HBox(5);
					choosehb.getChildren().addAll(cancel, postpone);
					choosehb.setAlignment(Pos.CENTER);
					VBox choosevb = new VBox(10);
					choosevb.getChildren().addAll(unavailable, choosehb);
					choosevb.setAlignment(Pos.CENTER);
					Scene s = new Scene(choosevb, 350, 100);
					choose.setTitle("Options Screen");
					choose.setScene(s);
					choose.show();
				}

//				doublylist.getNode(firstOrder.getBrand()).getList().printList();
//				queue.printQueue();
//				System.out.println("****************88");
//				tempQueue.printQueue();
//				System.out.println("++++++++++++++++++++++++");
//				System.out.println(queue.getFront().getElement());
//				System.out.println("*************");
//				stack.printStack();
			});
			show.setOnAction(a -> {
				Stage showStage = new Stage();
				Stack tempStack = new Stack();
				for (int i = 0; i < 10; i++) {
					tempStack.push(stack.pop());
				}
				TableView<Car> table1 = new TableView<>();
				ObservableList<Car> data1 = FXCollections.observableArrayList();
				table1.setItems(data1);
				TableColumn modelCol1 = new TableColumn("Model");
				modelCol1.setMinWidth(100);
				modelCol1.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
				TableColumn yearCol1 = new TableColumn("Year");
				yearCol1.setMinWidth(100);
				yearCol1.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
				TableColumn colorCol1 = new TableColumn("Color");
				colorCol1.setMinWidth(100);
				colorCol1.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
				TableColumn priceCol1 = new TableColumn("Price");
				priceCol1.setMinWidth(100);
				priceCol1.setCellValueFactory(new PropertyValueFactory<Car, Double>("price"));
				table1.getColumns().addAll(modelCol1, yearCol1, colorCol1, priceCol1);

				for (int i = 0; i < 10; i++) {
					Order order = (Order) (tempStack.pop());
					stack.push(order);
					Car car = order.getCar();
					data1.add(car);
				}
//				System.out.println("*******stack*********************");
//				stack.printStack();
				Scene showscene = new Scene(table1);
				showStage.setTitle("Last 10 sold Cars");
				showStage.setScene(showscene);
				showStage.show();

			});
			hb.setPadding(new Insets(5, 5, 5, 5));
			bp.setCenter(tv);
			bp.setBottom(hb);
			tab1.setContent(bp);
			/*******************************************************/
			Font labelfont = Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 14);
			Label addlbl = new Label("Add new brand: ");
			addlbl.setFont(labelfont);
			TextField addtf = new TextField();
			Button addbt = new Button("add");
			addbt.setFont(button);

			Label deletelbl = new Label("Delete an existing brand: ");
			deletelbl.setFont(labelfont);
			TextField deletetf = new TextField();
			Button deletebt = new Button("delete");
			deletebt.setFont(button);

			Label updatelbl = new Label("Update an existing brand");
			updatelbl.setFont(labelfont);
			ComboBox<String> brands = new ComboBox<>();
			for (int i = 0; i < doublylist.size(); i++) {
				brands.getItems().add(doublylist.getNode(i).getBrand());
			}
			brands.setPromptText("Choose a brand");
			ComboBox<String> brands2 = new ComboBox<>();
			for (int i = 0; i < doublylist.size(); i++) {
				brands2.getItems().add(doublylist.getNode(i).getBrand());
			}
			brands2.setPromptText("Choose a brand");
			TextField updatetf = new TextField();
			Button updatebt = new Button("update");
			updatebt.setFont(button);

			Label searchlbl = new Label("Search for a brand: ");
			searchlbl.setFont(labelfont);
			TextField searchtf = new TextField();
			TableView<DNode> brandTable = new TableView<>();
			ObservableList<DNode> brandData = FXCollections.observableArrayList();
			TableColumn brandColumn = new TableColumn("Brand");
			brandColumn.setMinWidth(100);
			brandColumn.setCellValueFactory(new PropertyValueFactory<DNode, String>("brand"));
			brandTable.setItems(brandData);
			brandTable.getColumns().add(brandColumn);
			Button searchbt = new Button("search");
			searchbt.setFont(button);
			brandTable.setMaxWidth(150);
			brandTable.setMaxHeight(300);
			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(addlbl, addtf, addbt);
			HBox hb2 = new HBox(10);
			hb2.getChildren().addAll(deletelbl, brands, deletebt);
			HBox hb3 = new HBox(10);
			hb3.getChildren().addAll(updatelbl, brands2, updatetf, updatebt);
			HBox hb4 = new HBox(10);
			hb4.getChildren().addAll(searchlbl, searchtf, searchbt);
			VBox vb = new VBox(10);
			vb.getChildren().addAll(hb1, hb2, hb3, hb4, brandTable);
			vb.setPadding(new Insets(10, 10, 10, 10));
			addbt.setOnAction(a -> {
				if (addtf.getText() != "" && doublylist.getNode(addtf.getText()) == null) {
					doublylist.add(addtf.getText());
					brands.getItems().add(addtf.getText());
					brands2.getItems().add(addtf.getText());
				}
//				doublylist.printList();
			});
			deletebt.setOnAction(a -> {
				String item = brands.getSelectionModel().getSelectedItem();
				if (item != null) {
					doublylist.remove(item);
					brands.getItems().remove(item);
					brands2.getItems().remove(item);
				}
//				doublylist.printList();
//				System.out.println(item);
			});
			updatebt.setOnAction(a -> {
				if (updatetf.getText() != "" && doublylist.getNode(updatetf.getText()) == null
				        && brands2.getSelectionModel().getSelectedItem() != null) {
					SinglyLinkedList l = doublylist.getNode(brands2.getSelectionModel().getSelectedItem()).getList();
					doublylist.add(updatetf.getText());
					doublylist.getNode(updatetf.getText()).setList(l);
					doublylist.remove(brands2.getSelectionModel().getSelectedItem());
					for (int i = 0; i < queue.size(); i++) {
						if (data.get(i).getBrand().equals(brands2.getSelectionModel().getSelectedItem()))
							data.get(i).setBrand(updatetf.getText());
					}

//					doublylist.printList();
				}

			});
			searchbt.setOnAction(a -> {
				if (searchtf.getText() != "") {
					brandTable.getItems().clear();
					for (int i = 0; i < doublylist.size(); i++) {
						if (doublylist.getNode(i).getBrand().toLowerCase().contains(searchtf.getText().toLowerCase())) {
							brandData.add(doublylist.getNode(i));
						}
					}
				}
			});
			tab2.setContent(vb);
			/********************************************************************/
			Label branddd = new Label(doublylist.getNode(0).getBrand());
			branddd.setFont(titlefont);
			Button nexttt = new Button("next>");
			nexttt.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			nexttt.setFont(buttonfont);
			nexttt.setOnMouseEntered(a -> {
				nexttt.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:red;");

			});
			nexttt.setOnMouseExited(a -> {
				nexttt.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			});
			Button prevvv = new Button("<previous");
			prevvv.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			prevvv.setFont(buttonfont);
			prevvv.setOnMouseEntered(a -> {
				prevvv.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:red;");

			});
			prevvv.setOnMouseExited(a -> {
				prevvv.setStyle(
				        "-fx-background-radius: 0px;-fx-background-color:transparent;-fx-border-color:transparent;-fx-text-fill:black;");
			});

			TableView<Car> tablee = new TableView<>();
			ObservableList<Car> dataa = FXCollections.observableArrayList();
			tablee.setItems(dataa);
			TableColumn modelCol = new TableColumn("Model");
			modelCol.setMinWidth(70);
			modelCol.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
			TableColumn yearCol = new TableColumn("Year");
			yearCol.setMinWidth(70);
			yearCol.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
			TableColumn colorCol = new TableColumn("Color");
			colorCol.setMinWidth(70);
			colorCol.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
			TableColumn priceCol = new TableColumn("Price");
			priceCol.setMinWidth(70);
			priceCol.setCellValueFactory(new PropertyValueFactory<Car, Double>("price"));
			tablee.getColumns().addAll(modelCol, yearCol, colorCol, priceCol);
			for (int i = 0; i < doublylist.getNode(branddd.getText()).getList().size(); i++) {
				Car car = (Car) doublylist.getNode(branddd.getText()).getList().getNode(i).getElement();
				dataa.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
			}
			nexttt.setOnAction(a -> {
				branddd.setText(doublylist.getNode(branddd.getText()).getNext().getBrand());
				dataa.clear();
				for (int i = 0; i < doublylist.getNode(branddd.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(branddd.getText()).getList().getNode(i).getElement();
					dataa.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
			});
			prevvv.setOnAction(a -> {
				branddd.setText(doublylist.getNode(branddd.getText()).getPrev().getBrand());
				data.clear();
				for (int i = 0; i < doublylist.getNode(branddd.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(branddd.getText()).getList().getNode(i).getElement();
					dataa.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
			});
			VBox info = new VBox(10);
			info.getChildren().addAll(branddd, tablee);
			BorderPane bpp = new BorderPane();
			bpp.setCenter(info);
			bpp.setRight(nexttt);
			bpp.setLeft(prevvv);
			tablee.setMaxHeight(300);
			tablee.setMaxWidth(500);
			info.setAlignment(Pos.CENTER);
			bpp.setAlignment(info, Pos.BOTTOM_CENTER);
			bpp.setAlignment(prevvv, Pos.CENTER);
			bpp.setAlignment(nexttt, Pos.CENTER);
			Label carmodel = new Label("Model: ");
			carmodel.setFont(labelfont);
			TextField modeltf = new TextField();
			Label caryear = new Label("Year: ");
			caryear.setFont(labelfont);
			TextField yeartf = new TextField();
			Label carcolor = new Label("Color: ");
			carcolor.setFont(labelfont);
			TextField colortf = new TextField();
			Label carprice = new Label("Price: ");
			carprice.setFont(labelfont);
			TextField pricetf = new TextField();
			Button addcar = new Button("add");
			addcar.setFont(button);
			addcar.setOnAction(a -> {
				Car newcar = new Car(modeltf.getText(), Integer.parseInt(yeartf.getText()), colortf.getText(),
				        Double.parseDouble(pricetf.getText()));
				doublylist.getNode(branddd.getText()).getList().add(newcar);
				tablee.getItems().clear();
				for (int i = 0; i < doublylist.getNode(branddd.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(branddd.getText()).getList().getNode(i).getElement();
					dataa.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
//				doublylist.getNode(branddd.getText()).getList().printList();
//				System.out.println("*********************");
			});
			HBox addbox = new HBox(5);
			addbox.getChildren().addAll(carmodel, modeltf, caryear, yeartf, carcolor, colortf, carprice, pricetf,
			        addcar);
			Label select = new Label("Select a car to delete or update: ");
			select.setFont(labelfont);
			Button delete = new Button("delete");
			delete.setFont(button);
			delete.setOnAction(a -> {
				int index = tablee.getSelectionModel().getSelectedIndex();
				doublylist.getNode(branddd.getText()).getList().remove(index);
				tablee.getItems().clear();
				for (int i = 0; i < doublylist.getNode(branddd.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(branddd.getText()).getList().getNode(i).getElement();
					dataa.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
//				doublylist.getNode(branddd.getText()).getList().printList();
//				System.out.println("*********************");
			});
			Button update = new Button("update");
			update.setFont(button);
			HBox selectbox = new HBox(5);
			selectbox.getChildren().addAll(select, delete, update);
			Label carmodel2 = new Label("Model: ");
			carmodel2.setFont(labelfont);
			TextField modeltf2 = new TextField();
			Label caryear2 = new Label("Year: ");
			caryear2.setFont(labelfont);
			TextField yeartf2 = new TextField();
			Label carcolor2 = new Label("Color: ");
			carcolor2.setFont(labelfont);
			TextField colortf2 = new TextField();
			Label carprice2 = new Label("Price: ");
			carprice2.setFont(labelfont);
			TextField pricetf2 = new TextField();
			Button updatecar = new Button("update");
			updatecar.setFont(button);
			updatecar.setDisable(true);
			update.setOnAction(a -> {
				Car item = tablee.getSelectionModel().getSelectedItem();
				modeltf2.setText(item.getModel());
				yeartf2.setText(item.getYear() + "");
				colortf2.setText(item.getColor());
				pricetf2.setText(item.getPrice() + "");
				updatecar.setDisable(false);
			});
			updatecar.setOnAction(a -> {
				int index = tablee.getSelectionModel().getSelectedIndex();
				doublylist.getNode(branddd.getText()).getList().remove(index);
				Car updatedcar = new Car(modeltf2.getText(), Integer.parseInt(yeartf2.getText()), colortf2.getText(),
				        Double.parseDouble(pricetf2.getText()));
				doublylist.getNode(branddd.getText()).getList().add(updatedcar);
				tablee.getItems().clear();
				for (int i = 0; i < doublylist.getNode(branddd.getText()).getList().size(); i++) {
					Car car = (Car) doublylist.getNode(branddd.getText()).getList().getNode(i).getElement();
					dataa.add(new Car(car.getModel(), car.getYear(), car.getColor(), car.getPrice()));
				}
//				doublylist.getNode(branddd.getText()).getList().printList();
//				System.out.println("*********************");
			});
			HBox updatebox = new HBox(5);
			updatebox.getChildren().addAll(carmodel2, modeltf2, caryear2, yeartf2, carcolor2, colortf2, carprice2,
			        pricetf2, updatecar);
			Label search = new Label("Search within this brand: ");
			search.setFont(labelfont);
			Button searchbrandbt = new Button("search");
			searchbrandbt.setFont(button);
			searchbrandbt.setOnAction(a -> {
				Stage searchStage = new Stage();
				Label searchlabel = new Label("Search according to: ");
				searchlabel.setFont(labelfont);
				ComboBox<String> searchcb = new ComboBox<>();
				searchcb.getItems().addAll("Model", "Year", "Color", "Price");
				TextField searchtxt = new TextField();
				Button searchbutton = new Button("search");
				searchbutton.setFont(button);
				HBox hbb = new HBox();
				hbb.getChildren().addAll(searchlabel, searchcb);
				HBox hbb2 = new HBox(5);
				hbb2.getChildren().addAll(searchtxt, searchbutton);
				hbb2.setAlignment(Pos.CENTER_LEFT);
				VBox vbb = new VBox(5);
				vbb.getChildren().addAll(hbb, hbb2);
				TableView<Car> searchtable = new TableView<>();
				ObservableList<Car> searchdata = FXCollections.observableArrayList();
				searchtable.setItems(searchdata);
				TableColumn searchmodelCol = new TableColumn("Model");
				searchmodelCol.setMinWidth(70);
				searchmodelCol.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
				TableColumn searchyearCol = new TableColumn("Year");
				searchyearCol.setMinWidth(70);
				searchyearCol.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
				TableColumn searchcolorCol = new TableColumn("Color");
				searchcolorCol.setMinWidth(70);
				searchcolorCol.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
				TableColumn searchpriceCol = new TableColumn("Price");
				searchpriceCol.setMinWidth(70);
				searchpriceCol.setCellValueFactory(new PropertyValueFactory<Car, Double>("price"));
				searchtable.getColumns().addAll(searchmodelCol, searchyearCol, searchcolorCol, searchpriceCol);
				searchtable.setMaxHeight(350);
				searchtable.setMaxWidth(320);
				BorderPane p = new BorderPane();
				p.setTop(vbb);
				p.setCenter(searchtable);
				p.setPadding(new Insets(10, 10, 10, 10));
				searchbutton.setOnAction(z -> {
					SinglyLinkedList list = doublylist.getNode(branddd.getText()).getList();
					if (searchtxt.getText() != "") {
						if (searchcb.getSelectionModel().getSelectedIndex() == 0) {
							searchtable.getItems().clear();
							for (int i = 0; i < list.size(); i++) {
								Car car = (Car) list.getNode(i).getElement();
								if (car.getModel().toLowerCase().contains(searchtxt.getText().toLowerCase())) {
									searchdata.add(car);
								}
							}
						} else if (searchcb.getSelectionModel().getSelectedIndex() == 1) {
							searchtable.getItems().clear();
							for (int i = 0; i < list.size(); i++) {
								Car car = (Car) list.getNode(i).getElement();
								if (Integer.toString(car.getYear()).contains(searchtxt.getText())) {
									searchdata.add(car);
								}
							}
						} else if (searchcb.getSelectionModel().getSelectedIndex() == 2) {
							searchtable.getItems().clear();
							for (int i = 0; i < list.size(); i++) {
								Car car = (Car) list.getNode(i).getElement();
								if (car.getColor().toLowerCase().contains(searchtxt.getText().toLowerCase())) {
									searchdata.add(car);
								}
							}
						} else if (searchcb.getSelectionModel().getSelectedIndex() == 3) {
							searchtable.getItems().clear();
							for (int i = 0; i < list.size(); i++) {
								Car car = (Car) list.getNode(i).getElement();
								if (Double.toString(car.getPrice()).contains(searchtxt.getText())) {
									searchdata.add(car);
								}
							}
						}
					}
				});
				Scene s = new Scene(p, 500, 500);
				searchStage.setScene(s);
				searchStage.setTitle("Search within " + branddd.getText());
				searchStage.show();
			});
			HBox searchbox = new HBox(5);
			searchbox.getChildren().addAll(search, searchbrandbt);
			VBox editvb = new VBox(3);
			editvb.getChildren().addAll(searchbox, selectbox, updatebox, addbox);
			editvb.setPadding(new Insets(5, 5, 5, 5));
			bpp.setPadding(new Insets(5, 5, 5, 5));

			bpp.setBottom(editvb);
			tab3.setContent(bpp);
			/***************************************************************/
			TableView<Brand> tableforsummary = new TableView<>();
			ObservableList<Brand> summary = FXCollections.observableArrayList();
			tableforsummary.setItems(summary);
			TableColumn c1 = new TableColumn("Brand");
			c1.setMinWidth(100);
			c1.setCellValueFactory(new PropertyValueFactory<Brand, String>("brand"));
			TableColumn c2 = new TableColumn("Highest price model");
			c2.setMinWidth(70);
			c2.setCellValueFactory(new PropertyValueFactory<Brand, String>("highestPriceModel"));
			TableColumn c3 = new TableColumn("Highest price");
			c3.setMinWidth(100);
			c3.setCellValueFactory(new PropertyValueFactory<Brand, Double>("highestPrice"));
			TableColumn c4 = new TableColumn("Lowest price model");
			c4.setMinWidth(70);
			c4.setCellValueFactory(new PropertyValueFactory<Brand, String>("lowestPriceModel"));

			TableColumn c5 = new TableColumn("Lowest price");
			c5.setMinWidth(70);
			c5.setCellValueFactory(new PropertyValueFactory<Brand, Double>("lowestPrice"));

			tableforsummary.getColumns().addAll(c1, c2, c3, c4, c5);
			for (int i = 0; i < doublylist.size(); i++) {
				String l = doublylist.getNode(i).getBrand();
				summary.add(new Brand(l));
			}
			tab4.setContent(tableforsummary);
			/***************************************************************/

			tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
			Scene s = new Scene(tabPane);
			adminStage.setScene(s);
			adminStage.setTitle("Admin Screen");
			adminStage.show();
		});
		save.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		save.setFont(buttonfont);
		save.setOnMouseEntered(e -> {
			save.setTextFill(lightPurple);
			save.setStyle("-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;");
		});
		save.setOnMouseExited(e -> {
			save.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		save.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(stage);
			try (FileWriter output = new FileWriter(f)) {
				if (f.length() == 0) {
					output.append("Brand, Model, Year, Color, Price\r\n");
				}
				for (int i = 0; i < doublylist.size(); i++) {
					for (int j = 0; j < doublylist.getNode(i).getList().size(); j++) {
						Car m = (Car) doublylist.getNode(i).getList().getNode(j).getElement();
						try {
							output.append(doublylist.getNode(i).getBrand() + ", " + m.getModel() + ", " + m.getYear()
							        + ", " + m.getColor() + ", " + (int) m.getPrice() / 1000 + "K\r\n");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
		});
		saveorders.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		saveorders.setFont(buttonfont);
		saveorders.setOnMouseEntered(e -> {
			saveorders.setTextFill(lightPurple);
			saveorders.setStyle("-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;");
		});
		saveorders.setOnMouseExited(e -> {
			saveorders.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		saveorders.setOnAction(e -> {
			if (!tempQueue.isEmpty()) {
				while (!queue.getFront().getElement().equals(tempQueue.getFront().getElement())) {
					queue.enQueue(queue.deQueue());
				}
			}
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(stage);
			try (FileWriter output = new FileWriter(f)) {
				if (f.length() == 0) {
					output.append(
					        "CustomerName, CustomerMobile, Brand, Model, Year, Color, Price, OrderDate, OrderStatus\r\n");
				}
				int stacksize = stack.size();
				for (int i = 0; i < stacksize; i++) {
					Order m = (Order) (stack.pop());
					String dateod = m.getOrderDate().getDate() + "/" + (m.getOrderDate().getMonth() + 1) + "/"
					        + (m.getOrderDate().getYear() + 1900);
					try {
						output.append(" " + m.getCustomerName().trim() + ", " + m.getCustomerMobile() + ", "
						        + m.getBrand().trim() + ", " + m.getCar().getModel().trim() + ", "
						        + m.getCar().getYear() + ", " + m.getCar().getColor().trim() + ", "
						        + (int) m.getCar().getPrice() / 1000 + "K, " + dateod + ", " + m.getOrderStatus().trim()
						        + "\r\n");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				int queuesize = queue.size();
				for (int i = 0; i < queuesize; i++) {
					Order m = (Order) (queue.deQueue());
					String dateod = m.getOrderDate().getDate() + "/" + (m.getOrderDate().getMonth() + 1) + "/"
					        + (m.getOrderDate().getYear() + 1900);
					try {
						output.append(" " + m.getCustomerName().trim() + ", " + m.getCustomerMobile() + ", "
						        + m.getBrand().trim() + ", " + m.getCar().getModel().trim() + ", "
						        + m.getCar().getYear() + ", " + m.getCar().getColor().trim() + ", "
						        + (int) m.getCar().getPrice() / 1000 + "K, " + dateod + ", " + m.getOrderStatus().trim()
						        + "\r\n");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

			} catch (IOException ex) {
				System.out.println("Error");
				ex.printStackTrace();
			}
		});
		HBox hb = new HBox(10);
		hb.getChildren().addAll(readCars, readOrders, client, admin, save, saveorders);
		hb.setAlignment(Pos.CENTER);
		VBox vb = new VBox(15);
		vb.getChildren().addAll(title, logoiv, hb);
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-background-color:BLACK;");
		Scene scene = new Scene(vb, 1100, 550);
		stage.setScene(scene);
		stage.setTitle("Main Screen");
		stage.show();
	}

	public class Brand {
		private String brand;
		private String highestPriceModel = null;
		private String lowestPriceModel = null;
		private double highestPrice = 0;
		private double lowestPrice = 0;

		public Brand(String brand) {
			this.brand = brand;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getHighestPriceModel() {
			SinglyLinkedList list = doublylist.getNode(this.getBrand()).getList();
			if (list.size() > 0) {
				Car expensive = (Car) list.getNode(0).getElement();
				for (int i = 1; i < list.size(); i++) {
					if (expensive.getPrice() <= ((Car) list.getNode(i).getElement()).getPrice()) {
						expensive = (Car) list.getNode(i).getElement();
					}
				}
				this.highestPriceModel = expensive.getModel();
			}
			return highestPriceModel;
		}

		public void setHighestPriceModel(String highestPriceModel) {
			this.highestPriceModel = highestPriceModel;
		}

		public String getLowestPriceModel() {
			SinglyLinkedList list = doublylist.getNode(this.getBrand()).getList();
			if (list.size() > 0) {
				Car cheap = (Car) list.getNode(0).getElement();
				for (int i = 1; i < list.size(); i++) {
					if (cheap.getPrice() >= ((Car) list.getNode(i).getElement()).getPrice()) {
						cheap = (Car) list.getNode(i).getElement();
					}
				}
				this.lowestPriceModel = cheap.getModel();
			}
			return lowestPriceModel;
		}

		public void setLowestPriceModel(String lowestPriceModel) {
			this.lowestPriceModel = lowestPriceModel;
		}

		public double getHighestPrice() {
			SinglyLinkedList list = doublylist.getNode(this.getBrand()).getList();
			if (list.size() > 0) {
				double max = ((Car) list.getNode(0).getElement()).getPrice();
				for (int i = 1; i < list.size(); i++) {
					max = Math.max(max, ((Car) list.getNode(i).getElement()).getPrice());
				}
				this.highestPrice = max;
			}
			return highestPrice;
		}

		public void setHighestPrice(double highestPrice) {
			this.highestPrice = highestPrice;
		}

		public double getLowestPrice() {
			SinglyLinkedList list = doublylist.getNode(this.getBrand()).getList();
			if (list.size() > 0) {
				double min = ((Car) list.getNode(0).getElement()).getPrice();
				for (int i = 1; i < list.size(); i++) {
					min = Math.min(min, ((Car) list.getNode(i).getElement()).getPrice());
				}
				this.lowestPrice = min;
			}
			return lowestPrice;
		}

		public void setLowestPrice(double lowestPrice) {
			this.lowestPrice = lowestPrice;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
