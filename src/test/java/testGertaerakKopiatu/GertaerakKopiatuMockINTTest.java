package testGertaerakKopiatu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataaccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Team;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businesslogic.BLFacade;
import businesslogic.BLFacadeImplementation;
import configuration.UtilDate;

@RunWith(MockitoJUnitRunner.class)
public class GertaerakKopiatuMockINTTest {
		// sut:system under test
		DataAccess da =Mockito.mock(DataAccess.class);
		@InjectMocks
		BLFacadeImplementation sut;
		
		@Mock
		BLFacade dao;
		
		// datu guztiak ongi emanda, gertaera ongi sortzen da
		@Test
		public void testGertaerakKopiatu1() {
			try {
				List<Event> lag = new ArrayList<Event>();
				Team team1 =new Team("LA_Lakers");
				Team team2 = new Team("Phoenix_Suns");
				Event evLag=new Event(22, "LA Lakers-Phoenix Suns", UtilDate.newDate(2022,11,01), team1, team2);;
				Question q1= evLag.addQuestion("Who will win?", 3);
				q1.addQuote(6.2, "LA_Lakers wins", q1);
				lag.add(evLag);
				Mockito.when(da.gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class))).thenReturn(true);
				Mockito.when(da.getEventsAll()).thenReturn(lag);
				List<Event> gertaerak = sut.getEventsAll();
				boolean proba =false;
				Date data = new Date();
				Event ev = gertaerak.get(0);
				proba = sut.gertaerakKopiatu(ev, data);
				assertTrue(proba == true);
								
			}catch(Exception ex) {
				fail("Not correct");
			}
		}
		//data null emanda
		@Test
		public void testGertaerakKopiatu2() {
			try {
				List<Event> lag = new ArrayList<Event>();
				Team team1 =new Team("LA_Lakers");
				Team team2 = new Team("Phoenix_Suns");
				Event evLag=new Event(22, "LA Lakers-Phoenix Suns", UtilDate.newDate(2022,11,01), team1, team2);;
				lag.add(evLag);
				Mockito.when(da.getEventsAll()).thenReturn(lag);
				List<Event> gertaerak = sut.getEventsAll();
				Event ev = gertaerak.get(0);
				boolean a = sut.gertaerakKopiatu(ev, null);	
				assertTrue(!a);
			}catch(Exception ex) {
				fail("Not correct");
			}
		}
		//event null emanda edo existitzen ezdena
		@Test
		public void testGertaerakKopiatu3() {
			try {
				Date data = new Date();
				Event ev = null;
				boolean a= sut.gertaerakKopiatu(ev, data);
				assertTrue(!a);
			}catch(Exception ex) {
				fail("Not correct");
			}
		}
		//gaurkoa baino lehenagoko data emanda, ez da gertaera sortzen
		@Test
		public void testGertaerakKopiatu4() {
			try {
				List<Event> lag = new ArrayList<Event>();
				Team team1 =new Team("LA_Lakers");
				Team team2 = new Team("Phoenix_Suns");
				Event evLag=new Event(22, "LA Lakers-Phoenix Suns", UtilDate.newDate(2022,11,01), team1, team2);;
				lag.add(evLag);
				Mockito.doReturn(false).when(da).gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class));
				Mockito.when(da.getEventsAll()).thenReturn(lag);
				List<Event> gertaerak = sut.getEventsAll();
				Date data = new Date(2003, 01, 17);
				Event ev = gertaerak.get(0);
				boolean a= sut.gertaerakKopiatu(ev, data);
				assertTrue(!a);
			}catch(Exception ex) {
				fail("Ez du ondo egin");
			}
		}
		//datu basean jada dagoen gertaera emanda ez da berririk sortzen
		@Test
		public void testGertaerakKopiatu5() {
			try {
				List<Event> lag = new ArrayList<Event>();
				Team team1 =new Team("Real_Madrid");
				Team team2 = new Team("Barcelona");
				Event evLag=new Event(1, "Real Madrid-Barcelona", UtilDate.newDate(2023, 11, 2), team1, team2);
				lag.add(evLag);
				Mockito.when(da.gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class))).thenReturn(false);
				Mockito.when(da.getEventsAll()).thenReturn(lag);
				List<Event> gertaerak = sut.getEventsAll();
				boolean proba2 = false;
				Event ev = gertaerak.get(0);
				Date data = UtilDate.newDate(2022, 11, 17);
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
				List<Event> lag = new ArrayList<Event>();
				Team team1 =new Team("Djokovic");
				Team team2 = new Team("Federer");
				Event evLag=new Event(27, "Djokovic-Federer", UtilDate.newDate(2023,04,24), team1, team2);
				lag.add(evLag);
				Mockito.when(da.gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class))).thenReturn(true);
				Mockito.when(da.getEventsAll()).thenReturn(lag);
				List<Event> gertaerak = sut.getEventsAll();
				boolean proba =false;
				Date data = new Date();
				Event ev = gertaerak.get(0);
				proba = sut.gertaerakKopiatu(ev, data);
				assertTrue(proba == true);
								
			}catch(Exception ex) {
				fail("Not correct");
			}
		}
		//gertaera batek ez du kuotarik
		@Test
		public void testGertaerakKopiatu7() {
			try {
				List<Event> lag = new ArrayList<Event>();
				Team team1 =new Team("Atletico");
				Team team2 = new Team("Athletic");
				Event evLag=new Event(11, "Atletico-Athletic", UtilDate.newDate(2023,01,02), team1, team2);
				evLag.addQuestion("Who will win?", 3);
				lag.add(evLag);
				Mockito.when(da.gertaerakKopiatu(Mockito.any(Event.class), Mockito.any(Date.class))).thenReturn(true);
				Mockito.when(da.getEventsAll()).thenReturn(lag);
				List<Event> gertaerak = sut.getEventsAll();
				boolean proba =false;
				Date data = new Date();
				Event ev = gertaerak.get(0);
				proba = sut.gertaerakKopiatu(ev, data);
				assertTrue(proba == true);
								
			}catch(Exception ex) {
				fail("Not correct");
			}
		}
}