package library.model;

import java.util.Date;

/**
 * Customer
 * Represents a registered customer with contact and login details.
 */
public class Customer {
    private int id;                   // PK
    private String name;              // Full name
    private String email;             // Email address
    private String phone;             // Phone number
    private String address;           // Postal address
    private String username;          // Login username
    private String passwordHash;      // Hashed password
    private Date registrationDate;    // When they registered

    /**
     * Full constructor for Customer.
     */
    public Customer(int id,
                    String name,
                    String email,
                    String phone,
                    String address,
                    String username,
                    String passwordHash,
                    Date registrationDate) {
        this.id               = id;
        this.name             = name;
        this.email            = email;
        this.phone            = phone;
        this.address          = address;
        this.username         = username;
        this.passwordHash     = passwordHash;
        this.registrationDate = registrationDate;
    }


    /** @return the customer ID */
    public int getId() { return id; }

    /** @return the full name */
    public String getName() { return name; }

    /** @return the email address */
    public String getEmail() { return email; }

    /** @return the phone number */
    public String getPhone() { return phone; }

    /** @return the postal address */
    public String getAddress() { return address; }

    /** @return the login username */
    public String getUsername() { return username; }

    /** @return the stored password hash */
    public String getPasswordHash() { return passwordHash; }

    /** @return the registration timestamp */
    public Date getRegistrationDate() { return registrationDate; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
}
