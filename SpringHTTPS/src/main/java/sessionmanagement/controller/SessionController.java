package sessionmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sessionmanagement.model.LoginRequest;
import sessionmanagement.model.TestUserRepository;
import sessionmanagement.model.TestUsers;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@CrossOrigin
public class SessionController
{

    @Autowired
    TestUserRepository testUserRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public String createUser(@RequestBody TestUsers user)
    {
        try
        {
            testUserRepository.save(user);
            return "User account created for " +user.getFirstName();
        }
        catch (Exception e)
        {
            return "User account creation failed " +e.getMessage();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginDto, HttpServletRequest request, HttpServletResponse response)
    {
        // read cookies
        Cookie[] cookiesFromBrowser = request.getCookies();
        // Auth
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String sessionId = attributes.getRequest().getSession().getId();

        // Set the session ID as a cookie
        Cookie sessionCookie = new Cookie("JSESSIONID", sessionId);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(false);
        sessionCookie.setSecure(true);
        response.addCookie(sessionCookie);
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Add this line

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<List> getMessage(Model model, HttpSession session)
     {
        List greetings = (List) session.getAttribute("GREETING_MESSAGES");
        if(greetings == null) {
            greetings = new ArrayList<>();
        }

        Integer count = (Integer) session.getAttribute("MESSAGES_COUNT");

        List messageAndCount = new ArrayList<>();
        messageAndCount.add(greetings);
        messageAndCount.add(count);

        return new ResponseEntity<List>(messageAndCount, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<List> saveMessage(@RequestParam("message") String greeting, HttpServletRequest request)
    {
        List greetings = (List) request.getSession().getAttribute("GREETING_MESSAGES");
        if(greetings == null)
        {
            greetings = new ArrayList<>();
            request.getSession().setAttribute("GREETING_MESSAGES", greetings);
        }

        greetings.add(greeting);
        request.getSession().setAttribute("GREETING_MESSAGES", greetings);
        request.getSession().setAttribute("MESSAGES_COUNT", greetings.size());

        return new ResponseEntity<List>(greetings, HttpStatus.OK);
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "Session cleared";
    }
}
