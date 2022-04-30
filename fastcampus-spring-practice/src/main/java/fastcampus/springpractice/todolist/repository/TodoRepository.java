package fastcampus.springpractice.todolist.repository;

import fastcampus.springpractice.todolist.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
