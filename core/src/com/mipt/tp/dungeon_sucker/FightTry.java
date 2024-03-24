package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.IronChestplate;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Club;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Character;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Creatures.Rat;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Creature;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class FightTry {
  public static void aboba() {
    DungeonMasster DM = new DungeonMasster();
    Character vasya = new Character(15, 9999, "Vasya", DM);
    IronChestplate chestplate = new IronChestplate();
    chestplate.getObtained(vasya);
    DM.add(vasya.weight, vasya);
    vasya.getDamaged(10, "Physical");
    Creature[] entities = new Creature[4];
    for (int i = 0; i < 4; ++i) {
      entities[i] = new Rat(10,
          10,
          10,
          true,
          new HauntedRoom(new IntVector2(), new Texture("room.png"), new Creature[0], DM),
          "rat" + i);
    }
    System.out.println("Passed String 23");
    HauntedRoom room = new HauntedRoom(new IntVector2(), new Texture("room.png"), entities, DM);
    Club club = new Club(1, "club");
    System.out.println(vasya.weight);
    vasya.addWeapon(club);
    vasya.moveToRoom(room);
    System.out.println(DM.orderOfSteps.size());
    System.out.println(vasya.isAlive);
    DM.move();
  }
}
