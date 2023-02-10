package edu.kit.informatik.listings;

public class todoN {
    // TODO auto-generated method stub
    public EncounterResult executeEncounter(Player player, Monster monster) {
        // Get the attack strength of the monster and deal a corresponding amount of damage to the player
        player.takeDamage(monster.getAttackStrength());
        if (player.isDead()) { // If the player has died...
            // ... return the variant "PLAYER_DIED" of the ennum "EncounterResult"
            return EncounterResult.PLAYER_DIED; // Checkstyle forces me to use the ugly underscore in "PLAYER_DIED"
        }
        // TODO include the damage multiplier of the player's weapon
        monster.takeDamage(player.getAttackStrength());
        if (monster.isDead()) {
            // System.out.println("Monster died");
            return EncounterResult.MONSTER_DIED;
        }
        return EncounterResult.TIE; // FIXME choose a better name than "TIE"
    }
}