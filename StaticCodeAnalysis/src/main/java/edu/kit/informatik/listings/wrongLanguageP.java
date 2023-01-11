package edu.kit.informatik.listings;

import static edu.kit.informatik.listings.Player.findPlayerById;

public class wrongLanguageP {
    /**
     * Prints the status of the player to the console.
     * @param player The id of the player
     */
    public void printPlayerStatus(int playerId) {
        // Returns null if the player is unknown
        Player player = findPlayerById(playerId);

        if (player == null) {
            System.err.println("Error, unknwon player");
        } else {
            System.out.println("Player " + playerId + ": " + player.getHP() + "HP");
        }
    }
}

class Player {
    public static Player findPlayerById(int playerId) {
        return new Player();
    }

    public String getHP() {
        return "10";
    }

    public void takeDamage(int damage) {

    }

    public int getAttackStrength() {
        return 1;
    }

    public boolean isDead() {
        return false;
    }
}
