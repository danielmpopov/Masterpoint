package com.dmp.masterpoint.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Вече сте логнат!")
public class UserAlreadyLoggedInException extends RuntimeException {
}
