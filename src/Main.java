import java.util.ArrayList;
import java.util.List;

/**
 * Created by trenkinan on 25.10.14.
 */
public class Main {
    public static void main(String[] args) {
       // httpFileHelper hfh = new httpFileHelper("test.html");
       // System.out.println(new String(hfh.buildResponse().getBytes()));
        Server server = new Server();
        server.start();

    }
}
