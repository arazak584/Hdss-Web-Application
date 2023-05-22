package org.arn.hdsscapture.controller;

import java.security.Principal;
import org.arn.hdsscapture.entity.UserTable;
import org.arn.hdsscapture.repository.GroupTableRepository;
import org.arn.hdsscapture.repository.UserTableRepository;
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
			BindingResult result) {

		boolean userExists = userRepo.findById(selected.getUsername()).isPresent();
		boolean emailExists = userRepo.findByUserEmail(selected.getUser_email()).isPresent();

		if (userExists) {
			result.rejectValue("username", "user.exists");
		}
		if (emailExists) {
			result.rejectValue("userEmail", "user.exists");
		}

		if (result.hasErrors()) {

			model.addAttribute("users", "active");
			model.addAttribute("usercreate", "active");
			model.addAttribute("user_groups", groupRepo.findAll());


			return "controls/user-list";
		}

		String password = selected.getUser_password();
		String encodedPassword = bCrypt.encode(password);
		selected.setUser_password(encodedPassword);

		userRepo.save(selected);

		return "redirect:/controls/users/add?success=yes";
	}

	@GetMapping("/users/{id}")
	public String editUser(Principal principal, final Model model, @PathVariable("id") String user_id,
			@RequestParam(name = "success", required = false) String success) {

		model.addAttribute("selected", userRepo.findById(user_id).get());

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

		userRepo.save(selected);

		return "redirect:/controls/users/" + user_id + "?success=yes";
	}

	@GetMapping("/users/{id}/password")
	public String editUserPassword(Principal principal, final Model model, @PathVariable("id") String user_id,
			@RequestParam(name = "success", required = false) String success) {

		UserTable user = userRepo.findById(user_id).get();

		model.addAttribute("selected", user);
		model.addAttribute("usr", "active");

		if (user_id.equals(user.getUser_email()))
			model.addAttribute("currentuser", "currentuser");

		if (success != null) {
			model.addAttribute("success", "Saved successfully");
		}

		return "controls/user-update-password";
	}

	@PostMapping("/users/{id}/password")
	public String editUserPassword(Principal principal, @ModelAttribute("selected") UserTable selected,
			BindingResult results) {

		if (selected.getUser_email() == principal.getName()) {

			UserTable check = userRepo.findById(selected.getUser_email()).get();

			if (selected.getCur_password() != check.getUser_password()) {
				results.rejectValue("current_password", "wrong.password", "Current password is incorrect");
			}

			if (results.hasErrors()) {
				return "controls/user-update-password";
			}

		}

		userRepo.save(selected);

		return "redirect:/controls/users/{id}/password?success=1";

	}


	/// SITES//////////////////////////////////
	

}// end class