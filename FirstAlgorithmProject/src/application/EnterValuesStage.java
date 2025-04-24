package application;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javafx.stage.FileChooser;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import java.util.Random;
import javafx.animation.Animation;

public class EnterValuesStage {
	int[] arrayCoins;
	static final int MAX_COINS = 100; // Set to a reasonable number
	private int coinCount;
	TextField tf = new TextField();
	TextField tfTwoPlayer1 = new TextField();
	TextField tfTwoPlayer2 = new TextField();

	public EnterValuesStage() {
		// Set a default coinCount if not set yet
		this.coinCount = 0;
		this.arrayCoins = null;
	}

	public void setCoinCount(int count) {
		this.coinCount = count;
		this.arrayCoins = new int[coinCount]; // Re-initialize array with the correct size
	}

	public int getCoinCount() {
		return coinCount;
	}

	public int[] getArrayCoins() {
		if (arrayCoins == null || arrayCoins.length == 0) {
			showAlert("Warning", "Coins array is null or empty, Initializing with default values!");
			arrayCoins = new int[coinCount]; // Default values
		}
		return arrayCoins; // Returns the current arrayCoins
	}

	public void setArrayCoins(int[] arrayCoins) {
		if (arrayCoins == null || arrayCoins.length == 0) {
			showAlert("Error", "Coins array is empty or not properly set.");
		} else {
			this.arrayCoins = arrayCoins;
		}
	}

