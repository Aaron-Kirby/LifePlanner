package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoriesController implements Initializable{
	Connection connection;
	@FXML
	private VBox vboxx;
	//@FXML
	//private BorderPane bPane = new BorderPane();
	//@FXML
	//private static Label selectedLabel;
	//String label = selectedLabel.getText();


	List<Button> buttonList = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "SELECT Title from Categories where ID = ?";

		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, LoginModel.getUserID());
			resultSet = pstmt.executeQuery();

			while(resultSet.next()) {
				String title = resultSet.getString("Title");
				Button tempButton = new Button(title);
				tempButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							((Node)event.getSource()).getScene().getWindow().hide();
							Stage primaryStage = new Stage();
							FXMLLoader loader = new FXMLLoader();
							BorderPane root = loader.load(getClass().getResource("/application/SelectCategory.fxml").openStream());
							SelectCategoryController scController = (SelectCategoryController)loader.getController();
							scController.setLabel(title);
							Scene scene = new Scene(root);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene);
							primaryStage.show();
						} catch (Exception e) {

						}
					}
				});
				buttonList.add(tempButton);
				//buttonList.add(new Button(title));
			}

			vboxx.getChildren().clear();
			vboxx.getChildren().addAll(buttonList);

			resultSet.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

	}

	/*public static Label getLabel () {
		return selectedLabel;
	}

	private void setLabel (Label lbl) {
		selectedLabel = lbl;
	}*/

	public void selectCategory (ActionEvent event) {
		//setLabel((Label)event.getSource());
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			BorderPane root = loader.load(getClass().getResource("/application/SelectCategory.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

	public void goToCreateCategory (ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/CreateCategory.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

}
