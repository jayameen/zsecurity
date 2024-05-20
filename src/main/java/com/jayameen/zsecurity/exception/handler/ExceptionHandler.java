package com.jayameen.zsecurity.exception.handler;

import com.jayameen.zsecurity.dto.AppResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size:10}")
    private Integer allowedFileSize;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription("File size exceeds the limit of "+ (allowedFileSize)/(1000000) +" MB");
        return ResponseEntity.badRequest().body(appResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription(exception.getMessage());
        return ResponseEntity.badRequest().body(appResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> nullPointerException(NullPointerException exception) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription(exception.getMessage());
        return ResponseEntity.internalServerError().body(appResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exception(Exception exception) {
        System.out.println("#########################\n Unhandled Exception occurred\n#########################");
        exception.printStackTrace();
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription(exception.getMessage());
        return ResponseEntity.internalServerError().body(appResponse);
    }


}
