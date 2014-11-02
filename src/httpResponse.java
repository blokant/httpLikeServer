/**
 * Created by trenkinan on 30.10.14.
 */
public class httpResponse {
    private String responseCode;
    private int contentLength;
    private byte[] data;
    public httpResponse(String responseCode, int contentLength, byte[] data) {
        this.responseCode = responseCode;
        this.contentLength = contentLength;
        this.data = data;
        if(responseCode.equals("404")) {
            data = new String("Sorry, this page was not found").getBytes();
        }
    }
}
