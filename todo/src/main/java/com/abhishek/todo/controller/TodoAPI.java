package com.abhishek.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/todo")
public class TodoAPI {

    private final Map<Long, Todo> todos = new HashMap<>();
    private Long counter = 1L;

    @GetMapping
    @ResponseBody
    public List<Todo> getAll() {
        return new ArrayList<>(todos.values());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        if ((todo.getTitle() == null) || todo.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Long id = counter++;
        todo.setId(id);
        todos.put(id, todo);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo updated) {
        Todo existing = todos.get(id);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (updated.getTitle() != null && !updated.getTitle().isBlank()) {
            existing.setTitle(updated.getTitle());
        }
        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }

        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!todos.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        todos.remove(id);
        return ResponseEntity.noContent().build();
    }
}
