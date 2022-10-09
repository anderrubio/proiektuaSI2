package testGertaerakKopiatu;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import dataaccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;



public class GertaerakKopiatuDAWTest {
	// sut:system under test
	static DataAccess sut = new DataAccess();
	
	//Kopiatu nahi dugun datan dagoeneko kopiatu nahi dugun gertaera existitzea
	@Test
	public void testGertaerakKopiatu1() {		
		try {
			List<Event> gertaerak = sut.getEventsAll();
			Date data = null;
			boolean proba2 = false;
			Event ev = gertaerak.get(21);
			try {
				data = ev.getEventDate();
			}catch(Exception pe) {
				pe.printStackTrace();
			}
			proba2 = sut.gertaerakKopiatu(ev, data);
			assertTrue(proba2 == false);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}
	//Kopiatu nahi dugun gertaerak galderarik ez izatea
	@Test
	public void testGertaerakKopiatu2() {
		try {
			List<Event> gertaerak = sut.getEventsAll();
			Event ev = gertaerak.get(8);
			Date data = new Date();
			boolean proba2 = sut.gertaerakKopiatu(ev, data);
			assertTrue(proba2 == true);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}
	//Kopiatu nahi dugun gertaeraren galderek kuotarik ez izatea
	@Test
	public void testGertaerakKopiatu3() {
		//gertaera = new Event(42, "Atletico-Athletic", UtilDate.newDate(2023,01,02), team1, team2);
		try {
			List<Event> gertaerak = sut.getEventsAll();
			Event ev = gertaerak.get(10);
			Question q = ev.getQuestions().get(1);
			Date data = new Date();
			boolean proba2 = sut.gertaerakKopiatu(ev, data);
			assertTrue(proba2 == true);
		}catch(Exception e) {
			fail("Not correct");
		}
	}
	//Kopiatu nahi dugun gertaerak galderak izatea, eta hauek kuotak izatea
	@Test
	public void testGertaerakKopiatu4() {
		try {
			List<Event> gertaerak = sut.getEventsAll();
			Date data = new Date();
			boolean proba2 = false;
			Event ev = gertaerak.get(21);
			
			proba2 = sut.gertaerakKopiatu(ev, data);
			assertTrue(proba2 == true);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}

}
