package bg.softuni.MobiLeLeLe.model.dtoS.model;

import bg.softuni.MobiLeLeLe.model.enums.Role;

public class UserRoleDto extends BaseEntityDTO {

    private Role role;

    public Role getRole() {
        return role;
    }

    public UserRoleDto setRole(Role role) {
        this.role = role;
        return this;
    }
}