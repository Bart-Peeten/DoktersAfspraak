package be.pxl.s2it.Messaging;

import be.pxl.s2it.Domain.Appointment;
import be.pxl.s2it.HelperClass.Helper;
import be.pxl.s2it.Interfaces.AppointmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    @Autowired
    private AppointmentManager manager;

    /* Pick the message(object) from the queue and add it to the DB. */
    @JmsListener(destination = Helper.JMS_QUEUE, containerFactory = "myFactory")
    public void onMessage(Appointment appointment){
        System.out.println("We zijn in de listener class!");
        System.out.println(appointment.getName());

        manager.addNewAppointment(appointment);
    }
}
