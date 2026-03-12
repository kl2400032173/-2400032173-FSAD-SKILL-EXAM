package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo 
{
 public static void main(String[] args) 
 {
  Configuration cfg = new Configuration();
  cfg.configure("hibernate.cfg.xml");
  cfg.addAnnotatedClass(Ticket.class);

  SessionFactory sf = cfg.buildSessionFactory();
  Session session = sf.openSession();

  Transaction tx = session.beginTransaction();

  // Insert record
  Ticket t = new Ticket(101,"Train Ticket","12-03-2026","Booked","Hyderabad");

  session.save(t);

  System.out.println("Ticket Inserted Successfully");

  tx.commit();

  // HQL Update
  session.beginTransaction();

  String hql = "update Ticket set name=?1, status=?2 where id=?3";

  Query q = session.createQuery(hql);

  q.setParameter(1,"Bus Ticket");
  q.setParameter(2,"Confirmed");
  q.setParameter(3,101);

  int rows = q.executeUpdate();

  System.out.println(rows + " record updated");

  session.getTransaction().commit();

  session.close();
  sf.close();
 }
}