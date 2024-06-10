package douglas.CarWash.domain

import jdk.jfr.MemoryAddress
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "establishments")
data class Establishment (

    @Id
    private val id : Long? = null,

    private var name : String? = null,

    private var address : Address? = null,

    private var telephone : String? = null,

    private var vehicleNumber : Int? = null

)