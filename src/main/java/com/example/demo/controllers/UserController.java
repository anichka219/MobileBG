package com.example.demo.controllers;

<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.configuration.PassEncoding;
import com.example.demo.models.Advertisement;
import com.example.demo.models.User;
import com.example.demo.models.enums.Status;
import com.example.demo.models.enums.UserRoles;
import com.example.demo.service.AdvService;
import com.example.demo.service.MainCategoryServiceImpl;
import com.example.demo.service.RegionServiceImpl;
import com.example.demo.service.UserService;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GlobalController globalController;

    @Autowired
    UserService userService;
    
    @Autowired
    AdvService advService;
    
    @Autowired
    RegionServiceImpl regionService;
    
    @Autowired
    MainCategoryServiceImpl mainCategoryService;
    
    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("root");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("login");
        return "login";
    }
    
    @RequestMapping("/home")
    public String home(Model model) {
    	Advertisement ad =new Advertisement();
        model.addAttribute("reqAdv", ad);
        model.addAttribute("allReg",regionService.getAllRegions());
        model.addAttribute("allMainCategory", mainCategoryService.getAllMainCategories());
        model.addAttribute("allAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "home";
    }

    @RequestMapping("/admin")
    public String helloAdmin() {
        logger.info("admin");
        return "admin";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("register");
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {

        logger.info("/user/register");
        User user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/register";
        }
        
        reqUser.setCryptPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));
        reqUser.setRole(UserRoles.ROLE_USER.getValue());

        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/register";
    }


}

=======
import com.example.demo.exceptions.BaseException;
import com.example.demo.exceptions.InvalidInputException;
import com.example.demo.exceptions.NotLoggedInException;
import com.example.demo.models.ErrorMsg;
import com.example.demo.models.User;
import com.example.demo.daova.sql.Connection.UserDAO;
import com.example.demo.service.UserRepository;
import lombok.*;
//import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

//import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "users", produces = "application/json")
public class UserController extends BaseController{

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "register")
    public User addUser(@RequestBody User user, HttpSession session){
    	System.out.println("here!!!!!!!");
    	try {
        validateUserInput(user);
        System.out.println(14);
    	userRepository.save(user);
        session.setAttribute(LOGGED_USER, user);
        return user;
        }
    	catch(BaseException ex) {
    		System.out.println(ex.getMessage());
    		return null;
    	}

    }
//
//    @PostMapping(value = "register/admin")
//    public User addAdmin(@RequestBody User user , HttpSession session) throws BaseException{
//        validateUserInput(user);
//        user.setAdministrator(true);
//        session.setAttribute(LOGGED_USER, user);
//        userRepository.save(user);
//        return user;
//    }
//
//    @GetMapping(value = "logout")
//    public Object logout(HttpSession session) {
//        if(session.getAttribute(LOGGED_USER) != null) {
////            session.removeAttribute(LOGGED_USER);
//            session.invalidate();
//            return new ErrorMsg("You logged out successfully", LocalDateTime.now(), HttpStatus.OK.value());
//        }
//        return new ErrorMsg("You are not logged in", LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
//    }
//
    private void validateUserInput( User user) throws BaseException{
        String newUsername = user.getFirstName();
        String newEmail = user.getEmail();
        newEmail = newEmail.trim();
        validateNullInput(user);
        user.setPassword(user.getPassword().trim());
        user.setPassword2(user.getPassword2().trim());
        if ( getUserByName(newUsername) != null ) {
        	System.out.println(1);
            throw new InvalidInputException("This username is already used by another user.");
        }
        else if( getUserByEmail(newEmail) != null) {
        	System.out.println(2);
        	throw new InvalidInputException("This email is already used by another user.");
        }
        else if(user.getPassword().length() < 3 ) {
        	System.out.println(3);
        	throw new InvalidInputException("The password should be more than 3 symbols");
        }
        else if (!user.getPassword().equals(user.getPassword2()) ) {
            throw new InvalidInputException("The passwords don't match.");
        }
        else if (!user.getEmail().matches("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$")) {
            throw new InvalidInputException("The email is not valid");
        }
        System.out.println(123);
    }

