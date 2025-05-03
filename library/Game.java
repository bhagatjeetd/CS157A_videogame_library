package library;

import java.util.Date;

public class Game {
    private int id;
    private String title, genre, esrb, publisher;
    private Date releaseDate;
    private double baseRentalPrice;

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

    // getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getEsrb() { return esrb; }
    public Date getReleaseDate() { return releaseDate; }
    public String getPublisher() { return publisher; }
    public double getBaseRentalPrice() { return baseRentalPrice; }

    // setters omitted for brevity
}
