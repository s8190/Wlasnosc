package pl.mpr.services;


import java.util.ArrayList;
import java.util.Scanner;

import pl.mpr.blazej.Rzecz;
import pl.mpr.blazej.Wlasciciel;

public class WlasnosciManager {

	private Scanner in = null;
	private DBManager db = null;
	private ArrayList<Wlasciciel> wlasciciele = null;

	public WlasnosciManager() {

		in = new Scanner(System.in);

		db = new DBManager();

		wlasciciele = db.wczytaj();

	}

	public void dodajWlasciciela() {

		System.out.print("Podaj imie: ");
		String imie = in.next();
		System.out.print("Podaj nazwisko: ");
		String nazwisko = in.next();
		System.out.print("Podaj adres: ");
		String adres = in.next();
		System.out.print("Podaj adres e-mail: ");
		String email = in.next();

		int id = 0;
		for (Wlasciciel w : wlasciciele) {
			id = w.getId();
			if (imie.equals(w.getImie()) && nazwisko.equals(w.getNazwisko())
					&& adres.equals(w.getAdres()) && email.equals(w.getEmail())) {
				System.err.println("Podany klient juĹĽ istnieje");
				return;
			}
		}

		Wlasciciel nowy_w = new Wlasciciel(id+1, imie, nazwisko, adres, email);
		wlasciciele.add(nowy_w);
		db.dodajWlasciciela(nowy_w);

	}
	public Wlasciciel SzukajWlasciciela(){
		System.out.println("Wpisz Nazwisko: ");
		in = new Scanner(System.in);		
		String tekst = in.toString();
		for(Wlasciciel w: wlasciciele){
		if(w.getNazwisko()==tekst) return w;
		}
		return null;
		}
	
	public void edytujWlasciciela(){

		Wlasciciel w = SzukajWlasciciela();
		
		System.out.println("Wprowadz poprawna wartosc: ");
		in = new Scanner(System.in);		
		String tekst = in.toString();
		w.setNazwisko(tekst);
	}

	public void usunWlasciciela() {
		
		if (wlasciciele.isEmpty()) {
			System.out.println("Brak wĹ‚aĹ›cicieli!");
			return;
		}
		
		for (Wlasciciel w : wlasciciele) {
			System.out.println("ID:  " + w.getId() + " Imie: " + w.getImie() + " Nazwisko: "
					+ w.getNazwisko() + " Adres: " + w.getAdres()
					+ " Adres e-mail: " + w.getEmail());
		}
		System.out.print("Podaj ID wĹ‚aĹ›ciciela do usuniÄ™cia: ");
		int wybrane = in.nextInt();
		
		for (Wlasciciel w : wlasciciele) {
			if (w.getId() == wybrane) {
				wlasciciele.remove(w);
				db.usunWlasciciela(w);
				break;
			}
		}

	}

	public void pokarzWlascicieli() {

		if (wlasciciele.isEmpty()) {
			System.out.println("Brak wĹ‚aĹ›cicieli!");
			return;
		}
			
		for (Wlasciciel w : wlasciciele) {
			System.out.println("ID:  " + w.getId() + " Imie: " + w.getImie() + " Nazwisko: "
					+ w.getNazwisko() + " Adres: " + w.getAdres()
					+ " Adres e-mail: " + w.getEmail());
			ArrayList<Rzecz> rzeczy = w.getRzeczy();

			for (Rzecz r : rzeczy) {
				String stan = "nowy";
				if (r.getStan() == false) {
					stan = "uĹĽywany";
				}
				
				System.out.println("   ID: " + r.getId() + " Rzecz: " + r.getRzecz() + " Numer seryjny: " + r.getNumer_seryjny() + " Stan: " + stan);
			}

		}

	}

	public void dodajRzecz() {
		
		if (wlasciciele.isEmpty()) {
			System.out.println("Brak wĹ‚aĹ›cicieli!");
			return;
		}
		
		for (Wlasciciel w : wlasciciele) {
			System.out.println("ID:  " + w.getId() + " Imie: " + w.getImie() + " Nazwisko: "
					+ w.getNazwisko() + " Adres: " + w.getAdres()
					+ " Adres e-mail: " + w.getEmail());
		}
		System.out.print("Podaj ID wĹ‚aĹ›ciciela: ");
		int wybrane = in.nextInt();
		
		int id = 0;
		for (Wlasciciel w : wlasciciele) {
			ArrayList<Rzecz> rzeczy = w.getRzeczy();
			for (Rzecz r : rzeczy) {
				id = r.getId();
			}
		}
		
		for (Wlasciciel w : wlasciciele) {
			if (w.getId() == wybrane) {
				
				System.out.print("Podaj rzecz: ");
				String rzecz = in.next();
				System.out.print("Podaj numer seryjny: ");
				int numer_seryjny = in.nextInt();
				System.out.print("Podaj stan [nowy lub uĹĽywany]: ");
				String stan = in.next();
				boolean s = false;
				if(stan.equals("nowy")) {
					s = true;
				}
				
				Rzecz r = new Rzecz(id+1, rzecz, numer_seryjny, s);
				w.addRzecz(r);
				db.dodajRzecz(w, r);
				
			}
		}

	}

	public void usunRzecz() {
		
		if (wlasciciele.isEmpty()) {
			System.out.println("Brak wĹ‚aĹ›cicieli!");
			return;
		}
		
		for (Wlasciciel w : wlasciciele) {
			ArrayList<Rzecz> rzeczy = w.getRzeczy();

			for (Rzecz r : rzeczy) {
				String stan = "nowy";
				if (r.getStan() == false) {
					stan = "uĹĽywany";
				}

				System.out.println("ID: " + r.getId() + " Rzecz: " + r.getRzecz() + " Numer seryjny: " + r.getNumer_seryjny() + " Stan: " + stan);
			}
		}
		
		System.out.print("Podaj ID rzeczy do usuniÄ™cia: ");
		int wybrane = in.nextInt();
		
		for (Wlasciciel w : wlasciciele) {
			ArrayList<Rzecz> rzeczy = w.getRzeczy();

			for (Rzecz r : rzeczy) {
				if (r.getId() == wybrane) {
					w.delRzecz(r);
					db.usunRzecz(r);
					break;
				}
				
			}
		}

	}

	public void usunWszytsko() {
		
		wlasciciele.clear();
		db.usunWszytsko();
		
	}

}
