package douglas.CarWash.service

import douglas.CarWash.domain.Vehicle
import douglas.CarWash.repository.VehicleRepository
import java.util.*

class VehicleService (private var vehicleRepository: VehicleRepository) {

    fun saveVehicle(vehicle: Vehicle): Vehicle {
        return vehicleRepository.save(vehicle)
    }

    fun findById(id : Long) : Optional<Vehicle> {
        return vehicleRepository.findById(id)
    }

    fun findAll() : List<Vehicle>  {
        return vehicleRepository.findAll()
    }

    fun delete(id : Long) {
        vehicleRepository.deleteById(id)
    }

    fun update(vehicle: Vehicle) : Vehicle {
        val vehicleFound = vehicle.id ?.let { vehicleRepository.findById(it) }

        if (vehicleFound?.isPresent == true) {
            val existingVehicle = vehicleFound.get()
            existingVehicle.brand = vehicle.brand
            existingVehicle.model = vehicle.model
            existingVehicle.color = vehicle.color
            existingVehicle.plate = vehicle.plate

            return vehicleRepository.save(existingVehicle)
        }
        else {
            throw RuntimeException("Vehicle not found.")
        }
    }
}