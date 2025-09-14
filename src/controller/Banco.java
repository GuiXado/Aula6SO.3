package controller;

import java.util.concurrent.Semaphore;

public class Banco extends Thread {

	private static Semaphore saqueMutex = new Semaphore(1);
	private static Semaphore depositoMutex = new Semaphore(1);
	private int codConta, sorte;
	private static double saldo = 1000;
	private double valor;

	public Banco(int codConta, double valor, int sorte) {
		this.codConta = codConta;
		this.valor = valor;
		this.sorte = sorte;
	}

	public void run() {
		if (sorte == 0) {
			deposito();			 
		} else {
			saque();
		}
	}

	private void deposito() {
		try {
			depositoMutex.acquire();
			System.out.println("#" + codConta + " Deposito: " + String.format("%.2f", valor) + " Saldo antes: " + String.format("%.2f", saldo));
			//System.out.printf("#%d Deposito: %.2f Saldo antes: %.2f\n", codConta, valor, saldo);
			saldo += valor;
			System.out.println("#" + codConta + " Novo saldo: " + String.format("%.2f", saldo));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			depositoMutex.release();
		}

	}

	private void saque() {
		try {
			saqueMutex.acquire();
			System.out.println("#" + codConta + " Saque: " + String.format("%.2f", valor) + " Saldo antes: " + String.format("%.2f", saldo));
			if (valor <= saldo) {
				saldo -= valor;
			} else {
				System.out.println("#" + codConta + " Saldo insuficiente!");
			}
			System.out.println("#" + codConta + " Novo saldo: " + String.format("%.2f", saldo));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			saqueMutex.release();
		}
	}

}
