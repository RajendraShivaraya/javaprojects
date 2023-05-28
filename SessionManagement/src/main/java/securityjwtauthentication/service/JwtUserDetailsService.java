package securityjwtauthentication.service;

import java.util.ArrayList;
import java.util.Optional;

import securityjwtauthentication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import securityjwtauthentication.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Users> optionalUsers =userRepository.findById(username);
        if (optionalUsers.isPresent())
        {
            Users loginUser = optionalUsers.get();

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
