package com.gerenciadorDeTarefas.application;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.gerenciadorDeTarefas.constantes.OpcoesMenu;
import com.gerenciadorDeTarefas.entities.Tarefa;
import com.gerenciadorDeTarefas.services.ServicoTarefa;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		try (Scanner scanner = new Scanner(System.in)) {
			int opcao = 0;

			Set<Tarefa> tarefas = new HashSet<Tarefa>();

			do {

				System.out.println(Menu());

				try {
					opcao = scanner.nextInt();

					if (opcao == OpcoesMenu.ADICIONAR) {

						System.out.println("Digite a tarefa que você quer adicionar:");
						scanner.nextLine();
						String tarefaParaAdicionar = scanner.nextLine().toLowerCase();

						if (ServicoTarefa.jaContemTarefa(tarefas, tarefaParaAdicionar)) {
							System.out.println("Já contém essa tarefa. Tente outra!");
						} else {
							ServicoTarefa.adicionarTarefa(tarefas, tarefaParaAdicionar);
							System.out.println("Tarefa adicionada com sucesso!");
						}
					} else if (opcao == OpcoesMenu.VISUALIZAR) {
						System.out.println("Visualizador de tarefas: ");
						ServicoTarefa.visualizarTarefa(tarefas);

					} else if (opcao == OpcoesMenu.REMOVER) {
						System.out.println("Digite a tarefa que você quer remover:");
						scanner.nextLine();
						String tarefaParaRemover = scanner.nextLine();

						if (tarefas.isEmpty()) {
							System.out.println("Adicione primeiro uma tarefa para poder remover.");
						} else if (!ServicoTarefa.jaContemTarefa(tarefas, tarefaParaRemover)) {
							System.out.println(
									"Atividade já não constava no gerenciador de tarefas. Tente novamente com uma tarefa existente.");
						} else {
							ServicoTarefa.removerTarefa(tarefas, tarefaParaRemover.toLowerCase());
							System.out.println("Tarefa removida com sucesso!");
						}

					} else if (opcao == OpcoesMenu.SAIR) {
						System.out.println("Fim do programa.");
					}

					else {
						System.out.println("Valor inválido, por favor digite uma opção válida");
					}

				} catch (InputMismatchException e) {
					System.out.println("Erro: Por favor, digite um número inteiro válido.");
					scanner.next(); // limpa o buffer, se não fica em loop
				}
				Thread.sleep(2000);
			} while (opcao != OpcoesMenu.SAIR);
		}

	}

	public static String Menu() {

		StringBuilder menu = new StringBuilder();
		menu.append("Bem-vindo à Lista de Tarefas\n\n");
		menu.append("Menu:\n");
		menu.append("1. Adicionar Tarefa\n");
		menu.append("2. Visualizar Tarefas\n");
		menu.append("3. Remover Tarefa\n");
		menu.append("4. Sair\n");
		menu.append("\nEscolha uma opção: ");
		return menu.toString();

	}

}
