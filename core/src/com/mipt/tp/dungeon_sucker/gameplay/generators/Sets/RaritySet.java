package com.mipt.tp.dungeon_sucker.gameplay.generators.Sets;

public enum RaritySet {
    Poor, Common, Uncommon, Rare, Epic, Legendary;

    public static int getID(RaritySet rarity) {
        switch(rarity){
            case Poor: return 0;
            case Common:return 1;
            case Uncommon:return 2;
            case Rare:return 3;
            case Epic:return 4;
            case Legendary:return 5;
        }
        return 0;
    }
    public static RaritySet getByID(int ID){
        switch (ID){
            case 0: return Poor;
            case 1: return Common;
            case 2: return Uncommon;
            case 3: return Rare;
            case 4: return Epic;
            case 5: return Legendary;
        }
        return Poor;
    }
}
