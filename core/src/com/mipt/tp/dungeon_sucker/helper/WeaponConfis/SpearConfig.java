package com.mipt.tp.dungeon_sucker.helper.WeaponConfis;

public class SpearConfig {
    public static final double BASE_DEXTERITY_SCALE = 0.5;
    public static final double BASE_STRENGTH_SCALE = 0.3;
    public static final double PERCENT_OF_ELEMENTAL_DAMAGE = 0.5;

    public static final double FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS = 0.3;
    public static final double SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS = 0.3;
    public static final double THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS = 0.5;

    public static final double COEFFICIENT_TO_CRIT_IF_NO_CRIT = 0.75;
    public static final int NUMERATOR_OF_CHANCE_TO_CRIT = 1;
    public static final int DIVIDER_OF_CHANCE_TO_CRIT = 3;
    public static final int CRIT_MULTIPLIER = 2;

    public static final double COEFFICIENT_TO_EXISTING_STATS_IF_POOR = 1 / 1.25;
    public static final double COEFFICIENT_TO_EXISTING_STATS_IF_UNCOMMON = 1.2;
    public static final double COEFFICIENT_TO_EXISTING_STATS_IF_RARE = 1.4;
    public static final double COEFFICIENT_TO_EXISTING_STATS_IF_EPIC = 2;
    public static final double COEFFICIENT_TO_EXISTING_STATS_IF_LEGENDARY = 3;
    public static final int VIGOR_SCALE_IF_LEGENDARY = 1;
}
