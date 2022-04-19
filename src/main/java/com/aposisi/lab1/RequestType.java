package com.aposisi.lab1;

import java.util.Arrays;
import java.util.Optional;

public enum RequestType {
    GET("GET"),
    POST("POST"),
    OPTIONS("OPTIONS");

    private String name;

    RequestType(String name){
        this.name = name;
    }

    public static Optional<RequestType> of(String name){
        return Arrays.stream(RequestType.values())
                .filter(x -> x.name.equalsIgnoreCase(name))
                .findFirst();
    }
}
