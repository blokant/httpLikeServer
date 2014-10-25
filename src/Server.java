
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Слушает указанный в первом параметре порт и выводит все приходящие на него
 * сообщения на консоль.
 */
public class Server {

    public static void main(String[] args) {
      /* Если аргументы отсутствуют, порт принимае значение поумолчанию */
        int port = DEFAULT_PORT;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Порт занят: " + port);
            System.exit(-1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Ошибка при подключении к порту: " + port);
            System.exit(-1);
        }
      /*
       * Теперь можно получить поток ввода, в который помещаются сообщения от
       * клиента
       */
        InputStream in = null;
        OutputStream out = null;
        try {
            in = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();
        } catch (IOException e) {
            System.out.println("Не удалось получить поток ввода.");
            System.exit(-1);
        }
      /*
       * В этой реализации сервер будет без конца читать поток и выводить его
       * содержимое на консоль
       */
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String ln = null;
        try {
            while ((ln = reader.readLine()) != null && ln.length() != 0) {
                System.out.println(ln + ": " + ln.length());
                System.out.flush();

                //break;
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении сообщения.");
            System.exit(-1);
        }
        System.out.println("After getting the mesage");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        try {
            writer.write("HTTP/1.1 200 OK\n" + "\n"  +
                    "<html>Hello world</html>\n");
            writer.flush();
        } catch (IOException ioe) {

        }
    }

    private static final int DEFAULT_PORT = 8080;
}

