package fastcampus.springpractice.todolist.controller;

import fastcampus.springpractice.todolist.model.TodoEntity;
import fastcampus.springpractice.todolist.model.TodoRequest;
import fastcampus.springpractice.todolist.model.TodoResponse;
import fastcampus.springpractice.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/")
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        if (ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        if (ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }

        if (ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoEntity result = this.todoService.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable("id") Long id) {
        TodoEntity result = this.todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readALl() {
        List<TodoResponse> result = this.todoService.searchAll().stream()
                .map(TodoResponse::new)
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(result);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable("id") Long id, @RequestBody TodoRequest request) {
        TodoEntity result = todoService.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") Long id) {
        this.todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteALl() {
        this.todoService.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
