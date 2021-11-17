package com.example.challenge.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestDto {

    @NotEmpty(message = "Field name should not be null or empty")
    private String name;

}
