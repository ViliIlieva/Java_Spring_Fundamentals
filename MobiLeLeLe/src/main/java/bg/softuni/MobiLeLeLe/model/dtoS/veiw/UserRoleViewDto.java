package bg.softuni.MobiLeLeLe.model.dtoS.veiw;

import bg.softuni.MobiLeLeLe.model.enums.Role;

//това дто ще се използва само за визуализация
public class UserRoleViewDto {
    private String role;

    public String getRole() {
        return role;
    }

    public UserRoleViewDto setRole(String role) {
        this.role = role;
        return this;
    }
}
