package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	Statement statement;
	String url = "jdbc:mysql://localhost:3306/ABC_Delivery";
	String username = "root";
	String password = "";

	@PostMapping("/newUser")
	public String post(HttpServletRequest request, HttpServletResponse res) throws SQLException {

		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String id = request.getParameter("id");

		Connection connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();

		String query = "insert into user values ('" + name + "','" + mobile + "','" + email + "' , '" + id + "');";

		statement.executeUpdate(query);

		return "Record Inserted";

	}

	@PostMapping("/newOrder")
	public String newOrder(HttpServletRequest request, HttpServletResponse res) throws SQLException {

		String quantity = request.getParameter("quantity");
		String user = request.getParameter("user");
		String item = request.getParameter("items");
		String id = request.getParameter("id");

		Connection connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();

		String query = "insert into orders values ('" + quantity + "','" + user + "','" + item + "' , '" + id + "');";

		statement.executeUpdate(query);

		return "Order Record Inserted";

	}

	@GetMapping("/all_order/{user_id}")
	public ArrayList<HashMap<String, String>> allOrder(@PathVariable("user_id") String id) throws SQLException {

		Connection connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();

		String query = "select name, items, quantity from user u,orders o where o.orderId = u.id";
		ResultSet resultSet = statement.executeQuery(query);

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		HashMap<String, String> map = new HashMap<>();

		while (resultSet.next()) {
			map = new HashMap<>();
			map.put("name", resultSet.getString("name"));
			map.put("item", resultSet.getString("items"));
			map.put("quantity", resultSet.getString("quantity"));

			list.add(map);

		}

		return list;

	}

	@PutMapping("/updateOrder/{item}")
	public String update(HttpServletRequest request, HttpServletResponse res, @PathVariable("item") String item)
			throws SQLException {

		Connection connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();

		String orderId = request.getParameter("orderId");

		String query = "update orders set items = '" + item + "' where orderId = '" + orderId + "' ";
		statement.executeUpdate(query);

		return "Record Updated";

	}

	@DeleteMapping("/deleteOrder")
	public String delete(HttpServletRequest request, HttpServletResponse res) throws SQLException {

		Connection connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();

		String orderId = request.getParameter("orderId");

		String query = "delete from orders where orderId = '" + orderId + "' ";
		statement.executeUpdate(query);

		return "Record Deleted";

	}

}
