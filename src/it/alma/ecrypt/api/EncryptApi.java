package it.alma.ecrypt.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.alma.ecrypt.util.Encrypter;

/**
 * Servlet implementation class EncryptApi
 */
@WebServlet("/encryptapi")
public class EncryptApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncryptApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/html");
		if (request.getParameter("setting") != null) {
			String setting = request.getParameter("setting");
			System.out.println("EncryptAPI ClearText::"+setting);
			String encryptedSetting = Encrypter.encrypt(setting);
			System.out.println("EncryptAPI EncryptedText::"+encryptedSetting);
			String encryptedSettingURL= URLEncoder.encode(encryptedSetting);
			
			System.out.println("EncryptAPI EncryptedText URL::"+encryptedSettingURL);
			printWriter.println(encryptedSettingURL);// URLEncoder.encode(encryptedSetting));
		}
		else {
			printWriter.println("setting null");
			System.out.println("EncryptAPI::"+"setting null");
		}
	}

}
