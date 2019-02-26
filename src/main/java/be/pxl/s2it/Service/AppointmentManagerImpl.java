package be.pxl.s2it.Service;

import be.pxl.s2it.Domain.Appointment;
import be.pxl.s2it.Enums.Roles;
import be.pxl.s2it.Interfaces.AppointmentManager;
import be.pxl.s2it.Repositories.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentManagerImpl implements AppointmentManager {

    private static final String USER = "ROLE_USER";
    private static final String DOCTOR = "ROLE_DOCTOR";

    @Autowired
    AppointmentRepo repo;

    @Override
    @Transactional(readOnly = true)
    @Secured(DOCTOR)
    public Iterable<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    @Override
    @Transactional()
    public Appointment getAppointment(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public Iterable<Appointment> getAppointmentsByNameAndLastName(String name, String lastName) {
        return repo.findAppointmentsByNameAndLastName(name, lastName);
    }

    @Override
    @Transactional
    public Appointment addNewAppointment(Appointment appointment) {
        return repo.save(appointment);
    }

    @Override
    @Transactional()
    @Secured(DOCTOR)
    public Appointment changeAppointmentById(long id, Appointment appointment) {
        if(id == appointment.getId()) {
            return repo.save(appointment);
        } else {
            return null;
        }
    }

    @Override
    @Transactional()
    @Secured(DOCTOR)
    public void deleteAppointmentById(long id) {
        repo.deleteById(id);
    }
}
