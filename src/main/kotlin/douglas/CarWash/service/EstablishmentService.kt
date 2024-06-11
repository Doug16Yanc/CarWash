package douglas.CarWash.service

import douglas.CarWash.domain.Establishment
import douglas.CarWash.dto.EstablishmentDTO
import douglas.CarWash.repository.EstablishmentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EstablishmentService (private var establishmentRepository: EstablishmentRepository) {

    fun createEstablishment(establishmentDTO: EstablishmentDTO): Establishment? {
        return establishmentDTO.doEstablishment()?.let { establishmentRepository.save(it) }
    }

    fun findById(id : String) : Optional<Establishment> {
        return establishmentRepository.findById(id)
    }

    fun findAll() : List<Establishment>  {
        return establishmentRepository.findAll()
    }

    fun delete(establishment : Establishment) {
        establishmentRepository.delete(establishment)
    }

    fun update(establishment: Establishment): Establishment {
        val establishmentFound = establishment.id?.let { establishmentRepository.findById(it) }

        if (establishmentFound?.isPresent == true) {
            val existingEstablishment = establishmentFound.get()
            existingEstablishment.name = establishment.name
            existingEstablishment.telephone = establishment.telephone
            existingEstablishment.vehicleNumber = establishment.vehicleNumber

            var address = existingEstablishment.address

            if (address != null) {
                address.street = establishment.address?.street
                address.number = establishment.address?.number
                address.neighborhood = establishment.address?.neighborhood
                address.city = establishment.address?.city
            }

            return establishmentRepository.save(existingEstablishment)
        }
        else {
            throw RuntimeException("Establishment not found.")
        }
    }

    fun existsById(id : String): Boolean {
        return establishmentRepository.existsById(id)
    }
}