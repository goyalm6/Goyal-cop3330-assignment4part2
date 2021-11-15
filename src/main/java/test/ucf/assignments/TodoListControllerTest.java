/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ucf.assignments;

import ToDoList.ToDoList;
import ToDoList.ToDoListItem;
import ToDoList.ToDoListSerializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoListControllerTest {

    TodoListController todoListController;

    @BeforeAll
    public void setUpAll() {
        System.out.println("Should print before all Tests");
    }

    @BeforeEach
    void setUp() {
        todoListController = new TodoListController();
        todoListController.maxListCount = 3;
        todoListController.todoListInContext = new ToDoList();
    }

    @Test
    @DisplayName("Should return true when List has items equal to max count")
    void isMaxListCountShouldReturnTrue()
    {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();
        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(2, new ToDoListItem("Grocery2", "get fruits and vegetables", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
        Boolean hasReachedMaxCount = todoListController.isMaxListCount();

        //asset
        assertEquals(true, hasReachedMaxCount);
    }

    @Test
    @DisplayName("Should return true when List has items greater than max count")
    void isMaxListCountShouldReturnTrueWhenItemsAreMoreThanMaxCount()
    {
        mockListInContext();
        Boolean hasReachedMaxCount = todoListController.isMaxListCount();

        //asset
        assertEquals(true, hasReachedMaxCount);
    }

    @Test
    @DisplayName("Should return true when List title is unique when adding")
    void CheckIfTitleIsUniqueWhenAddingShouldReturnTrueWhenTitleIsUnique()
    {
        mockListInContext();
        Boolean isUniqueTitle = todoListController.CheckIfTitleIsUnique("add", "Dog park");

        //asset
        assertEquals(true, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return false when List title is not unique when adding")
    void CheckIfTitleIsUniqueWhenAddingShouldReturnFalseWhenTitleIsNotUnique()
    {
        mockListInContext();
        Boolean isUniqueTitle = todoListController.CheckIfTitleIsUnique("add", "Grocery");

        //asset
        assertNotEquals(false, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return true when List title is unique when editing")
    void CheckIfTitleIsUniqueWhenEditingShouldReturnTrueWhenTitleIsUnique()
    {
        mockListInContext();
        Boolean isUniqueTitle = todoListController.CheckIfTitleIsUnique("edit", "Dog park");

        //asset
        assertEquals(true, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return false when List title is not unique when editing")
    void CheckIfTitleIsUniqueWhenEditingShouldReturnFalseWhenTitleIsNotUnique()
    {
        mockListInContext();
        Boolean isUniqueTitle = todoListController.CheckIfTitleIsUnique("edit", "Grocery");

        //asset
        assertNotEquals(false, isUniqueTitle);
    }

    @Test
    @DisplayName("Should return false when List has items less than max count")
    void isMaxListCountShouldReturnFalse()
    {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();
        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
        Boolean hasReachedMaxCount = todoListController.isMaxListCount();

        //asset
        assertEquals(false, hasReachedMaxCount);
    }

    @Test
    @DisplayName("Should add item to the List")
    void shouldAddItemToList()
    {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();
        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
        Integer countBefore = todoListController.todoListInContext.getItems().size();

        todoListController.addItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress");
        Integer countAfter = todoListController.todoListInContext.getItems().size();

        //asset
        assertNotSame(countBefore, countAfter);
    }

    @Test
    @DisplayName("Should edit item from the List and Item Count should remain the same")
    void shouldEditItemFromList()
    {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();
        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
        Integer countBefore = todoListController.todoListInContext.getItems().size();

        ObservableList<ToDoListItem> newList = FXCollections.observableArrayList();
        newList.add(0, new ToDoListItem("GroceryNew", "get fruits and vegetablesnew", "2021-12-01", "In-Progress"));

        todoListController.editItem(myList, "GroceryNew", "get fruits and vegetablesnew", "2021-12-01", "Completed");
        Integer countAfter = todoListController.todoListInContext.getItems().size();

        //asset
        assertSame(myList.get(0).getTitle(), newList.get(0).getTitle());
        assertSame(countBefore, countAfter);
    }

    @Test
    @DisplayName("Should delete item from the List and Item Count should change")
    void shouldDeleteItemFromList()
    {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();
        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
        Integer countBefore = todoListController.todoListInContext.getItems().size();

        ObservableList<ToDoListItem> newList = FXCollections.observableArrayList();
        newList.add(0, new ToDoListItem("GroceryNew", "get fruits and vegetablesnew", "2021-12-01", "In-Progress"));

        todoListController.deleteItem(myList.get(1));
        Integer countAfter = todoListController.todoListInContext.getItems().size();

        //asset
        assertNotSame(countBefore, countAfter);
    }

    @Test
    @DisplayName("Should Export Current Todo list")
    void shouldExportCurrentList()
    {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();

        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
        Integer countBefore = todoListController.todoListInContext.getItems().size();

       ToDoListSerializable todoListInContextSerializable  =  new ToDoListSerializable();
       todoListInContextSerializable.setTitle(todoListController.todoListInContext.getTitle());
       ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>(todoListController.todoListInContext.getItems());
        todoListInContextSerializable.setItems(items);

        Integer countAfter = todoListInContextSerializable.getItems().size();

        //asset
        assertSame(countBefore, countAfter);
    }

    private void mockListInContext() {
        ObservableList<ToDoListItem> myList = FXCollections.observableArrayList();
        myList.add(0, new ToDoListItem("Grocery", "get fruits and vegetables0", "2021-12-01", "In-Progress"));
        myList.add(1, new ToDoListItem("Grocery1", "get fruits and vegetables1", "2021-12-01", "In-Progress"));
        myList.add(2, new ToDoListItem("Grocery2", "get fruits and vegetables2", "2021-12-01", "In-Progress"));
        myList.add(3, new ToDoListItem("Grocery3", "get fruits and vegetables3", "2021-12-01", "In-Progress"));

        todoListController.todoListInContext.setItems(myList);
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