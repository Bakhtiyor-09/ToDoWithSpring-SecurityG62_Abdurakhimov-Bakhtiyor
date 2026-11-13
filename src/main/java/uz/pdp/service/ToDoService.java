package uz.pdp.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import uz.pdp.dto.ToDoDto;
import uz.pdp.entity.ToDo;
import uz.pdp.exceptions.FieldIsWrongException;
import uz.pdp.exceptions.ItemNotFoundException;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.repository.ToDoRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Component
public class ToDoService {


    private final ToDoRepository toDoRepository;

    public ToDoService( ToDoRepository toDoRepository) {

        this.toDoRepository = toDoRepository;
    }

    public void create(@Valid ToDoDto dto) {

        if (dto.title()==null || dto.title().isBlank()){
            throw new FieldIsWrongException("title is wrong");
        }

        ToDo build = ToDo.builder()
                .title(dto.title())
                .completed(false)
                .createdAt(dto.createdAt())
                .userid(dto.userId())
                .build();

         toDoRepository.save(build);
    }

    public Optional<List<ToDo>> getToDOs(Long id) {

       return toDoRepository.findAllByUserId(id);

    }

    public Optional<ToDo> getToDoById(Long id) {
        return toDoRepository.findById(id);
    }

    public void updateToDoById(Long id) {

        Optional<ToDo> byId = toDoRepository.findById(id);

        ToDo toDo = byId.orElseThrow(() -> new ItemNotFoundException("todo is not found with id :" + id));
        
        toDo.setCompleted(true);
        toDoRepository.save(toDo);
    }

    public void deleteToDoById(Long id) {

        toDoRepository.deleteById(id);

    }
}
