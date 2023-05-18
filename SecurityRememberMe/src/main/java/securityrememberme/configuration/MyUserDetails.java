package securityrememberme.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetails implements UserDetailsService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserCredentials userCredentials = getUserDetails(username);

        return new User(userCredentials.getUserid(),
                userCredentials.getPassword(),
                new ArrayList<>()
        );
    }

    public UserCredentials getUserDetails(String username)
    {
        try {
            String sql = "SELECT userid, password FROM users where userid = '"+username + "'";

            UserCredentials userCredentials = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserCredentials.class)).get(0);
            if (userCredentials != null) {
                return userCredentials;
            } else {
                throw new UsernameNotFoundException("User " + username + " not exists");
            }
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
