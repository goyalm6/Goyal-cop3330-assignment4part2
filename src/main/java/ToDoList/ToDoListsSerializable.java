/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ToDoList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoListsSerializable implements Serializable {

    private ArrayList<ToDoListSerializable>  lists = new ArrayList<ToDoListSerializable>();;

    public ToDoListsSerializable() {
    }

    public ToDoListsSerializable(ArrayList<ToDoListSerializable> lists) {
        this.lists = lists;
    }

    public ArrayList<ToDoListSerializable> getLists() {
        return lists;
    }

    public void setLists(ArrayList<ToDoListSerializable> lists) {
        this.lists = lists;
    }
}
