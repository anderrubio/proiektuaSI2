package testgertaerakSortu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import dataaccess.DataAccess;
//import test.dataaccess.TestDataAccess;
import domain.Event;

public class GertaerakSortuDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	@Test
	// sut.gertaerakSortu: The sport is not in the BD.
	public void test1() {
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
	// sut.gertaerakSortu: No events in that date
	public void test2() {
		try {

			String kirola = "Futbol";
			String deskr = "Eibar-Barcelona";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("03/11/2022");
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
	// sut.gertaerakSortu: There are events but there's not the one we want to introduce.
	public void test3() {
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

	@Test
	// sut.gertaerakSortu: Already exists
	public void test4() {
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

}