	public void secondStage() {
		Stage stage1 = new Stage();

		Label lblFirst = new Label("Choose the way that you want to enter values");
		lblFirst.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

		Image image = new Image(getClass().getResource("/application/images/520_coins.png").toExternalForm());

		ImageView img = new ImageView(image);
		img.setFitHeight(200);
		img.setFitWidth(200);

		Image image2 = new Image(getClass().getResource("/application/images/firstStage.jpg").toExternalForm());

		BackgroundImage backgroundimage = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, true, true, false, true));
		Background background = new Background(backgroundimage);
		Button btFromFile = new Button("From File");
		btFromFile.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btFromFile.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
				"-fx-border-color: black; " + // Border color
				"-fx-border-width: 2; " + // Border width
				"-fx-border-radius: 20;" // Border radius to match the background radius
		);
		addEffect(btFromFile);

		btFromFile.setOnAction(e -> {
			fileStage();

		});

		Button btUser = new Button("By User");
		btUser.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btUser.setStyle("-fx-background-color: lightblue; -fx-background-radius: 20; "
				+ "-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;");
		addEffect(btUser);

		btUser.setOnAction(e -> secondSceneEnterByUser());

		Button btChooseRndomly = new Button("Random");
		btChooseRndomly.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btChooseRndomly.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																										// corners
				"-fx-border-color: black; " + // Border color
				"-fx-border-width: 2; " + // Border width
				"-fx-border-radius: 20;" // Border radius to match the background radius
		);
		addEffect(btChooseRndomly);

		btChooseRndomly.setOnAction(e -> {
			Stage randomStage = new Stage();
			if (coinCount <= 0) {
				showAlert("Error", "Please enter a valid number of coins first!");
				return;
			}
			randomStage.setTitle("🎲 Random Coin Generator 🎲");

			Label lblInstruction = new Label("Enter The Range of Coins Values");
			lblInstruction.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
			lblInstruction.setTextFill(Color.DARKSLATEBLUE);
			lblInstruction.setTextAlignment(TextAlignment.CENTER);

			Label lblRange = new Label("Range :");
			lblRange.setFont(Font.font("Comic Sans MS", 18));
			lblRange.setTextFill(Color.DARKBLUE);
			TextField txtRange = new TextField();
			txtRange.setPromptText("For Example 10-35");

			Button btGenerate = new Button("Generate Coins");
//			btGenerate.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
//			btGenerate.setStyle("-fx-background-color: #32CD32; " + "-fx-background-radius: 15; "
//					+ "-fx-border-color: darkgreen; " + "-fx-border-width: 2; " + "-fx-border-radius: 15;");
			btGenerate.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btGenerate.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btGenerate);

			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			Button btNext = new Button("Next -⫸");
			btNext.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btNext.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
					"-fx-border-color: black; " + "-fx-border-width: 2; " + "-fx-border-radius: 20;");
			addEffect(btNext);

			HBox hbox1 = new HBox(50);
			hbox1.setAlignment(Pos.CENTER);
			hbox1.getChildren().addAll(lblRange, txtRange);

			HBox hbox = new HBox(40);
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(btBack, btGenerate);

			btGenerate.setOnAction(event -> {
				String range = txtRange.getText().trim();
//				String coinCountText = op.tfNumCoins.getText().trim();
				// Check if range or coin count is empty
				if (range.isEmpty()) {
					showAlert("Invalid Input", "Please fill in all fields.");
					return;
				}

				try {
					// Parse range and coin count input
					String[] rangeLimits = range.split("-");
					if (rangeLimits.length != 2) {
						showAlert("Invalid Input", "Range must be in the format 'min-max' (e.g., 10-35).");
						return;
					}
					int min = Integer.parseInt(rangeLimits[0].trim());
					int max = Integer.parseInt(rangeLimits[1].trim());
//					int coinCount = Integer.parseInt(coinCountText);

					// Validate range and count
					if (min >= max || coinCount <= 0) {
						showAlert("Invalid Input", "Ensure range is valid and coin count is positive.");
						return;
					}

					// Generate random coin values within the range
					int[] randomCoins = new int[coinCount];
					Random random = new Random();
					for (int i = 0; i < coinCount; i++) {
						randomCoins[i] = random.nextInt((max - min) + 1) + min;
					}
//					showAlert("Notification", "Range: " + range + ", Coin Count: " + coinCount);

//					setCoinCount(coinCount);
					setArrayCoins(randomCoins);
					showAlert("Success", "Random coin values generated!");
					hbox.getChildren().add(btNext);
					btNext.setOnAction(ev -> {
						// Proceed to next stage
						randomScene();
					});
				} catch (ArrayIndexOutOfBoundsException ex) {
					showAlert("Invalid Input", "Please enter a valid range and coin count.");
				}
			});

			btBack.setOnAction(even -> {
				randomStage.close();
			});
			// Set up the scene and show the stage
			VBox layout = new VBox(80, lblInstruction, hbox1, hbox);
			layout.setPadding(new Insets(30));
			layout.setAlignment(Pos.CENTER);
			layout.setStyle("-fx-background-color: #F5F5F5; " + "-fx-border-color: #FFD700; " + "-fx-border-width: 3; "
					+ "-fx-border-radius: 20;");

			Scene scene = new Scene(layout, 1550, 800);
			randomStage.setScene(scene);
			randomStage.show();
		});
		// Back button
		Button btBack = new Button();
		Image imgage = new Image(getClass().getResource("/application/images/Back.png").toExternalForm());
		ImageView imageView = new ImageView(imgage);
		imageView.setFitHeight(80);
		imageView.setFitWidth(80);
		btBack.setGraphic(imageView);
		btBack.setStyle("-fx-background-radius: 15; " + "-fx-border-width: 2; " + "-fx-border-radius: 15;");

		btBack.setOnAction(e -> stage1.close()); // Close stage2 on back button click

		VBox vbox = new VBox(30);
		vbox.setBackground(background);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(img, lblFirst, btFromFile, btUser, btChooseRndomly, btBack);

		Scene scene1 = new Scene(vbox, 1550, 800);
		stage1.setScene(scene1);
		stage1.setTitle("Enter Coin Values");
		stage1.show();
	}

	public void addEffect(Button button) {
		button.setOnMouseEntered(e -> button.setStyle(button.getStyle() + "-fx-opacity: 0.8;"));
		button.setOnMouseExited(e -> button.setStyle(button.getStyle().replace("-fx-opacity: 0.8;", "")));
	}

	private void secondSceneEnterByUser() {
		Stage stage2 = new Stage();

		Label lblCoins = new Label("Enter coin values separated by spaces:");
		lblCoins.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

		Image image = new Image(getClass().getResource("/application/images/coins.png").toExternalForm());

		ImageView image2 = new ImageView(image);
		image2.setFitHeight(120);
		image2.setFitWidth(100);

		HBox hbox = new HBox(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(lblCoins, image2);

		TextArea taCoins = new TextArea();
		taCoins.setPromptText("Example: 4 15 7 3 8 9");
		taCoins.setMaxWidth(600);
		// Style the TextArea
		taCoins.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ccc; ");
		Button btInsert = new Button("Insert Coins");
		btInsert.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btInsert.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
				"-fx-border-color: black; " + // Border color
				"-fx-border-width: 2; " + // Border width
				"-fx-border-radius: 20;" // Border radius to match the background radius
		);
		addEffect(btInsert);

		// Back button
		Button btBack = new Button("⫷- Back");
		btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
				"-fx-border-color: black; " + // Border color
				"-fx-border-width: 2; " + // Border width
				"-fx-border-radius: 20;" // Border radius to match the background radius
		);
		addEffect(btBack);

		// Layout for buttons and inputs
		HBox box = new HBox(40);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(btBack, btInsert);
		btInsert.setOnAction(e -> {
			try {
				String[] coinValues = taCoins.getText().trim().split("\\s+");
				if (coinValues.length != coinCount) {
					showAlert("Invalid Input", "Please enter exactly " + coinCount + " coin values.");
					return;
				}

				arrayCoins = new int[coinCount];
				for (int i = 0; i < coinCount; i++) {
					arrayCoins[i] = Integer.parseInt(coinValues[i].trim());
					if (arrayCoins[i] < 0) {
						showAlert("Invalid Input",
								"Negative coin values are not allowed. Please enter positive integers!");
						taCoins.clear();
						return;
					}
				}

				setArrayCoins(arrayCoins); // Set the coins for the next stage

				showAlert("Success", "Coin values inserted successfully! Coins collected: " + taCoins.getText());

				Button btNext = new Button("Next -⫸");
				btNext.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				btNext.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
						"-fx-border-color: black; " + "-fx-border-width: 2; " + "-fx-border-radius: 20;");
				addEffect(btNext);
				btNext.setOnAction(event -> {
					if (taCoins.getText().isEmpty()) {
						showAlert("Error", "Please re-enter values in the text area!");
					} else if (arrayCoins != null && arrayCoins.length > 0) {
						ChoosingPlayersStage start = new ChoosingPlayersStage();
						start.choiceStage();
					}
				});
				box.getChildren().add(btNext);

			} catch (NumberFormatException ex) {
				showAlert("Invalid Input", "Please enter valid integers for coins!");
			}
		});
		btBack.setOnAction(e -> stage2.close()); // Close stage2 on back button click

		VBox vBox2 = new VBox(80);
		vBox2.setAlignment(Pos.CENTER);
		vBox2.getChildren().addAll(hbox, taCoins, box);
		vBox2.setStyle("-fx-background-color: lightgray");

		Scene scene2 = new Scene(vBox2, 1550, 800);
		stage2.setScene(scene2);
		stage2.show();
	}

	public void fileStage() {
		Stage stage = new Stage();
		Label lblSelectFile = new Label("Select The File: ");
		lblSelectFile.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Button btSelectFile = new Button("Select");
		btSelectFile.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		btSelectFile.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 15; " + // Rounded corners
				"-fx-border-color: black; " + "-fx-border-width: 2; " + "-fx-border-radius: 15;");
		addEffect(btSelectFile);

		HBox hbox = new HBox(80);
		hbox.setAlignment(Pos.CENTER);
		Button btNext = new Button("Next -⫸");
		btNext.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btNext.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
				"-fx-border-color: black; " + "-fx-border-width: 2; " + "-fx-border-radius: 20;");
		addEffect(btNext);

		Button btBack = new Button("⫷- Back");
		btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
				"-fx-border-color: black; " + "-fx-border-width: 2; " + "-fx-border-radius: 20;");
		addEffect(btBack);

		btBack.setOnAction(e -> {
			stage.close();
		});
		hbox.getChildren().add(btBack);
		VBox box = new VBox(20);
		box.getChildren().addAll(lblSelectFile, btSelectFile);
		TextArea fileOutput = new TextArea();
		fileOutput.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded corners
				"-fx-border-color: black; " + "-fx-border-width: 2; " + "-fx-border-radius: 20;");
		fileOutput.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

		btSelectFile.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Coin Values File");
			File file = fileChooser.showOpenDialog(new Stage());
			if (file != null) {
				try (Scanner in = new Scanner(file)) {
					// Read the number of coins
					String firstLine = in.nextLine();
					if (firstLine.startsWith("The number of coins:")) {
						int coinCount = Integer.parseInt(firstLine.split(":")[1].trim());
						setCoinCount(coinCount); // Set the number of coins in EnterValuesStage

						// Read the values of the coins
						String secondLine = in.nextLine();
						if (secondLine.startsWith("The values of the coins:")) {
							String[] coinValuesStr = secondLine.split(":")[1].trim().split(",");
							int[] coinValues = new int[coinValuesStr.length];
							for (int i = 0; i < coinValuesStr.length; i++) {
								coinValues[i] = Integer.parseInt(coinValuesStr[i].trim());
							}
							fileOutput.setEditable(false);
							// Print individual coin values
							String output = "The number of coins: " + coinCount + "\n";
							output += "The Values of coins: ";
							for (int value : coinValues) {
								output += value + " ";
							}
							fileOutput.setText(output);
							setArrayCoins(coinValues); // Set the arrayCoins with values from the file
							showAlert("Success", "Coin values loaded successfully!");
							btNext.setOnAction(ev -> {
								fileScene();
							});
							hbox.getChildren().add(btNext);

						} else {
							showAlert("Error", "Invalid format for coin values!");
						}
					} else {
						showAlert("Error", "Invalid format for the number of coins!");
					}
				} catch (FileNotFoundException ex) {
					showAlert("Error", "File not found!");
				} catch (Exception ex) {
					showAlert("Error", "An error occurred while reading the file!");
				}
			}
		});

		VBox vbox = new VBox(30);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(fileOutput, hbox);
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setHgap(15);
		grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);
		grid.add(box, 0, 0);
		grid.add(vbox, 4, 0);
		grid.setStyle("-fx-background-color: #F5F5F5; " + "-fx-border-color: #FFD700; " + "-fx-border-width: 4; "
				+ "-fx-border-radius: 5;");
		Scene scene = new Scene(grid, 1550, 800);
		stage.setScene(scene);
		stage.show();
	}

	public void fileScene() {

		Stage stage = new Stage();
//		EnterValuesStage e = new EnterValuesStage();
		Label lblChoose = new Label("Choose 1 or 2 players: ");
		lblChoose.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

		ToggleGroup tgGroup = new ToggleGroup();
		RadioButton rb1 = new RadioButton("one player");
		rb1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));

		RadioButton rb2 = new RadioButton("two players");
		rb2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));

		rb1.setToggleGroup(tgGroup);
		rb2.setToggleGroup(tgGroup);
		HBox box = new HBox(50);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(rb1, rb2);
		Image image2 = new Image(getClass().getResource("/application/images/coins_background.jpg").toExternalForm());
		BackgroundImage backgroundimage = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, true, true, false, true));
		Background background = new Background(backgroundimage);
		VBox vbox = new VBox(40);
		vbox.setPadding(new Insets(100, 20, 20, 20));
		vbox.setAlignment(Pos.CENTER);
		vbox.setBackground(background);
		vbox.getChildren().addAll(lblChoose, box);
