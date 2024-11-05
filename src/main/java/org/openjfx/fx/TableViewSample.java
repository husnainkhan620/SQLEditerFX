package org.openjfx.fx;

import java.util.HashMap;

import org.openjfx.fx.TableSample3.EditingCell;
import org.openjfx.fx.TableSample3.Person;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
 
public class TableViewSample extends Application {
 
    private TableView<HashMap<String,String>> table = new TableView<HashMap<String,String>>();
  
    
    private final ObservableList<HashMap<String,String>> mapdata =
            FXCollections.observableArrayList();
    final HBox hb = new HBox();
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));  

        
        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                       return new EditingCell();
                    }
        };
    
    	
    	TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new MapValueFactory<>("firstName"));
        firstNameCol.setCellFactory(cellFactory);   //TextFieldTableCell.forTableColumn()  default wheere TextFeild valus is set on enter
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<HashMap,String>>() {
                @Override
                public void handle(CellEditEvent<HashMap,String> t) {
                	System.out.println("Editing firstName..");
                	System.out.println("Table View "+ t.getTableView().getItems().get(t.getTablePosition().getRow()));
                	 t.getTableView().getItems().get(t.getTablePosition().getRow()).replace(t.getTableColumn(), t.getTableView().getItems().get(t.getTablePosition().getRow()).get(firstNameCol));
                	System.out.println("Table Position"+t.getTablePosition().getRow());
                	System.out.println("Table Column"+t.getTableColumn());
                            
                }
            }
        );
 
 
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new MapValueFactory<>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<HashMap,String>>() {
                    @Override
                    public void handle(CellEditEvent<HashMap,String> t) {
                    	System.out.println("Editing lasName..");
                    	System.out.println("Table View "+t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    	System.out.println("Table Position"+t.getTablePosition().getRow());
                    	System.out.println("Table Column"+t.getTableColumn());
                                
                    }
                }
            );
 
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new MapValueFactory<>("email"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<HashMap,String>>() {
                    @Override
                    public void handle(CellEditEvent<HashMap,String> t) {
                        System.out.println("Editing email.."); 
                        System.out.println("Table View "+t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    	System.out.println("Table Position"+t.getTablePosition().getRow());
                    	System.out.println("Table Column"+t.getTableColumn());
                                
                    }
                }
            );
 
    	
        HashMap<String,String> personMap = new HashMap<String,String>();
        
        personMap.put("firstName", "Jacob");
        personMap.put("lastName", "Smith");
        personMap.put("email", "jacob.smith@example.com");
        mapdata.add(personMap);
        
        personMap = new HashMap<String,String>();
        personMap.put("firstName", "Isabella");
        personMap.put("lastName", "Johnson");
        personMap.put("email", "isabella.johnson@example.com");
        mapdata.add(personMap);
        personMap = new HashMap<String,String>();
        personMap.put("firstName", "Ethan");
        personMap.put("lastName", "Williams");
        personMap.put("email", "ethan.williams@example.com");
        mapdata.add(personMap);
        personMap = new HashMap<String,String>();
        personMap.put("firstName", "Emma");
        personMap.put("lastName", "Jones");
        personMap.put("email", "emma.jones@example.com");
        mapdata.add(personMap);
        personMap = new HashMap<String,String>();
        personMap.put("firstName", "Michael");
        personMap.put("lastName", "Brown");
        personMap.put("email", "michael.brown@example.com");
        mapdata.add(personMap);
    
        // table.getItems().addAll(mapdata);
        

        
        table.setItems(mapdata);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	System.out.println("Adding new row....");
            	HashMap<String,String> personMap = new HashMap<String,String>();                 
                personMap.put("firstName",addFirstName.getText());
                personMap.put("lastName", addLastName.getText());
                personMap.put("email", addEmail.getText());
                mapdata.add(personMap);
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });
 
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString("Hello World");
        clipboard.setContent(content);
        
        
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
    class EditingCell extends TableCell<Person, String> {
    	 
        private TextField textField;
 
        public EditingCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {  // Comment out this part then it will be editable but never persisted
                             commitEdit(textField.getText());
                        }
                }
            });
        }
        
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
 
  
}
