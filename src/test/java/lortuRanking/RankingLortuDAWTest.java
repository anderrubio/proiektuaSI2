package lortuRanking;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataaccess.DataAccess;
import domain.Registered;
import test.dataAccess.TestDataAccess;

public class RankingLortuDAWTest {
	static DataAccess sut=new DataAccess();
	static TestDataAccess testDA=new TestDataAccess();
	
	
	@Test
	//Ez dago Registered motako User-ik DBan
	public void test1() {
		try {
			//prestatu datu basea
			testDA.open();
			//ezabatu datu basetik Regisred motako User-ak bat ere ez gera dadin
			ArrayList<Registered> erreg = (ArrayList<Registered>) sut.getRegistered();
			for (Registered r: erreg) {
				sut.removeUser(r);
			}
			
			//lortu emandako balioa
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();
			
			
			//sortu esperotako balioa
			List<Registered> esp = new ArrayList<Registered>();
			
			//berregin datubasea
			for (Registered r: erreg) {
				sut.addUser(r);
			}
			testDA.close();
			//balioztatu lortutakoa eta esperotakoa berdina den
			assertTrue(ema.equals(esp));
			
		}
		catch(Exception e) {
			fail();
		}
	}
	@Test
	//Registered motako user bakarra DBan
	public void test2() {
		try {
			//prestatu datu basea
			testDA.open();
			//ezabatu datu basetik Registered motako User-ak soilik bat geratu dadin
			ArrayList<Registered> erreg = (ArrayList<Registered>) sut.getRegistered();
			for (int i=0; i<erreg.size()-1; i++) {
				sut.removeUser(erreg.get(i));
			}
			
			//lortu emandako balioa
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();
			
			
			//sortu esperotako balioa
			List<Registered> esp = new ArrayList<Registered>();
			esp.add(erreg.get(erreg.size()-1));
			
			//berregin datubasea
			for (Registered r: erreg) {
				sut.addUser(r);
			}
			testDA.close();
			//balioztatu lortutakoa eta esperotakoa berdina den
			assertTrue(ema.equals(esp));
			
		}
		catch(Exception e) {
			fail();
		}
	}
	@Test
	//Registered motako bi User edo gehiago DBan, baina ez daude ordenatuta irabazien arabera (handitik txikira)
	public void test3() {
		try {
			//prestatu datu basea
			testDA.open();
			//lortu Registered motako User-ak
			ArrayList<Registered> erreg = (ArrayList<Registered>) sut.getRegistered();
			//back up aldagai batean gorde hasierako Registered-ak
			ArrayList<Registered> backup = erreg;
			//Registered-en datuak aldatu baldintzarekin betetzeko
			erreg.get(0).setIrabazitakoa(4.86);
			erreg.get(1).setIrabazitakoa(62.61);
			erreg.get(2).setIrabazitakoa(34.95);
			erreg.get(3).setIrabazitakoa(45.33);
			//sartu datu basean Registered-ak datu eguneratuekin
			for (Registered r: erreg) {
				sut.addUser(r);
			}
			
			//lortu emandako balioa
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();
			
			
			//sortu esperotako balioa
			List<Registered> esp = new ArrayList<Registered>();
			esp.add(erreg.get(1));
			esp.add(erreg.get(3));
			esp.add(erreg.get(2));
			esp.add(erreg.get(0));
			//berregin datubasea backup aldagaiaren Registered-ekin
			for (Registered r: backup) {
				sut.addUser(r);
			}
			testDA.close();
			assertTrue(ema.equals(esp));
			
		}
		catch(Exception e) {
			fail();
		}
	}
	@Test
	//Registered motako bi User edo gehiago DBan, baina ordenatuta daude irabazien arabera (handitik txikira)
	public void test4() {
		try {
			//prestatu datu basea
			testDA.open();
			//lortu Registered motako User-ak
			ArrayList<Registered> erreg = (ArrayList<Registered>) sut.getRegistered();
			//back up aldagai batean gorde hasierako Registered-ak
			ArrayList<Registered> backup = erreg;
			//Registered-en datuak aldatu baldintzarekin betetzeko
			erreg.get(0).setIrabazitakoa(67.42);
			erreg.get(1).setIrabazitakoa(42.61);
			erreg.get(2).setIrabazitakoa(34.95);
			erreg.get(3).setIrabazitakoa(15.33);
			//sartu datu basean Registered-ak datu eguneratuekin
			for (Registered r: erreg) {
				sut.addUser(r);
			}
			
			//lortu emandako balioa
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();
			
			
			//sortu esperotako balioa
			List<Registered> esp = erreg;
			//berregin datubasea backup aldagaiaren Registered-ekin
			for (Registered r: backup) {
				sut.addUser(r);
			}
			testDA.close();
			assertTrue(ema.equals(esp));
			
		}
		catch(Exception e) {
			fail();
		}
	}
}
