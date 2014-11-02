import java.util.ArrayList;
import java.util.List;

/**
 * Created by trenkinan on 25.10.14.
 */
public class Main {
    public static void main(String[] args) {
        httpResponse response = new httpResponse("404", null);
        System.out.println(new String(response.getBytes()));

    }
}
