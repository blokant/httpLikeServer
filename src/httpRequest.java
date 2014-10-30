import java.util.List;

/**
 * Created by trenkinan on 30.10.14.
 */
public class httpRequest {
    private enum HttpMethods {
        GET, POST, PUT, UPDATE, DELETE
    };
    private final HttpMethods httpMethod;
    public httpRequest(List<String> rawData) {
        String methodString = rawData.get(0).substring(0,3);
        if(methodString.equals("GET")) {
            httpMethod = HttpMethods.GET;
        }
        else {
            httpMethod = HttpMethods.POST;
        }
    }


}