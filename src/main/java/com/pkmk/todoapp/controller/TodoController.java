package com.pkmk.todoapp.controller;

import com.pkmk.todoapp.TodoDTO.TodoDTO;
import com.pkmk.todoapp.Util.Names;
import com.pkmk.todoapp.entity.Todo;
import com.pkmk.todoapp.service.TodoIntf;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/todo")
public class TodoController {

    @Autowired
    private TodoIntf todoService;

    @PostMapping("create")
    public JSONObject createTodo(@RequestBody TodoDTO dto) {
        JSONObject response = new JSONObject();
        try {
            todoService.createTodo(dto);
            response.put(Names.MESSAGE, "Todo successfully created");
            response.put(Names.STATUS, HttpStatus.CREATED.value());
        } catch (Exception ex) {
            response.put(Names.MESSAGE, "Todo is failed to create!");
            response.put(Names.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @GetMapping("")
    public JSONObject allTodos() {
        List<Todo> allTodos;
        JSONObject response = new JSONObject();
        try {
            allTodos = todoService.getAll();
            response.put(Names.MESSAGE, "All todos fetched successfully");
            response.put(Names.STATUS, HttpStatus.OK.value());
            response.put(Names.DATA, allTodos);
        } catch (Exception ex) {
            response.put(Names.MESSAGE, "Failed to fetch all todos!!");
            response.put(Names.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @DeleteMapping("delete/{id}")
    public JSONObject deleteTodo(@PathVariable("id") Long id) {
        JSONObject response = new JSONObject();
        try {
            todoService.deleteTodo(id);
            response.put(Names.MESSAGE, "Todo deleted successfully");
            response.put(Names.STATUS, HttpStatus.OK.value());
        } catch (Exception ex) {
            response.put(Names.MESSAGE, "Failed to deleted todo");
            response.put(Names.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @PutMapping("update")
    public JSONObject updateTodo(@RequestBody TodoDTO todoDTO) {
        JSONObject response = new JSONObject();
        boolean res;
        try {
            res = todoService.updateTodo(todoDTO.getId(), todoDTO.getMessage());
            if(!res){
                response.put(Names.MESSAGE, "No todo found");
                response.put(Names.STATUS, HttpStatus.NOT_FOUND.value());
            }else{
                response.put(Names.MESSAGE, "Todo updated successfully");
                response.put(Names.STATUS, HttpStatus.OK.value());
            }
        } catch (Exception ex) {
            response.put(Names.MESSAGE, "Failed to update todo");
            response.put(Names.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}
