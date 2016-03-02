import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Response {

	private OutputStream output;
	private Request request;
	public Response(OutputStream output) {
		this.output = output;
	}
	
	public Response(Request request, OutputStream output) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.output = output;
	}
	
	
	private String getSeparator() {
		return System.getProperty("line.separator", "\n");
	}

	public void response() throws IOException {
//		System.out.println(request.getResourceUri());
		File file = new File(MetaWebServer.getWebroot(), request.getResourceUri());
//		System.out.println(file);				
		DataOutputStream dataOutput = new DataOutputStream(output);
//		System.out.println("run in response");
		if (file.exists()) {
			System.out.println("Resourse exists...Response begin to read file");
			dataOutput.writeBytes("HTTP/1.0 200 Document Follow" + getSeparator());
			String resourceFormat = request.getResourceUri();
			if (resourceFormat.endsWith(".jpg")) {
				dataOutput.writeBytes("Content-type:image/jpeg" + getSeparator());
			} 
			if (resourceFormat.endsWith(".jpg")) {
				dataOutput.writeBytes("Content-type:image/gif" + getSeparator());
			}
			int len = (int) file.length();
//			System.out.println(len);
			dataOutput.writeBytes("Content-Length:" + len + getSeparator());
			dataOutput.writeBytes(getSeparator());		
			FileInputStream readFile = new FileInputStream(file);
			byte [] bytes = new byte [len];
			readFile.read(bytes);
			dataOutput.write(bytes);
			readFile.close();
			request.input.close();
			dataOutput.close();
			System.out.println("Done........");
		} else {
//			System.out.println("File not found!");
			File fileNot = new File(MetaWebServer.getWebroot(), "404.html");
//			System.out.println(fileNot);
			dataOutput.writeBytes("HTTP/1.0 200 Document Follow" + getSeparator());
			FileInputStream readFile = new FileInputStream(fileNot);
			int len = (int) fileNot.length();
			dataOutput.writeBytes("Content-Length:" + len + getSeparator());
			dataOutput.writeBytes(getSeparator());
			byte [] bytes = new byte [len];
			readFile.read(bytes);
			dataOutput.write(bytes);
			readFile.close();
			request.input.close();
			dataOutput.close();
			System.out.println("Done........");		
		}
	}
	
	public void responseOtherMethod() throws IOException {		
		DataOutputStream dataOutput = new DataOutputStream(output);
		File methodNotSupport = new File(MetaWebServer.getWebroot(), "methodNotSupport.html");
//		System.out.println(methodNotSupport);
		dataOutput.writeBytes("HTTP/1.0 200 Document Follow" + getSeparator());
		FileInputStream readFile = new FileInputStream(methodNotSupport);
		int len = (int) methodNotSupport.length();
		dataOutput.writeBytes("Content-Length:" + len + getSeparator());
		dataOutput.writeBytes(getSeparator());
		byte [] bytes = new byte [len];
		readFile.read(bytes);
		dataOutput.write(bytes);
		readFile.close();
		request.input.close();
		dataOutput.close();
		System.out.println("Done........");		
	}

}
