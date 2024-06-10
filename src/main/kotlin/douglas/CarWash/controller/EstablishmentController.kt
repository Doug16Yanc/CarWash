package douglas.CarWash.controller

import douglas.CarWash.domain.Establishment
import douglas.CarWash.dto.EstablishmentDTO
import douglas.CarWash.service.EstablishmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.ArrayList

@RestController
class EstablishmentController (var establishmentService: EstablishmentService) {

    @PostMapping("/establishments")
    fun toSaveEstablishment(@RequestBody establishmentDTO: EstablishmentDTO) : ResponseEntity<Establishment> {

        val establishment = establishmentService.createEstablishment(establishmentDTO)

        return ResponseEntity.ok(establishment)
    }

    @GetMapping("/establishments/{id}")
    fun findEstablishmentById(@PathVariable("id") id : Long) : ResponseEntity<Establishment> {

        val establishmentFound = establishmentService.findById(id)

        if (establishmentFound.isPresent) {
            val establishment = establishmentFound.get()
            return ResponseEntity.ok(establishment)
        }
        else {
            return ResponseEntity.notFound().build()
        }
    }
    @GetMapping("/list_establishments")
    fun listEstablishments() : ResponseEntity<MutableList<EstablishmentDTO>> {
        val establisments = establishmentService.findAll()
        val listEstablishmentDTO : MutableList<EstablishmentDTO> = ArrayList()
        
        if (establisments.isNotEmpty()) {
            for (establisment : Establishment in establisments) {
                establisment.id?.let {
                    establisment.address?.let { it1 ->
                        establisment.name?.let { it2 ->
                            establisment.telephone?.let { it3 ->
                                establisment.vehicleNumber?.let { it4 ->
                                    EstablishmentDTO(
                                        it, it2,
                                        it1, it3, it4
                                    )
                                }
                            }
                        }
                    }
                }?.let { listEstablishmentDTO.add(it) }
            }
        }
        return ResponseEntity.ok(listEstablishmentDTO)

    }

    @PutMapping("/establisments/{id}")
    fun updateEstablishment(@PathVariable("id") id : Long, @RequestBody establishmentDTO: EstablishmentDTO) : ResponseEntity<Establishment> {
        val establishmentFound = establishmentService.findById(id)

        if (establishmentFound.isPresent) {
            val establishment = establishmentFound.get()
            establishment.name = establishmentDTO.name
            establishment.telephone = establishmentDTO.telephone
            establishment.vehicleNumber = establishmentDTO.vehicleNumber

            var address = establishment.address

            if (address != null) {
                address.street = establishment.address?.street
                address.number = establishment.address?.number
                address.neighborhood = establishment.address?.neighborhood
                address.city = establishment.address?.city
            }
            val updatedEstablishment = establishmentService.update(establishment)
            return ResponseEntity.ok(updatedEstablishment)
        }
        else {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/establisments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEstablishmentById(@PathVariable id : Long) : ResponseEntity<String> {

        val establishmentFound = establishmentService.findById(id)

        if (establishmentFound.isPresent) {
            val establishment = establishmentFound.get()
            establishmentService.delete(establishment)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Establishment " + establishment.id + " " + establishment.name + " deleted successfully!")
        }
        else {
            throw RuntimeException("Establishment not found.")
        }
    }
}
