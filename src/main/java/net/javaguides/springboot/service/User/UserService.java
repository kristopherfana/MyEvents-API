package net.javaguides.springboot.service.User;

import com.twilio.exception.ApiException;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService{
	User save(UserDto registrationDto);
    User toNewEntity(UserDto userDto);

    User toExistingEntity(UserDto userDto, User user);

    UserDto toDto(User user);

    void updatePassword(Long id, String password);

    //GET Methods
    List<User> findAllById(List<Long> userIds);

    List<User> getAllUsers();
    User getLoggedInUser();
    User findById(Long id);
    User updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
