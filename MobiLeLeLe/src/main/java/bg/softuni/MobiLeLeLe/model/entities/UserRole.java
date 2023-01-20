package bg.softuni.MobiLeLeLe.model.entities;

import bg.softuni.MobiLeLeLe.model.enums.Role;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@NoArgsConstructor
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Role role;

    public Role getRole() {
        return role;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }
}
