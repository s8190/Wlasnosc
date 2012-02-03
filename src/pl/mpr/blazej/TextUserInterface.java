package pl.mpr.blazej;

import java.util.InputMismatchException;
import java.util.Scanner;

import pl.mpr.services.WlasnosciManager;

public class TextUserInterface {
	
	private Scanner in = null;
	private WlasnosciManager wm = new WlasnosciManager();

	public void start() {
		printWelcomeMsg();
		while (true) {
			printMenu();
			switch (readchoice()) {
			case ADDOWNER: wm.dodajWlasciciela(); break;
			case DELOWNER: wm.usunWlasciciela(); break;			
			case SHOWOWNER: wm.pokarzWlascicieli(); break;
			case ADDTHING: wm.dodajRzecz(); break;
			case DELTHING: wm.usunRzecz(); break;
			case DELALL: wm.usunWszytsko(); break;
			case SEARCHOWNER: wm.SzukajWlasciciela().; break;
			case EDITOWNER: wm.edytujWlasciciela(); break;
			case END: printGoodbyeMsg(); return;
			case NONE: continue;
			}
		}
	}
	
	private Action readchoice() {
		in = new Scanner(System.in);
		int choice = -1;
		try {
			choice = in.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Wprowadzona wartoĹ›Ä‡ jest bĹ‚Ä™dna, sprĂłbuj ponownie.");
			return Action.NONE;
		}
		switch (choice) {
		case 1: return Action.ADDOWNER;
		case 2: return Action.DELOWNER;
		case 3: return Action.SHOWOWNER;
		case 4: return Action.ADDTHING;
		case 5: return Action.DELTHING;
		case 6: return Action.DELALL;
		case 7: return Action.SEARCHOWNER;
		case 8: return Action.EDITOWNER;
		case 0: return Action.END;
		default: return Action.NONE;
		}
	}
	
	private void printWelcomeMsg() {
		System.out.println("Witamy w menedĹĽerze wĹ‚asnoĹ›ci\n");
	}

	private void printMenu() {
		System.out.print("Wybierz :\n" + "1. Dodaj wĹ‚aĹ›ciciela\n"
				+ "2. UsuĹ„ wĹ‚aĹ›ciciela\n" + "3. WyĹ›wietl wĹ‚aĹ›cicielĂłw\n"
				+ "4. Dodaj rzecz\n" + "5. UsuĹ„ rzecz\n"
				+ "6. Usun wszytsko\n" + "7. Szukaj Wlasciciela" + "8. Edytuj Wlasciciela" + "0. WyĹ›cie\n" + "> ");
	}
	
	private void printGoodbyeMsg() {
		System.out.println("MiĹ‚ego dnia\n");
	}
	
}
