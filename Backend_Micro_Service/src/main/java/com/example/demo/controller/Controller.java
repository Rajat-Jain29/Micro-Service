package com.example.demo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

	HashMap<String, String> user = new HashMap<>();
	HashMap<String, String> order = new HashMap<>();

	@PostMapping("/newUser")
	public String post(HttpServletRequest request, HttpServletResponse res) throws SQLException {

		user = new HashMap<>();

		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String id = request.getParameter("id");

		user.put("name", name);
		user.put("mobile", mobile);
		user.put("email", email);
		user.put("userId", id);

		return "Record Inserted";

	}

	@PostMapping("/newOrder")
	public String newOrder(HttpServletRequest request, HttpServletResponse res) throws SQLException {

		order = new HashMap<>();

		String quantity = request.getParameter("quantity");
		String user = request.getParameter("user");
		String item = request.getParameter("items");
		String id = request.getParameter("id");

		order.put("quantity", quantity);
		order.put("user", user);
		order.put("item", item);
		order.put("orderId", id);

		return "Order Record Inserted";

	}

	@GetMapping("/all_order/{user_id}")
	public HashMap<String, String> allOrder(@PathVariable("user_id") String id) throws SQLException {

		HashMap<String, String> map = new HashMap<>();

		if (user.get(id) == order.get(id)) {

			for (Map.Entry val : order.entrySet()) {

				map.put("" + val.getKey(), "" + val.getValue());

			}

		}
		return map;

	}

	@PutMapping("/updateOrder/{item}")
	public String update(HttpServletRequest request, HttpServletResponse res, @PathVariable("item") String item)
			throws SQLException {

		String orderId = request.getParameter("orderId");

		return "Record Updated";

	}

	@DeleteMapping("/deleteOrder")
	public String delete(HttpServletRequest request, HttpServletResponse res) throws SQLException {

		String orderId = request.getParameter("orderId");

		return "Record Deleted";

	}

}
