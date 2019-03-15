package be.pxl.s2it.Controllers;

import be.pxl.s2it.Domain.Appointment;
import be.pxl.s2it.HelperClass.Helper;
import be.pxl.s2it.Interfaces.AppointmentManager;
import be.pxl.s2it.Messaging.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/appointment/")
public class AppointmentController {

    @Autowired
    private AppointmentManager manager;

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(path = "all")
    @Transactional(readOnly = true)
    public ResponseEntity<Iterable<Appointment>> getAllAppointments(){
        Iterable<Appointment> appointments = manager.getAllAppointments();
        System.out.println(appointments.spliterator().getExactSizeIfKnown());

        if(appointments.spliterator().getExactSizeIfKnown() > 0){
            System.out.println(appointments);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id){
        Appointment appointment = manager.getAppointmentById(id);

        if(appointment != null){
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            System.out.println("Het gezochte object kan niet gevonden worden!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "add", consumes = "application/json")
    public ResponseEntity<Appointment> addNewAppointment(@RequestBody @Valid Appointment appointment){

        Appointment appointment1 = manager.addNewAppointment(appointment);

        return new ResponseEntity(appointment1, HttpStatus.OK);
    }

    @PostMapping(path = "add/message", consumes = "application/json")
    public ResponseEntity<Appointment> addNewAppointmentWithQueue(@RequestBody @Valid Appointment appointment){
        jmsTemplate.convertAndSend(Helper.JMS_QUEUE, appointment);
        //listener.onMessage(appointment);
        System.out.println("Er is naar de Message Queue geschreven!");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "change/{id}")
    public ResponseEntity<Appointment> modifyAppointment(@PathVariable long id,
                                            @RequestBody Appointment appointment){
        Appointment result =  manager.changeAppointmentById(id, appointment);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "remove/{id}")
    public ResponseEntity deleteAppointment(@PathVariable long id){
        return null;
    }
}
