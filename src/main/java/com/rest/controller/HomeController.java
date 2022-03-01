package com.rest.controller;

import com.rest.model.Doctor;
import com.rest.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value="/")
    public String homePage()
    {
        return "home";
    }

    @RequestMapping(value="/patientRegister")
    public String patientRegisterPage(Model model)
    {
        model.addAttribute("patient",new Patient());
        return "patientregister";
    }

    @RequestMapping(value="/doctorRegister")
    public String doctorRegisterPage(Model model)
    {
        model.addAttribute("doctor",new Doctor());
        return "doctorregister";
    }

    @RequestMapping(value="/patientLogin")
    public String patientLogin(Model model)
    {
        model.addAttribute("patient",new Patient());
        return "patientlogin";
    }

    @RequestMapping(value="/doctorLogin")
    public String doctorLogin(Model model)
    {
        model.addAttribute("doctor",new Doctor());
        return "doctorlogin";
    }
}
