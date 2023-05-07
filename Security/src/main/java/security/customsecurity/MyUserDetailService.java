package security.customsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.datamodel.Users;
import security.repository.RolesRepository;
import security.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        Optional<Users> optionalUsers =userRepository.findById(username);
        if (optionalUsers.isPresent())
        {
            Users loginUser = optionalUsers.get();
            MyUserDetails customUserDetails = new MyUserDetails(loginUser.getUserId());
            customUserDetails.setPassword(loginUser.getPassword());
            customUserDetails.addUserRole(loginUser.getUserRole());
            return customUserDetails;
        }
        else
        {
            throw new UsernameNotFoundException("User name not found in database");
        }
    }
}
