package com.vuduy.restfulapi.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vuduy.restfulapi.domain.RestReponse;

@RestControllerAdvice // Xử lý toàn bộ exception controller
public class GlobalException {

    @ExceptionHandler(value = IdInValidException.class)
    public ResponseEntity<RestReponse<Object>> handleIdInValidException(IdInValidException idException) {
        RestReponse<Object> res = new RestReponse<>(); // Trả về format theo constructor (RestReponse)
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(idException.getMessage());
        res.setMessage("IdInValidException");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}
