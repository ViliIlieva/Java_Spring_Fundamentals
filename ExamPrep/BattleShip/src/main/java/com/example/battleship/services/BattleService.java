package com.example.battleship.services;

import com.example.battleship.domain.dtos.StartBattleDTO;
import com.example.battleship.domain.entities.Ship;
import com.example.battleship.repositories.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

    private final ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDTO attackData) {
        //намираме двата кораба които са избрани от формата, като подаваме данните им и търсим по техните ID
        Optional<Ship> attackerOpt = this.shipRepository.findById((long) attackData.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById((long) attackData.getDefenderId());

        //ако не открива такива кораби хвърли грешка
        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        //създаваме двата кораба
        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        //атакуващия намаля здравето на дефендара със неговия пауър
        long newDefenderHealth = defender.getHealth() - attacker.getPower();

        //ако падне на 0 или под нея премахвам от базата този кораб
        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defender);
        } else {
            //ако не просто сетваме новот здраве на дефендъра и го сейфаме в базата
            defender.setHealth(newDefenderHealth);
            this.shipRepository.save(defender);
        }
    }
}
