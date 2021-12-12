package service;

import model.Korisnik;
import validacija.Validacija;

public class LoginService {

	Validacija validacija = new Validacija();
	
	public Korisnik daLiPostojiKorisnik(String korisnickoIme) {

		return validacija.daLiPostoiKorisnik(korisnickoIme);
	}
	
	

}
