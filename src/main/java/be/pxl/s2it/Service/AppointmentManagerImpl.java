package be.pxl.s2it.Service;

import be.pxl.s2it.Domain.Appointment;
import be.pxl.s2it.Interfaces.AppointmentManager;
import be.pxl.s2it.Repositories.AppointmentRepo;
import be.pxl.s2it.Roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentManagerImpl implements AppointmentManager {

    @Autowired
    AppointmentRepo repo;

    @Override
    @Transactional(readOnly = true)
    @Secured(Roles.DOCTOR)
    public Iterable<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    @Override
    @Transactional()
    @Secured({Roles.DOCTOR, Roles.USER})
    public Appointment getAppointmentById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    @Secured({Roles.DOCTOR, Roles.USER})
    public Iterable<Appointment> getAppointmentsByNameAndLastName(String name, String lastName) {
        return repo.findAppointmentsByNameAndLastName(name, lastName);
    }

    @Override
    @Transactional
    @Secured({Roles.DOCTOR, Roles.USER})
    public Appointment addNewAppointment(Appointment appointment) {
        return repo.save(appointment);
    }

    @Override
    @Transactional()
    @Secured(Roles.DOCTOR)
    public Appointment changeAppointmentById(long id, Appointment appointment) {
        if(id == appointment.getId()) {
            return repo.save(appointment);
        } else {
            return null;
        }
    }

    @Override
    @Transactional()
    @Secured(Roles.DOCTOR)
    public void deleteAppointmentById(long id) {
        repo.deleteById(id);
    }
}
