package douglas.CarWash.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "establishments")
data class Establishment (

    @Id val id : Long? = null,

    var name : String? = null,

    var address : Address? = null,

    var telephone : String? = null,

    var vehicleNumber : Int? = null

)