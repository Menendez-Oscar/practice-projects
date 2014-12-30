import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Scanner;

/**
* @author Oscar Menendez
* CS3750, homework 6
*/
public class Client {

	public static void main(String[] args) {
		String ip;
		int port;
		if (args.length >= 2) {
			ip = args[0];
			port = Integer.parseInt(args[1]);
		} else {
			System.out.println("Enter server's ip:");
			ip = new Scanner(System.in).next();
			System.out.println("Enter server's port number:");
			port = new Scanner(System.in).nextInt();
		}
		try {
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
					.getDefault();
			SSLSocket sslSocket = (SSLSocket) sslsocketfactory.createSocket(ip,
					port);
			System.out.println(socketInfo(sslSocket));

			InputStreamReader inputstreamreader = new InputStreamReader(
					System.in);
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);

			OutputStream outputstream = sslSocket.getOutputStream();
			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(
					outputstream);
			BufferedWriter serverWriter = new BufferedWriter(outputstreamwriter);

			BufferedReader serverReader = new BufferedReader(
					new InputStreamReader(sslSocket.getInputStream()));

			String fromUser = null;
			String fromServer = null;
			while ((fromServer = serverReader.readLine()) != null) {

				System.out.println(fromServer);
				fromUser = bufferedreader.readLine();
				serverWriter.write(fromUser + '\n');
				serverWriter.flush();

				if (fromServer.equals("Add more users? (yes or any for no)")) {
					if(fromUser.equalsIgnoreCase("yes")){
						continue;
					}
					else{
						break;
					}
				}
			}
			sslSocket.close();
			bufferedreader.close();
			serverWriter.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
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
}
