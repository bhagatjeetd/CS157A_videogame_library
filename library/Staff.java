package model;

public class Staff {
    private int id;
    private String name;
    private String email;
    private String username;
    private String role;

    public Staff(int id, String name, String email, String username, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}
