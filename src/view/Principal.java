package view;

import controller.Banco;

public class Principal {

	public static void main(String[] args) {

		for (int codConta = 1; codConta <= 20; codConta++) {
			int sorte = (int)(Math.random() * 2);
			double valor = (Math.random() * 201); 
			Banco banco = new Banco(codConta, valor, sorte);
			banco.start();
		}

	}

}
