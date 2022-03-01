package com.rest.controller;

import com.rest.model.Doctor;
import com.rest.model.Patient;
import com.rest.repository.DoctorRepository;
import com.rest.service.AppointmentService;
import com.rest.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String saveDetails(Doctor doctor)
    {
        doctorRepository.save(doctor);
        return "doctorlogin";
    }

    @RequestMapping(value="/doctorlogin",method= RequestMethod.POST)
    public String doctorLogin(Model model, Doctor doctor)
    {
        Doctor doct= doctorService.loginByCredentials(doctor.getEmail(),doctor.getPassword());
        model.addAttribute("doctor",doct);
        return "doctorhome";
    }

    @RequestMapping(value="/myprofile/{id}")
    public String doctorProfile(@PathVariable("id") int id, Model model)
    {
        Doctor doctor=  doctorRepository.findById(id).get();
        model.addAttribute("doctor",doctor);
        return "doctorprofile";
    }

    @RequestMapping(value="/myappointments/{id}")
    public String myappointment(@PathVariable("id") int id, Model model)
    {
        Doctor doctor=  doctorRepository.findById(id).get();
        model.addAttribute("doctor",doctor);
        model.addAttribute("list",appointmentService.myAppointments(doctor.getSpecialist()));
        return "doctorappointments";
    }

}
