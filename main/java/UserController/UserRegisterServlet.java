package UserController;

import DAO.DAO;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("form-username");
		String userPassword = request.getParameter("form-password");

		if(userName != null && userPassword != null){
			try{
				User user = new User();
				user.setUserName(userName);
				user.setUserPassword(userPassword);

				boolean isUserRegistered = DAO.addNewUser(user);

				response.setContentType("text/html");
				if(isUserRegistered){
					out.print("<h3>Successful registration</h3>");
					out.print("<a href='Login.jsp'>Go To Login Page</a>");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				out.close();
			}
		}else{
			response.sendRedirect("registration.jsp");
		}
	}
}
