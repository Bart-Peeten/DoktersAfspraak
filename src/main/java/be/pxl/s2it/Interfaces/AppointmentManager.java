package be.pxl.s2it.Interfaces;

import be.pxl.s2it.Domain.Appointment;

public interface AppointmentManager {

    Iterable<Appointment> getAllAppointments();
    Appointment getAppointmentById(long id);
    Iterable<Appointment> getAppointmentsByNameAndLastName(String name, String lastName);
    Appointment addNewAppointment(Appointment appointment);
    Appointment changeAppointmentById(long id, Appointment appointment);
    void deleteAppointmentById(long id);
}
