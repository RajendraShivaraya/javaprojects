package security.customsecurity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import security.datamodel.Roles;
import security.repository.RolesRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
public class MyUserDetails implements UserDetails
{
    String userName;
    String password;
    /*
    List   userRoles;

    @Autowired
    RolesRepository rolesRepository;

     */

    public MyUserDetails(String userId)
    {
        this.userName = userId;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        //return userRoles;
    }

    public void addUserRole(String roleName)
    {
        //userRoles.add(new SimpleGrantedAuthority(roleName));
    }
    // If we have multiple roles for user
    public void initUserRoles()
    {
        /*
        List<Roles> loggedUserRoles = rolesRepository.findByName(this.userName);
        for ( Roles role: loggedUserRoles)
        {
            userRoles.add(new SimpleGrantedAuthority(role.getName()));
        }
        */
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
