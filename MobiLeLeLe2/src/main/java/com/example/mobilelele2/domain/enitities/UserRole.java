package com.example.mobilelele2.domain.enitities;


import com.example.mobilelele2.domain.enums.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

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
