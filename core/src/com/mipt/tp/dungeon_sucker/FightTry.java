package com.mipt.tp.dungeon_sucker;

import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

import java.util.LinkedList;
import java.util.Scanner;

public class FightTry {
    public static void aboba() {
//        DungeonMasster DM = DungeonMasster.getInstance();
//        Character vasya = new Character(15, 9999, "Vasya", DM , new InventoryWindow);
//        IronChestplate chestplate = new IronChestplate();
//        chestplate.getObtained(vasya);
//        DM.add(vasya.weight, vasya);//  public Vampire(int health, int power, int weight, boolean isHostile, Room place) {
//        Creature[] entities = new Creature[1];
//        for (int i = 0; i < 1; ++i) {
//            entities[i] = new Rat(1, 1, 1,
//                    true,
//                    new Room(new IntVector2(), new Texture("room.png"), new Creature[0], DM));
//        }
//        Room room = new Room(new IntVector2(), new Texture("room.png"), entities, DM);
//        room.threatLevel = 3;
//        Club club = new Club(1, 1, "club", RaritySet.Common);
//        club.getObtained(vasya);
//        new HammerMastery().getObtained(vasya);
//        vasya.setActiveWeapon(club);
//        vasya.weight = 1000;
//        vasya.moveToRoom(room);
//        DM.move();
    }

    public static void generateWeapons() {
        Scanner in = new Scanner(System.in);
        in.nextInt();
        LinkedList<Weapon> weapons = new LinkedList<>();
        for (int i = 0; i < 1000; ++i) {
            Weapon weapon = new WeaponGenerator().generateWeapon(1);
            weapons.add(weapon);
            System.out.println(weapon.name);
        }
        System.out.println("lol");
    }
}
