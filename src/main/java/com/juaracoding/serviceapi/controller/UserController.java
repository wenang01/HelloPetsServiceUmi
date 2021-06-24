package com.juaracoding.serviceapi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.serviceapi.entity.ERole;
import com.juaracoding.serviceapi.entity.Role;
import com.juaracoding.serviceapi.entity.User;
import com.juaracoding.serviceapi.payload.request.SignupRequest;
import com.juaracoding.serviceapi.payload.response.MessageResponse;
import com.juaracoding.serviceapi.repository.RoleRepository;
import com.juaracoding.serviceapi.repository.UserRepository;
import com.juaracoding.serviceapi.services.ModelUser;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	ModelUser modelUser;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepository;

	
	@GetMapping("/")
	public List<User> indexUsers(Model model) {
		model.addAttribute("lstUsers", modelUser.getAllUser());
		return modelUser.getAllUser();
	}
		
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsers(@PathVariable String id, @RequestBody User user, @RequestBody SignupRequest signUpRequest) {
		user.setId(Long.parseLong(id));		
		
		User userUpdate = new User(user.getName(), user.getUsername(),user.getEmail(),
				encoder.encode(user.getPassword()), user.getAddressOne(), user.getAddressTwo(),
				user.getProvincesId(), user.getRegenciesId(), user.getZipCode(),
				user.getCountry(), user.getPhoneNumber(), user.getBirthday(), user.getGender()
				 );

		userUpdate.setId(Long.parseLong(id));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		userUpdate.setRole(roles);

		userRepo.save(userUpdate);
		return ResponseEntity.ok(new MessageResponse("User Updated Successfully!"));
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUsers(@PathVariable String id, Model model) {
		this.modelUser.deleteUser(id);
		return "User has been Deleted!";
	}
}
