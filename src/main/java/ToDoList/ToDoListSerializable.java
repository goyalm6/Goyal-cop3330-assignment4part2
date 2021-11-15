/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ToDoList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoListSerializable implements Serializable {

    private String title = "MyList";
    private ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();

    public ToDoListSerializable() {
    }

    public ToDoListSerializable(String title, ArrayList<ToDoListItem> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ToDoListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ToDoListItem> items) {
        this.items = items;
    }
}
