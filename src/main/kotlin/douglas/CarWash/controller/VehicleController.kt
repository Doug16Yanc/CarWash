package douglas.CarWash.controller

import douglas.CarWash.domain.Vehicle
import douglas.CarWash.dto.VehicleDTO
import douglas.CarWash.service.VehicleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class VehicleController (var vehicleService: VehicleService) {

    @PostMapping("/vehicles")
    fun toSaveVehicle(@RequestBody vehicleDTO : VehicleDTO) : ResponseEntity<Vehicle> {

        val vehicle = vehicleService.saveVehicle(vehicleDTO)

        return ResponseEntity.ok(vehicle)
    }

    @GetMapping("/vehicles/{id}")
    fun findVehicleById(@PathVariable("id") id : Long) : ResponseEntity<Vehicle> {

        val vehicleFound = vehicleService.findById(id)

        if (vehicleFound.isPresent) {
            val vehicle = vehicleFound.get()
            return ResponseEntity.ok(vehicle)
        }
        else {
            return ResponseEntity.notFound().build()
        }
    }
    @GetMapping("/list_vehicles")
    fun listVehicles() : ResponseEntity<MutableList<VehicleDTO>> {
        val vehicles = vehicleService.findAll()
        val listVehicleDTO : MutableList<VehicleDTO> = ArrayList()

        if (vehicles.isNotEmpty()) {
            for (vehicle : Vehicle in vehicles) {
                vehicle.id?.let { vehicle.brand?.let { it1 ->
                    vehicle.model?.let { it2 ->
                        vehicle.color?.let { it3 ->
                            vehicle.plate?.let { it4 ->
                                vehicle.type?.let { it5 ->
                                    VehicleDTO(it,
                                        it1, it2, it3, it4, it5
                                    )
                                }
                            }
                        }
                    }
                } }
                    ?.let { listVehicleDTO.add(it) }
            }
        }
        return ResponseEntity.ok(listVehicleDTO)

    }

    @PutMapping("/vehicles/{id}")
    fun updateVehicle(@PathVariable("id") id : Long, @RequestBody vehicleDTO: VehicleDTO) : ResponseEntity<Vehicle> {
        val vehicleFound = vehicleService.findById(id)

        if (vehicleFound.isPresent) {
            val vehicle = vehicleFound.get()
            vehicle.color = vehicleDTO.color
            vehicle.plate = vehicleDTO.plate

            val updatedVehicle = vehicleService.update(vehicle)
            return ResponseEntity.ok(updatedVehicle)
        }
        else {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/vehicles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteVehicleById(@PathVariable id : Long) : ResponseEntity<String> {

        val vehicleFound = vehicleService.findById(id)

        if (vehicleFound.isPresent) {
            val vehicle = vehicleFound.get()
            vehicleService.delete(vehicle)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Vehicle " + vehicle.id + " " + vehicle.brand + " " + vehicle.model + " deleted successfully!")
        }
        else {
            throw RuntimeException("Establishment not found.")
        }
    }
}