//		vbox.setStyle("-fx-background-color: lightgray");
		rb1.setOnAction(event -> {
			Label lblName = new Label("The name of the Player: ");
			lblName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			tf.setMaxWidth(400);
			tf.setPromptText("Enter Player name");

			Button btStart = new Button("Let's Start -⫸");
			btStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btStart.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btStart);

			btStart.setOnAction(ev -> {

				StartTheGameOnePlayer start = new StartTheGameOnePlayer(getArrayCoins()); // Pass the name
				start.startTheGame(getArrayCoins(), tf);

			});

			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			btBack.setOnAction(ev -> {
				stage.close();
			});

			HBox hbox = new HBox(100);
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(btBack, btStart);

			vbox.getChildren().addAll(lblName, tf, hbox);
		});
		rb2.setOnAction(ev -> {
			Label lbl1 = new Label("The name of the first Player: ");
			lbl1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
//			TextField text1 = new TextField();
			tfTwoPlayer1.setMaxWidth(400);
			tfTwoPlayer1.setPromptText("Enter the name of the first Player");
			Label lbl2 = new Label("The name of the second Player: ");
			lbl2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
//			TextField text2 = new TextField();
			tfTwoPlayer2.setMaxWidth(400);
			tfTwoPlayer2.setPromptText("Enter the name of the second Player");
			Button btStart = new Button("Let's Start -⫸");
			btStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btStart.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			btStart.setOnAction(event -> {
				String playerName1 = tfTwoPlayer1.getText().trim();
				String playerName2 = tfTwoPlayer2.getText().trim();

				// Check if the text field is empty
				if (playerName1.isEmpty() || playerName2.isEmpty()) {
					showAlert("Error", "Please enter a name in the text field!");
				} else {

					TwoPlayers two = new TwoPlayers();
					two.playWithTwoPlayers();

				}
			});
			addEffect(btStart);

			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			btBack.setOnAction(eve -> {
				stage.close();
			});

			HBox hbox1 = new HBox(100);
			hbox1.setAlignment(Pos.CENTER);
			hbox1.getChildren().addAll(btBack, btStart);

			vbox.getChildren().addAll(lbl1, tfTwoPlayer1, lbl2, tfTwoPlayer2, hbox1);

		});

		Scene scene = new Scene(vbox, 1550, 800);
		stage.setScene(scene);
		stage.show();
	}

	public void randomScene() {
		TextField tf = new TextField();

		Stage stage = new Stage();
//		EnterValuesStage e = new EnterValuesStage();
		Label lblChoose = new Label("Choose 1 or 2 players: ");
		lblChoose.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

		ToggleGroup tgGroup = new ToggleGroup();
		RadioButton rb1 = new RadioButton("one player");
		rb1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		RadioButton rb2 = new RadioButton("two players");
		rb2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		rb1.setToggleGroup(tgGroup);
		rb2.setToggleGroup(tgGroup);
		HBox box = new HBox(50);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(rb1, rb2);
		Image image2 = new Image(getClass().getResource("/application/images/coins_background.jpg").toExternalForm());

		BackgroundImage backgroundimage = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, true, true, false, true));
		Background background = new Background(backgroundimage);
		VBox vbox = new VBox(40);
		vbox.setAlignment(Pos.CENTER);
		vbox.setBackground(background);
		vbox.getChildren().addAll(lblChoose, box);
