package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	public LoginModel loginModel = new LoginModel();

	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isDbConnected()) {
			isConnected.setText("Connected");
		}
		else {
			isConnected.setText("Not Connected");
		}
	}

	public void Login (ActionEvent event) {
		try {
			if (loginModel.isLogin(txtUserName.getText(), txtPassword.getText())) {
				isConnected.setText("usrname and passowrd is correct");
			}
			else {
				isConnected.setText("usrname and passowrd is NOT correct");
			}
		} catch (SQLException e) {
			isConnected.setText("usrname and passowrd is NOT correct");
			e.printStackTrace();
		}
	}

}
