package fastcampus.springpractice.todolist.service;

import fastcampus.springpractice.todolist.model.TodoModel;
import fastcampus.springpractice.todolist.model.TodoRequest;
import fastcampus.springpractice.todolist.service.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoModel add(TodoRequest request) {
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setCompleted(request.getCompleted());

        return this.todoRepository.save(todoModel);
    }

    public TodoModel searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoModel> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoModel updateById(Long id, TodoRequest request) {
        TodoModel todoModel = this.searchById(id);
        if (request.getTitle() != null) {
            todoModel.setTitle(request.getTitle());
        }

        if (request.getOrder() != null) {
            todoModel.setOrder(request.getOrder());
        }

        if (request.getCompleted() != null) {
            todoModel.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoModel);
    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }



}
