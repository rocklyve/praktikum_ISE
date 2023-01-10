public EncounterResult executeEncounter(Player player, Monster monster) {
    // The monster attacks first...
    player.takeDamage(monster.getAttackStrength());
    if (player.isDead()) {
        return EncounterResult.PLAYER_DIED;
    }

    // .. and then the player attacks
    monster.takeDamage(player.getAttackStrength());
    if (monster.isDead()) {
        return EncounterResult.MONSTER_DIED;
    }

    return EncounterResult.TIE;
}
