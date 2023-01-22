package bg.softuni.MobiLeLeLe.services.role;

import bg.softuni.MobiLeLeLe.model.dtoS.veiw.UserRoleViewDto;
import bg.softuni.MobiLeLeLe.model.entities.UserRole;
import bg.softuni.MobiLeLeLe.model.enums.Role;
import bg.softuni.MobiLeLeLe.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();//за да напълним базата
    }


    @Override
    //ще създаде две роли
    public void dbInit() {
        if (!isDbInit()) {//проверява дали вече има създадени
            List<UserRole> roles = new ArrayList<>();//създавам лист роли и го иницирам

            roles.add(new UserRole().setRole(Role.USER));//използвам сет метода който ми връща целия клас
            //без да използвам конструктор
            roles.add(new UserRole().setRole(Role.ADMIN));

            this.roleRepository.saveAllAndFlush(roles);//добавяме ги в репозиторито
        }
    }

    @Override//ако големината е по голяма от 0, значи вече има роли
    //викаме метода dbInit
    public boolean isDbInit() {
        return this.roleRepository.count() > 0;
    }


    public List<UserRoleViewDto> getAll() {
        return this.roleRepository.findAll()//от репозиторито.намери всички
                .stream()//за всеки го мапни към ДТО-то за ролята
                .map(r -> this.modelMapper.map(r, UserRoleViewDto.class))
                .collect(Collectors.toList());//съхрани го в лист и ми го върни
    }
}