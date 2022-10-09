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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.*;

import businesslogic.BLFacade;
import businesslogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;

@RunWith(MockitoJUnitRunner.class)
public class GertaerakSortuMockINTTest {
	
	@Mock
	BLFacade BLFacadeDAO;

	@InjectMocks
	BLFacadeImplementation sut;
	
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
			
			Mockito.doReturn(true).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
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
			
			Mockito.doReturn(false).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(null, data, kirola);
			fail();
			assertEquals(esperotakoa, emaitza);
		} catch (NullPointerException e) {
			// if the program goes to this point fail
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
			
			Mockito.doReturn(false).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, null, kirola);
			fail();
			assertEquals(esperotakoa, emaitza);
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
			
			Mockito.doReturn(false).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, null);
			fail();
			assertEquals(esperotakoa, emaitza);
		} catch (PersistenceException e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			e.printStackTrace();
			fail();
		}
	}
	
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
			
			Mockito.doReturn(false).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

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
			
			Mockito.doReturn(false).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: The date is yesterday
	public void test7_1() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("08/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Mockito.doReturn(false).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());
			
			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);
			fail();

			assertEquals(esperotakoa, emaitza);
			
		} catch (EventFinished e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
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

			Mockito.doReturn(true).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());
			
			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

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
			
			Mockito.doReturn(true).when(BLFacadeDAO).gertaerakSortu(Mockito.anyString(),Mockito.any(Date.class), Mockito.anyString());

			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
}
