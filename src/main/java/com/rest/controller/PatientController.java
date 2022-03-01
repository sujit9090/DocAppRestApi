package com.rest.controller;

import com.rest.model.Patient;
import com.rest.repository.PatientRepository;
import com.rest.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;


    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String saveDetails(Model model, Patient patient)
    {
        patientRepository.save(patient);
        model.addAttribute("patient",patient);
        return "patientlogin";
    }

    @RequestMapping(value="/patientlogin",method= RequestMethod.POST)
    public String patientLogin(Model model,Patient patient)
    {
       Patient pt= patientService.loginByCredentials(patient.getEmail(),patient.getPassword());
        model.addAttribute("patient",pt);
        return "patienthome";
    }

    @RequestMapping(value="/myprofile/{id}")
    public String patientProfile(@PathVariable("id") int id,Model model)
    {
    Patient patient=  patientRepository.findById(id).get();
    System.out.println(patient.getEmail());
    model.addAttribute("patient",patient);
    return "patientprofile";
    }

}
