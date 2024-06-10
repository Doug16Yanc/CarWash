package douglas.CarWash.repository

import douglas.CarWash.domain.Establishment
import org.springframework.data.mongodb.repository.MongoRepository

interface EstablishmentRepository : MongoRepository<Establishment, Long> {
}