package mentees.jamilxt.borrowmybook.service;


import mentees.jamilxt.borrowmybook.entity.Role;
import mentees.jamilxt.borrowmybook.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }


    public Set<Role> specificRole(String roleName) {
        List<Role> roleList = roleRepository.findAll();
        Set<Role> roles = new HashSet<>();
        for (Role r : roleList){
            if (r.getName().equals(roleName)){
                roles.add(r);
            }
        }
        return roles;
    }


}
