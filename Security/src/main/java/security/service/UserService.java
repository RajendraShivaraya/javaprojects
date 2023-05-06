package security.service;

import security.datamodel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public boolean createUser(Users user)
    {
        try
        {
            userRepository.save(user);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public Users getUserById(String id)
    {
        Users userDetails;
        try
        {
            Optional<Users> optionalUser =  userRepository.findById(id);
            if (optionalUser.isPresent())
            {
                userDetails = optionalUser.get();
                return userDetails;
            }
            else
                return null;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public List<Users> getUsers()
    {
        try
        {
            return userRepository.findAll();
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /*
    public boolean authenticateUser(String userId, String pwd)
    {
        Users userDetails;
        try
        {
            Optional<Users> optionalUser =  userRepository.findById(userId);
            if (optionalUser.isPresent())
            {
                userDetails = optionalUser.get();
                return true;
            }
            else
                return false;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

     */
}
