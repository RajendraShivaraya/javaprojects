package sessionmanagement.model;

import org.springframework.stereotype.Repository;
import sessionmanagement.model.TestUsers;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TestUserRepository extends JpaRepository<TestUsers, String>
{
}
