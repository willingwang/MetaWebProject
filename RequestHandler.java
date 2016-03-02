import java.io.IOException;


public class RequestHandler implements Runnable {

	Request request;
	Response response;
	
	public RequestHandler(Request request, Response response) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (request.isGetMethod()) {
			try {
				System.out.println("Begin to response!");
				response.response();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.responseOtherMethod();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
