package library.model;

/**
 * OrderItem
 * Represents a single game within a rental order.
 */
public class OrderItem {
    private int orderItemId;  // PK
    private int orderId;      // FK → Order.OrderID
    private int gameId;       // FK → Game.GameID
    private double rentalFee; // Fee charged for this game

    /**
     * @param orderItemId unique ID
     * @param orderId     parent order
     * @param gameId      rented game
     * @param rentalFee   fee for this item
     */
    public OrderItem(int orderItemId, int orderId,
                     int gameId, double rentalFee) {
        this.orderItemId = orderItemId;
        this.orderId     = orderId;
        this.gameId      = gameId;
        this.rentalFee   = rentalFee;
    }

    public int getOrderItemId() { return orderItemId; }
    public int getOrderId()     { return orderId;     }
    public int getGameId()      { return gameId;      }
    public double getRentalFee(){ return rentalFee;  }

    public void setOrderItemId(int id)     { this.orderItemId = id;    }
    public void setOrderId(int oid)        { this.orderId     = oid;   }
    public void setGameId(int gid)         { this.gameId      = gid;   }
    public void setRentalFee(double fee)   { this.rentalFee   = fee;   }
}
