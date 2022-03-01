package com.rest.service;

import com.rest.model.Patient;
import com.rest.repository.PatientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class PatientService {

    @Autowired
    private SessionFactory factory;

    public Patient loginByCredentials(String email,String password)
    {
       Session session=factory.getSessionFactory().openSession();
      Query query= session.createQuery("from Patient e where e.email=:x and e.password=:y", Patient.class);
      query.setParameter("x",email);
      query.setParameter("y",password);
      List<Patient> patient=query.getResultList();
        if(patient!=null)
            return (Patient) patient.get(0);
        return null;
    }


}

