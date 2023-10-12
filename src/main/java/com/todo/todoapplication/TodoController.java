package com.todo.todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public String getAllTodos(Model model) {
        List<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @PostMapping("/todos")
    public String createTodo(@RequestParam String task) {
        Todo todo = new Todo();
        todo.setTask(task);
        todo.setCompleted(false);
        todoRepository.save(todo);
        return "redirect:/todos";
    }

    // Additional methods for completing and deleting todos

    @GetMapping("/todos/{id}")
    public String getTodoById(@PathVariable Long id, Model model) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        todoOptional.ifPresent(todo -> model.addAttribute("todo", todo));
        return "todo-detail";
    }

    @PostMapping("/todos/{id}/complete")
    public String completeTodo(@PathVariable Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        todoOptional.ifPresent(todo -> {
            todo.setCompleted(true);
            todoRepository.save(todo);
        });
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/todos";
    }
}
