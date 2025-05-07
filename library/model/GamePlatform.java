package library.model;

/**
 * GamePlatform
 * Join entity linking Games to Platforms (many-to-many).
 */
public class GamePlatform {
    private int gameId;      // FK → Game.GameID
    private int platformId;  // FK → Platforms.PlatformID

    /**
     * @param gameId     ID of the game
     * @param platformId ID of the platform
     */
    public GamePlatform(int gameId, int platformId) {
        this.gameId = gameId;
        this.platformId = platformId;
    }

    /** @return game ID */
    public int getGameId()     { return gameId; }
    /** @return platform ID */
    public int getPlatformId() { return platformId; }

    /** @param gameId ID to set */
    public void setGameId(int gameId)           { this.gameId = gameId; }
    /** @param platformId ID to set */
    public void setPlatformId(int platformId)   { this.platformId = platformId; }
}
