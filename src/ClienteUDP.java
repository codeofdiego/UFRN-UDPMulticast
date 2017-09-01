
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ClienteUDP {
	public static void main(String[] args) throws Exception {
		int porta = 5555;
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		InetAddress group = InetAddress.getByName("239.1.1.66");
		MulticastSocket socketCliente = new MulticastSocket(porta);
		socketCliente.joinGroup(group);
		// Lê mensagem
		System.out.println("Aguardando timestamp: ");

		while (true) {
			byte[] dadosRecebidos = new byte[100];

			// Constri pacote de retorno e espera pela resposta
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
			socketCliente.receive(pacoteRecebido);
			
			// Extrai mensagem
			String mensagem = new String(dadosRecebidos);
			
			// Exibe mensagem 
			System.out.println("Timestamp recebido: " + mensagem);
						
		}
	}
}