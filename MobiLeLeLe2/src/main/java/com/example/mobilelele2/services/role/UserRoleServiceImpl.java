package com.example.mobilelele2.services.role;

import com.example.mobilelele2.domain.dtoS.model.UserRoleModel;
import com.example.mobilelele2.domain.dtoS.veiw.UserRoleViewDto;
import com.example.mobilelele2.domain.enitities.UserRole;
import com.example.mobilelele2.domain.enums.Role;
import com.example.mobilelele2.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();
    }


    @Override
    public void dbInit() {
        if (!isDbInit()) {
            List<UserRole> roles = new ArrayList<>();

            roles.add(new UserRole().setRole(Role.USER));
            roles.add(new UserRole().setRole(Role.ADMIN));

            this.roleRepository.saveAllAndFlush(roles);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.roleRepository.count() > 0;
    }

    public List<UserRoleViewDto> getAll() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleViewDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<UserRoleModel> findAllRoles() {//открива всички роли
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleModel findRoleByName(String name) {//откриваме ролята по име
        return this.modelMapper.map(this.roleRepository.findByRole(name)
                .orElseThrow(NoSuchElementException::new),
                UserRoleModel.class);
    }
}