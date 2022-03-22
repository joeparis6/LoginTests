import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;


public class LoginTests {

    //This hashmap will simulate a database for storing registered users
    private HashMap<String, String> registeredUsers;

    private String login(String username, String password) {

        if (username.equals("") || password.equals("")) {
            //empty field
            return "Please fill out Username and Password";
        }

        if (registeredUsers.containsKey(username)) {
            String lookupPassword = registeredUsers.get(username);
            if (!lookupPassword.equals(password)) {
                //incorrect password
                return "Wrong password";
            }
        }
        else {
            //invalid username
            return "User does not exist";
        }

        return "Login Successful";
    }

    private String signUp(String username, String password) {

        if (username.equals("") || password.equals("")) {
            //empty field
            return "Please fill out Username and Password";
        }

        if (registeredUsers.containsKey(username)) {
           //user already exists
            return "This user already exists";
        }
        else {
            registeredUsers.put(username, password);
            return "Sign-up Successful";
        }

    }

    @BeforeEach
    void setup() {
        registeredUsers = new HashMap<String, String>();
        registeredUsers.clear();

        signUp("ScoobyDoo", "scoobysnacks");
        signUp("FredFlintstone", "yabbadabbadoo");

    }

    @Test
    void loginTest1() {
        String response = login("ScoobyDoo", "scoobysnacks");
        Assertions.assertEquals("Login Successful", response);
        String response2 = login("FredFlintstone", "yabbadabbadoo");
        Assertions.assertEquals("Login Successful", response2);
    }

    @Test
    void loginTest2() {
        String response = login("ScoobyDoo", "scoobysnax");
        Assertions.assertEquals("Wrong password", response);
    }

    @Test
    void loginTest3() {
        String response = login("FredFlintstone", "scoobysnacks");
        Assertions.assertEquals("Wrong password", response);
    }

    @Test
    void loginTest4() {
        String response = login("FredFlintstone", "");
        Assertions.assertEquals("Please fill out Username and Password", response);
    }

    @Test
    void loginTest5() {
        String response = login("", "yabbadabbadoo");
        Assertions.assertEquals("Please fill out Username and Password", response);
    }

    @Test
    void loginTest6() {
        String response = login("", "");
        Assertions.assertEquals("Please fill out Username and Password", response);
    }

    @Test
    void loginTest7() {
        String response = login("BugsBunny", "scoobysnacks");
        Assertions.assertEquals("User does not exist", response);
    }

    @Test
    void signupTest1() {
        String response = signUp("ScoobyDoo", "scoobysnacks");
        Assertions.assertEquals("This user already exists", response);
    }

    @Test
    void signupTest2() {
        String response = signUp("ScoobyDoo", "scoobysnax");
        Assertions.assertEquals("This user already exists", response);
    }

    @Test
    void signupTest3() {
        String response = signUp("BugsBunny", "scoobysnacks");
        Assertions.assertEquals("Sign-up Successful", response);
    }

    @Test
    void signupTest4() {
        String response = signUp("BugsBunny", "");
        Assertions.assertEquals("Please fill out Username and Password", response);
    }

    @Test
    void signupTest5() {
        String response = signUp("", "scoobysnacks");
        Assertions.assertEquals("Please fill out Username and Password", response);
    }

    @Test
    void signupTest6() {
        String response = signUp("", "");
        Assertions.assertEquals("Please fill out Username and Password", response);
    }

}
