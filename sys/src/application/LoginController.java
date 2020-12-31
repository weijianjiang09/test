package application;

import java.awt.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.CapsJdbc;
import util.CapsRes;
import javafx.scene.control.PasswordField;

public class LoginController{
	@FXML
	private Button btnLogin;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtUserId;
	
	private CapsRes capsRes;
	
	public void btnLoginAction() throws Exception {
		String password = String.valueOf(txtPassword.getText());
		String userId =txtUserId.getText();
		if(userId.equals("")||txtPassword.equals("")) {
			new Alert(Alert.AlertType.NONE, "账号或密码为空！", new ButtonType[]{ButtonType.CLOSE}).show();
			return;
		}else {
			String strSQL = "select * from user where id=? and password=?";
			List<Object> params = new ArrayList<>(2);
			params.add(userId);
			params.add(password);
			capsRes = CapsJdbc.execSQL(strSQL,params);
			if(capsRes.getTtlNum()!=0) {
				String key;
				String id = userId.substring(1);
				if((key=String.valueOf(userId.charAt(0))).equals("s")) {
	//				Parent root = FXMLLoader.load(getClass().getResource("StudentMain.fxml"));
					FXMLLoader loader =new  FXMLLoader(getClass().getResource("StudentMain.fxml"));
					Parent root =loader.load();
					StudentMainController controller = loader.getController();
					controller.initData(id);
					Scene scene = new Scene(root,600, 500);
					Stage stage=new Stage();
					stage.initStyle(StageStyle.DECORATED); 
					stage.setScene(scene);
					stage.setTitle("学生教务系统");  
					stage.show(); 
				}else if((key=String.valueOf(userId.charAt(0))).equals("t")){
//				    Parent root = FXMLLoader.load(getClass().getResource("TeacherMain.fxml"));  
					FXMLLoader loader =new  FXMLLoader(getClass().getResource("TeacherMain.fxml"));
					Parent root =loader.load();
					TeacherMainController controller = loader.getController();
					controller.initData(id);
				    Scene scene = new Scene(root,600, 500);
				    Stage stage=new Stage();
					stage.initStyle(StageStyle.DECORATED); 
				    stage.setScene(scene);
				    stage.setTitle("教师教务系统");  
			        stage.show();
				}
			}else {
				new Alert(Alert.AlertType.NONE, "账号或者密码错误！", new ButtonType[]{ButtonType.CLOSE}).show();
			}
			Stage stager = (Stage) btnLogin.getScene().getWindow();
			stager.close();
		}
	}
}
