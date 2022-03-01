package com.rest.service;

import com.rest.model.Doctor;
import com.rest.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private SessionFactory factory;

    public Doctor loginByCredentials(String email, String password)
    {
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("from Doctor e where e.email=:x and e.password=:y", Doctor.class);
        query.setParameter("x",email);
        query.setParameter("y",password);
        List<Doctor> doctor=query.getResultList();
        if(doctor!=null)
            return (Doctor) doctor.get(0);
        return null;
    }
}
