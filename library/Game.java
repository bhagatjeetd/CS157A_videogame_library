package library;

import java.util.Date;

/**
 * Game
 * Model class representing video game entity in system.
 * Encapsulates all attributes corresponding to Games table.
 */
public class Game {

    // Unique identifier matching Games.GameID
    private int id;

    // Title of game
    private String title;

    // Genre (e.g., Action, Adventure)
    private String genre;

    // ESRB rating (e.g., E, T, M)
    private String esrb;

    // Release date of game
    private Date releaseDate;

    // Publisher name
    private String publisher;

    // Base rental price per day
    private double baseRentalPrice;

    /**
     * Constructor
     * Initializes all fields for Game instance.
     *
     * @param id         Unique game ID
     * @param title      Game title
     * @param genre      Game genre
     * @param esrb       ESRB rating
     * @param releaseDate Release date
     * @param publisher  Publisher name
     * @param price      Base rental price
     */
    public Game(int id, String title, String genre, String esrb,
                Date releaseDate, String publisher, double price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.esrb = esrb;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.baseRentalPrice = price;
    }

    /** @return game ID */
    public int getId() {
        return id;
    }

    /** @return game title */
    public String getTitle() {
        return title;
    }

    /** @return genre */
    public String getGenre() {
        return genre;
    }

    /** @return ESRB rating */
    public String getEsrb() {
        return esrb;
    }

    /** @return release date */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /** @return publisher */
    public String getPublisher() {
        return publisher;
    }

    /** @return base rental price */
    public double getBaseRentalPrice() {
        return baseRentalPrice;
    }
}
