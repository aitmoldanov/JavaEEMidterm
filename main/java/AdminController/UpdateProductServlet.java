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

@WebServlet("/EditProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		Product product = new Product();

		if(productId != null && !(productId.equals(""))){
			try {
				product = new DAO().fetchSingle(Integer.parseInt(productId));
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			request.getSession().setAttribute("product", product);
			response.sendRedirect("admin/EditProduct.jsp");
		}else{
			request.getSession().setAttribute("message", "Error ! Try again!");
			response.sendRedirect("admin/AdminHome.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int productId = Integer.parseInt(request.getParameter("product_id"));
		String productName= request.getParameter("product_name");
		String productPrice = request.getParameter("product_price");
		Product product = new Product();
		HttpSession adminSession = request.getSession(false);

		boolean status = false;
		PrintWriter out = response.getWriter();
		if(productName != null && productPrice != null && productId != 0){
			product.setProductId(productId);
			product.setProductName(productName);
			product.setProductPrice(Double.parseDouble(productPrice));
			try {
				status = new DAO().update(product);
			} catch (Exception e) {
				adminSession.setAttribute("exception",e);
				e.printStackTrace();
			}
			if(status){
				out.println("<script>alert('Successful addition!!!')</script>");
				adminSession.setAttribute("message","Successful addition!!!" );
				response.sendRedirect("admin/AdminHome.jsp");
			}else{
				out.println("<script>alert('Error !!! Already exists  !!!')</script>");
				adminSession.setAttribute("message","Error !!! Already exists !!!" );
				response.sendRedirect("admin/AdminHome.jsp");
			}
		}else{
			out.println("<script>alert('Try Again !!!')</script>");
			response.sendRedirect("AdminLogin.jsp");
		}
	}

}
