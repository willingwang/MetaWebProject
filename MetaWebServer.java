import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MetaWebServer {

	private static final String WEBROOT = System.getProperty("user.dir")+System.getProperty("file.separator")+"Webcontent";

	//private static final String WEBROOT = System.getProperty("user.dir");
	
	public static void main(String [] args) throws IOException {
		System.out.println(WEBROOT);
		System.out.println(System.getProperty("user.dir"));
		ServerSocket serverSocket = new ServerSocket(10000);
		System.out.println("MetaWebServer is running on port: " + serverSocket.getLocalPort());
		while(true) {
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			Request request = new Request(input);	
			request.parse();
			Response response = new Response(request, output);
			new Thread(new RequestHandler(request,response)).start();
		}
	}

	public static String getWebroot() {
		return WEBROOT;
	}
}
