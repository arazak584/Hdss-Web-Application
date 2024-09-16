package org.arn.hdsscapture.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.arn.hdsscapture.entity.UserTable;
import org.arn.hdsscapture.repository.GroupTableRepository;
import org.arn.hdsscapture.repository.UserTableRepository;
import org.arn.hdsscapture.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/controls")
public class ControlsController {

	@Autowired
	private UserTableRepository userRepo;

	@Autowired
	private GroupTableRepository groupRepo;

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
    private EmailService emailService;

	@GetMapping("")
	public String controls(Principal principal, Model model,
			@RequestParam(name = "success", required = false) String success) {

		model.addAttribute("back", "back");

		if (success != null) {
			model.addAttribute("success", "Synced Successfully!");
		}

		return "controls/controls";
	}

	@GetMapping("/users")
	public String listUsers(Principal principal, final Model model) {

		model.addAttribute("users", "active");
		model.addAttribute("userlist", "active");
		model.addAttribute("items", userRepo.findAll());

		return "controls/user-list";
	}

	@GetMapping("/users/add")
	public String addUser(Principal principal, final Model model,
			@RequestParam(name = "success", required = false) String success) {

		model.addAttribute("selected", new UserTable());

		model.addAttribute("users", "active");
		model.addAttribute("usercreate", "active");
		model.addAttribute("user_groups", groupRepo.findAll());

		if (success != null) {
			model.addAttribute("success", "Saved successfully");
		}

		return "controls/user-create";
	}

	@PostMapping("/users/add")
	public String addUser(Principal principal, final Model model, @ModelAttribute("selected") UserTable selected,
			BindingResult result, HttpServletRequest request) {

		//String username = selected.getUsername().toLowerCase();
		boolean userExists = userRepo.findById(selected.getUsername()).isPresent();
		boolean emailExists = userRepo.findByUserEmail(selected.getUser_email()).isPresent();

		if (userExists) {
			result.rejectValue("username", "user.exists");
		}
		if (emailExists) {
			result.rejectValue("user_email", "email.exists");
		}

		if (result.hasErrors()) {

			model.addAttribute("users", "active");
			model.addAttribute("usercreate", "active");
			model.addAttribute("user_groups", groupRepo.findAll());

			return "controls/user-create";
		}

		String password = selected.getUser_password();
		String encodedPassword = bCrypt.encode(password);
		selected.setUser_password(encodedPassword);
		//selected.setUsername(username);

		userRepo.save(selected);
		
		// Get the base URL
//	    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	    
	    // Get the base URL with port conditionally
	    String baseUrl;
	    if ((request.getScheme().equals("http") && request.getServerPort() == 80) || 
	        (request.getScheme().equals("https") && request.getServerPort() == 443)) {
	        baseUrl = request.getScheme() + "://" + request.getServerName();
	    } else {
	        baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	    }

	    // Send registration email
	    String subject = "HDSS CAPTURE USER REGISTRATION";
	    String url = baseUrl + "/login"; 
	    String pwdurl = baseUrl + "/controls/password";
	    String fullName = selected.getUser_fname() + " " + selected.getUser_lname();
	    String text = String.format("Dear %s,\n\nThank you for registering with us. Here are your details:\n\nUsername: %s\nEmail: %s\nPassword: %s\n\nYou can login at the following URL: %s\n\nYou can change your password after Login at the following URL: %s\n\nBest regards,\nHDSS CAPTURE",
	    		fullName, selected.getUsername(), selected.getUser_email(), password, url,pwdurl);
	    emailService.sendSimpleMessage(selected.getUser_email(), subject, text);

		return "redirect:/controls/users";
	}

	@GetMapping("/users/{id}")
	public String editUser(Principal principal, final Model model, @PathVariable("id") String user_id,
			@RequestParam(name = "success", required = false) String success) {
		
		UserTable user = userRepo.findById(user_id).orElse(null);
		
		if (user == null) {
	        // Handle user not found case
	        // You might redirect or show an error message
	        return "redirect:/controls/users?error=userNotFound";
	    }

		model.addAttribute("selected", user);
		model.addAttribute("users", "active");
		model.addAttribute("userupdate", "active");
		model.addAttribute("user_groups", groupRepo.findAll());

		if (success != null) {
			model.addAttribute("success", "Saved successfully");
		}

		return "controls/user-update";
	}

