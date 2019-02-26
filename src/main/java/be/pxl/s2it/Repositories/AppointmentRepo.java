package be.pxl.s2it.Repositories;

import be.pxl.s2it.Domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, Long> {
    Iterable<Appointment> findAppointmentsByNameAndLastName(String name, String lastName);
}
