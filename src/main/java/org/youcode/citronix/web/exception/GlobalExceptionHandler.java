package org.youcode.citronix.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.youcode.citronix.web.exception.Farm.FarmNotFoundException;
import org.youcode.citronix.web.exception.Farm.FarmSizeException;
import org.youcode.citronix.web.exception.Farm.InvalidFarmException;
import org.youcode.citronix.web.exception.Field.FieldNotFoundException;
import org.youcode.citronix.web.exception.Harvest.HarvestAlreadyExistException;
import org.youcode.citronix.web.exception.Harvest.HarvestAlreadySoldException;
import org.youcode.citronix.web.exception.Harvest.HarvestNotFoundException;
import org.youcode.citronix.web.exception.Sale.SaleNotFoundException;
import org.youcode.citronix.web.exception.Tree.InvalidPlantingDateException;
import org.youcode.citronix.web.exception.Tree.TreeDensityException;
import org.youcode.citronix.web.exception.Tree.TreeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    //////    Farm exception handler    //////

    @ExceptionHandler(InvalidFarmException.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidFarmException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<String> handleInvalidUserException(FarmNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FarmSizeException.class)
    public ResponseEntity<String> handleInvalidUserException(FarmSizeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //////    Field exception handler    //////

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<String> handleInvalidUserException(FieldNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //////    Tree exception handler    //////

    @ExceptionHandler(InvalidPlantingDateException.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidPlantingDateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TreeDensityException.class)
    public ResponseEntity<String> handleInvalidUserException(TreeDensityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TreeNotFoundException.class)
    public ResponseEntity<String> handleInvalidUserException(TreeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    //////    Harvest exception handler    //////

    @ExceptionHandler(HarvestAlreadyExistException.class)
    public ResponseEntity<String> handleInvalidUserException(HarvestAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HarvestNotFoundException.class)
    public ResponseEntity<String> handleInvalidUserException(HarvestNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HarvestAlreadySoldException.class)
    public ResponseEntity<String> handleInvalidUserException(HarvestAlreadySoldException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //////    Sale exception handler    //////

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<String> handleInvalidUserException(SaleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
