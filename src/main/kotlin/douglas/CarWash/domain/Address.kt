package douglas.CarWash.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Address (
    var street : String? = null,
    var number : Int? = null,
    var neighborhood : String? = null,
    var city : String? = null,
    var zipCode : String? = null
)