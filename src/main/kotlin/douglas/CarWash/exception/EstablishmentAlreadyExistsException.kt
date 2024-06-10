package douglas.CarWash.exception

import org.springframework.http.ProblemDetail

class EstablishmentAlreadyExistsException : EstablishmentException() {

    var detail : String? = null

    @Override
    override fun toProblemDetail() : ProblemDetail {
        return super.toProblemDetail()
    }
}