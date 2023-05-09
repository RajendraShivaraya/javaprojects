package securityjwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securityjwtauthentication.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>
{
    public Roles findByName(String userRole);
}
