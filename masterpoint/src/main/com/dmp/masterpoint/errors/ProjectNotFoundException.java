package com.dmp.masterpoint.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Не е намерен такъв проект!")
public class ProjectNotFoundException extends RuntimeException {
}
