/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ucf.assignments;

import ToDoList.Context;
import ToDoList.ToDoList;
import ToDoList.ToDoListItem;
import ToDoList.ToDoListSerializable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TodoListController implements Initializable {
    //region UX Properties
    @FXML private Pane pntodolist;
    @FXML private TextField txtTitle;
    @FXML private TextArea txtDescription;
    //@FXML private TextField txtDueDate;
    @FXML private DatePicker dpDueDate;
    @FXML private ChoiceBox<String> drpStatus;
    @FXML private ChoiceBox<String> drpStatusFilter;
    @FXML public Label lblMsg;
    @FXML private Label lblCurrentList;
    @FXML private Pane pnlForm;
    @FXML private TableView<ToDoListItem> tableView;
    @FXML private TableColumn<ToDoListItem, String> titleColumn;
    @FXML private TableColumn<ToDoListItem, String>  descriptionColumn;
    @FXML private TableColumn<ToDoListItem, Date> listduedateColumn;
    @FXML private TableColumn<ToDoListItem, String>  statusColumn;
    //endregion

    public ToDoList  todoListInContext ;
    public FilteredList<ToDoListItem> filteredData;
    public SortedList<ToDoListItem> sortedData;
    public ObservableList<ToDoListItem> selectedItem;
    public ToDoListItem TodoItem;
    public int maxListCount = 100;
    public String mode = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        todoListInContext =  Context.getInstance().getTodoListInContext();
        populateTable();

        //Hide form
        pnlForm.setVisible(false);

        //populate status ChoiceBox;
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("In Progress", "Completed");
        drpStatus.setItems(list);
        ObservableList<String> listFilter = FXCollections.observableArrayList();
        listFilter.addAll("All","In Progress", "Completed");
        drpStatusFilter.setItems(listFilter);

        drpStatusFilter.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    FilterList(new_val.intValue());
                });

        // bind table column
        titleColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("description"));
        listduedateColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, Date>("listduedate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("status"));
    }

    private void populateTable()
    {
        lblCurrentList.setText(todoListInContext.getTitle());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        filteredData = new FilteredList<>(todoListInContext.getItems(), p -> true);
        SortedList<ToDoListItem> sortedData = new SortedList<>(filteredData);

        tableView.setItems(sortedData);

        TableView.TableViewSelectionModel<ToDoListItem> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        selectionModel.select(0);
        selectedItem = selectionModel.getSelectedItems();

        selectedItem.addListener(new ListChangeListener<ToDoListItem>() {
            @Override
            public void onChanged(Change<? extends ToDoListItem> change) {
                txtTitle.setText(selectedItem.get(0).getTitle());
                txtDescription.setText(selectedItem.get(0).getDescription());
                drpStatus.setValue(selectedItem.get(0).getStatus());
            }
        });
    }

    private void FilterList(Integer status)
    {
        if(status == 0){
            // filterToApply = "All" ;
            filteredData.setPredicate(null);
        }

        filteredData.setPredicate(ListToDoItem -> {
            // If filter text is empty, display all persons.
            // Compare first name and last name of every person with filter text.
            // String lowerCaseFilter = newValue.toLowerCase();
            String filterToApply = "" ;

            if(status == 1){
                filterToApply = "In Progress" ;
            }
            if(status == 2){
                filterToApply = "Completed" ;
            }

            if (ListToDoItem.getStatus().contains(filterToApply)) {
                return true; // Filter matches first name.
            } else {

                return false; // Does not match.
            }
        });
        // 3. Wrap the FilteredList in a SortedList.
        // SortedList<ListToDo> sortedData = new SortedList<>(filteredData);
        sortedData = new SortedList<>(filteredData);;
        tableView.setItems(sortedData);
    }

    private void ExportListInContext()
    {
        ToDoListSerializable todoListInContextSerializable  =  new ToDoListSerializable();
        todoListInContextSerializable.setTitle(todoListInContext.getTitle());
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>(todoListInContext.getItems());
        todoListInContextSerializable.setItems(items);
        try {
            FileOutputStream f = new FileOutputStream(new File("MyToDoList.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(todoListInContextSerializable);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
        System.out.println("ExportListInContextNew called ends");
    }

    private void populateItem(String Mode) {
        if(Mode == "Edit")
        {
            TableView.TableViewSelectionModel<ToDoListItem> selectionModel = tableView.getSelectionModel();
            selectedItem = selectionModel.getSelectedItems();

            TodoItem = selectedItem.get(0);
            txtTitle.setText(selectedItem.get(0).getTitle());
            txtDescription.setText(selectedItem.get(0).getDescription());
            drpStatus.setValue(selectedItem.get(0).getStatus());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

            LocalDate dt = LocalDate.parse(selectedItem.get(0).getListduedate());
            dpDueDate.setValue(dt);
        }

        if(Mode == "Add")
        {
            txtTitle.setText("") ;
            txtDescription.setText("");
            drpStatus.setValue("In Progress");
        }
    }

    //region UX Button Actions
    @FXML
    public void btnBackOnAction() throws IOException {
        Context.getInstance().setMode("Back");
        int size =  Context.getInstance().getTodoListInContext().getItems().size();

        Pane pne = FXMLLoader.load(getClass().getResource("todolists.fxml"));
        pntodolist.getChildren().setAll(pne);
    }

    @FXML
    public void btnExportListOnAction() {
        ExportListInContext();
    }

    @FXML
    public void btnAddListOnAction() {
        //show form
        mode = "Add" ;
        lblMsg.setText("");

        if(isMaxListCount())
        {
            lblMsg.setText("Max List count reached. You cannot add more than " + maxListCount + " item.");
            pnlForm.setVisible(false);

            return;
        }
        pnlForm.setVisible(true);
        populateItem("Add");
    }

    @FXML
    public void btnEditOnAction() {
        mode = "Edit" ;
        populateItem("Edit");
        //show form
        pnlForm.setVisible(true);
    }

    private void EditItem() {
        if(mode == "Edit")
        {
            TableView.TableViewSelectionModel<ToDoListItem> selectionModel = tableView.getSelectionModel();
            selectedItem = selectionModel.getSelectedItems();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
            String dd = formatter.format(dpDueDate.getValue());

            editItem(selectedItem, txtTitle.getText(), txtDescription.getText(), drpStatus.getValue(), dd);
            tableView.refresh();
        }
    }

    @FXML
    public void btnDeleteOnAction() {
        TableView.TableViewSelectionModel<ToDoListItem> selectionModel = tableView.getSelectionModel();
        selectedItem = selectionModel.getSelectedItems();
        deleteItem(selectedItem.get(0));
    }

    @FXML
    public void btnCancelOnAction(){
       pnlForm.setVisible(false);
    }

    @FXML
    public void btnSubmitOnAction(){
        boolean isTitleUnique = CheckIfTitleIsUnique(mode, txtTitle.getText());
        if (isTitleUnique == true)
        {
            // if unique item than add/update
            EditItem();
            AddListItem();
        }
        else
        {
            //else display message to the user
            lblMsg.setText("This List Item already exist");
        }
    }

    public boolean CheckIfTitleIsUnique(String mode, String title)
    {
        boolean TitleIsUnique = true;
        if (mode == "Add") {

            for (int i = 0; i < todoListInContext.getItems().size(); i++) {
                //ToDoListItem todoListItemInContext = todoListInContext.getItems().get(i);
                int index = todoListInContext.getItems().get(i).getTitle().compareToIgnoreCase(title);

                if (index == 0) {
                    TitleIsUnique = false;
                }
            }
        }
        if (mode == "Edit") {
            TableView.TableViewSelectionModel<ToDoListItem> selectionModel = tableView.getSelectionModel();
            selectedItem = selectionModel.getSelectedItems();
            for (int i = 0; i < todoListInContext.getItems().size(); i++) {
                if(selectedItem.get(0).getTitle().compareToIgnoreCase(todoListInContext.getItems().get(i).getTitle()) != 0) {
                    if (todoListInContext.getItems().get(i).getTitle().compareToIgnoreCase(txtTitle.getText()) == 0) {
                        TitleIsUnique = false;
                    }
                }
            }
        }

        return TitleIsUnique;
    }

    public void AddListItem() {
        if(mode == "Add")
        {
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            String status = drpStatus.getValue();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
            String dd = formatter.format(dpDueDate.getValue());
            addItem(title, description, status, dd);

            TableView.TableViewSelectionModel<ToDoListItem> selectionModel = tableView.getSelectionModel();
            selectionModel.select(todoListInContext.getItems().size() - 1);
        }
    }

    public boolean isMaxListCount() {
        return todoListInContext.getItems().size() >= maxListCount;
    }

    public void addItem(String title, String description, String status, String dd) {
        if(todoListInContext != null) {
            todoListInContext.getItems().add(new ToDoListItem(title, description, dd, status));
        }
    }

    public void editItem(ObservableList<ToDoListItem> item, String title, String description, String status, String duedate) {
        item.get(0).setTitle(title);
        item.get(0).setDescription(description);
        item.get(0).setStatus(status);
        item.get(0).setListduedate(duedate);
    }

    public void deleteItem(ToDoListItem item) {
        todoListInContext.getItems().remove(item);
    }
    //endregion
}