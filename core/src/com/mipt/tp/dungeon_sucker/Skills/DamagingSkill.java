package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Scanner;

public class DamagingSkill extends Skill {
    protected Damage damage;
    protected Weapon weapon;
    protected DamageTypeSet type;

    public DamagingSkill() {
        this.identifier = 0;
    }

    public int chooseVictimToAttack(Room room) {
        Scanner in = new Scanner(System.in);
        System.out.println("choose enemy to punch");
        for (int i = 0; i < (room).hostileEntities.length; ++i) {
            if (room.hostileEntities[i] != null) {
                if (room.hostileEntities[i].isAlive) {
                    System.out.println(i + 1 + ": " + ((room.hostileEntities[i])).name + ": "
                            + ((room).hostileEntities[i]).health + " hp");
                }
            }
        }
        return in.nextInt() - 1;
    }
}
