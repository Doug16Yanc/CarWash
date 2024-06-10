package douglas.CarWash.repository

import douglas.CarWash.domain.Vehicle
import org.springframework.data.mongodb.repository.MongoRepository

interface VehicleRepository : MongoRepository<Vehicle, Long> {
}