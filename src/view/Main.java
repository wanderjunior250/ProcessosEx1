package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rede = new RedesController();
		int opc = 0;


		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - ipconfig \n2- ping \n9 - Finalizar"));

			switch (opc) {
			case 1:
				JOptionPane.showMessageDialog(null, rede.ip(rede.so()));
				break;
			case 2:
				JOptionPane.showMessageDialog(null,
						rede.ping(JOptionPane.showInputDialog("Insira o site a ser 'pingado'"), rede.so()));
				break;
			case 9:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");

			}
		}

	}

}