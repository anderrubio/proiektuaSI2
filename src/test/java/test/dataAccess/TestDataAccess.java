package test.dataAccess;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.User;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
	public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
	}
	public List<Registered> rankingLortu(){
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		List<Registered> ema= new ArrayList<Registered>();
		int i;
		for(Registered r: listR) {
			if(ema.isEmpty()) {
				ema.add(0, r);
			}else {
				i=0;
				while(i<ema.size() && r.getIrabazitakoa()<ema.get(i).getIrabazitakoa()) {
					i++;
				}
				ema.add(i, r);
			}
		}
		return ema;
	}
	public List<Registered> getRegistered(){
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		return listR;
	}
	public void removeUser(User u) {
		db.getTransaction().begin();
		db.remove(u);
		db.getTransaction().commit();
	}
	public void addUser(User u) {
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
	}
}

