package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.config.JwtTokenUtil;
import uz.pdp.dto.ToDoDto;
import uz.pdp.entity.AuthUser;
import uz.pdp.entity.ToDo;
import uz.pdp.service.ToDoService;
import uz.pdp.service.authService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final ToDoService toDoService;
    private final authService authService;
    private final JwtTokenUtil jwtTokenUtil;

    public ToDoController(ToDoService toDoService, authService authService, JwtTokenUtil jwtTokenUtil) {
        this.toDoService = toDoService;
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> createToDo(@Valid @RequestBody ToDoDto dto){

        toDoService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<ToDo>> getToDos( Principal principal){

       AuthUser authUser= authService.getIdByUsername(principal.getName()).get();

       Optional<List<ToDo>> toDos = toDoService.getToDOs(authUser.getId());

       return ResponseEntity.ok(toDos.get());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") Long id){


        Optional<ToDo> toDo = toDoService.getToDoById(id);

        return ResponseEntity.ok(toDo.get());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateToDoById(@PathVariable("id") Long id){

         toDoService.updateToDoById(id);

        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable("id") Long id){

        toDoService.deleteToDoById(id);

        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

}
