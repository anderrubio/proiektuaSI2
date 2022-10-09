package testGertaerakKopiatu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dataaccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businesslogic.BLFacade;
import businesslogic.BLFacadeImplementation;

@RunWith(MockitoJUnitRunner.class)
public class GertaerakKopiatuMockINTTest {
		// sut:system under test
	
		BLFacadeImplementation sut;
		
		@Mock
		BLFacade forumDAO;
		
		// datu guztiak ongi emanda, gertaera ongi sortzen da
		@Test
		public void testGertaerakKopiatu1() {
			try {
				Mockito.doReturn(true).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
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
				Mockito.doReturn(true).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
				List<Event> gertaerak = sut.getEventsAll();
				Date data = null;
				Event ev = gertaerak.get(21);
				sut.gertaerakKopiatu(ev, data);	
				fail("Ez du ongi egin");
			}catch(NullPointerException np) {
				assertTrue(true);
			}catch(Exception ex) {
				fail("Not correct");
			}
		}
		//event null emanda edo existitzen ezdena
		@Test
		public void testGertaerakKopiatu3() {
			try {
				Mockito.doReturn(true).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
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
				Mockito.doReturn(true).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
				List<Event> gertaerak = sut.getEventsAll();
				Date data = new Date(2003, 01, 17);
				Event ev = gertaerak.get(21);
				sut.gertaerakKopiatu(ev, data);
				fail("Ez du ondo egin");
			}catch(Exception ex) {
				assertTrue(true);
			}
		}
		//datu basean jada dagoen gertaera emanda ez da berririk sortzen
		@Test
		public void testGertaerakKopiatu5() {
			try {
				Mockito.doReturn(false).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
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
				Mockito.doReturn(true).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
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
				Mockito.doReturn(true).when(forumDAO).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
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
