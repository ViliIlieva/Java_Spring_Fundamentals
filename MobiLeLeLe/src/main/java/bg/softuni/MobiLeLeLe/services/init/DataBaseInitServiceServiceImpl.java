package bg.softuni.MobiLeLeLe.services.init;

import bg.softuni.MobiLeLeLe.services.role.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataBaseInitServiceServiceImpl implements DataBaseInitServiceService {
    private final UserRoleService roleService;

    @Autowired
    public DataBaseInitServiceServiceImpl(UserRoleService roleService) {
        this.roleService = roleService;
    }


    @PostConstruct//след като се вдигне цялото нещо искам да кажа вдигни базата
    public void postConstruct() {
        dbInit();
    }

    @Override
    public void dbInit() {
        if (isDbInit()) {
            this.roleService.dbInit();
        }
    }

    @Override
    public boolean isDbInit() {
        return this.roleService.isDbInit();
    }
}