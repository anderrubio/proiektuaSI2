package testGertaerakKopiatu;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import dataaccess.DataAccess;
import domain.Event;
import domain.Question;

public class GertaerakKopiatuDABTest {
	// sut:system under test
	static DataAccess sut = new DataAccess();
	
	// datu guztiak ongi emanda, gertaera ongi sortzen da
	@Test
	public void testGertaerakKopiatu1() {
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
	//data null emanda
	@Test
	public void testGertaerakKopiatu2() {
		try {
			List<Event> gertaerak = sut.getEventsAll();
			Date data = null;
			Event ev = gertaerak.get(21);
			sut.gertaerakKopiatu(ev, data);
		}catch(NullPointerException np) {
			assertTrue(true);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}
	//event null emanda edo existitzen ez dena
	@Test
	public void testGertaerakKopiatu3() {
		try {
			Date data = new Date();
			Event ev = null;
			sut.gertaerakKopiatu(ev, data);
			fail("Ez du ondo egin");
		}catch(NullPointerException np) {
			assertTrue(true);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}
	//gaurkoa baino lehenagoko data emanda, ez da gertaera sortzen
	@Test
	public void testGertaerakKopiatu4() {
		try {
			List<Event> gertaerak = sut.getEventsAll();
			Date data = new Date(2003, 01, 17);
			Event ev = gertaerak.get(21);
			sut.gertaerakKopiatu(ev, data);
		}catch(NullPointerException np) {
			assertTrue(true);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}
	//datu basean jada dagoen gertaera emanda ez da berririk sortzen
	@Test
	public void testGertaerakKopiatu5() {
		try {
			List<Event> gertaerak = sut.getEventsAll();
			boolean proba2 = false;
			Event ev = gertaerak.get(21);
			Date data = ev.getEventDate();
			proba2 = sut.gertaerakKopiatu(ev, data);
			assertTrue(proba2 == false);
		}catch(Exception ex) {
			fail("Not correct");
		}
	}
	//gertaera batek ez du galderarik
	@Test
	public void testGertaerakKopiatu6() {
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
	//gertaera batek ez du kuotarik
	@Test
	public void testGertaerakKopiatu7() {
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
	

}