//
//    @PostMapping(value = "login")
//    public Object login(@RequestBody User user, HttpSession session) throws BaseException{
//        String pendingUsername = user.getFirstName();
//        String pendingPassword = user.getPassword();
//        if(pendingPassword == null || pendingUsername == null) {
//            throw new InvalidInputException("Username/password should not be empty.");
//        }
//        if(session.getAttribute(LOGGED_USER) != null) {
//            throw new InvalidInputException("You are already logged in.");
//        }
//        if(getUserByName(pendingUsername).getFirstName().equals(pendingUsername) &&
//            getUserByName(pendingUsername).getPassword().equals(pendingPassword)) {
//            session.setAttribute(LOGGED_USER, getUserByName(pendingUsername));
//            return new ErrorMsg("You logged successfully.", LocalDateTime.now(), 200);
//        }
//        throw new InvalidInputException("Wrong username/password");
//    }
//
//    @PutMapping(value = "profile/delete")
//    public Object deleteProfile(@RequestBody User user, HttpSession session)
//                                throws BaseException {
//        validateLogin(session);
//        User userSession = (User)session.getAttribute(LOGGED_USER);
//        if(userRepository.findById(userSession.getId()).isPresent()) {
//            if(userSession.getPassword().equals(user.getPassword()) && userSession.getEmail().equals(user.getEmail())) {
//                userSession.setFirstName("deleted");
//                userSession.setFirstName("deleted");
//                userSession.setLasstName("deleted");
//                userSession.setPassword("deleted");
//                userSession.setPassword2("deleted");
//                userSession.setEmail("deleted");
//                userRepository.save(userSession);
//                return new ErrorMsg("Profile deleted successfully.", LocalDateTime.now(), HttpStatus.OK.value());
//            }
//        }
//        throw new InvalidInputException("Wrong email/password. Can not delete profile.");
//    }
//
//    @PutMapping(value = "profile/update/password")
//    public Object updatePassword(@RequestBody ChangePasswordUser pendingUser,  HttpSession session) throws BaseException {
//        validateLogin(session);
//        User user = (User)session.getAttribute(LOGGED_USER);
//        System.out.println("?????????????????????????????????");
//        System.out.println(pendingUser);
//        if(userRepository.findById(user.getId()).isPresent()) {
//            if(user.getPassword().equals(pendingUser.getPassword()) && user.getFirstName().equals(pendingUser.getUsername())) {
//                if(!user.getPassword().equals(pendingUser.getNewPassword())) {
//                    if (pendingUser.getNewPassword().length() >= 3) {
//                        user.setPassword(pendingUser.getNewPassword());
//                        userRepository.save(user);
//                        return new ErrorMsg("You successfully changed your password.", LocalDateTime.now(),
//                                            HttpStatus.OK.value());
//                    }
//                    throw new InvalidInputException("New password should be more than 3 symbols.");
//                }
//                throw new InvalidInputException("New password can not be the same as old password.");
//            }
//            throw new InvalidInputException("Wrong username/password. Can not change password.");
//        }
//        throw new InvalidInputException("No user with that username/password.");
//    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Component
    @ToString
    private static class ChangePasswordUser {
        private String username;
        private String password;
        private String newPassword;
    }


    private void validateNullInput(User user) throws BaseException {
        if(user.getFirstName() == null) {
            throw new InvalidInputException("The username should not be empty");
        }
        if(user.getFirstName() == null) {
            throw new InvalidInputException("The first name should not be empty");
        }
        if(user.getLasstName() == null) {
            throw new InvalidInputException("The last name should not be empty");
        }
    }

    private User getUserByName(String username) {
        return userRepository.findByFirstName(username);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Autowired
    private JdbcTemplate jdbcTemplate;


}
>>>>>>> 6b81749543366aa59826d73d396c0a005962dc99
