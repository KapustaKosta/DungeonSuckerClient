package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfFortune extends Artifact {

  public RingOfFortune() {
    super();
    this.description = "Makes you much more lucky. Definitely";   //not really
    this.effectiveness = 0; //not a useful line, just a reminder. IT IS USELESS!
    this.weight = 1; // so it's even harmful
  }
}