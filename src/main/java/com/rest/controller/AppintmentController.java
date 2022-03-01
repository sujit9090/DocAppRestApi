package com.rest.controller;

import com.rest.model.Appointment;
import com.rest.model.Patient;
import com.rest.repository.AppointmentRepository;
import com.rest.repository.PatientRepository;
import com.rest.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.List;

@Controller
public class AppintmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/patient/{id}/appointment")
    public String bookAppointment(Model model, @PathVariable int id)
    {
        model.addAttribute("id",id);
        model.addAttribute("patient",patientRepository.findById(id).get());
        model.addAttribute("appointment", new Appointment());
        return "bookappointment";
    }
    @RequestMapping(value="/patient/{id}/appointment/save",method= RequestMethod.POST)
    public String saveAppointment(Appointment appointment,@PathVariable int id,Model model)
    {
        Patient patient=patientRepository.findById(id).get();
        appointment.setPatientId(patient);
        appointmentRepository.save(appointment);
        model.addAttribute("patient",patient);
        return "patienthome";
    }
    @RequestMapping(value="/patient/{id}/myappointments",method= RequestMethod.GET)
    public String myAppointment(Appointment appointment, @PathVariable int id, Model model)
    {
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("appointments",appointmentService.getAppointments(patient));
        model.addAttribute("patient",patient);
        return "myappointments";
    }

    @RequestMapping(value="/accept/{id}",method= RequestMethod.GET)
    public String acceptAppointment(Appointment appointment, @PathVariable int id, Model model) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
    	appointmentService.acceptAppointment(id);
    	Patient patient=patientRepository.findById(id).get();
        model.addAttribute("appointments",appointmentService.getAppointments(patient));
        model.addAttribute("patient",patient);
        return "myappointments";
    }

    @RequestMapping(value="/reject/{id}",method= RequestMethod.GET)
    public String rejectAppointment(Appointment appointment, @PathVariable int id, Model model) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        appointmentService.rejectAppointment(id);
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("appointments",appointmentService.getAppointments(patient));
        model.addAttribute("patient",patient);
        return "myappointments";
       
    }

}
