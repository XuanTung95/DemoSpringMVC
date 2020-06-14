package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.controllers.db.UserDb;
import demo.models.User;


@Controller
public class LoginController {
	UserDb userDb = new UserDb();
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "Login/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String postLoginPage(@RequestParam(value = "email", defaultValue = "") String email, @RequestParam(value = "pass", defaultValue = "") String pass) {
		System.out.println("Post payload = " + email + ":"+pass);
		User user = userDb.getUser(email);
		if(user==null) {
			System.out.println("There is no user with input email");
		} else {
			ObjectMapper mapper = new ObjectMapper();
			try {
				return mapper.writeValueAsString(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loginFailed(email, pass);
	}
	
	private String loginFailed(String email, String pass) {
		return "Login failed: email="+email + ": pass="+pass;
	}
}
