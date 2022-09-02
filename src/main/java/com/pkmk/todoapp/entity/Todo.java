package com.pkmk.todoapp.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp createdOn;
    @Column(name = "todo_message")
    private String todoMessage;
    private Timestamp modifiedOn;

    public Todo(){}

    public Todo(String todo, Timestamp createOn, Timestamp modifiedOn) {
        this.todoMessage = todo;
        this.createdOn = createOn;
        this.modifiedOn = modifiedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getTodo() {
        return todoMessage;
    }

    public void setTodo(String todo) {
        this.todoMessage = todo;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
