package AdminController;

import DAO.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieId = request.getParameter("movieId");
		if(movieId != null && !(movieId.equals(""))){
			boolean status = false;
			try {
				status = new DAO().delete(Integer.parseInt(movieId));
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			if(status){
				request.getSession().setAttribute("message", "Successful deletion.");
				response.sendRedirect("admin/AdminHome.jsp");
			}else{
				request.getSession().setAttribute("message", "Error !!!! Try again!!!");
			}
		}else{
			request.getSession().setAttribute("message", "Error !!!! Single category");
			response.sendRedirect("admin/AdminHome.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
