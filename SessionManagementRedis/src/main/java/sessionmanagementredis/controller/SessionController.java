package sessionmanagementredis.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sessionmanagementredis.model.TestUserRepository;
import sessionmanagementredis.model.TestUsers;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SessionController
{

    @Autowired
    TestUserRepository testUserRepository;

    @PostMapping("/signup/")
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
