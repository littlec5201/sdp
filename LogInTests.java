import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scz5487
 */
public class NewTests {
    
    public NewTests() {
        /*Establishing a new LogIn class*/
        LogIn test = new LogIn();
        
        /*Adding some temporary user profiles to test login methods with*/
        test.add("ethan", "password");
        test.add("callum", "1234");
        test.add("rudy", "5678");
        test.add("gavin", "goodpassword");
        
        /*Username check test with correct username*/
        assertTrue(test.checkUsername("ethan"));
        /*Username check test with incorrect username*/
        assertFalse(test.checkUsername("Goofy"));
        
        
        /*Password check test with correct username and correct password*/
        assertTrue(test.checkPassword("ethan", "password"));
        /*Password check test with correct username and incorrect password*/
        assertFalse(test.checkPassword("rudy", "woopsies"));
        
        
        /*Username and password check test with correct username and password*/
        assertTrue(test.checkUsernameAndPassword("gavin", "goodpassword"));
        /*Username and password check test with incorrect username and password*/
        assertFalse(test.checkUsernameAndPassword("callem", "12345"));
        
    }
    
    public static void main(String[] args) {
        NewTests unitTests = new NewTests();
    }
}
