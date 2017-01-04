/**
 * Created by Rubel on 04-Jan-17.
 */
import Automator.User;

public class Main {
    public static void main(String[] args){
        User user = new User("051203043", "051203043");
        user.login();
        user.updateDetails();
        user.debug();
    }
}
