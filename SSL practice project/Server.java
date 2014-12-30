import java.io.*;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.util.*;

/**
* @author Oscar Menendez
* homework 6
*/
public class Server extends Thread {
	SSLSocket clientSocket = null;

	public Server(SSLSocket socket) {
		super("echo server");
		clientSocket = socket;
		System.out.println(socketInfo(socket));
	}

	public static void main(String[] args) throws IOException {
		int port;
		if (args.length >= 1) {
			port = Integer.parseInt(args[0]);
		} else {
			System.out.println("Please enter port number");
			port = new Scanner(System.in).nextInt();
		}
		SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
				.getDefault();
		SSLServerSocket serverSocket = null;
		boolean listening = true;

		try {
			serverSocket = (SSLServerSocket) sslserversocketfactory
					.createServerSocket(port);

		} catch (IOException e) {
			System.err.println("Could not listen on port: 4567.");
			System.exit(-1);
		}

		while (listening) {
			new Server((SSLSocket) serverSocket.accept()).start();
		}

		serverSocket.close();
	}

	private static String socketInfo(SSLSocket s) {
		String info = "Peer host is " + s.getSession().getPeerHost()
				+ "\nCypher suite is " + s.getSession().getCipherSuite()
				+ "\nProtocol is " + s.getSession().getProtocol()
				+ "\nSession ID is " + s.getSession().getId().toString()
				+ "\nThe creation time of this session is "
				+ s.getSession().getCreationTime()
				+ "\nThe last accessed time of this session is"
				+ s.getSession().getLastAccessedTime();

		return info;
	}

	@Override
	public void run() {

		try {
			PrintWriter cSocketOut = new PrintWriter(
					clientSocket.getOutputStream(), true);
			BufferedReader cSocketIn = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));

			String fromClient;

			cSocketOut.println("User Name:");
			cSocketOut.flush();
			FileWriter file = null;
			while ((fromClient = cSocketIn.readLine()) != null) {
				file = new FileWriter(new File(fromClient + ".txt"));
				file.write("User Name: " + fromClient);
				System.out.println("User Name: " + fromClient);

				cSocketOut.println("Full Name:");
				cSocketOut.flush();
				fromClient = cSocketIn.readLine();
				file.write("F\null Name: " + fromClient);
				System.out.println("Full Name: " + fromClient);

				cSocketOut.println("Address:");
				cSocketOut.flush();
				fromClient = cSocketIn.readLine();
				file.write("\nAddress: " + fromClient);
				System.out.println("Address: " + fromClient);

				cSocketOut.println("Phone number:");
				cSocketOut.flush();
				fromClient = cSocketIn.readLine();
				file.write("\nPhone number: " + fromClient);
				System.out.println("Phone number: " + fromClient);

				cSocketOut.println("Email address:");
				cSocketOut.flush();
				fromClient = cSocketIn.readLine();
				file.write("\nEmail address: " + fromClient);
				System.out.println("Email address: " + fromClient);

				cSocketOut.println("Add more users? (yes or any for no)");
				cSocketOut.flush();
				fromClient = cSocketIn.readLine();
				System.out.println("User's choice: " + fromClient);

				if (fromClient.equalsIgnoreCase("yes")) {
					cSocketOut.println("User Name:");
					file.close();
				} else {
					break;
				}
			}

			cSocketOut.close();
			cSocketIn.close();
			file.close();
			clientSocket.close();

		} catch (IOException e) {
			System.out.println("Exception " + e);
		}
	}
}
