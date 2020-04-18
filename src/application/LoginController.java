package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public LoginModel loginModel = new LoginModel();

	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (!loginModel.isDbConnected()) {
			System.out.println("Database Not Connected");
		}
	}

	public void Login (ActionEvent event) {
		try {
			if (loginModel.isLogin(txtUserName.getText(), txtPassword.getText())) {
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				BorderPane root = loader.load(getClass().getResource("/application/User.fxml").openStream());
				UserController userController = (UserController)loader.getController();
				userController.getUser(txtUserName.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
