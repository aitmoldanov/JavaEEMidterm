<%@page import="DAO.DAO"%>

<%@ page import="java.util.List"%>
<%@ page import="DAO.DAO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Product Details</title>
<jsp:include page="AdminBar.jsp" />
</head>
<body>
	<div align="center">
		<h2>Add New Product</h2>
	</div>
	<div>
		<form action="../AddProductServlet" method="post">
			<table align="center">
				<tr>
					<td>Product Name :</td>
					<td><input type="text" name="product_name" required="true" /></td>
				</tr>
				<tr>
					<td>Product Price :</td>
					<td><input type="text" name="product_price" required="true" pattern="[0-9.]+"/></td>
				</tr>
				<tr>
					<td>Product Description :</td>
					<td><input type="text" name="product_desc" required="true"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input value="Submit"
						type="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
