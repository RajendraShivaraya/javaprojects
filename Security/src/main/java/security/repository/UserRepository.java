package security.repository;

import org.springframework.stereotype.Repository;
import security.datamodel.Users;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users, String>
{
}
