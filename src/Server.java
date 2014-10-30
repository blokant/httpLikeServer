
import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Слушает указанный в первом параметре порт и выводит все приходящие на него
 * сообщения на консоль.
 */
public class Server   {

    private static final int DEFAULT_PORT = 8080;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server() {
               /* Если аргументы отсутствуют, порт принимае значение поумолчанию */
        int port = DEFAULT_PORT;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Порт занят: " + port);
            System.exit(-1);
        }

    }
    public void start() {

    }

    public void run() {

        while (true) {


            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Ошибка при подключении к порту: " + DEFAULT_PORT);
                System.exit(-1);
            }
      /*
       * Теперь можно получить поток ввода, в который помещаются сообщения от
       * клиента
       */

      /*
       * В этой реализации сервер будет без конца читать поток и выводить его
       * содержимое на консоль
       */

            /*
            System.out.println("After getting the mesage");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            try {
                writer.write("HTTP/1.1 200 OK\n" + "\n" +
                        "<html>Hello world</html>\n");
                writer.flush();
            } catch (IOException ioe) {

            }

            */
        }


      }

}


