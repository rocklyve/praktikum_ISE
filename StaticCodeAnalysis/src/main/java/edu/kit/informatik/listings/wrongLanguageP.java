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
