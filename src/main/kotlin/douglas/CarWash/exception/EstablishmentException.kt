package douglas.CarWash.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

open class EstablishmentException {

    open fun toProblemDetail() : ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        problemDetail.title = "Establishment data already exists. Check the possibility of update, please."

        return problemDetail
    }
}