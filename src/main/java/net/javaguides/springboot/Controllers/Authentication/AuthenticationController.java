package net.javaguides.springboot.Controllers.Authentication;

import io.swagger.v3.oas.annotations.Operation;
import net.javaguides.springboot.config.JwtUtils;
import net.javaguides.springboot.config.UserDetailsImpl;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.service.User.UserService;
import net.javaguides.springboot.web.dto.UserDto;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,
		allowCredentials="true")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AuthenticationManager authenticationManager;

	private final UserService userService;
	private final UserRepository userRepository;

	public AuthenticationController(UserService userService, UserRepository userRepository) {
		super();
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@Operation(summary = "Create a User.")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUserAccount(@RequestBody @Valid UserDto userDto) {
		System.out.println(userDto);

			if (userRepository.existsByEmail(userDto.getEmail())) {
				return ResponseEntity.status(409).body("Email " +
						"is already taken");
			}
			userService.save(userDto);
			return ResponseEntity.ok().body("User registered " +
					"successfully");

	}

	@Operation(summary = "Authentication using email and password.")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto userDto){

		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
				jwtCookie.toString()).body("Login successful");
	}

	@Operation(summary = "Log out.")
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body("You have signed out successfully!");
	}
}
