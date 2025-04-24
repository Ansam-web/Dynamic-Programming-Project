package application;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class OptimalGameStrategyProblem extends Application {
	// Declare EnterValuesStage object and TextField for user input
	EnterValuesStage m = new EnterValuesStage();
	TextField tfNumCoins = new TextField();

	@Override
	public void start(Stage primaryStage) {

		Label lblTitle = new Label("Welcome To My Coins Game");
		lblTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));

		// Prompt for number of coins
		Label lblNumCoins = new Label("Please enter the number of Coins:");
		lblNumCoins.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

		ImageView img = new ImageView(
				new Image(getClass().getResource("/application/images/fund_of_coins.png").toExternalForm()));

		img.setFitHeight(200);
		img.setFitWidth(200);

		tfNumCoins.setPromptText("Enter number of Coins (even number)");
		tfNumCoins.setMaxWidth(200);

		Button btNext = new Button("Next -⫸");
		btNext.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btNext.setStyle("-fx-background-color: lightblue; -fx-background-radius: 20; "
				+ "-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;");
		m.addEffect(btNext);

		Button btInstruction = new Button("Instructions");
		btInstruction.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		btInstruction.setStyle("-fx-background-color: lightblue; -fx-background-radius: 20; "
				+ "-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;");
		m.addEffect(btInstruction);

		btInstruction.setOnAction(e -> {

			Label lblInstructions = new Label("Game Instructions:\n"
					+ "1. The game is a two-player game. There are even number of coins arranged in a row.\n"
					+ "2. Players take turns selecting a coin from either the left or right end of the row.\n"
					+ "3. The goal is to collect coins with the highest total value.\n"
					+ "4. The player with the maximum score at the end of the game wins.\n"
					+ "5. Use strategy to maximize your score while minimizing your competitor.\n" + "\n"
					+ "Controls:\n" + "- Click on a coin to select it during your turn.\n"
//					+ "- Use the \"Show Score\" button to view the current scores.\n"
					+ "- Use the \"Show Coins Chosen\" button to see the coins each player has selected.\n"
					+ "- Click \"Reset\" to start a new game.\n" + "\n" + "Enjoy and play strategically!");

			// Styling the label to suit the background
			lblInstructions.setFont(Font.font("Papyrus", FontWeight.BOLD, 20)); // A medieval-style font
			lblInstructions.setTextFill(Color.BEIGE); // Light text for contrast
			lblInstructions.setWrapText(true); // Enable text wrapping for long lines
			lblInstructions.setPadding(new Insets(15)); // Add padding for better readability
			lblInstructions.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); " + // Semi-transparent black background
					"-fx-border-color: goldenrod; " + // Gold border
					"-fx-border-width: 2; " + // Border thickness
					"-fx-border-radius: 10; " + // Rounded corners for the border
					"-fx-background-radius: 10;" // Rounded background corners
			);
			lblInstructions.setAlignment(Pos.TOP_CENTER); // Center-align the text

			// Create a custom dialog for the instructions
			Stage instructionStage = new Stage();
			instructionStage.setTitle("Game Instructions");

			lblInstructions.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
			lblInstructions.setTextAlignment(TextAlignment.CENTER);
			lblInstructions.setTextFill(Color.AZURE);

			Image image2 = new Image(getClass().getResource("/application/images/instructions.jpeg").toExternalForm());

			BackgroundImage backgroundimage = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
					new BackgroundSize(0, 0, true, true, false, true));
			Background background = new Background(backgroundimage);

			// Add a close button
			Button closeButton = new Button("Close");
			closeButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
			closeButton.setStyle("-fx-background-color: #ffef99; -fx-border-color: #ffc107; -fx-border-radius: 5;");
			closeButton.setOnAction(event -> instructionStage.close());

			// Layout for the dialog
			VBox layout = new VBox(20, lblInstructions, closeButton);
			layout.setAlignment(Pos.CENTER);
			layout.setPadding(new Insets(20));
//			layout.setStyle("-fx-background-color: #fff5e1; -fx-border-color: #d4af37; -fx-border-width: 3;"); // Gold-themed
			// background
			layout.setBackground(background);

			// Create the scene
			Scene scene = new Scene(layout, 1550, 800);
			instructionStage.setScene(scene);
			instructionStage.showAndWait();
		});

		btNext.setOnAction(e -> {
			try {
				int n = Integer.parseInt(tfNumCoins.getText().trim());

				if (n % 2 != 0) {
					showAlert("Invalid Input", "Number of coins must be even!");
					tfNumCoins.clear(); // Clear the input field for re-entry
					return;
				}
				if (n <= 0) {
					showAlert("Invalid Input", "Number of coins must be positive!");
					tfNumCoins.clear(); // Clear the input field for re-entry
					return;
				}
				if (n > m.MAX_COINS) {
					showAlert("Warning",
							"The number of coins cannot exceed " + m.MAX_COINS + ". Setting to maximum allowed value!");
					tfNumCoins.clear(); // Clear the input field for re-entry
					return;
				}
				m.setCoinCount(n); // Set coin count in EnterValuesStage
//				m.setArrayCoins(new int[n]); // Initialize array with the correct size
				stage2();
			} catch (NumberFormatException ex) {
				showAlert("Invalid Input", "Please enter valid integers for coins.");
				tfNumCoins.clear(); // Clear the input field for re-entry

			}
		});

		Image image3 = new Image(getClass().getResource("/application/images/firstStage.jpg").toExternalForm());

		BackgroundImage backgroundimage = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, true, true, false, true));
		Background background = new Background(backgroundimage);

		HBox box = new HBox(20);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(btInstruction, btNext);

		VBox vbox = new VBox(30);
		vbox.getChildren().addAll(lblTitle, img, lblNumCoins, tfNumCoins, box);
		vbox.setBackground(background);
		vbox.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(vbox, 1550, 800);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public void stage2() {
		m.secondStage();
	}

	public void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
