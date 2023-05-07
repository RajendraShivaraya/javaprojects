package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.datamodel.Roles;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>
{
    public Roles findByName(String userRole);
}
