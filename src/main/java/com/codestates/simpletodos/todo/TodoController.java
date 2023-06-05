package com.codestates.simpletodos.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TodoDto postTodo(@Valid @RequestBody TodoDto todoDto,
                            HttpServletRequest request) {
        Todo createdTodo = todoService.createTodo(new Todo(todoDto));

        String requestUrl = request.getRequestURL().toString();
        return new TodoDto(createdTodo, requestUrl + String.valueOf(createdTodo.getId()));
    }

    @GetMapping
    public List<TodoDto> getAllTodos(HttpServletRequest request) {
        List<Todo> allTodos = todoService.findAllTodos();

        String requestUrl = request.getRequestURL().toString();
        return allTodos.stream()
                .map(todo -> new TodoDto(todo, requestUrl + String.valueOf(todo.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public TodoDto getTodo(@PathVariable long id,
                           HttpServletRequest request) {
        Todo foundTodo = todoService.findTodo(id);

        String requestUrl = request.getRequestURL().toString();
        return new TodoDto(foundTodo, requestUrl);
    }

    @PatchMapping(value = "/{id}")
    public TodoDto patchTodo(@PathVariable long id,
                             @RequestBody TodoDto todoDto,
                             HttpServletRequest request) {
        Todo updatedTodo = todoService.updateTodo(new Todo(todoDto), id);

        String requestUrl = request.getRequestURL().toString();
        return new TodoDto(updatedTodo, requestUrl);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteAllTodos() {
        todoService.deleteAllTodos();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
    }
}
