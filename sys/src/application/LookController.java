package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;

public class LookController {
	@FXML
	private Button btnLogin;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtUserId;
	public void btnLoginAction() {
		System.out.println(txtUserId.getText());
	}

}
