package douglas.CarWash.controller

import douglas.CarWash.exception.EstablishmentException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ProblemDetail {
        val fieldErrors = e.fieldErrors
            .map { InvalidParam(it.field, it.defaultMessage ?: "Invalid value") }

        val problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)
        problemDetail.title = "Your request parameters don’t validate."
        problemDetail.setProperty("Invalid-params", fieldErrors)

        return problemDetail
    }

    private data class InvalidParam(val fieldName: String, val reason: String)

}