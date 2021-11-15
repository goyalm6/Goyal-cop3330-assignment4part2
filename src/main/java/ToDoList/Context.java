/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ToDoList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Context {
    private final static Context instance = new Context();
    private  ToDoList  todoListInContext ;
    private ObservableList<ToDoList> lists = FXCollections.observableArrayList();

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    private String Mode;
    public static Context getInstance() {
        return instance;
    }

    public ToDoList getTodoListInContext() {
        if(todoListInContext == null)
            return new ToDoList();
        else
            return todoListInContext;
    }

    public void setTodoListInContext(ToDoList todoListInContext) {
        this.todoListInContext = todoListInContext;
    }

    public ObservableList<ToDoList> getLists() {
        return lists;
    }

    public void setLists(ObservableList<ToDoList> lists) {
        this.lists = lists;
    }
}
