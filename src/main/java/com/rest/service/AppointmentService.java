package com.rest.service;

import com.rest.model.Appointment;
import com.rest.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private SessionFactory factory;

    public List<Appointment> getAppointments(Patient patient){
        Session session=factory.getSessionFactory().openSession();
       Query query= session.createQuery("from Appointment a where a.patientId=:x");
       query.setParameter("x",patient);
      List<Appointment> appointment= query.getResultList();
        return appointment;
    }

    public List<Appointment> myAppointments(String spec){
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("from Appointment a where a.specialist=:x");
        query.setParameter("x",spec);
        List<Appointment> appointment= query.getResultList();
        return appointment;
    }

    public void acceptAppointment(int id) {
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("update Appointment a set a.status=:y where a.aid=:z");
     Transaction trx=session.beginTransaction();
        query.setParameter("y","Accepted");
        query.setParameter("z",id);
        query.executeUpdate();
        trx.commit();
    }

    public void rejectAppointment(int id) {
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("update Appointment a set a.status=:y where a.aid=:z");
        Transaction trx=session.beginTransaction();
        query.setParameter("y","Rejected");
        query.setParameter("z",id);
        query.executeUpdate();
        trx.commit();
    }

}
