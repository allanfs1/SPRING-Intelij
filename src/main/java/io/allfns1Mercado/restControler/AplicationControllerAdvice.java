package io.allfns1Mercado.restControler;


import io.allfns1Mercado.Exception.PedidoNaoEncontradoException;
import io.allfns1Mercado.Exception.RegradeNegocioException;
import io.allfns1Mercado.api.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class AplicationControllerAdvice {


    @ExceptionHandler(RegradeNegocioException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError RegradeNegocioException(RegradeNegocioException ex){

       String messagem  = ex.getMessage();
       return new ApiError(messagem);

    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiError PedidoNotfondException(PedidoNaoEncontradoException ex){

         return new ApiError(ex.getMessage());

    }



}
