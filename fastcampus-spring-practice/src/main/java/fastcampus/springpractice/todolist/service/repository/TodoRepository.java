package fastcampus.springpractice.todolist.service.repository;

import fastcampus.springpractice.todolist.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
}
