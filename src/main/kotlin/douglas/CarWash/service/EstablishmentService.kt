package douglas.CarWash.service

import douglas.CarWash.domain.Establishment
import douglas.CarWash.repository.EstablishmentRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.swing.plaf.synth.Region

@Service
class EstablishmentService (private var establishmentRepository: EstablishmentRepository) {

    fun createEstablishment(establishment: Establishment): Establishment {
        return establishmentRepository.save(establishment)
    }

    fun findById(id : Long) : Optional<Establishment> {
        return establishmentRepository.findById(id)
    }

    fun findAll() : List<Establishment>  {
        return establishmentRepository.findAll()
    }

    fun delete(id : Long) {
        establishmentRepository.deleteById(id)
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
}