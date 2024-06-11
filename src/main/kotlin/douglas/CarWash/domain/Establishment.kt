package douglas.CarWash.domain

import douglas.CarWash.enumeration.VehicleStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "establishments")
class Establishment(
    @Id var id: Long?,
    var name: String?,
    var address: Address?,
    var telephone: String?,
    var vehicleNumber: Int?,
    private var vehicle: MutableList<Vehicle>?,
    private var vehiclesEntered: Int?,
    private var vehiclesExited: Int?
) {

    private fun calculateProhibited(): Int {
        return vehicle?.count { it.status == VehicleStatus.PROHIBITED } ?: 0
    }

    private fun calculateExited(): Int {
        return vehicle?.count { it.status == VehicleStatus.EXIT } ?: 0
    }

}
