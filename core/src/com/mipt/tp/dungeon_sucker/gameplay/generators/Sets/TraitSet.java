package com.mipt.tp.dungeon_sucker.gameplay.generators.Sets;

public enum TraitSet {
  Mighty, // power = power * 5 / 3;
  Weak, // power = power * 3 / 4
  Heavy, // weight = weight * 2
  Featherweight, // weight = weight / 2

  Potent, // strengthScale += 0.2
  Elegant, // dexterityScale += 0.2
  Delicate, // intelligenceScale += 0.2
  Blessed, // faithScale += 0.2

  Flimsy, // strengthScale += 0.2
  Blunt , // dexterityScale += 0.2
  Thoughtless, // intelligenceScale += 0.2
  Unholy, // faithScale += 0.2
  Basic // no buffs and/or debuffs;
}
