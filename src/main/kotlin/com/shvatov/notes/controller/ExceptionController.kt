package com.shvatov.notes.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.persistence.EntityNotFoundException

/**
 * @author shvatov
 */
@ControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [IllegalArgumentException::class])
    protected fun handleIllegalArgumentException(
        ex: RuntimeException,
        request: WebRequest
    ): ResponseEntity<Any?> {
        return handleExceptionInternal(
            ex, ex.message,
            HttpHeaders(), HttpStatus.CONFLICT,
            request
        )
    }

    @ExceptionHandler(value = [EntityNotFoundException::class])
    protected fun handleEntityNotFoundException(
        ex: RuntimeException,
        request: WebRequest
    ): ResponseEntity<Any?> {
        return handleExceptionInternal(
            ex, ex.message,
            HttpHeaders(), HttpStatus.NOT_FOUND,
            request
        )
    }
}