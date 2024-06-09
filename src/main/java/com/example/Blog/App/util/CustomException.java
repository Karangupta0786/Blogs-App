package com.example.Blog.App.util;

public class CustomException extends RuntimeException {
    String field;
    String field_type;
    Long value;

    public CustomException(String field, String field_type, Long value) {
//        super(field+"doesn't contains "+field_type+": "+value);
        super(String.format("%s is not available at %s %d",field,field_type,value));
        this.field = field;
        this.field_type = field_type;
        this.value = value;
    }
}
