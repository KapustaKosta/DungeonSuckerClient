package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.LinkedList;

public class SkillSet extends Skill {
    private LinkedList<Skill> skills;

    public SkillSet() {
        this.skills = new LinkedList<>();
    }

    public void AddSkill(Skill skill) {
        this.skills.add(skill);
        this.identifier = this.skills.getFirst().identifier;
    }

    public void use(Room room) {
        for (Skill skill : this.skills) {
            skill.use(room);
        }
    }
}
