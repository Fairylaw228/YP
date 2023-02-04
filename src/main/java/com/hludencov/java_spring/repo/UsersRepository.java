package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Group;
import com.hludencov.java_spring.models.Role;
import com.hludencov.java_spring.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findByLoginContains(String title);
    List<User> findByLogin(String title);
    default List<User> findActive() {
        return this.findByActive(true);
    }
    List<User> findByActive(boolean active);
    List<User> findByGroupsContaining(Group group);
    List<User> findByRoles(Role roles);
//    List<User> findByRoleS(Role[] roles);
    List<User> findByRolesIn(Set<Role> roles);


}
