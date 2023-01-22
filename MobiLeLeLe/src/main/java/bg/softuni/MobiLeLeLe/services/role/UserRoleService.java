package bg.softuni.MobiLeLeLe.services.role;


import bg.softuni.MobiLeLeLe.model.dtoS.veiw.UserRoleViewDto;
import bg.softuni.MobiLeLeLe.services.init.DataBaseInitServiceService;

import java.util.List;

public interface UserRoleService extends DataBaseInitServiceService {
    List<UserRoleViewDto> getAll();
}
