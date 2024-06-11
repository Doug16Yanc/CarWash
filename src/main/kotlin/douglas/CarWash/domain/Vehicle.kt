package douglas.CarWash.domain

import douglas.CarWash.enumeration.VehicleStatus
import douglas.CarWash.enumeration.VehicleType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "vehicles")
data class Vehicle (
    @Id
    val id : String,

    var brand : String? = null,

    var model : String? = null,

    var color : String? = null,

    var plate : String? = null,

    val type : VehicleType? = null,

    var status : VehicleStatus? = null
)