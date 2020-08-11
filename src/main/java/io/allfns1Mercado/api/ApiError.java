package io.allfns1Mercado.api;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;



@Data
public class ApiError {


    @Getter
    List<String> errors;


    public ApiError(String errors){
         this.errors = Arrays.asList(errors);

    }


}
