package sessionmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sessionmanagement.model.TestUserRepository;
import sessionmanagement.model.TestUsers;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    TestUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<TestUsers> optionalUsers =userRepository.findById(username);
        if (optionalUsers.isPresent())
        {
            TestUsers loginUser = optionalUsers.get();

            return new User(loginUser.getUserId(),
                    loginUser.getPassword(),
                    new ArrayList<>()
            );
        }
        else
        {
            throw new UsernameNotFoundException("User name " +username+ " not found in database");
        }
    }
}
