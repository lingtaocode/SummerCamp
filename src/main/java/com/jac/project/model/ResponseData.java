package com.jac.project.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseData {
    private Object object;
    private String message;
}
