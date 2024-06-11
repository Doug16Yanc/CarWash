package douglas.CarWash.controller

import douglas.CarWash.domain.Establishment
import douglas.CarWash.dto.EstablishmentDTO
import douglas.CarWash.service.EstablishmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random


@RestController
class EstablishmentController (var establishmentService: EstablishmentService) {

    @PostMapping("/establishments")
    fun toSaveEstablishment(@RequestBody establishmentDTO: EstablishmentDTO) : ResponseEntity<Establishment> {

        val establishment = establishmentService.createEstablishment(establishmentDTO)

        var id: String = ""
        do {
            id = Random.nextInt(5, 11).toString() + Random(10 - id.length)
        } while (establishmentService.existsById(id))
        return ResponseEntity.ok(establishment)
    }

    @GetMapping("/establishments/{id}")
    fun findEstablishmentById(@PathVariable("id") id : String) : ResponseEntity<Establishment> {

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
                                    establisment.vehiclesEntered?.let { it5 ->
                                        establisment.vehiclesExited?.let { it6 ->
                                            EstablishmentDTO(
                                                it, it2,
                                                it1, it3, it4,
                                                it5, it6
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }?.let { listEstablishmentDTO.add(it) }
            }
        }
        return ResponseEntity.ok(listEstablishmentDTO)

    }

    @PutMapping("/establishments/{id}")
    fun updateEstablishment(@PathVariable("id") id : String, @RequestBody establishmentDTO: EstablishmentDTO) : ResponseEntity<Establishment> {
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

    @DeleteMapping("/establishments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEstablishmentById(@PathVariable id : String) : ResponseEntity<String> {

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
