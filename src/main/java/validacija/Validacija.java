package validacija;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;

import dao.HibernateUtil;
import model.Korisnik;

public class Validacija {
	
		public void ispisiParametre(String imeKorisnika, String ime, String prezime, String adresa,
				String eMail, String password, String ponoviPassword, String tipKorisnika) {
			
			System.out.println("Parametri su: ");
			System.out.println("Ime korisnika:  " + imeKorisnika);
			System.out.println("Adresa korisnika: " + adresa);
			System.out.println("Password:  " + password);
			System.out.println("Ponovljeni password: " + ponoviPassword);
			System.out.println("Tip korisnika: " + tipKorisnika);
		}

		public boolean daLiJePassDobar(String password) {
			
			int duzinaPassworda = password.length();
			if(duzinaPassworda < 8) {
				System.out.println("Password FAILED!");
				return false;
			}
			
			int brojacCifara = 0;
			int brojacVelikihSlova = 0;
			for(int i = 0; i < duzinaPassworda; i++) {
				char karakter = password.charAt(i);
				if(Character.isDigit(karakter)) {
					brojacCifara ++;
				}
				if(Character.isUpperCase(karakter)) {
					brojacVelikihSlova ++;
				}
				
			}
			
			if(brojacCifara >= 1 && brojacVelikihSlova >= 2) {
				System.out.println("Password OK...");
				return true;
			}else {
				System.out.println("Password FAILED!!!");
				return false;
			}
			
			
		}

		public Korisnik daLiPostoiKorisnik(String korisnickoIme) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			try {
				String hql = "from Korisnik where korisnickoIme = :ime";
				Query query = session.createQuery(hql);
				query.setParameter("ime", korisnickoIme);
				Korisnik k = (Korisnik) query.getSingleResult();
				return k;
			}catch (Exception e) {
				
				System.out.println("Exception u daLiPostoiKorisnik!");
				return null;
			}finally {
				session.close();
			}

		}
		
		
		
	

}
