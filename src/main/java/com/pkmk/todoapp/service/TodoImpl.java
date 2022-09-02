package com.pkmk.todoapp.service;

import com.pkmk.todoapp.TodoDTO.TodoDTO;
import com.pkmk.todoapp.entity.Todo;
import com.pkmk.todoapp.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoImpl implements TodoIntf {

    final Logger logger = LoggerFactory.getLogger(TodoImpl.class);

    @Autowired
    private TodoRepository todoRepo;


    @Override
    public void createTodo(TodoDTO todo) {
        logger.info("create todo method called");
        Todo todojObj = new Todo();
        System.out.println(todo.toString());
        try {
            todojObj.setTodo(todo.getMessage());
            todojObj.setCreatedOn(new Timestamp(System.currentTimeMillis()));
            todojObj.setModifiedOn(new Timestamp(System.currentTimeMillis()));
            todoRepo.save(todojObj);
        } catch (Exception ex) {
            logger.error(String.format("Exception occurred during saving new todo. Because of %s", ex.getMessage()));
        }
        logger.info("todo is successfully saved");
    }

    @Override
    public void deleteTodo(Long id) {
        logger.info("delete todo method called");
        try {
            todoRepo.deleteById(id);
        } catch (Exception ex) {
            logger.error(String.format("Exception occurred during deleting a todo. Because of %s", ex.getMessage()));
        }
        logger.info("todo is successfully deleted");
    }

    @Override
    public boolean updateTodo(Long id, String todoMessageToUpdate) {
        boolean response;
        try {
            Optional<Todo> res = todoRepo.findById(id);
            Todo todo = res.orElse(new Todo());
            if (!todo.getTodo().isEmpty()) {
                todo.setTodo(todoMessageToUpdate);
                todo.setModifiedOn(new Timestamp(System.currentTimeMillis()));
                todoRepo.save(todo);
                response = true;
            }else{
                response = false;
            }
        } catch (Exception ex) {
            response = false;
            logger.error(String.format("Exception occurred during updating a todo. Because of %s", ex.getMessage()));
        }
        return response;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> response = new ArrayList<>();
        try{
            logger.info("Fetched all todos successfully");
            response = todoRepo.findAll();
        }catch (Exception exception){
            logger.error(String.format("Exception occurred during getting all todos. Because of %s", exception.getMessage()));
        }
        return response;
    }
}