//		vbox.setStyle("-fx-background-color: lightgray");
		rb1.setOnAction(event -> {
			Label lblName = new Label("The name of the Player: ");
			lblName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			tf.setMaxWidth(400);
			tf.setPromptText("Enter Player name");

			Button btStart = new Button("Let's Start -⫸");
			btStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btStart.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btStart);

			btStart.setOnAction(ev -> {
				String playerName = tf.getText().trim();

				// Check if the text field is empty
				if (playerName.isEmpty()) {
					showAlert("Error", "Please enter a name in the text field!");
				} else {
					// Print player name and prevent multiple instances of StartingGameStage
//					showAlert("Success","Player 1's name: " + playerName+" Inserted Succesfully");

					StartTheGameOnePlayer start = new StartTheGameOnePlayer(getArrayCoins()); // Pass the name
					start.startTheGame(getArrayCoins(), tf);

				}
			});

			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			btBack.setOnAction(ev -> {
				stage.close();
			});

			HBox hbox = new HBox(100);
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(btBack, btStart);

			vbox.getChildren().addAll(lblName, tf, hbox);
		});
		rb2.setOnAction(ev -> {
			Label lbl1 = new Label("The name of the first Player: ");
			lbl1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
//			TextField text1 = new TextField();
			tfTwoPlayer1.setMaxWidth(400);
			tfTwoPlayer1.setPromptText("Enter the name of the first Player");
			Label lbl2 = new Label("The name of the second Player: ");
			lbl2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
//			TextField text2 = new TextField();
			tfTwoPlayer2.setMaxWidth(400);
			tfTwoPlayer2.setPromptText("Enter the name of the second Player");
			Button btStart = new Button("Let's Start -⫸");
			btStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btStart.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btStart);

			btStart.setOnAction(event -> {
				String playerName1 = tfTwoPlayer1.getText().trim();
				String playerName2 = tfTwoPlayer2.getText().trim();

				// Check if the text field is empty
				if (playerName1.isEmpty() || playerName2.isEmpty()) {
					showAlert("Error", "Please enter a name in the text field!");
				} else {

					TwoPlayers two = new TwoPlayers();
					two.playWithTwoPlayers();

				}
			});

			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			btBack.setOnAction(eve -> {
				stage.close();
			});

			HBox hbox1 = new HBox(100);
			hbox1.setAlignment(Pos.CENTER);
			hbox1.getChildren().addAll(btBack, btStart);

			vbox.getChildren().addAll(lbl1, tfTwoPlayer1, lbl2, tfTwoPlayer2, hbox1);

		});

		Scene scene = new Scene(vbox, 1550, 800);
		stage.setScene(scene);
		stage.show();
	}

	void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public class ChoosingPlayersStage {

		TextField tf = new TextField();

		public void choiceStage() {
			Stage stage = new Stage();
			Label lblChoose = new Label("Choose 1 or 2 players: ");
			lblChoose.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

			ToggleGroup tgGroup = new ToggleGroup();
			RadioButton rb1 = new RadioButton("one player");
			rb1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			RadioButton rb2 = new RadioButton("two players");
			rb2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			rb1.setToggleGroup(tgGroup);
			rb2.setToggleGroup(tgGroup);
			HBox box = new HBox(50);
			box.setAlignment(Pos.CENTER);
			box.getChildren().addAll(rb1, rb2);
			VBox vBox = new VBox(50);
			vBox.setAlignment(Pos.CENTER);
			vBox.getChildren().addAll(lblChoose, box);
//			vBox.setStyle("-fx-background-color: lightgray");
			rb1.setOnAction(event -> {
				Label lblName = new Label("The name of the Player: ");
				lblName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				tf.setMaxWidth(400);
				tf.setPromptText("Enter Player name");

				Button btStart = new Button("Let's Start -⫸");
				btStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				btStart.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																										// corners
						"-fx-border-color: black; " + // Border color
						"-fx-border-width: 2; " + // Border width
						"-fx-border-radius: 20;" // Border radius to match the background radius
				);
				addEffect(btStart);

				btStart.setOnAction(ev -> {
					String playerName = tf.getText().trim();

					// Check if the text field is empty
					if (playerName.isEmpty()) {
						showAlert("Error", "Please enter a name in the text field!");
					} else {
						// Print player name and prevent multiple instances of StartingGameStage
//						System.out.println("Player 1's name: " + playerName);

						StartTheGameOnePlayer start = new StartTheGameOnePlayer(getArrayCoins()); // Pass the name
						start.startTheGame(getArrayCoins(), tf);

					}
				});

				Button btBack = new Button("⫷- Back");
				btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																										// corners
						"-fx-border-color: black; " + // Border color
						"-fx-border-width: 2; " + // Border width
						"-fx-border-radius: 20;" // Border radius to match the background radius
				);
				addEffect(btBack);

				btBack.setOnAction(ev -> {
					stage.close();
				});

				HBox hbox = new HBox(100);
				hbox.setAlignment(Pos.CENTER);
				hbox.getChildren().addAll(btBack, btStart);

				vBox.getChildren().addAll(lblName, tf, hbox);
			});
			rb2.setOnAction(ev -> {
				Label lbl1 = new Label("The name of the first Player: ");
				lbl1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				tfTwoPlayer1.setMaxWidth(400);
				tfTwoPlayer1.setPromptText("Enter the name of the first Player");
				Label lbl2 = new Label("The name of the second Player: ");
				lbl2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				tfTwoPlayer2.setMaxWidth(400);
				tfTwoPlayer2.setPromptText("Enter the name of the second Player");
				Button btStart = new Button("Let's Start -⫸");
				btStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				btStart.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																										// corners
						"-fx-border-color: black; " + // Border color
						"-fx-border-width: 2; " + // Border width
						"-fx-border-radius: 20;" // Border radius to match the background radius
				);
				addEffect(btStart);

				btStart.setOnAction(event -> {
					String playerName1 = tfTwoPlayer1.getText().trim();
					String playerName2 = tfTwoPlayer2.getText().trim();

					// Check if the text field is empty
					if (playerName1.isEmpty() || playerName2.isEmpty()) {
						showAlert("Error", "Please enter a name in the text field!");
					} else {

						TwoPlayers two = new TwoPlayers();
						two.playWithTwoPlayers();

					}
				});

				Button btBack = new Button("⫷- Back");
				btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																										// corners
						"-fx-border-color: black; " + // Border color
						"-fx-border-width: 2; " + // Border width
						"-fx-border-radius: 20;" // Border radius to match the background radius
				);
				addEffect(btBack);

				btBack.setOnAction(eve -> {
					stage.close();
				});

				HBox hbox1 = new HBox(100);
				hbox1.setAlignment(Pos.CENTER);
				hbox1.getChildren().addAll(btBack, btStart);

				vBox.getChildren().addAll(lbl1, tfTwoPlayer1, lbl2, tfTwoPlayer2, hbox1);

			});
			Image image2 = new Image(
					getClass().getResource("/application/images/coins_background.jpg").toExternalForm());

			BackgroundImage backgroundimage = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
					new BackgroundSize(0, 0, true, true, false, true));
			Background background = new Background(backgroundimage);
			vBox.setBackground(background);

			Scene scene = new Scene(vBox, 1550, 800);
			stage.setScene(scene);
			stage.show();
		}

	}

	public class StartTheGameOnePlayer {
		private int i = 0; // Left pointer
		private int j; // Right pointer
		private HBox box = new HBox(10); // Coin UI container
		private Label lblStatus = new Label(); // Status display label
		private StringBuilder animationSteps = new StringBuilder(); // Logs simulation steps
		private String playerName = " "; // Player name
		private boolean isPlayerTurn = true; // Alternates turns between the player and the computer
		private int[][] dp; // Dynamic programming table
		private int maxAmount; // Maximum amount collected
		private int playerScore = 0; // Player's score
		private int computerScore = 0; // Computer's score
		// Track coins chosen by each player
		private StringBuilder playerOneCoins = new StringBuilder();
		private StringBuilder playerTwoCoins = new StringBuilder();
		private PauseTransition pause; // Declare the animation globally

		// Constructor to initialize coins and player name
		public StartTheGameOnePlayer(int[] coins) {
			this.j = coins.length - 1; // Initialize the right pointer
			this.dp = new int[coins.length][coins.length]; // Initialize the DP table
		}

		public void startTheGame(int[] coins, TextField tf) {
			Image image = new Image(getClass().getResource("/application/images/gold-coins.png").toExternalForm());
			ImageView image2 = new ImageView(image);
			image2.setFitHeight(120);
			image2.setFitWidth(200);

			Button bt1 = new Button("Start Animation");
			bt1.setFont(Font.font("Arial", 15));
			bt1.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			addEffect(bt1);

			// Add animation to the coin button
			ScaleTransition st = new ScaleTransition(Duration.millis(200), bt1);
			st.setFromX(1.0);
			st.setToX(1.1);
			st.setFromY(1.0);
			st.setToY(1.1);
			st.setAutoReverse(true);
			bt1.setOnMouseEntered(e -> st.playFromStart());

			Button btnStopAnimation = new Button("Stop Animation");
			btnStopAnimation.setFont(Font.font("Arial", 15));
			btnStopAnimation
					.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			addEffect(btnStopAnimation);

			// Add animation to the coin button
			ScaleTransition stt = new ScaleTransition(Duration.millis(200), btnStopAnimation);
			stt.setFromX(1.0);
			stt.setToX(1.1);
			stt.setFromY(1.0);
			stt.setToY(1.1);
			stt.setAutoReverse(true);
			btnStopAnimation.setOnMouseEntered(e -> stt.playFromStart());
			btnStopAnimation.setOnAction(e -> stopAnimation()); // Stop animation on click

			Button bt2 = new Button("Show DP Table");
			bt2.setFont(Font.font("Arial", 15));
			bt2.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			addEffect(bt2);

			// Add animation to the coin button
			ScaleTransition st2 = new ScaleTransition(Duration.millis(200), bt2);
			st2.setFromX(1.0);
			st2.setToX(1.1);
			st2.setFromY(1.0);
			st2.setToY(1.1);
			st2.setAutoReverse(true);
			bt2.setOnMouseEntered(e -> st2.playFromStart());

			Button btRestart = new Button("Restart"); // Reset button
			btRestart.setFont(Font.font("Arial", 15));
			btRestart.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			addEffect(btRestart);

			// Add animation to the coin button
			ScaleTransition st3 = new ScaleTransition(Duration.millis(200), btRestart);
			st3.setFromX(1.0);
			st3.setToX(1.1);
			st3.setFromY(1.0);
			st3.setToY(1.1);
			st3.setAutoReverse(true);
			btRestart.setOnMouseEntered(e -> {
				st3.playFromStart();
			});

			Button btShowCoins = new Button("Show Coins Chosen"); // Reset button
			btShowCoins.setFont(Font.font("Arial", 15));
			btShowCoins.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			addEffect(btShowCoins);
			// Add action to show coins chosen
			btShowCoins.setOnAction(e -> {
				// Update the content of the TextArea with the chosen coins
				String playerOneChosen = playerOneCoins.length() > 0 ? playerOneCoins.toString() : "No coins selected.";// If
																														// true:
																														// The
																														// chosen
																														// coins
																														// for
																														// Player
																														// 1
																														// are
																														// converted
																														// to
																														// a
																														// string
																														// (playerOneCoins.toString()).
				// If false: Defaults to "No coins selected.", indicating that Player 1 hasn’t
				// picked any coins.
				String playerTwoChosen = playerTwoCoins.length() > 0 ? playerTwoCoins.toString() : "No coins selected.";
				Alert alert = new Alert(AlertType.INFORMATION); // Create an information alert
				alert.setTitle("Coins Chosen");
				alert.setHeaderText("Coins Collected by Each Player:");
				alert.setContentText("Coins Collected by Each Player:\n\n" + playerName + ": " + playerOneChosen + "\n"
						+ "Computer" + ": " + playerTwoChosen);
				// Add an image of coins
				Image coinImage = new Image(
						getClass().getResource("/application/images/game-coins.png").toExternalForm());
				// Replace
				// with
				// a local or
				// online image
				ImageView coinImageView = new ImageView(coinImage);
				coinImageView.setFitWidth(80); // Adjust the width of the image
				coinImageView.setFitHeight(80); // Adjust the height of the image
				alert.setGraphic(coinImageView); // Set the image as the graphic for the alert

				// Custom styling for the dialog
				alert.getDialogPane().setStyle(
						"-fx-background-color: #fffbe6; -fx-font-size: 14px; -fx-border-color: gold; -fx-border-width: 2px;");

				alert.showAndWait(); // Display the alert and wait for user response
			});

			// Add animation to the coin button
			ScaleTransition st4 = new ScaleTransition(Duration.millis(200), btShowCoins);
			st4.setFromX(1.0);
			st4.setToX(1.1);
			st4.setFromY(1.0);
			st4.setToY(1.1);
			st4.setAutoReverse(true);
			btShowCoins.setOnMouseEntered(e -> st4.playFromStart());

			playerName = tf.getText().trim();
			if (playerName.isEmpty()) {
				playerName = "Player"; // Default name
			}

			// Ensure the coins array is not empty
			if (coins == null || coins.length == 0) {
				lblStatus.setText("No coins available. Please check the coin values.");
				return;
			}

			// Create coin UI dynamically
			for (int k = 0; k < coins.length; k++) {
				StackPane pane = new StackPane();
				Circle coinCircle = new Circle(30, Color.LIGHTGRAY); // Coin shape
				Label lblCoinValue = new Label(String.valueOf(coins[k]));
				lblCoinValue.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
				lblCoinValue.setTextFill(Color.BLACK);

				pane.getChildren().addAll(coinCircle, lblCoinValue);
				box.getChildren().add(pane);
			}

			box.setAlignment(Pos.TOP_CENTER);

			// Create a label for the winner and max amount
			Label lblFirst = new Label();
			lblFirst.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
			lblFirst.setTextFill(Color.BLACK);
			lblFirst.setTextAlignment(TextAlignment.CENTER);
			lblFirst.setText("Computer VS " + playerName);

			// Calculate the results before the animation
			apearTheResults(coins);

			lblStatus.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
			lblStatus.setTextFill(Color.YELLOWGREEN);
			lblStatus.setTextAlignment(TextAlignment.CENTER);
			// Display the results
			lblStatus.setText(
					"Game Result: \nMax coins collected: " + maxAmount + "\n" + "The winner is: " + getWinner());
			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			HBox hbox = new HBox(40);
			hbox.getChildren().addAll(bt1, btnStopAnimation, btShowCoins, bt2, btRestart);
			hbox.setAlignment(Pos.CENTER);
			VBox root = new VBox(40, image2, lblFirst, hbox, box, lblStatus, btBack);
			root.setAlignment(Pos.CENTER);

			Scene scene = new Scene(root, 1550, 800); // Scaled to accommodate larger arrays
			Stage stage = new Stage();
			stage.setTitle("Optimal Coins Game");
			stage.setScene(scene);
			stage.show();

			// Button to start the animation
			bt1.setOnAction(e -> startAnimation(coins));

			bt2.setOnAction(e -> displayDPTable());
			// Restart button action
			btRestart.setOnAction(e -> {
				showAlert("Notification", "Are You Sure That You Want To Restart The Game?");
				resetGame();
				Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
				currentStage.close();
				startTheGame(coins, tf);
			});
			btBack.setOnAction(ev -> {
				stage.close();
			});
		}

		// Function to calculate the result using dynamic programming
		private void apearTheResults(int[] coins) {
			int lengthOfTheArray = coins.length;

			// Initialize the DP table with zero
			dp = new int[lengthOfTheArray][lengthOfTheArray];

			for (int distance = 0; distance < lengthOfTheArray; distance++) { // Distance between left and right indices
				for (int l = 0, r = distance; r < lengthOfTheArray; l++, r++) {
					// Initial Values:
					if (distance == 0) {
						// Single coin case
						dp[l][l] = coins[0];
					} else if (distance == 1) {
						// Two coins case
						dp[l][r] = Math.max(coins[l], coins[r]);
					} else {
						int a, b, c;

						if (l + 2 <= r) {
							a = dp[l + 2][r]; // If the competitor picks l+1
						} else {
							a = 0;
						}

						if (l + 1 <= r - 1) {
							b = dp[l + 1][r - 1]; // If the competitor picks i or r-1
						} else {
							b = 0;
						}

						if (l <= r - 2) {
							c = dp[l][r - 2]; // If the competitor picks r-1
						} else {
							c = 0;
						}
						dp[l][r] = Math.max((coins[l] + Math.min(a, b)), (coins[r] + Math.min(b, c)));
					}
				}
			}

			// Track the coins chosen by the player and the computer
			int leftCoins = 0, rightCoins = lengthOfTheArray - 1;
			boolean isPlayerTurn = true;

			// Reset the coin trackers
			playerOneCoins.setLength(0); // Player's chosen coins
			playerTwoCoins.setLength(0); // Computer's chosen coins

			// Follow the choices based on the DP table
			while (leftCoins <= rightCoins) {
				if (isPlayerTurn) {
					int leftChoice = coins[leftCoins];
					if (leftCoins + 2 <= rightCoins) {
						leftChoice += dp[leftCoins + 2][rightCoins];
					}

					int midChoice = 0;
					if (leftCoins + 1 <= rightCoins - 1) {
						midChoice = dp[leftCoins + 1][rightCoins - 1];
					}

					int rightChoice = coins[rightCoins];
					if (leftCoins <= rightCoins - 2) {
						rightChoice += dp[leftCoins][rightCoins - 2];
					}

					int leftScore = coins[leftCoins] + Math.min(leftChoice, midChoice);
					int rightScore = coins[rightCoins] + Math.min(midChoice, rightChoice);

					if (leftScore >= rightScore) {
						playerOneCoins.append(coins[leftCoins]).append(" ");
						leftCoins++;
					} else {
						playerOneCoins.append(coins[rightCoins]).append(" ");
						rightCoins--;
					}
				} else {
					int leftChoice = coins[leftCoins];
					if (leftCoins + 2 <= rightCoins) {
						leftChoice += dp[leftCoins + 2][rightCoins];
					}

					int midChoice = 0;
					if (leftCoins + 1 <= rightCoins - 1) {
						midChoice = dp[leftCoins + 1][rightCoins - 1];
					}

					int rightChoice = coins[rightCoins];
					if (leftCoins <= rightCoins - 2) {
						rightChoice += dp[leftCoins][rightCoins - 2];
					}

					int leftScore = coins[leftCoins] + Math.min(leftChoice, midChoice);
					int rightScore = coins[rightCoins] + Math.min(midChoice, rightChoice);

					if (leftScore >= rightScore) {
						playerTwoCoins.append(coins[leftCoins]).append(" ");
						leftCoins++;
					} else {
						playerTwoCoins.append(coins[rightCoins]).append(" ");
						rightCoins--;
					}
				}

				// Switch turns
				isPlayerTurn = !isPlayerTurn;
			}

			// Calculate final scores
			playerScore = calculateScoreFromCoins(playerOneCoins.toString());
			computerScore = calculateScoreFromCoins(playerTwoCoins.toString());
			maxAmount = dp[0][lengthOfTheArray - 1]; // Maximum amount collected
		}

		private int calculateScoreFromCoins(String coinsString) {
			int score = 0;
			String[] coins = coinsString.trim().split(" ");
			for (int i = 0; i < coins.length; i++) {
				score += Integer.parseInt(coins[i]);
			}
			return score;
		}

		// Function to determine the winner based on scores
		private String getWinner() {
			if (playerScore > computerScore) {
				return playerName;
			} else if (computerScore > playerScore) {
				return "Computer Wins!";
			} else {
				return "It's a Tie!";
			}
		}

		private void startAnimation(int[] coins) {
			// Check if an animation is already running
			if (pause != null && pause.getStatus() == Animation.Status.RUNNING) {
				showAlert("Notification", "Animation is already running!");
				return;
			}

			isPlayerTurn = true; // Reset the player's turn flag
			i = 0; // Reset the left pointer
			j = coins.length - 1; // Reset the right pointer
			animationSteps.setLength(0); // Clear the simulation log

			// Set up animation to simulate the coin picking
			pause = new PauseTransition(Duration.seconds(1)); // Use the global pause object
			pause.setOnFinished(event -> {
				if (i <= j) {
					// Calculate left and right choices based on the DP table
					int leftChoice = coins[i];
					if (i + 1 <= j - 1) {
						leftChoice += Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
					} else {
						leftChoice += 0;
					}

					int rightChoice = coins[j];
					if (i <= j - 2) {
						rightChoice += Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
					} else {
						rightChoice += 0;
					}

					String currentPlayer = isPlayerTurn ? playerName : "Computer";

					if (leftChoice >= rightChoice) {
						// Animate picking the left coin
						animationSteps.append(currentPlayer).append(" picks ").append(coins[i]).append("\n");
						lblStatus.setText(animationSteps.toString());

						StackPane pickedCoin = (StackPane) box.getChildren().get(0);
						((Circle) pickedCoin.getChildren().get(0))
								.setFill(isPlayerTurn ? Color.PINK : Color.AQUAMARINE);

						PauseTransition removalPause = new PauseTransition(Duration.seconds(0.5));
						removalPause.setOnFinished(removeEvent -> box.getChildren().remove(0));
						removalPause.play();

						i++; // Move the left pointer
					} else {
						// Animate picking the right coin
						animationSteps.append(currentPlayer).append(" picks ").append(coins[j]).append("\n");
						lblStatus.setText(animationSteps.toString());

						StackPane pickedCoin = (StackPane) box.getChildren().get(box.getChildren().size() - 1);
						((Circle) pickedCoin.getChildren().get(0))
								.setFill(isPlayerTurn ? Color.PINK : Color.AQUAMARINE);

						PauseTransition removalPause = new PauseTransition(Duration.seconds(0.5));
						removalPause
								.setOnFinished(removeEvent -> box.getChildren().remove(box.getChildren().size() - 1));
						removalPause.play();

						j--; // Move the right pointer
					}

					// Alternate turn
					isPlayerTurn = !isPlayerTurn;

					pause.playFromStart(); // Continue the animation loop
				} else {
					// End of the simulation
					lblStatus.setText(animationSteps.toString() + "\nSimulation Complete! Max coins collected: "
							+ maxAmount + " and the winner is: " + getWinner());
				}
			});

			pause.play(); // Start the animation
		}

		// Stops the animation
		private void stopAnimation() {
			if (pause != null && pause.getStatus() == Animation.Status.RUNNING) {
				pause.stop();
				showAlert("Notification", "Animation stopped.");
			} else {
				showAlert("Notification", "No animation is running.");
			}
		}

		// Function to display the DP table in a new window
		private void displayDPTable() {
			GridPane myGrid = new GridPane();
			myGrid.setVgap(10);
			myGrid.setHgap(10);
			// Add column headers (k indices)
			for (int k = 0; k < dp[0].length; k++) {
				Label lblHeader = new Label("j = " + k);
				lblHeader.setStyle(" -fx-font-size: 20px; -fx-padding: 10;");
				lblHeader.setAlignment(Pos.CENTER);
				myGrid.add(lblHeader, k + 1, 0);
			}
			// Add row headers (index indices) and table values
			for (int index = 0; index < dp.length; index++) {
				Label lblRowHeader = new Label("i = " + index);
				lblRowHeader.setStyle(" -fx-font-size: 20px; -fx-padding: 10;");
				lblRowHeader.setAlignment(Pos.CENTER);
				myGrid.add(lblRowHeader, 0, index + 1);
				for (int m = 0; m < dp[index].length; m++) {
					Label lblValue = new Label(String.valueOf(dp[index][m]));
					lblValue.setStyle("-fx-font-size: 20px;");
					myGrid.add(lblValue, m + 1, index + 1);
				}
			}
			// Add "Back" button
			Button btnBack = new Button("⫷- Back");
			btnBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
			btnBack.setStyle("-fx-background-color: lightblue; " + "-fx-border-color: black; -fx-border-width: 2;");
			addEffect(btnBack);
			myGrid.add(btnBack, 0, 0);

			Scene scene = new Scene(myGrid, 1550, 800);
			Stage tableStage = new Stage();
			btnBack.setOnAction(e -> {
				tableStage.close();
			});
			tableStage.setTitle("DP Table");
			tableStage.setScene(scene);
			tableStage.show();
		}

		private void resetGame() {
			i = 0;
			j = getArrayCoins().length - 1;
			playerScore = 0;
			computerScore = 0;
			maxAmount = 0;
			isPlayerTurn = true;
			animationSteps.setLength(0);
			box.getChildren().clear();
		}

	}

	public class TwoPlayers {
		private HBox coinRow = new HBox(10);
		private boolean playerOneTurn = true;
		private int left = 0;
		private int right;
		private int playerOneScore = 0, playerTwoScore = 0;
		private String playerName1 = " "; // Player name
		private String playerName2 = " "; // Player name
		private Label lblStatus = new Label(); // Status display label
		private int maxAmount; // Maximum amount collected
		int[] coins = getArrayCoins();
		// Track coins chosen by each player
		private StringBuilder playerOneCoins = new StringBuilder();
		private StringBuilder playerTwoCoins = new StringBuilder();

		public TwoPlayers() {
			if (coins == null || coins.length == 0) {
				showAlert("Warning", "Coins array is null or empty. Initializing with default values.");
				coins = new int[getCoinCount()]; // Default values if coins are uninitialized
				setArrayCoins(coins);
			}
			right = coins.length - 1;
			maxAmount = calculateTotalCoins(); // Compute maxAmount here

		}

		private int calculateTotalCoins() {
			int sum = 0;
			for (int coin : coins) {
				sum += coin;
			}
			return sum;
		}

		public void playWithTwoPlayers() {
			GridPane pane = new GridPane();
			pane.setPadding(new Insets(20));
			pane.setHgap(15);
			pane.setVgap(20);
			pane.setAlignment(Pos.CENTER);
			Stage stage = new Stage();
			stage.setTitle("Optimal Strategy for a Game using Dynamic Programming");
			coinRow.setAlignment(Pos.CENTER);
			Image image = new Image(getClass().getResource("/application/images/5600_coin_pack.png").toExternalForm());
			ImageView image2 = new ImageView(image);
			image2.setFitHeight(200);
			image2.setFitWidth(200);
			Button btRestart = new Button("Restart"); // Reset button
			btRestart.setFont(Font.font("Arial", 15));
			btRestart.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 2;");
			addEffect(btRestart);

			// Add animation to the coin button
			ScaleTransition st3 = new ScaleTransition(Duration.millis(200), btRestart);
			st3.setFromX(1.0);
			st3.setToX(1.1);
			st3.setFromY(1.0);
			st3.setToY(1.1);
			st3.setAutoReverse(true);
			btRestart.setOnMouseEntered(e -> {
				st3.playFromStart();
			});

			Button btShowCoins = new Button("Show Coins Chosen"); // Reset button
			btShowCoins.setFont(Font.font("Arial", 15));
			btShowCoins.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			addEffect(btShowCoins);
			// Add action to show coins chosen
			btShowCoins.setOnAction(e -> {
				// Update the content of the TextArea with the chosen coins
				String playerOneChosen = playerOneCoins.length() > 0 ? playerOneCoins.toString() : "No coins selected.";
				String playerTwoChosen = playerTwoCoins.length() > 0 ? playerTwoCoins.toString() : "No coins selected.";
				Alert alert = new Alert(AlertType.INFORMATION); // Create an information alert
				alert.setTitle("Coins Chosen");
				alert.setHeaderText("Coins Collected by Each Player:");
				alert.setContentText("Coins Collected by Each Player:\n\n" + playerName1 + ": " + playerOneChosen + "\n"
						+ playerName2 + ": " + playerTwoChosen);
				// Add an image of coins
				Image coinImage = new Image(
						getClass().getResource("/application/images/game-coins.png").toExternalForm());
				// Replace
				// with
				// a local or
				// online image
				ImageView coinImageView = new ImageView(coinImage);
				coinImageView.setFitWidth(80); // Adjust the width of the image
				coinImageView.setFitHeight(80); // Adjust the height of the image
				alert.setGraphic(coinImageView); // Set the image as the graphic for the alert

				// Custom styling for the dialog
				alert.getDialogPane().setStyle(
						"-fx-background-color: #fffbe6; -fx-font-size: 14px; -fx-border-color: gold; -fx-border-width: 2px;");

				alert.showAndWait(); // Display the alert and wait for user response
			});

			// Add animation to the coin button
			ScaleTransition st = new ScaleTransition(Duration.millis(200), btShowCoins);
			st.setFromX(1.0);
			st.setToX(1.1);
			st.setFromY(1.0);
			st.setToY(1.1);
			st.setAutoReverse(true);
			btShowCoins.setOnMouseEntered(e -> st.playFromStart());

			playerName1 = tfTwoPlayer1.getText().trim();
			if (playerName1.isEmpty()) {
				playerName1 = "Player1"; // Default name
			}
			playerName2 = tfTwoPlayer2.getText().trim();
			if (playerName2.isEmpty()) {
				playerName2 = "Player2"; // Default name
			}
			// Create a label for the winner and max amount
			Label lblFirst = new Label();
			lblFirst.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
			lblFirst.setTextFill(Color.BLACK);
			lblFirst.setTextAlignment(TextAlignment.CENTER);
			lblFirst.setText(playerName1 + " Vs " + playerName2);

			// Display coins as clickable circles
			updateCoinRow();
			lblStatus.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
			lblStatus.setTextFill(Color.YELLOWGREEN);
			lblStatus.setTextAlignment(TextAlignment.CENTER);

			Button btBack = new Button("⫷- Back");
			btBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			btBack.setStyle("-fx-background-color: lightblue; " + "-fx-background-radius: 20; " + // Rounded
																									// corners
					"-fx-border-color: black; " + // Border color
					"-fx-border-width: 2; " + // Border width
					"-fx-border-radius: 20;" // Border radius to match the background radius
			);
			addEffect(btBack);

			HBox hbox = new HBox(40);
			hbox.getChildren().addAll(btShowCoins, btRestart);
			hbox.setAlignment(Pos.CENTER);

			Image imagee = new Image(getClass().getResource("/application/images/twoPlayer.jpg").toExternalForm());

			BackgroundImage backgroundimage = new BackgroundImage(imagee, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
					new BackgroundSize(0, 0, true, true, false, true));
			Background background = new Background(backgroundimage);

			VBox root = new VBox(40, image2, lblFirst, hbox, coinRow, lblStatus, btBack);
			root.setAlignment(Pos.CENTER);
			pane.add(root, 0, 0);
			pane.setBackground(background);

			Scene scene = new Scene(pane, 1550, 800);
			stage.setScene(scene);
			stage.show();
			// Restart button action
			btRestart.setOnAction(e -> {
				showAlert("Notification", "Are You Sure That You Want To Restart The Game?");
				resetGame();
				Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
				currentStage.close();
				playWithTwoPlayers();
			});

			btBack.setOnAction(ev -> {
				stage.close();
			});
		}

		// Function to determine the winner based on scores
		private String getWinner() {
			if (playerOneScore > playerTwoScore) {
				return playerName1;
			} else if (playerTwoScore > playerOneScore) {
				return playerName2;
			} else {
				return "It's a Tie!";
			}
		}

		private void updateCoinRow() {
			coinRow.getChildren().clear();

			for (int i = 0; i < getArrayCoins().length; i++) {
				if (i >= left && i <= right) {
					Circle coin = new Circle(20, playerOneTurn ? Color.PINK : Color.YELLOW);
					coin.setStroke(Color.BLACK);
					final int index = i;
					coin.setOnMouseClicked(e -> handlePlayerTurn(index));
					coinRow.getChildren().add(new VBox(coin, new Label(String.valueOf(getArrayCoins()[i]))));

				} else {
					coinRow.getChildren().add(new Label(" "));
				}

			}
		}

		private void handlePlayerTurn(int index) {
			if (index == left) {
				takeCoin("left");
				left++;
			} else if (index == right) {
				takeCoin("right");
				right--;
			}
			if (left > right) {
				showGameResults();
			}
			playerOneTurn = !playerOneTurn;
			updateCoinRow();
		}

		private void takeCoin(String side) {
			int coinValue = (side.equals("left")) ? getArrayCoins()[left] : getArrayCoins()[right];
			if (playerOneTurn) {
				playerOneScore += coinValue;
				playerOneCoins.append(coinValue).append(" "); // Track coins for Player 1

			} else {
				playerTwoScore += coinValue;
				playerTwoCoins.append(coinValue).append(" "); // Track coins for Player 2

			}
		}

		private void showGameResults() {
			lblStatus.setText(String.format(
					"Game Result:\nTotal Coins: %d\nPlayer 1 Score: %d\nPlayer 2 Score: %d\nThe winner is: %s",
					maxAmount, playerOneScore, playerTwoScore, getWinner()));
		}

		private void resetGame() {
			left = 0;
			right = getArrayCoins().length - 1;
			playerOneScore = 0;
			playerTwoScore = 0;
			maxAmount = 0;
			playerOneTurn = true;
			playerOneCoins.setLength(0); // Clear coin records
			playerTwoCoins.setLength(0); // Clear coin records
			coinRow.getChildren().clear();

		}

	}
}