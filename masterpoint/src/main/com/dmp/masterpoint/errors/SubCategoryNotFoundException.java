package com.dmp.masterpoint.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Не е намерена такава услуга!")
public class SubCategoryNotFoundException extends RuntimeException {
}
