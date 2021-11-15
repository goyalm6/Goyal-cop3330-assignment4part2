/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ToDoList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

public class ToDoLists implements Serializable {

    private ObservableList<ToDoList>  lists = FXCollections.observableArrayList();

    public ToDoLists() {
    }

    public ToDoLists(ObservableList<ToDoList> lists) {
        this.lists = lists;
    }

    public ObservableList<ToDoList> getLists() {
        return lists;
    }

    public void setLists(ObservableList<ToDoList> lists) {
        this.lists = lists;
    }
}
