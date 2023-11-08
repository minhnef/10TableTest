package com.example.quanlykhoahoc.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Request<T> {
    private int status;
    private String error;
    private T data;
}
