package mentees.jamilxt.borrowmybook.service;

import lombok.RequiredArgsConstructor;
import mentees.jamilxt.borrowmybook.exception.custom.NotFoundException;
import mentees.jamilxt.borrowmybook.mapper.RoleMapper;
import mentees.jamilxt.borrowmybook.model.domain.Role;
import mentees.jamilxt.borrowmybook.model.dto.request.CreateRoleRequest;
import mentees.jamilxt.borrowmybook.persistence.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoleService {
    public static final String ROLE_NOT_FOUND = "Role not found";
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public Page<Role> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toDomain);
    }

    public Role getRole(UUID id) {
        var roleEntity = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        return roleMapper.toDomain(roleEntity);
    }

    public void createRole(CreateRoleRequest request) {
        var roleEntity = roleMapper.toEntity(request);
        roleRepository.save(roleEntity);
    }

    public void updateRole(CreateRoleRequest request) {
        var roleEntity = roleRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        roleEntity.setName(request.getName());
        roleEntity.setDescription(request.getDescription());
        roleRepository.save(roleEntity);
    }

    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }
}
