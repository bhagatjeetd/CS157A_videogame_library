package library.model;

import java.util.Date;

/**
 * Staff
 * Represents an employee (admin or clerk) who processes orders.
 */
public class Staff {
    private int id;               // PK
    private String name;          // Full name
    private String email;         // Email address
    private String role;          // "Admin" or "Clerk"
    private String username;      // Login username
    private String passwordHash;  // Hashed password
    private Date hireDate;        // When they were hired

    /**
     * Full constructor for Staff.
     */
    public Staff(int id,
                 String name,
                 String email,
                 String role,
                 String username,
                 String passwordHash,
                 Date hireDate) {
        this.id           = id;
        this.name         = name;
        this.email        = email;
        this.role         = role;
        this.username     = username;
        this.passwordHash = passwordHash;
        this.hireDate     = hireDate;
    }

    /** @return the staff ID */
    public int getId() { return id; }

    /** @return the full name */
    public String getName() { return name; }

    /** @return the email address */
    public String getEmail() { return email; }

    /** @return their role */
    public String getRole() { return role; }

    /** @return the login username */
    public String getUsername() { return username; }

    /** @return the stored password hash */
    public String getPasswordHash() { return passwordHash; }

    /** @return the hire date */
    public Date getHireDate() { return hireDate; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
}
