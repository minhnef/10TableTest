package com.example.quanlykhoahoc.entity.respon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Respon<T>{
    int status;
    String error;
    T data;

}
