
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class ServidorUDP {
	public static void main(String[] args) throws Exception {
		int porta = 5555;
		System.setProperty("java.net.preferIPv4Stack", "true");

		InetAddress group = InetAddress.getByName("239.1.1.66");
		MulticastSocket socketServidor = new MulticastSocket();
		socketServidor.joinGroup(group);
		
		System.out.println("Serviço de MULTICAST UDP rodando...");
		 
		while(true) {
			// Calcula hora em Milis
			long hora = System.currentTimeMillis();
			String mensagem = hora + "";
			
			// Debug
			System.out.println("Enviando timestamp:" + mensagem);
			
			// Constrói pacote de retorno
			byte[] mensagemTimestamp = mensagem.getBytes();
			DatagramPacket pacoteRetorno = new DatagramPacket(mensagemTimestamp, mensagemTimestamp.length, group, porta);
			
			// Envia dados de volta
			socketServidor.send(pacoteRetorno);
			
			TimeUnit.SECONDS.sleep(5);
		}
	}
}