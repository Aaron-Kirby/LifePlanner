package application;

import java.net.URL;
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
		///////////////////////////////////////////////////////////change the database here
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
