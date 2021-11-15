/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Mayank Goyal
 */

package ToDoList;

import java.io.Serializable;

public class ToDoListItem implements Serializable {

    private String title = null;
    private String description = null;
    private String status;
    private String listduedate;

    public ToDoListItem() {
    }

    public ToDoListItem(String title, String description, String listduedate, String status) {
        this.title = title;
        this.description = description;
        this.listduedate = listduedate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getListduedate() {
        return listduedate;
    }

    public void setListduedate(String listduedate) {
        this.listduedate = listduedate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
