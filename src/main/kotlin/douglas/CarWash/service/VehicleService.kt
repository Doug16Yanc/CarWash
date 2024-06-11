package douglas.CarWash.service

import douglas.CarWash.domain.Vehicle
import douglas.CarWash.dto.VehicleDTO
import douglas.CarWash.repository.VehicleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleService (private var vehicleRepository: VehicleRepository) {

    fun saveVehicle(vehicleDTO: VehicleDTO): Vehicle? {
        return vehicleDTO.doVehicle()?.let { vehicleRepository.save(it) }
    }

    fun findById(id : String) : Optional<Vehicle> {
        return vehicleRepository.findById(id)
    }

    fun findAll() : List<Vehicle>  {
        return vehicleRepository.findAll()
    }

    fun delete(vehicle : Vehicle) {
        vehicleRepository.delete(vehicle)
    }

    fun update(vehicle: Vehicle) : Vehicle {
        val vehicleFound = vehicle.id ?.let { vehicleRepository.findById(it) }

        if (vehicleFound?.isPresent == true) {
            val existingVehicle = vehicleFound.get()
            existingVehicle.color = vehicle.color
            existingVehicle.plate = vehicle.plate
            existingVehicle.status = vehicle.status

            return vehicleRepository.save(existingVehicle)
        }
        else {
            throw RuntimeException("Vehicle not found.")
        }
    }
    fun existsById(id : String): Boolean {
        return vehicleRepository.existsById(id)
    }
}