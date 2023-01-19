package bg.softuni.MobiLeLeLe.model.entity;

import bg.softuni.MobiLeLeLe.model.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
