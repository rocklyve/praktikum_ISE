package edu.kit.informatik.listings;

import static edu.kit.informatik.listings.Player.findPlayerById;

public class wrongLanguageN {
    /**
     * Prints the status of the player to the console.
     *
     * @param player The id of the player
     */
    public void printPlayerStatus(int playerId) {
        // Falls der Spieler unbekannt ist wird null zurückgegeben
        Player player = findPlayerById(playerId);

        if (player == null) {
            System.err.println("Error, unbekannter Spieler");
        } else {
            System.out.println("Player " + playerId + ": " + player.getHP() + "HP");
        }
    }
}