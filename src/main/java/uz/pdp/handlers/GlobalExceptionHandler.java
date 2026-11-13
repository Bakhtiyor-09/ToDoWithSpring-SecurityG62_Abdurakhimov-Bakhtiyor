package uz.pdp.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.exceptions.FieldIsWrongException;
import uz.pdp.exceptions.ItemNotFoundException;
import uz.pdp.exceptions.UserAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "message", ex.getMessage(),
                        "status", 400,
                        "timestamp", LocalDateTime.now(),
                        "path", request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(FieldIsWrongException.class)
    public ResponseEntity<Object> handleFieldIsWrongException(FieldIsWrongException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "message", ex.getMessage(),
                        "status", 400,
                        "timestamp", LocalDateTime.now(),
                        "path", request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "message", ex.getMessage(),
                        "status", 404,
                        "timestamp", LocalDateTime.now(),
                        "path", request.getRequestURI()
                )
        );
    }

}
