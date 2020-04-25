package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Habit1Controller implements Initializable{
	Connection connection;
	@FXML
	private Label tally;
	@FXML
	private Label max;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void addTally() {
		int temp = Integer.parseInt(tally.getText());
		temp++;
		String string = Integer.toString(temp);
		tally.setText(string);

		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}

		PreparedStatement pstmt = null;
		String insert = "INSERT INTO Habits(ID, Goal, Title, Tally, Maximum, Description, BeenDeleted?) VALUES(1, Read, Number of books, ?, 10, Must be non-fiction books, 0)";

		try {
			pstmt = connection.prepareStatement(insert);
			pstmt.setString(1, tally.getText());
			pstmt.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void backToHabits (ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/Habits.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

}
