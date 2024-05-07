package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.TraitSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

import java.util.Random;

public class WeaponTraitsAdder {
    public static void addTrait(Weapon weapon) {
        TraitSet trait = chooseTrait();
        switch (trait) {
            case Mighty: {
                weapon.power = weapon.power * 5 / 3;
                break;
            }
            case Weak: {
                weapon.power = weapon.power * 3 / 4;
                break;
            }
            case Heavy: {
                weapon.weight *= 2;
                break;
            }
            case Featherweight: {
                weapon.weight /= 2;
                break;
            }

            case Potent: {
                weapon.strengthScale += 0.2;
                break;
            }
            case Elegant: {
                weapon.dexterityScale += 0.2;
                break;
            }
            case Delicate: {
                weapon.intellectScale += 0.2;
                break;
            }
            case Blessed: {
                weapon.faithScale += 0.2;
                break;
            }

            case Flimsy: {
                weapon.strengthScale -= 0.2;
                break;
            }
            case Blunt: {
                weapon.dexterityScale -= 0.2;
                break;
            }
            case Thoughtless: {
                weapon.intellectScale -= 0.2;
                break;
            }
            case Unholy: {
                weapon.faithScale -= 0.2;
                break;
            }

            case Basic: {
                return;
            }
        }
        weapon.setName(trait + " " + weapon.name);
    }

    private static TraitSet chooseTrait() {
        int choice = new Random().nextInt(20) + 1;
        switch (choice) {
            case 1:
                return TraitSet.Elegant;
            case 2:
                return TraitSet.Blunt;
            case 3:
                return TraitSet.Blessed;
            case 4:
                return TraitSet.Delicate;
            case 5:
                return TraitSet.Weak;
            case 6:
                return TraitSet.Flimsy;
            case 7:
                return TraitSet.Heavy;
            case 8:
                return TraitSet.Featherweight;
            case 9:
                return TraitSet.Mighty;
            case 10:
                return TraitSet.Potent;
            case 11:
                return TraitSet.Thoughtless;
            case 12:
                return TraitSet.Unholy;
        }
        return TraitSet.Basic;
    }
}
