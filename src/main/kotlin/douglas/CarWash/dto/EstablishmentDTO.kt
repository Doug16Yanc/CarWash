package douglas.CarWash.dto

import douglas.CarWash.domain.Address
import douglas.CarWash.domain.Establishment

data class EstablishmentDTO (
    var id : Long,
    var name : String,
    var address: Address,
    var telephone : String,
    var vehicleNumber : Int,
    var vehiclesEntered : Int,
    var vehiclesExited : Int
) {
    fun doEstablishment () : Establishment {
        return Establishment(
            id,
            name,
            Address(
                address.street,
                address.number,
                address.neighborhood,
                address.city
            ),
            telephone,
            vehicleNumber,
            null,
            vehiclesEntered,
            vehiclesExited
        )
    }
}