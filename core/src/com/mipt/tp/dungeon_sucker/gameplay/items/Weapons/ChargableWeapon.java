package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class ChargableWeapon extends Weapon {
    public int charges;
    public int[] chargesForSkill;

    public ChargableWeapon(int numberOfSkills) {
        super(numberOfSkills);
    }

    public void use(Room room) {
        this.recount();
        getSkillIndexUntilPredicate(args -> this.skills[args[0]].use(room),
                value -> this.chargesForSkill[value] <= this.charges);
    }

    public void useByCreature(Room room, int indexOfSkillToBeUsed) {
        this.recount();
        int index = new Random().nextInt(this.skills.length);
        while (this.chargesForSkill[index] > this.charges) {
            index = new Random().nextInt(this.skills.length);
        }
        this.skills[index].use(room);
        this.recount();
    }
}
