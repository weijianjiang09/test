package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import util.CapsJdbc;
import util.CapsRes;
import util.CapsTableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TeacherMainController implements Initializable{
	@FXML
	private Label txtcno_0;
	@FXML
	private Label txtteachername_0;
	@FXML
	private Button btnupdate;
	@FXML
	private Label txtTno_0;
	@FXML
	private Button btnstudent_look;
	@FXML
	private TableView<Map<String,String>> txtstudentchart;
	@FXML
	private TableColumn chart_sno_1;
	@FXML
	private TableColumn chart_cno_1;
	@FXML
	private TableColumn chart_classnum_1;
	@FXML
	private TableColumn chart_name_1;
	@FXML
	private TableColumn chart_sex_1;
	@FXML
	private TextField txtsno_add;
	@FXML
	private TextField txtcno_add;
	@FXML
	private TextField txtclassnum_add;
	@FXML
	private TextField txtname_add;
	@FXML
	private TextField txtsex_add;
	@FXML
	private Button btnstudent_add;
	@FXML
	private TextField txtsno_delete;
	@FXML
	private TextField txtsno_change;
	@FXML
	private TextField txtcno_change;
	@FXML
	private TextField txtclassnum_change;
	@FXML
	private TextField txtname_change;
	@FXML
	private TextField txtsex_change;
	@FXML
	private Button btnstudent_change;
	@FXML
	private TableView<Map<String,String>> txtdepartmentchart;
	@FXML
	private TableColumn chart_cno_2;
	@FXML
	private TableColumn chart_coursename_2;
	@FXML
	private TableColumn chart_coursepreposition_2;
	@FXML
	private TextField txtdepartmentnum_add;
	@FXML
	private TextField txtdepartmentname_add;
	@FXML
	private TextArea txtdepartmentpreposition_add;
	@FXML
	private Button btndepartment_add;
	@FXML
	private TextField txtdepartmentnum_change;
	@FXML
	private TextField txtdepartmentname_change;
	@FXML
	private TextArea txtdepartmentpreposition_change;
	@FXML
	private Button btndepartment_change;
	
	private CapsRes capsRes;
	public String txtUserId;
	// Event Listener on Button[#btnstudent_look].onAction
	@FXML
	public void updateAction(ActionEvent event) {
//		初始化个人信息
		txtUserId="11";
		String strSQL = "select * from teacher where teacher_num= '"+txtUserId+"'";
		capsRes = CapsJdbc.execSQL(strSQL,null);
		txtteachername_0.setText(capsRes.getRs().get(0).get(2));
		txtTno_0.setText(capsRes.getRs().get(0).get(0));
		txtcno_0.setText(capsRes.getRs().get(0).get(1));
	}
	// Event Listener on Button[#btnstudent_add].onAction
	@FXML
	public void btnstudent_addAction(ActionEvent event) {
		List<Object> params = new ArrayList<>(5);
		params.add(txtsno_add.getText());
		params.add(txtcno_add.getText());
		params.add(txtclassnum_add.getText());
		params.add(txtname_add.getText());
		params.add(txtsex_add.getText());
		String strSQL = "INSERT into student (Sno,Cno,Class,Sname,Ssex) VALUES (?,?,?,?,?);";
		CapsJdbc.execSQL(strSQL,params);
		new Alert(Alert.AlertType.NONE, "提交成功！", new ButtonType[]{ButtonType.CLOSE}).show();
		refresh();
	}
	// Event Listener on Button[#btnstudent_change].onAction
	@FXML
	public void btnstudent_changeAction(ActionEvent event) {
		String strSQL = "update student set Cno='"+txtcno_change.getText()+"',Class='"+txtclassnum_change.getText()+
				"',Sname='"+txtname_change.getText()+"',Ssex='"+txtsex_change.getText()+"' where Sno ='"+txtsno_change.getText()+"';";
		CapsJdbc.execSQL(strSQL,null);
		new Alert(Alert.AlertType.NONE, "提交成功！", new ButtonType[]{ButtonType.CLOSE}).show();
		refresh();
	}
	// Event Listener on Button[#btncourse_add].onAction
	public void btndepartment_addAction(ActionEvent event) {
		List<Object> params = new ArrayList<>(3);
		params.add(txtdepartmentnum_add.getText());
		params.add(txtdepartmentname_add.getText());
		params.add(txtdepartmentpreposition_add.getText());
		String strSQL = "INSERT into department (department_num,department_name,department_intro) VALUES (?,?,?);";
		CapsJdbc.execSQL(strSQL,params);
		new Alert(Alert.AlertType.NONE, "提交成功！", new ButtonType[]{ButtonType.CLOSE}).show();
		refresh();
	}
	// Event Listener on Button[#btncourse_change].onAction
	@FXML
	public void btndepartment_changeAction(ActionEvent event) {
		String strSQL = "update student set department_num='"+txtdepartmentnum_change.getText()+"',department_name='"+txtdepartmentname_change.getText()+
				"',department_intro='"+txtdepartmentpreposition_change.getText()+"' where department_num ='"+txtdepartmentnum_change.getText()+"';";
		CapsJdbc.execSQL(strSQL,null);
		new Alert(Alert.AlertType.NONE, "提交成功！", new ButtonType[]{ButtonType.CLOSE}).show();
		refresh();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//初始化学生信息
		refresh();
	}
	public void refresh() {
		String strSQL = "select * from student;";
		capsRes = CapsJdbc.execSQL(strSQL,null);
		txtstudentchart.setItems(null);
		txtstudentchart.getColumns().clear();
		ArrayList<String> listS = new ArrayList<String>();
		listS.add("学号");
		listS.add("系编码");
		listS.add("班级编码");
		listS.add("姓名");
		listS.add("性别");
		CapsTableView.dealTableView(capsRes,txtstudentchart,listS);
		CapsTableView.addButtonToTable(txtstudentchart,(recMap,idCol) -> {
			String delSql = "delete from student where Sno =?";
			   List<Object> params = new ArrayList<>(2);
			   params.add(recMap.get("Sno"));
			   CapsJdbc.execSQL(delSql,params);
			   refresh();
		   		},(recMap,idCol) -> {
//		   			System.out.println(recMap);
//		   			txtsno_change.setVisible(true);
		   			txtcno_change.setVisible(true);
		   			txtclassnum_change.setVisible(true);
		   			txtname_change.setVisible(true);
		   			txtsex_change.setVisible(true);
		   			btnstudent_change.setVisible(true);
		   			txtsno_change.setText(recMap.get("Sno"));
		   			txtcno_change.setText(recMap.get("Cno"));
		   			txtclassnum_change.setText(recMap.get("Class"));
		   			txtname_change.setText(recMap.get("Sname"));
		   			txtsex_change.setText(recMap.get("Ssex")); 
		   		});
//		初始化系信息
		String strSQL2 = "select * from department;";
		capsRes = CapsJdbc.execSQL(strSQL2,null);
		txtdepartmentchart.setItems(null);
		txtdepartmentchart.getColumns().clear();
		ArrayList<String> listD = new ArrayList<String>();
		listD.add("系编码");
		listD.add("系系名称");
		listD.add("系简介");
		CapsTableView.dealTableView(capsRes,txtdepartmentchart,listD);
		CapsTableView.addButtonToTable(txtdepartmentchart,(recMap,idCol) -> {  
	   		 String delSql = "delete from department where department_num =?";
			   List<Object> params = new ArrayList<>(2);
			   params.add(recMap.get("department_num"));
			   CapsJdbc.execSQL(delSql,params);
			   refresh();
		   		},(recMap,idCol) -> {
		   			txtdepartmentname_change.setVisible(true);
		   			txtdepartmentpreposition_change.setVisible(true);
		   			btndepartment_change.setVisible(true);
		   			txtdepartmentnum_change.setText(recMap.get("department_num"));
		   			txtdepartmentname_change.setText(recMap.get("department_name"));
		   			txtdepartmentpreposition_change.setText(recMap.get("department_intro"));
		   		}	);
	}
	public void initData(String string) {
		this.txtUserId=string;
	}
}
