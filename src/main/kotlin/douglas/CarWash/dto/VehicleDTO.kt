package douglas.CarWash.dto

import douglas.CarWash.domain.Vehicle
import douglas.CarWash.enumeration.VehicleType

data class VehicleDTO (
    var id : Long,
    var brand : String,
    var model : String,
    var color : String,
    var plate : String,
    var type : VehicleType
) {
    fun doVehicle() : Vehicle {
        return Vehicle(
            id,
            brand,
            model,
            color,
            plate,
            type
        )
    }
}

