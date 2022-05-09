package fastcampus.springpractice.todolist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fastcampus.springpractice.todolist.model.TodoModel;
import fastcampus.springpractice.todolist.model.TodoRequest;
import fastcampus.springpractice.todolist.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    private TodoModel expected;

    @MockBean
    private TodoService todoService;

    @BeforeEach
    void setup() {
        expected = new TodoModel();
        expected.setId(123L);
        expected.setTitle("Test Title");
        expected.setOrder(0L);
        expected.setCompleted(false);

    }

    @Test
    void create() throws Exception{
        Mockito
                .when(todoService.add(Mockito.any(TodoRequest.class)))
                .then((i) -> {
                    TodoRequest request = i.getArgument(0, TodoRequest.class);
                    return new TodoModel(expected.getId(), request.getTitle(), expected.getOrder(), expected.getCompleted());
                });

        TodoRequest request = new TodoRequest();
        request.setTitle("Super Title");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);


        mvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Super Title"));
    }

    @Test
    void readOne() throws Exception{
        Mockito
                .when(this.todoService.searchById(Mockito.anyLong()))
                .then(a -> expected);

        mvc
                .perform(get("/{%d}", expected.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(expected.getTitle()));
    }

}