	@PostMapping("/users/{id}")
	public String editUser(Principal principal, @ModelAttribute("selected") UserTable selected,
			@PathVariable("id") String user_id) {

		UserTable existingUser = userRepo.findById(user_id).orElse(null);

		if (existingUser != null) {
			// Update only non-sensitive fields (excluding password)
			existingUser.setUser_fname(selected.getUser_fname());
			existingUser.setUser_lname(selected.getUser_lname());
			existingUser.setUser_enabled(selected.isUser_enabled());
			existingUser.setEmail_enabled(selected.isEmail_enabled());
			existingUser.setGroups(selected.getGroups());

			// Save the updated user
			userRepo.save(existingUser);
		}

//		userRepo.save(selected);

		return "redirect:/controls/users/" + user_id + "?success=yes";
	}

	@GetMapping("/users/{id}/password")
	public String editUserPassword(Principal principal, final Model model, @PathVariable("id") String user_id,
			@RequestParam(name = "success", required = false) String success) {

		UserTable user = userRepo.findById(user_id).orElse(null);

		model.addAttribute("selected", user);
		model.addAttribute("usr", "active");

		if (user_id.equals(user.getUsername()))
			model.addAttribute("currentuser", "currentuser");

		if (success != null) {
			model.addAttribute("success", "Saved successfully");
		}

		return "controls/user-update-password";
	}

	@PostMapping("/users/{id}/password")
	public String editUserPassword(Principal principal, @ModelAttribute("selected") UserTable selected,
			@PathVariable("id") String user_id, BindingResult results, HttpServletRequest request) {

		if (selected.getUsername()!= null) {
			
			UserTable check = userRepo.findById(selected.getUsername()).orElse(null);

			if (selected.getCur_password() != null && check != null &&
	                !bCrypt.matches(selected.getCur_password(), check.getUser_password())) {
	            results.rejectValue("current_password", "wrong.password", "Current password is incorrect");
	        }

			if (results.hasErrors()) {
				return "controls/user-update-password";
			}

			String password = selected.getUser_password();
			String encodedPassword = bCrypt.encode(password);
			check.setUser_password(encodedPassword);

			userRepo.save(check);
			
			// Get the base URL with port conditionally
		    String baseUrl;
		    if ((request.getScheme().equals("http") && request.getServerPort() == 80) || 
		        (request.getScheme().equals("https") && request.getServerPort() == 443)) {
		        baseUrl = request.getScheme() + "://" + request.getServerName();
		    } else {
		        baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		    }

		    // Send registration email
		    String subject = "HDSS CAPTURE PASSWORD RESET";
		    String url = baseUrl + "/login"; 
		    String pwdurl = baseUrl + "/controls/password";
		    String fullName = selected.getUser_fname() + " " + selected.getUser_lname();
		    String text = String.format("Dear %s,\n\nAdministrator Has succesfully Changed your password. Here are your details:\n\nUsername: %s\nEmail: %s\nPassword: %s\n\nYou can login at the following URL: %s\n\nYou can change your password after Login at the following URL: %s\n\nBest regards,\nHDSS CAPTURE",
		    		fullName, selected.getUsername(), selected.getUser_email(), password, url,pwdurl);
		    emailService.sendSimpleMessage(selected.getUser_email(), subject, text);
			
			
			return "redirect:/controls/users/" + user_id + "/password?success=1";
		}else {
			return "redirect:/controls/users/" + user_id + "/password";
			
		}

	}
	
	@GetMapping("/password")
	public String showPasswordChangeForm(Model model, Principal principal) {
		
		//System.out.println("Entered username: " + principal.getName());
	    // Retrieve the email of the currently logged-in user
	    String userName = principal.getName();

	    // Create a new UserTable object and set the email
	    UserTable user = new UserTable();
	    user.setUsername(userName);

	    // Add the user to the model
	    model.addAttribute("selected", user);

	    return "controls/person-update-password";
	}
	
	@PostMapping("/password")
	public String updateUserPassword(Principal principal, @ModelAttribute("selected") UserTable selected,
			 BindingResult results) {
		
//		System.out.println("Entered username print: " + principal.getName());
//		System.out.println("Entered username selected: " + selected.getUsername());
//	    System.out.println("Submitted current password: " + selected.getCur_password());

		if (selected.getUsername() != null) {
			System.out.println("Entered username selected: " + selected.getUsername());
			UserTable check = userRepo.findById(selected.getUsername()).orElse(null);
			//System.out.println("User found: " + (check != null ? check.getUsername() : "null"));

			if (selected.getCur_password() != null && check != null &&
	                !bCrypt.matches(selected.getCur_password(), check.getUser_password())) {
	            results.rejectValue("current_password", "wrong.password", "Current password is incorrect");
	        }

			if (results.hasErrors()) {
				return "controls/person-update-password";
			}

			String password = selected.getUser_password();
			String encodedPassword = bCrypt.encode(password);
			check.setUser_password(encodedPassword);

			userRepo.save(check);
			return "redirect:/logout?success=1";
		}else {
			return "redirect:/controls/password?error=1";
			
		}

	}

}