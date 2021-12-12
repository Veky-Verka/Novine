package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Korisnik;
import service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost LoginController");
		
		LoginService service = new LoginService();
		String korisnickoIme = request.getParameter("ime_korisnika");
		String password = request.getParameter("password");
		String tipKorisnika = request.getParameter("tip_korisnika");
		
		//servis.ispisiParametre(korisnickoIme, password, tipKorisnika);
		System.out.println("Korisnicko ime: " + korisnickoIme);
		System.out.println("Password: " + password);
		System.out.println("Tip korisnika: " + tipKorisnika);
		
		Korisnik k = service.daLiPostojiKorisnik(korisnickoIme);
		if(k != null) {
			if(k.getPassword().equals(password)) {
				if(k.getTipKorisnika().equals(tipKorisnika)) {
					if(tipKorisnika.equals("urednik")) {
						response.sendRedirect("html_stranice/urednikGlavna.html");
					} else {
						response.sendRedirect("html_stranice/dopisnikGlavna.html");
					}
				} else {
					System.out.println("Pogresan tip korisnika!");
					//ispisati gresku
					response.sendRedirect("html_stranice/login.html");									
				}
			} else {
				System.out.println("Pogresan password!");
				//ispisati gresku
				response.sendRedirect("html_stranice/login.html");				
			}
		} else {
			System.out.println("Korisnicko ime " + korisnickoIme + " nije nadjeno u bazi");
			//ispisati gresku
			response.sendRedirect("html_stranice/login.html");
		}
	}

}
