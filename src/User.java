// User class represents a basic user with a username and password
public class User {

    // Private instance variables to store the user's username and password
    private String username;
    private String password;

    // Creating a  constructor to initialize the username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method to retrieve the user's username
    public String getUsername() {
        return username;
    }

    // Getter method to retrieve the user's password
    public String getPassword() {
        return password;
    }

}

