package net.javaguides.springboot.service.User;

import java.util.List;

import net.javaguides.springboot.ExceptionHandling.NotFoundException;
import net.javaguides.springboot.config.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	//POST methods
	@Override
	public User save(UserDto userDto) {
		User user=this.toNewEntity(userDto);
		return userRepository.save(user);
	}
	@Override
	public User updateUser(Long id, UserDto userDto){
		User retrievedUser= this.findById(id);
		return userRepository.save(this.toExistingEntity(userDto,
				retrievedUser));
	}
	@Override
	public void updatePassword(Long id, String password){
		User retrievedUser = this.findById(id);
		retrievedUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(retrievedUser);
    }

	//GET Methods
	@Override
	public List<User> findAllById(List<Long> userIds){
		return userRepository.findAllById(userIds);
	}
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return userRepository.findByEmail(email);
	}
	@Override
	public User findById(Long id){
		return userRepository.findById(id).orElseThrow(()-> new NotFoundException(
				"User not found with id "+id));
	}

	//DELETE Methods
	@Override
	public void deleteUser(Long id){
		User user = this.findById(id);
		userRepository.delete(user);
	}

	//UTILITY methods
	@Override
	public User toNewEntity(UserDto userDto){
		return new User(userDto.getFirstName(),
				userDto.getLastName(), userDto.getEmail(),
				passwordEncoder.encode(userDto.getPassword()),
				userDto.getPhoneNumber());
	}
	@Override
	public User toExistingEntity(UserDto userDto, User user){
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		return user;
	}
	@Override
	public UserDto toDto(User user){
		return new UserDto(
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhoneNumber()
		);
	}

	//AUTH Methods
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid email or " +
					"password.");
		}
		return UserDetailsImpl.build(user);
	}

}
