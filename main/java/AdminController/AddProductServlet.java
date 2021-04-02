package AdminController;


import DAO.DAO;
import Model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		boolean status = false;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String productName= request.getParameter("product_name");
		String productPrice = request.getParameter("product_price");
		HttpSession adminSession = request.getSession(false);

		if(productName != null && productPrice != null){
			product.setProductName(productName);
			product.setProductPrice(Double.parseDouble(productPrice));
			try {
				status = new DAO().insert(product);
			} catch (Exception e) {
				adminSession.setAttribute("exception",e);
				e.printStackTrace();
			}
			if(status){
				out.println("<script>alert('Successful Added new Product !!!')</script>");
				adminSession.setAttribute("message","Successful addition !!!" );
				response.sendRedirect("admin/AdminHome.jsp");
			}else{
				out.println("<script>alert('Error !!! This kind of product already exists !!!')</script>");
				adminSession.setAttribute("message","Error !!! This kind of product already exists !!!" );
				response.sendRedirect("admin/AdminHome.jsp");
			}
		}else{
			out.println("<script>alert('Try again! !!!')</script>");
			response.sendRedirect("AdminLogin.jsp");
		}


	}

}
