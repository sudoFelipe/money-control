package dev.programing.moneycontrol.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
    private String details;
}
