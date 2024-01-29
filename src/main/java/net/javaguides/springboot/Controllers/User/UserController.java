package net.javaguides.springboot.Controllers.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.User.UserService;
import net.javaguides.springboot.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get All Users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User " +
                    "found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    User.class)) })})
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList=userService.getAllUsers();
        return ResponseEntity.ok().body(userList);
    }

    @Operation(summary = "Get User by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User " +
                    "found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    User.class)) }),
            @ApiResponse(responseCode = "404", description = "User " +
                    "not found.",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Get Currently Authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User " +
                    "found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    User.class)) }),
            @ApiResponse(responseCode = "404", description = "User " +
                    "not found.",
                    content = @Content) })
    @GetMapping("/profile")
    public ResponseEntity<User> getAuthenticatedUser(){
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Update User by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User " +
                    "updated successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    User.class)) }),
            @ApiResponse(responseCode = "404", description = "User " +
                    "not found.",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id
            , @RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.updateUser(id, userDto));
    }


    @Operation(summary = "Delete User By Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User " +
                    "deleted successfully."),
            @ApiResponse(responseCode = "404", description = "User " +
                    "not found.",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User Deleted successfully");
    }

}
