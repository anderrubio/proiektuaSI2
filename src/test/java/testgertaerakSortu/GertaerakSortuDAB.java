package testgertaerakSortu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.persistence.PersistenceException;

import org.junit.Test;

import dataaccess.DataAccess;
//import test.dataaccess.TestDataAccess;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class GertaerakSortuDAB {

	// sut:system under test
	static DataAccess sut = new DataAccess();
	
	@Test
	// sut.gertaerakSortu: The event does not exist and it is introduced.
	public void test1() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			Vector<Event> gertaerak = sut.getEvents(data);
			boolean aurkitua = false;
			Event ev = null;
			int i = 0;
			while (!aurkitua && i < gertaerak.size()) {
				if (gertaerak.get(i).getDescription() == deskr) {
					ev = gertaerak.get(i);
					aurkitua = true;
				}
				i++;
			}
			sut.gertaeraEzabatu(ev);

			assertEquals(esperotakoa, emaitza);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	/*
	@Test
	// sut.gertaerakSortu: The description is null.
	public void test2() {
		try {

			String kirola = "Tennis";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(null, data, kirola);
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			e.printStackTrace();
			fail();
		}
	}

	@Test
	// sut.gertaerakSortu: The date is null.
	public void test3() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, null, kirola);

			fail();
		} catch (PersistenceException e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: The sport is null.
	public void test4() {
		try {

			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, null);
			fail();
		} catch (PersistenceException e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			e.printStackTrace();
			fail();
		}
	}*/

	@Test
	// sut.gertaerakSortu: The sport is not in the BD.
	public void test5() {
		try {

			String kirola = "Xakea";
			String deskr = "Donostia-Eibar";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("03/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}

	@Test
	// sut.gertaerakSortu: Already exists
	public void test6() {
		try {

			String kirola = "Baloncesto";
			String deskr = "LA Lakers-Phoenix Suns";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	//MUGA BALIOAK:

	//7.1. KASUA, NON SARTUTAKO DATA GAUR BAINO LEHENAGO DEN, MOCKITO-AN KONPROBATUKO DUGU, EVENTFINISHED EXCEPTION-A FACADE-TIK JAURTITZEN BAITA.

	@Test
	// sut.gertaerakSortu: The date is today
	public void test7_2() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("09/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			Vector<Event> gertaerak = sut.getEvents(data);
			boolean aurkitua = false;
			Event ev = null;
			int i = 0;
			while (!aurkitua && i < gertaerak.size()) {
				if (gertaerak.get(i).getDescription() == deskr) {
					ev = gertaerak.get(i);
					aurkitua = true;
				}
				i++;
			}
			sut.gertaeraEzabatu(ev);

			assertEquals(esperotakoa, emaitza);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}

	@Test
	// sut.gertaerakSortu: The date is tomorrow
	public void test7_3() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("10/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			Vector<Event> gertaerak = sut.getEvents(data);
			boolean aurkitua = false;
			Event ev = null;
			int i = 0;
			while (!aurkitua && i < gertaerak.size()) {
				if (gertaerak.get(i).getDescription() == deskr) {
					ev = gertaerak.get(i);
					aurkitua = true;
				}
				i++;
			}
			sut.gertaeraEzabatu(ev);

			assertEquals(esperotakoa, emaitza);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}

}
