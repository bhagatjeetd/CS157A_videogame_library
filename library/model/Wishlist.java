package library.model;

import java.util.Date;

/**
 * Wishlist
 * Represents a customer's wishlist entry.
 */
public class Wishlist {
    private int wishlistId;    // PK
    private int customerId;    // FK → Customer.CustomerID
    private int gameId;        // FK → Game.GameID
    private Date addedDate;    // When it was added

    /**
     * @param wishlistId unique ID
     * @param customerId who owns it
     * @param gameId     wished game
     * @param addedDate  date added
     */
    public Wishlist(int wishlistId, int customerId,
                    int gameId, Date addedDate) {
        this.wishlistId = wishlistId;
        this.customerId = customerId;
        this.gameId     = gameId;
        this.addedDate  = addedDate;
    }

    public int getWishlistId() { return wishlistId; }
    public int getCustomerId() { return customerId; }
    public int getGameId()     { return gameId;     }
    public Date getAddedDate() { return addedDate;  }

    public void setWishlistId(int id)       { this.wishlistId = id;      }
    public void setCustomerId(int cid)      { this.customerId = cid;     }
    public void setGameId(int gid)          { this.gameId     = gid;     }
    public void setAddedDate(Date date)     { this.addedDate  = date;    }
}
