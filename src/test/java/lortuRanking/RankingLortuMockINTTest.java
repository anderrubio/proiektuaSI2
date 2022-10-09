package lortuRanking;



import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businesslogic.BLFacade;
import businesslogic.BLFacadeImplementation;
import dataaccess.DataAccess;
import domain.Registered;

@RunWith(MockitoJUnitRunner.class)
public class RankingLortuMockINTTest {
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	@Mock
	BLFacade dao;
	@InjectMocks
	BLFacadeImplementation sut;
	
	@Test
	//Ez dago Registered motako User-ik DBan
	public void test1() {
		try {
			ArrayList<Registered> lag = new ArrayList<Registered>();
			Mockito.doReturn(lag).when(dao).rankingLortu();
			ArrayList<Registered> esp = lag;
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();	
			assertTrue(esp.equals(ema));
		}
		catch(Exception e) {
			fail();
		}
	}
	
	@Test
	//Registered motako user bakarra DBan
	public void test2() {
		try {
			ArrayList<Registered> lag = new ArrayList<Registered>();
			Registered r = new Registered("abc", "123", 43);
			lag.add(r);
			Mockito.doReturn(lag).when(dao).rankingLortu();
			ArrayList<Registered> esp = lag;
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();	
			assertTrue(esp.equals(ema));
		}
		catch(Exception e) {
			fail();
		}
	}
	@Test
	//Registered motako bi User edo gehiago DBan, baina ez daude ordenatuta irabazien arabera (handitik txikira)
	public void test3() {
		try {
			ArrayList<Registered> lag = new ArrayList<Registered>();
			Registered r1 = new Registered("abc", "123", 43);
			Registered r2 = new Registered("abcd", "123", 45);
			r1.setIrabazitakoa(33.2);
			r2.setIrabazitakoa(43.1);
			lag.add(r1);
			lag.add(r2);
			Mockito.doReturn(lag).when(dao).rankingLortu();
			ArrayList<Registered> esp = lag;
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();	
			assertTrue(esp.equals(ema));
		}
		catch(Exception e) {
			fail();
		}
	}
	@Test
	//Registered motako bi User edo gehiago DBan, baina ordenatuta daude irabazien arabera (handitik txikira)
	public void test4() {
		try {
			ArrayList<Registered> lag = new ArrayList<Registered>();
			Registered r1 = new Registered("abc", "123", 43);
			Registered r2 = new Registered("abcd", "123", 45);
			r1.setIrabazitakoa(33.2);
			r2.setIrabazitakoa(43.1);
			lag.add(r2);
			lag.add(r1);
			Mockito.doReturn(new ArrayList<Registered>()).when(dao).rankingLortu();
			ArrayList<Registered> esp = lag;
			ArrayList<Registered> ema = (ArrayList<Registered>) sut.rankingLortu();	
			assertTrue(esp.equals(ema));
		}
		catch(Exception e) {
			fail();
		}
	}
}
