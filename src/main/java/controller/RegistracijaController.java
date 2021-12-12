package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RegistracijaService;

/**
 * Servlet implementation class RegistracijaController
 */
@WebServlet("/RegistracijaController")
public class RegistracijaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistracijaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost RegistracijaController");
		
		RegistracijaService servis = new RegistracijaService();
		
		String korisnickoIme = request.getParameter("ime_korisnika");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String adresa = request.getParameter("adresa");
		String eMail = request.getParameter("email");
		String password = request.getParameter("password");
		String ponoviPassword = request.getParameter("ponovi_password");
		String tipKorisnika = request.getParameter("tip_korisnika");
		
		servis.ispisiParametreServis(korisnickoIme, ime, prezime, adresa, eMail, password, ponoviPassword, tipKorisnika);
			
		boolean daLiJePassOk = servis.daLiJePassDobar(password);
		if(daLiJePassOk) {
			if(password.equals(ponoviPassword)) {
				servis.upisiKorisnikaUBazu(korisnickoIme, ime, prezime, adresa, eMail, password, tipKorisnika);
				response.sendRedirect("html_stranice/uspesna_registracija.html");
			}else {
				response.sendRedirect("html_stranice/registracija.html");
				System.out.println("pogresan ponovljeni password");
			}
		}else {
			response.sendRedirect("html_stranice/registracija.html");
		}
		
		
		
	}

}
