package com.vuduy.restfulapi.service.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Xử lý toàn bộ exception controller
public class GlobalException {

    @ExceptionHandler(value = IdInValidException.class)
    public ResponseEntity<String> handleIdInValidException(IdInValidException duy) {
        return ResponseEntity.badRequest().body(duy.getMessage());
    }

}
