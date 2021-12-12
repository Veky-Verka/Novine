package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;
import model.Korisnik;
import validacija.Validacija;

public class RegistracijaService {
	
	Validacija validacija = new Validacija();
	
	public void ispisiParametreServis(String imeKorisnika, String ime, String prezime, String adresa,
			String eMail, String password, String ponoviPassword, String tipKorisnika) {
		
		validacija.ispisiParametre(imeKorisnika, ime, prezime, adresa, 
				eMail, password, ponoviPassword, tipKorisnika);
	}

	public boolean daLiJePassDobar(String password) {
		return validacija.daLiJePassDobar(password);
	}

	public boolean upisiKorisnikaUBazu(String korisnickoIme, String ime, String prezime, String adresa, String eMail,
			String password, String tipKorisnika) {
		
		Korisnik k = new Korisnik();
		k.setKorisnickoIme(korisnickoIme);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setAdresa(adresa);
		k.seteMail(eMail);
		k.setPassword(password);
		k.setTipKorisnika(tipKorisnika);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(k);
			session.getTransaction().commit();
			System.out.println("Korisnik koji ima korisnickoIme " + korisnickoIme + "je upisan u bazu.");
			return true;
			
		}catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Korisnik NIJE upisan u bazu!");
			return false;
		}finally {
			session.close();
		}
		
			
		
	}
	
	
	
	

}
