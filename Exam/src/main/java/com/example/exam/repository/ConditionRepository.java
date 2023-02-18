package com.example.exam.repository;

import com.example.exam.models.Enum.ConditionEnum;
import com.example.exam.models.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Condition findByConditionName(ConditionEnum name);
}
