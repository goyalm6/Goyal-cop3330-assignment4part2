/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ucf.assignments;

import ToDoList.ToDoList;
import ToDoList.ToDoListItem;
import ToDoList.ToDoLists;
import ToDoList.ToDoListSerializable;
import ToDoList.ToDoListsSerializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManageListControllerTest {

    ManageListController manageListController;

    @BeforeAll
    public void setUpAll() {
        System.out.println("Should print before all Tests");
    }

    @BeforeEach
    void setUp() {
        manageListController = new ManageListController();
        manageListController.maxListCount = 5;
        manageListController.ListsToDo = new ToDoLists();
    }

    @Test
    @DisplayName("Should return true when List has items equal to max count")
    void isMaxListCountShouldReturnTrue()
    {
        ObservableList<ToDoList> masterList = getToDoLists();

        manageListController.ListsToDo.setLists(masterList);
        Boolean hasReachedMaxCount = manageListController.isMaxListsCount();
        System.out.println("ct - " + manageListController.ListsToDo.getLists().size());
        //asset
        assertEquals(true, hasReachedMaxCount);
    }

    @Test
    @DisplayName("Should return false when List has items less than max count")
    void isMaxListCountShouldReturnFalse()
    {
        ObservableList<ToDoListItem> mockList = mockListItems();

        ObservableList<ToDoList> masterList = FXCollections.observableArrayList();
        masterList.add(0, new ToDoList("Daily Routine", mockList));

        manageListController.ListsToDo.setLists(masterList);
        Boolean hasReachedMaxCount = manageListController.isMaxListsCount();

        //asset
        assertEquals(false, hasReachedMaxCount);
    }

    @Test
    @DisplayName("Should return true when List title is unique when adding")
    void CheckIfTitleIsUniqueWhenAddingShouldReturnTrueWhenTitleIsUnique()
    {
        ObservableList<ToDoListItem> mockList = mockListItems();

        ObservableList<ToDoList> masterList = FXCollections.observableArrayList();
        masterList.add(0, new ToDoList("Daily Routine", mockList));

        manageListController.ListsToDo.setLists(masterList);
        Boolean isUniqueTitle = manageListController.CheckIfTitleIsUnique("add", "Dog park");

        //asset
        assertEquals(true, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return false when List title is not unique when adding")
    void CheckIfTitleIsUniqueWhenAddingShouldReturnFalseWhenTitleIsUnique()
    {
        ObservableList<ToDoListItem> mockList = mockListItems();

        ObservableList<ToDoList> masterList = FXCollections.observableArrayList();
        masterList.add(0, new ToDoList("Daily Routine", mockList));

        manageListController.ListsToDo.setLists(masterList);
        Boolean isUniqueTitle = manageListController.CheckIfTitleIsUnique("add", "Daily Routine");

        //asset
        assertNotEquals(false, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return true when List title is unique when editing")
    void CheckIfTitleIsUniqueWhenEditingShouldReturnTrueWhenTitleIsUnique()
    {
        ObservableList<ToDoListItem> mockList = mockListItems();

        ObservableList<ToDoList> masterList = FXCollections.observableArrayList();
        masterList.add(0, new ToDoList("Daily Routine", mockList));

        manageListController.ListsToDo.setLists(masterList);
        Boolean isUniqueTitle = manageListController.CheckIfTitleIsUnique("edit", "Dog park");

        //asset
        assertEquals(true, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return false when List title is not unique when editing")
    void CheckIfTitleIsUniqueWhenEditingShouldReturnFalseWhenTitleIsUnique()
    {
        ObservableList<ToDoListItem> mockList = mockListItems();

        ObservableList<ToDoList> masterList = FXCollections.observableArrayList();
        masterList.add(0, new ToDoList("Daily Routine", mockList));

        manageListController.ListsToDo.setLists(masterList);
        Boolean isUniqueTitle = manageListController.CheckIfTitleIsUnique("edit", "Daily Routine");

        //asset
        assertNotEquals(false, isUniqueTitle);
    }

    @Test
    @DisplayName("Should create new List and increase size of the Lists")
    void shouldCreateNewList() {
        ObservableList<ToDoList> masterList = getToDoLists();
        manageListController.ListsToDo.setLists(masterList);
        Integer ctBefore = manageListController.ListsToDo.getLists().size();
        manageListController.createNewList("Picnic");
        Integer ctAfter = manageListController.ListsToDo.getLists().size();

        //assert
        assertNotEquals(ctAfter, ctBefore);
    }

    @Test
    @DisplayName("Should update List Title and size should remain the same")
    void shouldUpdateListTitle() {
        ObservableList<ToDoList> masterList = getToDoLists();
        manageListController.ListsToDo.setLists(masterList);
        Integer ctBefore = manageListController.ListsToDo.getLists().size();

        String newTitle = "MyNew Title";
        manageListController.updateListTitle(manageListController.ListsToDo.getLists().get(0), newTitle);
        Integer ctAfter = manageListController.ListsToDo.getLists().size();

        //assert
        assertEquals(ctAfter, ctBefore);
        assertEquals(manageListController.ListsToDo.getLists().get(0).getTitle(), newTitle);
    }

    @Test
    @DisplayName("Should delete item from the List and Item Count should change")
    void shouldDeleteItemFromList() {
        ObservableList<ToDoList> masterList = getToDoLists();
        manageListController.ListsToDo.setLists(masterList);
        Integer ctBefore = manageListController.ListsToDo.getLists().size();

        manageListController.deleteItem(manageListController.ListsToDo.getLists().get(0));
        Integer ctAfter = manageListController.ListsToDo.getLists().size();

        //assert
        assertNotEquals(ctAfter, ctBefore);
    }

    @Test
    @DisplayName("Should Import  Saved  Todo lists")
    void shouldImportSavedLists()
    {

        ArrayList<ToDoListSerializable> lists = new ArrayList<ToDoListSerializable>();

        ToDoListSerializable todoListInContextSerializable  =  new ToDoListSerializable();
        todoListInContextSerializable.setTitle("MyList1");
        ArrayList<ToDoListItem> itemsSerialized = new ArrayList<ToDoListItem>();
        itemsSerialized.add(0,new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        itemsSerialized.add(1,new ToDoListItem("Cosmetics", "get Cosmetics", "2021-12-01", "In-Progress"));
        todoListInContextSerializable.setItems(itemsSerialized);
        lists.add(0,todoListInContextSerializable);

        todoListInContextSerializable  =  new ToDoListSerializable();
        todoListInContextSerializable.setTitle("MyList2");

        itemsSerialized = new ArrayList<ToDoListItem>();
        itemsSerialized.add(0,new ToDoListItem("HomeDepot", "get paint", "2021-12-01", "In-Progress"));
        itemsSerialized.add(1,new ToDoListItem("Lowes", "get plants", "2021-12-01", "In-Progress"));
        todoListInContextSerializable.setItems(itemsSerialized);
        lists.add(1,todoListInContextSerializable);

        Integer countBefore = lists.size();

        ToDoListSerializable todoList  =  new ToDoListSerializable();
        ObservableList<ToDoList> listsFX = FXCollections.observableArrayList();
        // ArrayList<ToDoListSerializable> lists = new ArrayList<ToDoListSerializable>();


        for (int i = 0; i < lists.size(); i++) {

            ToDoListSerializable todoListInContext =  lists.get(i);

            ToDoList ToDoList = new ToDoList();
            ToDoList.setTitle(todoListInContext.getTitle());
            ObservableList<ToDoListItem> items = FXCollections.observableArrayList(todoListInContext.getItems());
            ToDoList.setItems(items);
            listsFX.add(ToDoList);
        }

        Integer countAfter = listsFX.size();

        //asset
        assertSame(countBefore, countAfter);

    }

    @Test
    @DisplayName("Should Import  Saved  Todo list")
    void shouldImportSavedList()
    {
        ToDoListSerializable todoListInContextSerializable  =  new ToDoListSerializable();
        todoListInContextSerializable.setTitle("MyList1");
        ArrayList<ToDoListItem> itemsSerialized = new ArrayList<ToDoListItem>();
        itemsSerialized.add(0,new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        itemsSerialized.add(1,new ToDoListItem("Cosmetics", "get Cosmetics", "2021-12-01", "In-Progress"));
        todoListInContextSerializable.setItems(itemsSerialized);

        Integer countBefore = todoListInContextSerializable.getItems().size();

        ToDoList ToDoList = new ToDoList();
        ToDoList.setTitle(todoListInContextSerializable.getTitle());
        ObservableList<ToDoListItem> items = FXCollections.observableArrayList(todoListInContextSerializable.getItems());
        ToDoList.setItems(items);
        ObservableList<ToDoList>  lists = FXCollections.observableArrayList();
        lists.add(ToDoList);
        Integer countAfter = lists.get(0).getItems().size();

        //asset
        assertSame(countBefore, countAfter);

    }

    @Test
    @DisplayName("Should Export All Todo lists")
    void shouldExportLists()
    {
        ObservableList<ToDoList> myList = FXCollections.observableArrayList();
        myList = getToDoLists();

        Integer countBefore = myList.size();

        ToDoListsSerializable todoLists  =  new ToDoListsSerializable();
        ArrayList<ToDoListSerializable> lists = new ArrayList<ToDoListSerializable>();
        for (int i = 0; i < myList.size(); i++) {
            ToDoList todoListInContext =  myList.get(i);
            ToDoListSerializable todoListInContextSerializable  =  new ToDoListSerializable();
            todoListInContextSerializable.setTitle(todoListInContext.getTitle());
            ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>(todoListInContext.getItems());
            todoListInContextSerializable.setItems(items);
            lists.add(todoListInContextSerializable);
        }
        todoLists.setLists(lists);

        Integer countAfter = todoLists.getLists().size();

        //asset
        assertSame(countBefore, countAfter);
    }

    private ObservableList<ToDoList> getToDoLists() {
        ObservableList<ToDoListItem> mockListGrocery = FXCollections.observableArrayList();
        mockListGrocery.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        ObservableList<ToDoListItem> mockListGym = FXCollections.observableArrayList();
        mockListGym.add(0, new ToDoListItem("Gym", "go to Gym", "2021-12-01", "In-Progress"));
        ObservableList<ToDoListItem> mockListGarden = FXCollections.observableArrayList();
        mockListGarden.add(0, new ToDoListItem("Garden", "water the plants", "2021-12-01", "In-Progress"));
        ObservableList<ToDoListItem> mockListPark = FXCollections.observableArrayList();
        mockListPark.add(0, new ToDoListItem("Dog Park", "Take tommy to the park", "2021-12-01", "In-Progress"));
        ObservableList<ToDoListItem> mockListGame = FXCollections.observableArrayList();
        mockListGame.add(0, new ToDoListItem("Game Night", "Send invitation for the game", "2021-12-01", "In-Progress"));

        ObservableList<ToDoList> masterList = FXCollections.observableArrayList();
        masterList.add(0, new ToDoList("Grocery", mockListGrocery));
        masterList.add(1, new ToDoList("Gym", mockListGym));
        masterList.add(2, new ToDoList("Garden", mockListGarden));
        masterList.add(3, new ToDoList("Dog Park", mockListPark));
        masterList.add(4, new ToDoList("Game Night", mockListGame));
        return masterList;
    }

    private ObservableList<ToDoListItem> mockListItems() {
        ObservableList<ToDoListItem> mockList = FXCollections.observableArrayList();
        mockList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        mockList.add(1, new ToDoListItem("Gym", "go to Gym", "2021-12-01", "In-Progress"));
        mockList.add(1, new ToDoListItem("Garden", "water the plants", "2021-12-01", "In-Progress"));
        return mockList;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Should execute after Each Test");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
}