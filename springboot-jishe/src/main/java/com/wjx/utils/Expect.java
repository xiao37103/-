package com.wjx.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

@RestControllerAdvice
@Component
@Slf4j
public class Expect {
    @ExceptionHandler(Exception.class)
    public Result doExpect(Exception e) {
        log.info("{}", e.getMessage());
        if (e instanceof doSqlExpect) {
            doSqlExpect((doSqlExpect) e);
        }
        return Result.error("数据未收录");
    }

    @ExceptionHandler(doSqlExpect.class)
    public Result doSqlExpect(doSqlExpect e) {
        return Result.error(e.getMessage());
    }

}
