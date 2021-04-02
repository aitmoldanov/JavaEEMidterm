package AdminController;

import DAO.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminUsername = request.getParameter("admin-username");
		String adminPassword = request.getParameter("admin-password");
		
		HttpSession adminSession = request.getSession(false);
		RequestDispatcher forwardUser = null;

		if (adminUsername != null && adminPassword != null ) {
			try {
				if (DAO.getAdminAuthenticate(adminUsername, adminPassword)) {
						adminSession = request.getSession(true);
						adminSession.setAttribute("admin_sessionattr", adminUsername);
						request.getSession().setAttribute("admin_name", adminUsername);
						adminSession.setAttribute("admin_status", "true");
						adminSession.setAttribute("admin", "admin");
						adminSession.setAttribute("message", "Successful Login!");
						response.sendRedirect("GetProductList");
					} else {
						adminSession.setAttribute("admin_status", "false");
						forwardUser = getServletContext().getRequestDispatcher("/AdminLogin.jsp");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('Please enter correct/Pass as \"User : Password\".')</script>");
						forwardUser.include(request, response);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("AdminLogin.jsp");
		}
	}
}
