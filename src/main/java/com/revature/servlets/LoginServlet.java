package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.User;
import com.revature.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	/*
	 * USING LOG4J!
	 */
	final static Logger logger = Logger.getLogger(LoginServlet.class);
	UserService service = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//retrieve user from req body 
		User user = mapper.readValue(req.getInputStream(), User.class);
		//pass in username and password to service layer, get user obj
		user = service.login(user.getUsername(), user.getUsrPassword());
		System.out.println(user);
		//if obj == null, invalid credentials. send back null
		if(user == null) {
			resp.setStatus(204);
		}
		//if obj != null, add user to session, send back user data 
		else {
			HttpSession session = req.getSession();
			logger.info("Created HttpSession " + session.getId());
			
			session.setAttribute("loggedUser", user);
			logger.info("Logged in user " + user);
			
			
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setStatus(200);
			writer.write(mapper.writeValueAsString(user));
		}
	}
}


