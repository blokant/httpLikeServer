import java.util.ArrayList;
import java.util.List;

/**
 * Created by trenkinan on 25.10.14.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        List<String> li = new ArrayList<String>();
        li.add("GET /");
        li.add("sdfsdg");
        httpRequest request = new httpRequest(li);

    }
}
