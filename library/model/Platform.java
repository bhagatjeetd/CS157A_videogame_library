package library.model;

/**
 * Platform
 * Represents a gaming platform (e.g., PS5, Xbox Series X).
 */
public class Platform {
    private int platformId;    // PK
    private String name;       // Platform name

    /**
     * Constructor
     * @param platformId unique ID
     * @param name       platform name
     */
    public Platform(int platformId, String name) {
        this.platformId = platformId;
        this.name = name;
    }

    /** @return platform ID */
    public int getPlatformId() { return platformId; }
    /** @return platform name */
    public String getName()      { return name; }

    /** @param platformId ID to set */
    public void setPlatformId(int platformId) { this.platformId = platformId; }
    /** @param name       Name to set */
    public void setName(String name)          { this.name = name; }
}
