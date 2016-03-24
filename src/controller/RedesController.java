package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController {


	public String so() {
		String so = System.getProperty("os.name");
		return so;
	}

	public String ip(String so) {
		String comando = "";
		if (so.contains("Windows"))
			comando = "ipconfig";

		if (so.contains("Linux"))
			comando = "ifconfig";

		StringBuffer escritor = new StringBuffer();

		try {
			Process p = Runtime.getRuntime().exec(comando);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			escritor.append("Configuraçoes de ip: \n");
			while (linha != null) {
				if (linha.contains("Ethernet")) {
					escritor.append(linha + "\n");
				}
				if (linha.contains("IPv4")) {
					escritor.append(linha + "\n");
				}
				linha = buffer.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return escritor.toString();
	}

	public String ping(String caminho, String so) {

		String comando = "";

		if (so.contains("Windows"))
			comando = "ping /n 10 " + caminho;

		if (so.contains("Linux"))
			comando = "ping -c 10 " + caminho;

		StringBuffer escritor = new StringBuffer();
		String vetor[] = new String[4];
		double ping = 0;

		try {
			Process p = Runtime.getRuntime().exec(comando);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			
			int linux = 0;
			while (linha != null) {
				if (linha.contains("tempo") || linha.contains("time")){
					escritor.append(linha + "\n");
					vetor = linha.split("=");
					//System.out.println(vetor[1]);
					if (so.contains("Windows"))
						ping = ping + Integer.parseInt(vetor[2].substring(0, vetor[2].lastIndexOf("m")));
					if (so.contains("Linux")) {
						if (linux < 10)
							ping = ping + Double.parseDouble(vetor[3].substring(0, vetor[3].lastIndexOf(" m")));
						linux++;
					}
				}
				linha = buffer.readLine();
			}
			ping /= 10;
			escritor.append("Tempo médio de ping para " + caminho + " :\n" + ping + "ms");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return escritor.toString();
	}

}
