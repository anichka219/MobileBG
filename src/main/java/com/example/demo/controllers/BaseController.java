package com.example.demo.controllers;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.BaseException;
import com.example.demo.exceptions.InvalidInputException;
import com.example.demo.exceptions.NotLoggedAdminException;
import com.example.demo.exceptions.NotLoggedInException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.exceptions.ProductOutOfStockException;
import com.example.demo.models.ErrorMsg;
import com.example.demo.models.User;

@RestController
public abstract class BaseController {

    public static final String LOGGED_USER = "loggedUser";

    @ExceptionHandler({InvalidInputException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsg invalidInput(Exception e) {
        return new ErrorMsg(e.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMsg productNotFound(Exception e) {
        return new ErrorMsg(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({NotLoggedInException.class, NotLoggedAdminException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMsg notLoggedHandler(Exception e){
        return new ErrorMsg(e.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({ProductOutOfStockException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsg productOutOfStockHandler(Exception e) {
        return new ErrorMsg(e.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler({BaseException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsg basedHandler(){
        return new ErrorMsg(new BaseException("You are trying to input invalid properties. Try again!").getMessage(),
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMsg sqlHandler(Exception e) {
        return new ErrorMsg(e.getMessage(), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }





    protected void validateLogin(HttpSession session) throws NotLoggedInException{
        if(session.getAttribute(LOGGED_USER) == null) {
            throw new NotLoggedInException();
        }
    }

    protected void validateLoginAdmin(HttpSession session)throws BaseException {
        if(session.getAttribute(LOGGED_USER) == null){
            throw new NotLoggedInException();
        }
        else {
            User user = (User)(session.getAttribute(LOGGED_USER));
            if(!user.isAdministrator()) {
                throw new NotLoggedAdminException();
            }
        }
    }

}
