package com.example.exam.init;

import com.example.exam.models.Enum.ConditionEnum;
import com.example.exam.models.entity.Condition;
import com.example.exam.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DBInit implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    @Autowired
    public DBInit(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (this.conditionRepository.count() == 0) {
            Arrays.stream(ConditionEnum.values())
                    .forEach(conditionName -> {
                        Condition condition = new Condition ();
                        condition.setConditionName (conditionName);
                        switch (conditionName) {
                            case EXCELLENT -> condition.setDescription ("In perfect condition");
                            case GOOD -> condition.setDescription ("Some signs of wear and tear or minor defects");
                            case ACCEPTABLE -> condition.setDescription ("The item is fairly worn but continues to function properly");
                        }
                        conditionRepository.save(condition);
                    });
        }
    }
}
