package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangys
 * Desc: 根据数据生成tableview，增加复用，简化开发
 */
public class CapsTableView {

   /**
    * 根据从数据库表的查询结果，生成tableview
    * @param capsRes  查询结果
    * @param tableView  视图对象
    */
   public static void dealTableView(CapsRes capsRes,TableView<Map<String,String>> tableView) {
      int fldSize = capsRes.getFlds().size();
      List<Map<String,String>> mapList = new ArrayList<>(capsRes.getTtlNum());
      for (List<String> rec : capsRes.getRs()) {
         Map<String,String> recMap = new HashMap<>(rec.size());
         for (int i = 0; i < fldSize; i++) {
            recMap.put(capsRes.getFlds().get(i),rec.get(i));
         }
         mapList.add(recMap);
      }
      ObservableList<Map<String,String>> data =
              FXCollections.observableArrayList(mapList);
      List<TableColumn<Map<String,String>,String>> columnList =
              new ArrayList<>(capsRes.getFlds().size());
      
      for (String fld : capsRes.getFlds()) {
         TableColumn<Map<String,String>,String> col = new TableColumn<>(fld);
         col.setMinWidth(80);
         col.setCellValueFactory(new MapValueFactory(fld));
         col.setCellFactory(value -> {
            TableCell<Map<String,String>,String> cell = new TableCell<Map<String,String>,String>(){
               @Override
               public void updateItem(String item, boolean empty) {
                  if (item == getItem()) {
                     return;
                  }
                  super.updateItem(item, empty);
                  if (item == null) {
                     super.setText(null);
                     super.setGraphic(null);
                  } else {
                     super.setText(item);
                     super.setGraphic(null);
                  }
               }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
         });
         columnList.add(col);
      }
      tableView.setItems(data);
      tableView.getColumns().addAll(columnList);
   }
   
   /**
    * 把删除和修改的按钮加到最后一列
    * @param table  tableview视图
    * @param doDel  删除操作的回调
    * @param doUpd  修改操作的回调
    */
   public static void addButtonToTable(TableView<Map<String,String>> table,
                                       DelUpdFuncIntf doDel,DelUpdFuncIntf doUpd) {
      TableColumn<Map<String,String>, Void> colBtn = new TableColumn<>("操作");
      colBtn.setMinWidth(80);
      Callback<TableColumn<Map<String,String>, Void>, TableCell<Map<String,String>, Void>> cellFactory =
              params -> {
                 final TableCell<Map<String,String>, Void> cell =
                         new TableCell<Map<String,String>, Void>() {
                            private final Button updBtn = new Button("修改");
                            {
                               updBtn.setOnAction((ActionEvent event) -> {
                                  Map<String,String> recMap = getTableView().getItems().get(getIndex());
                                  doUpd.doDelUpd(recMap,"DNO");
                               });
                            }
                            private final Button delBtn = new Button("删除");
                            {
                               delBtn.setOnAction((ActionEvent event) -> {
                                  Map<String,String> recMap = getTableView().getItems().get(getIndex());
                                  doDel.doDelUpd(recMap,"DNO");
                               });
                            }
                            private final GridPane grid = new GridPane();
                            {
                               grid.setVgap(5);
                               grid.setHgap(5);
                               grid.setPadding(new Insets(0, 5, 0, 5));
                               grid.add(delBtn,1,0);
                               grid.add(updBtn,2,0);
                            }
                            @Override
                            public void updateItem(Void item, boolean empty) {
                               super.updateItem(item, empty);
                               if (empty) {
                                  setGraphic(null);
                               } else {
                                  setGraphic(grid);
                               }
                            }
                         };
                 return cell;
              };
      colBtn.setCellFactory(cellFactory);
      table.getColumns().add(colBtn);
   }

	public static void dealTableView(CapsRes capsRes, TableView<Map<String, String>> tableView,
			ArrayList<String> list) {
		  int fldSize = capsRes.getFlds().size();
	      List<Map<String,String>> mapList = new ArrayList<>(capsRes.getTtlNum());
	      for (List<String> rec : capsRes.getRs()) {
	         Map<String,String> recMap = new HashMap<>(rec.size());
	         for (int i = 0; i < fldSize; i++) {
	            recMap.put(capsRes.getFlds().get(i),rec.get(i));
	         }
	         mapList.add(recMap);
	      }
	      ObservableList<Map<String,String>> data =
	              FXCollections.observableArrayList(mapList);
	      List<TableColumn<Map<String,String>,String>> columnList =
	              new ArrayList<>(capsRes.getFlds().size());
	      int i =0;
	      for (String fld : capsRes.getFlds()) {
	    	 
	         TableColumn<Map<String,String>,String> col = new TableColumn<>(list.get(i++));
	         col.setMinWidth(80);
	         col.setCellValueFactory(new MapValueFactory(fld));
	         col.setCellFactory(value -> {
	            TableCell<Map<String,String>,String> cell = new TableCell<Map<String,String>,String>(){
	               @Override
	               public void updateItem(String item, boolean empty) {
	                  if (item == getItem()) {
	                     return;
	                  }
	                  super.updateItem(item, empty);
	                  if (item == null) {
	                     super.setText(null);
	                     super.setGraphic(null);
	                  } else {
	                     super.setText(item);
	                     super.setGraphic(null);
	                  }
	               }
	            };
	            cell.setAlignment(Pos.CENTER);
	            
	            return cell;
	         });
	         columnList.add(col);
	      }
	      tableView.setItems(data);
	      tableView.getColumns().addAll(columnList);
		
	}

}