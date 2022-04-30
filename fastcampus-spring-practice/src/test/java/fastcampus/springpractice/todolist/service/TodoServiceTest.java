package fastcampus.springpractice.todolist.service;


import fastcampus.springpractice.todolist.model.TodoEntity;
import fastcampus.springpractice.todolist.model.TodoRequest;
import fastcampus.springpractice.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        Mockito
                .when(this.todoRepository.save(Mockito.any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntity actual = this.todoService.add(expected);
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(123L);
        todoEntity.setCompleted(false);
        todoEntity.setOrder(0L);
        todoEntity.setTitle("Test Title");

        Optional<TodoEntity> optional = Optional.of(todoEntity);

        given(todoRepository.findById(Mockito.anyLong()))
                .willReturn(optional);

        TodoEntity actual = this.todoService.searchById(123L);
        TodoEntity expected = optional.get();

        org.assertj.core.api.Assertions.assertThat(expected.getId()).isEqualTo(actual.getId());
        org.assertj.core.api.Assertions.assertThat(expected.getTitle()).isEqualTo(actual.getTitle());
        org.assertj.core.api.Assertions.assertThat(expected.getOrder()).isEqualTo(actual.getOrder());
        org.assertj.core.api.Assertions.assertThat(expected.getCompleted()).isEqualTo(actual.getCompleted());
    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            this.todoService.searchById(1234544L);
        });
    }

}