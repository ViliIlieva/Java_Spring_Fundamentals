package com.example.exam.models.entity;

import com.example.exam.models.Enum.ConditionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, name = "condition_name")
    private ConditionEnum conditionName;

    @Column(nullable = false)
    private String description;

    public Condition(ConditionEnum conditionName) {
        this.conditionName = conditionName;
    }
}
