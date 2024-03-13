package com.kzvo.ptmbackend.api.controller;

import com.kzvo.ptmbackend.exceptions.badrequest.BadRequestException;
import com.kzvo.ptmbackend.exceptions.notfound.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseController {

    public void validateFound(Object object, Class<?> clazz, Long id) {
        validateFound(object, clazz.getSimpleName() + " with ID " + id + " not found.");
    }

    public void validateFound(Object object, String errorMessage) {
        if (object == null) {
            throw new NotFoundException(errorMessage);
        }
    }

    public void validateId(Long id, String errorMessage) {
        if (id == null) {
            throw new BadRequestException(errorMessage);
        }
    }

}
