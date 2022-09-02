package com.pkmk.todoapp.service;

import com.pkmk.todoapp.TodoDTO.TodoDTO;
import com.pkmk.todoapp.entity.Todo;

import java.util.List;

public interface TodoIntf {
    //create_todo
    void createTodo(TodoDTO todo);
    //delete_todo by id
    void deleteTodo(Long id);
    //update_todo by id
    boolean updateTodo(Long id, String todo);
    //get all todos
    List<Todo> getAll();
}
