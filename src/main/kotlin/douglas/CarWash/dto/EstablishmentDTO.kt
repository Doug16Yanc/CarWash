package douglas.CarWash.dto

import douglas.CarWash.domain.Address
import douglas.CarWash.domain.Establishment
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add

data class EstablishmentDTO (
    var id : Long,
    var name : String,
    var address: Address,
    var telephone : String,
    var vehicleNumber : Int
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
            vehicleNumber
        )
    }
}