package douglas.CarWash.domain

import douglas.CarWash.dto.EstablishmentDTO
import douglas.CarWash.enumeration.VehicleStatus
import douglas.CarWash.service.VehicleService
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "establishments")
data class Establishment (

    @Id val id : Long? = null,

    var name : String? = null,

    var address : Address? = null,

    var telephone : String? = null,

    var vehicleNumber : Int? = null,

    var vehicle: MutableList<Vehicle>? = null,

) {
    val vehiclesEntered: Int
        get() = calculateProhibited()

    val vehiclesExited: Int
        get() = calculateExited()

    private fun calculateProhibited(): Int {
        return vehicle?.count { it.status == VehicleStatus.PROHIBITED } ?: 0
    }

    private fun calculateExited(): Int {
        return vehicle?.count { it.status == VehicleStatus.EXIT } ?: 0
    }
}

