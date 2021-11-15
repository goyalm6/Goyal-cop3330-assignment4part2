/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ucf.assignments;

import ToDoList.Context;
import ToDoList.ToDoList;
import ToDoList.ToDoLists;
import ToDoList.ToDoListItem;
import ToDoList.ToDoListSerializable;
import ToDoList.ToDoListsSerializable;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageListController implements Initializable {
    public ToDoLists ListsToDo ;
    private FilteredList<ToDoList> filteredData;
    private ObservableList<ToDoList> selectedList;
    public int maxListCount = 100;
    private String mode = "";

    //region UX
    @FXML private Pane pntodolists;
    @FXML private Pane pnlForm;
    @FXML private Label lblMsg;
    @FXML private TextField txtTitle;
    @FXML private TableView<ToDoList> tableView;
    @FXML private TableColumn<ToDoList, String> titleColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListsToDo = new ToDoLists();

        //Initialize Lists from Context
        ListsToDo.setLists(Context.getInstance().getLists());

        //Initially hide panel
        pnlForm.setVisible(false);

        BindTable();

        //Populate selected list in the form for update
        PopulateSelectedListDetails();
    }

    private void BindTable() {
        // bind table column
        titleColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("title"));
        filteredData = new FilteredList<>(ListsToDo.getLists(), p -> true);
        SortedList<ToDoList> sortedData = new SortedList<>(filteredData);
        tableView.setItems(sortedData);
    }

    private void PopulateSelectedListDetails() {
        TableView.TableViewSelectionModel<ToDoList> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectionModel.select(0);
        selectedList = selectionModel.getSelectedItems();
        selectedList.addListener(new ListChangeListener<ToDoList>() {
            @Override
            public void onChanged(Change<? extends ToDoList> change) {
                txtTitle.setText(selectedList.get(0).getTitle());
            }
        });
    }

    private void populateListDetails(String Mode)
    {
        if (Mode == "Edit") {
            TableView.TableViewSelectionModel<ToDoList> selectionModel = tableView.getSelectionModel();
            selectedList = selectionModel.getSelectedItems();
            txtTitle.setText(selectedList.get(0).getTitle());
        }
        if(Mode == "Add")
        {
            txtTitle.setText("") ;
        }
    }

    private ToDoListSerializable ImportListInContext() {
        ToDoListSerializable ListToDoInContext = new ToDoListSerializable();
        boolean cont = true;

        try {
            FileInputStream fi = new FileInputStream(new File("MyToDoList.txt"));
            ObjectInputStream input = new ObjectInputStream(fi);
            while (cont) {
                ListToDoInContext = (ToDoListSerializable) input.readObject();


                if (ListToDoInContext != null) {
                    System.out.println("Imported List is " + ListToDoInContext.getItems().size());
                } else {
                    cont = false;
                }
            }

            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ListToDoInContext;
    }

    private ToDoListsSerializable ImportLists()
    {
        ToDoListsSerializable ListsToDoInContext = new ToDoListsSerializable();
        boolean cont = true;

        try {
            FileInputStream fi = new FileInputStream(new File("MyToDoLists.txt"));
            ObjectInputStream input = new ObjectInputStream(fi);

            while (cont) {
                ListsToDoInContext = (ToDoListsSerializable) input.readObject();

                if (ListsToDoInContext != null) {
                    System.out.println("Imported Lists are" + ListsToDoInContext.getLists().size());
                } else {
                    cont = false;
                }
            }

            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ListsToDoInContext;
    }

    private void ExportLists()
    {
        ToDoListsSerializable todoLists  =  new ToDoListsSerializable();
        ArrayList<ToDoListSerializable> lists = new ArrayList<ToDoListSerializable>();

        for (int i = 0; i < ListsToDo.getLists().size(); i++) {
            ToDoList todoListInContext =  ListsToDo.getLists().get(i);
            ToDoListSerializable todoListInContextSerializable  =  new ToDoListSerializable();
            todoListInContextSerializable.setTitle(todoListInContext.getTitle());
            ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>(todoListInContext.getItems());
            todoListInContextSerializable.setItems(items);
            lists.add(todoListInContextSerializable);
        }
        todoLists.setLists(lists);
        try {
            FileOutputStream f = new FileOutputStream(new File("MyToDoLists.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(todoLists);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    private ToDoList ImportListInContextOld()
    {
        ToDoList ListToDoInContext = new ToDoList();
        boolean cont = true;

        try {
            FileInputStream fi = new FileInputStream(new File("MyToDoList.txt"));
            ObjectInputStream input = new ObjectInputStream(fi);
            while (cont) {
                ListToDoInContext = (ToDoList) input.readObject();

                if (ListToDoInContext != null) {
                    System.out.println("Imported List is " + ListToDoInContext.getItems().size());
                } else {
                    cont = false;
                }
            }

            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ListToDoInContext;
    }

    private ToDoLists ImportListsold()
    {
        ToDoLists ListsToDo = new ToDoLists();
        boolean cont = true;
        try {
            FileInputStream fi = new FileInputStream(new File("MyToDoLists.txt"));
            ObjectInputStream input = new ObjectInputStream(fi);
            while (cont) {
                ListsToDo = (ToDoLists) input.readObject();
                if (ListsToDo != null) {
                    //todoList.add(item);
                } else {
                    cont = false;
                }
            }
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ListsToDo;
    }

    //region UX Buttons
    @FXML
    public void btnAddListOnAction()
    {
        mode = "Add" ;
        lblMsg.setText("");

        if(isMaxListsCount())
        {
            lblMsg.setText("Max List count reached. You cannot add more than " + maxListCount + " Lists.");
            pnlForm.setVisible(false);
            return;
        }
        pnlForm.setVisible(true);
        populateListDetails("Add");
    }

    @FXML
    public void btnDeleteOnAction()
    {
        TableView.TableViewSelectionModel<ToDoList> selectionModel = tableView.getSelectionModel();
        selectedList = selectionModel.getSelectedItems();
        deleteItem(selectedList.get(0));
    }

    @FXML
    public void btnSubmitOnAction()
    {
        boolean isTitleUnique = CheckIfTitleIsUnique(mode, txtTitle.getText());
        if (isTitleUnique == true)
        {
            // If unique title than add/update list
            AddList();
            UpdateList();
        }
        else
        {
            // else display message to the user
            lblMsg.setText("This List Title already exist");
        }
    }


    public boolean CheckIfTitleIsUnique(String mode, String title)
    {
        boolean TitleIsUnique = true;
        if (mode == "Add") {
            for (int i = 0; i < ListsToDo.getLists().size(); i++) {
                ToDoList todoListInContext = ListsToDo.getLists().get(i);
                int index = todoListInContext.getTitle().compareToIgnoreCase(title);

                if (index == 0) {
                    TitleIsUnique = false;
                }
            }
        }
        if (mode == "Edit") {
            TableView.TableViewSelectionModel<ToDoList> selectionModel = tableView.getSelectionModel();
            selectedList = selectionModel.getSelectedItems();
            for (int i = 0; i < ListsToDo.getLists().size(); i++) {
                if(selectedList.get(0).getTitle().compareToIgnoreCase(ListsToDo.getLists().get(i).getTitle()) != 0) {
                     ToDoList todoListInContext = ListsToDo.getLists().get(i);
                     if (todoListInContext.getTitle().compareToIgnoreCase(txtTitle.getText()) == 0) {
                         TitleIsUnique = false;
                     }
                 }
            }
        }

        return TitleIsUnique;
    }


    private void AddList()
    {
        if (mode == "Add") {
            String title = txtTitle.getText();
            createNewList(title);

            TableView.TableViewSelectionModel<ToDoList> selectionModel = tableView.getSelectionModel();
            selectionModel.select(ListsToDo.getLists().size() - 1);
        }
    }

    private void UpdateList()
    {
        if (mode == "Edit") {
            TableView.TableViewSelectionModel<ToDoList> selectionModel = tableView.getSelectionModel();
            selectedList = selectionModel.getSelectedItems();
            updateListTitle(selectedList.get(0), txtTitle.getText());
            tableView.refresh();
        }
    }

    @FXML
    public void btnCancelOnAction()
    {
        pnlForm.setVisible(false);
    }

    @FXML
    public void btnEditOnAction()
    {
        mode = "Edit" ;
        populateListDetails("Edit");
        //show form
        pnlForm.setVisible(true);
    }

    @FXML
    public void btnExportListsOnAction()
    {
        ExportLists();
    }

    @FXML
    public void btnManageItemsOnAction() throws IOException
    {
        Context.getInstance().setLists(ListsToDo.getLists());
        Context.getInstance().setTodoListInContext(selectedList.get(0));

        Pane pne = FXMLLoader.load(getClass().getResource("todolist.fxml"));
        pntodolists.getChildren().setAll(pne);
    }

    @FXML
    public void btnImportListsOnAction() throws IOException
    {
        ToDoListsSerializable ListsToDoFromImport  = ImportLists();

        ToDoLists ToDoLists = new ToDoLists();

        ToDoListSerializable todoList  =  new ToDoListSerializable();
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();

        for (int i = 0; i < ListsToDoFromImport.getLists().size(); i++) {
            ToDoListSerializable todoListInContext =  ListsToDoFromImport.getLists().get(i);

            ToDoList ToDoList = new ToDoList();
            ToDoList.setTitle(todoListInContext.getTitle());
            ObservableList<ToDoListItem> items = FXCollections.observableArrayList(todoListInContext.getItems());
            ToDoList.setItems(items);
            lists.add(ToDoList);
        }

        Context.getInstance().setLists(lists);

        Pane pne = FXMLLoader.load(getClass().getResource("todolists.fxml"));
        pntodolists.getChildren().setAll(pne);
    }

    @FXML
    public void btnImportListOnAction() throws IOException, ClassNotFoundException {
        ToDoListSerializable ListToDoFromImport  = ImportListInContext();

        ToDoList ToDoList = new ToDoList();
        ToDoList.setTitle(ListToDoFromImport.getTitle());
        ObservableList<ToDoListItem> items = FXCollections.observableArrayList(ListToDoFromImport.getItems());
        ToDoList.setItems(items);

        ObservableList<ToDoList>  lists = FXCollections.observableArrayList();
        lists.add(ToDoList);

        Context.getInstance().setLists(lists);
        Pane pne = FXMLLoader.load(getClass().getResource("todolists.fxml"));
        pntodolists.getChildren().setAll(pne);
    }
    //endregion

    public boolean isMaxListsCount() {
        return ListsToDo.getLists().size() >= maxListCount;
    }

    public void createNewList(String title) {
        ToDoList lst = new ToDoList();
        lst.setTitle(title);
        ListsToDo.getLists().add(lst);
    }

    public void updateListTitle(ToDoList item, String title) {
        item.setTitle(title);
    }

    public void deleteItem(ToDoList item) {
        ListsToDo.getLists().remove(item);
    }
}
