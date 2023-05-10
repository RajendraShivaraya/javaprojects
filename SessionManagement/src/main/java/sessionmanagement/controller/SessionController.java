package sessionmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController
{

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
