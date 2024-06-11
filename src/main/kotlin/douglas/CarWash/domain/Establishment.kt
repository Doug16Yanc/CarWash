package douglas.CarWash.domain

import douglas.CarWash.enumeration.VehicleStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.annotation.processing.Generated

@Document(collection = "establishments")
class Establishment(
    @Id
    var id: String,
    var name: String?,
    var address: Address?,
    var telephone: String?,
    var vehicleNumber: Int?,
    private var vehicle: MutableList<Vehicle>?,
    var vehiclesEntered: Int?,
    var vehiclesExited: Int?
) {

    private fun calculateProhibited(): Int {
        return vehicle?.count { it.status == VehicleStatus.PROHIBITED } ?: 0
    }

    private fun calculateExited(): Int {
        return vehicle?.count { it.status == VehicleStatus.EXIT } ?: 0
    }

}
