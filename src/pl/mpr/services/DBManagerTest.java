package pl.mpr.services;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.mpr.blazej.Rzecz;
import pl.mpr.blazej.Wlasciciel;


public class DBManagerTest {
	
	String IMIE = "Magda";
	String NAZWISKO = "Kowal";
	String ADRES = "Szkolna 4";
	String EMAIL ="magda.kowal@wp.pl";
	
	String RZECZ ="żelasko";
	int NUMER_SERYJNY = 45456345;
	boolean STAN = true;
	
	private static DBManager dbm;
	
	@BeforeClass
	public static void connectionTest(){
		
		dbm = new DBManager();
		
	}
	
	@Test
	public void usunWszytskoTest() {
		
		dbm.usunWszytsko();
		ArrayList<Wlasciciel> wlasciciele = dbm.wczytaj();
		assertEquals(0, wlasciciele.size());
		
	}
	
	@Test
	public void dodajWlascicielaTest() {
		
		dbm.usunWszytsko();
		Wlasciciel w = new Wlasciciel(0, IMIE, NAZWISKO, ADRES, EMAIL);
		dbm.dodajWlasciciela(w);
		
		ArrayList<Wlasciciel> wlasciciele = dbm.wczytaj();
		assertEquals(1, wlasciciele.size());
		
	}
	
	@Test
	public void usunWlascicielaTest() {
		
		dbm.usunWszytsko();
		Wlasciciel w = new Wlasciciel(0, IMIE, NAZWISKO, ADRES, EMAIL);
		dbm.dodajWlasciciela(w);
		dbm.usunWlasciciela(w);
		
		ArrayList<Wlasciciel> wlasciciele = dbm.wczytaj();
		assertEquals(0, wlasciciele.size());
		
	}
	
	@Test
	public void dodajRzeczTest() {
		
		dbm.usunWszytsko();
		Wlasciciel w = new Wlasciciel(0, IMIE, NAZWISKO, ADRES, EMAIL);
		dbm.dodajWlasciciela(w);
		Rzecz r = new Rzecz(0, RZECZ, NUMER_SERYJNY, STAN);
		dbm.dodajRzecz(w, r);
		
		ArrayList<Wlasciciel> wlasciciele = dbm.wczytaj();
		ArrayList<Rzecz> rzeczy = wlasciciele.get(0).getRzeczy();
		assertEquals(1, rzeczy.size());
		
	}
	
	@Test
	public void usunRzeczTest() {
		
		dbm.usunWszytsko();
		Wlasciciel w = new Wlasciciel(0, IMIE, NAZWISKO, ADRES, EMAIL);
		dbm.dodajWlasciciela(w);
		Rzecz r = new Rzecz(0, RZECZ, NUMER_SERYJNY, STAN);
		dbm.dodajRzecz(w, r);
		dbm.usunRzecz(r);
		
		ArrayList<Wlasciciel> wlasciciele = dbm.wczytaj();
		ArrayList<Rzecz> rzeczy = wlasciciele.get(0).getRzeczy();
		assertEquals(0, rzeczy.size());
		
	}
	
	@Test
	public void wczytajTest() {
		
		dbm.usunWszytsko();
		Wlasciciel w = new Wlasciciel(0, IMIE, NAZWISKO, ADRES, EMAIL);
		dbm.dodajWlasciciela(w);
		Rzecz r = new Rzecz(0, RZECZ, NUMER_SERYJNY, STAN);
		dbm.dodajRzecz(w, r);
		
		ArrayList<Wlasciciel> wlasciciele = dbm.wczytaj();
		assertEquals(IMIE, wlasciciele.get(0).getImie());
		assertEquals(NAZWISKO, wlasciciele.get(0).getNazwisko());
		assertEquals(ADRES, wlasciciele.get(0).getAdres());
		assertEquals(EMAIL, wlasciciele.get(0).getEmail());
		
		ArrayList<Rzecz> rzeczy = wlasciciele.get(0).getRzeczy();
		assertEquals(RZECZ, rzeczy.get(0).getRzecz());
		assertEquals(NUMER_SERYJNY, rzeczy.get(0).getNumer_seryjny());
		assertEquals(STAN, rzeczy.get(0).getStan());
		
	}	
}
