package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SelectCategoryController implements Initializable{
	Connection connection;
	//@FXML
	//private Label label;
	@FXML
	private VBox vboxx;
	List<Button> buttonList = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}

		//label = CategoriesController.getLabel();
		//String category = label.getText();

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "SELECT Title from Goals where ID = ? and Category = Intelligence";

		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, LoginModel.getUserID());
			//pstmt.setString(2, category);
			resultSet = pstmt.executeQuery();

			while(resultSet.next()) {
				String title = resultSet.getString("Title");
				buttonList.add(new Button(title));
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



}
