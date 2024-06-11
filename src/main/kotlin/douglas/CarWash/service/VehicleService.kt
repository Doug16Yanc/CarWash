package douglas.CarWash.service

import douglas.CarWash.domain.Vehicle
import douglas.CarWash.dto.VehicleDTO
import douglas.CarWash.repository.VehicleRepository
import java.util.*

class VehicleService (private var vehicleRepository: VehicleRepository) {

    fun saveVehicle(vehicleDTO: VehicleDTO): Vehicle {
        return vehicleRepository.save(vehicleDTO.doVehicle())
    }

    fun findById(id : Long) : Optional<Vehicle> {
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
}