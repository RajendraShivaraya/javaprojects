package securityjwtauthentication.repository;

import org.springframework.stereotype.Repository;
import securityjwtauthentication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users, String>
{
}
