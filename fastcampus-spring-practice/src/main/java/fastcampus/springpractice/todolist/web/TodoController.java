package fastcampus.springpractice.todolist.web;

import fastcampus.springpractice.todolist.model.TodoModel;
import fastcampus.springpractice.todolist.model.TodoRequest;
import fastcampus.springpractice.todolist.model.TodoResponse;
import fastcampus.springpractice.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger log = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) throws Exception {
        log.info(String.valueOf(this.getClass().getDeclaredMethod("create", TodoRequest.class)));

        if (ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        if (ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }

        if (ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoModel result = this.todoService.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable("id") Long id) throws Exception {
        log.info(String.valueOf(this.getClass().getDeclaredMethod("readOne", Long.class)));

        TodoModel result = this.todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readALl()  throws Exception {
        log.info(String.valueOf(this.getClass().getDeclaredMethod("readALl")));

        List<TodoResponse> result = this.todoService.searchAll().stream()
                .map(TodoResponse::new)
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(result);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable("id") Long id, @RequestBody TodoRequest request) throws Exception{
        log.info(String.valueOf(this.getClass().getDeclaredMethod("update", Long.class, TodoRequest.class)));

        TodoModel result = todoService.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") Long id) throws Exception {
        log.info(String.valueOf(this.getClass().getDeclaredMethod("deleteOne", Long.class)));

        this.todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteALl() throws Exception {
        log.info(String.valueOf(this.getClass().getDeclaredMethod("deleteALl")));

        this.todoService.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
