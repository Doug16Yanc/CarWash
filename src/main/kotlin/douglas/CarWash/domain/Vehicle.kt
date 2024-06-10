package douglas.CarWash.domain

import douglas.CarWash.enumeration.VehicleType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "vehicles")
data class Vehicle (
    @Id
    private val id : Long? = null,

    private val brand : String? = null,

    private val model : String? = null,

    private val color : String? = null,

    private val plate : String? = null,

    private val type : VehicleType? = null
)