/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ToDoList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ucf.assignments.ManageListController;

import java.io.Serializable;

public class ToDoList  implements Serializable {

    private String title = "MyList";
    private ObservableList<ToDoListItem> items = FXCollections.observableArrayList();

    public ToDoList() {
    }

    public ToDoList(String title, ObservableList<ToDoListItem> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObservableList<ToDoListItem> getItems() {
        return items;
    }

    public void setItems(ObservableList<ToDoListItem> items) {
        this.items = items;
    }
}
