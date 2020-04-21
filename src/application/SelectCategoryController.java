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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectCategoryController implements Initializable{
	Connection connection;
	@FXML
	private Label categoryLabel;
	@FXML
	private VBox vboxx;
	@FXML
	private BorderPane bPane = new BorderPane();
	List<Button> buttonList = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("in SelectCategoryController");
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}

		//label = CategoriesController.getLabel();
		//String category = categoryLabel.getText();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "SELECT Title from Goals where ID = ? and Category = ?";

		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, LoginModel.getUserID());
			pstmt.setString(2, categoryLabel.getText());
			resultSet = pstmt.executeQuery();

			System.out.println("before while loop");
			while(resultSet.next()) {
				System.out.println("in while loop");
				Button tempButton = new Button(resultSet.getString("Title"));
				tempButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							((Node)event.getSource()).getScene().getWindow().hide();
							Stage primaryStage = new Stage();
							FXMLLoader loader = new FXMLLoader();
							System.out.println("after loader");
							BorderPane root = loader.load(getClass().getResource("/application/SelectCategory.fxml").openStream()); //change this to Goals, eventually create string to change dynamically
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
			System.out.println("after while loop");

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

	public void getLabel(String catLabel) {
		categoryLabel.setText(catLabel);
	}

}
