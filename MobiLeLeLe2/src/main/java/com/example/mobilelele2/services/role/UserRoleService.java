package com.example.mobilelele2.services.role;

import com.example.mobilelele2.domain.dtoS.model.UserRoleModel;
import com.example.mobilelele2.domain.dtoS.veiw.UserRoleViewDto;
import com.example.mobilelele2.services.init.DataBaseInitServiceService;

import java.util.List;

public interface UserRoleService extends DataBaseInitServiceService {
    List<UserRoleViewDto> getAll();

    List<UserRoleModel> findAllRoles();

    UserRoleModel findRoleByName(String name);
}
