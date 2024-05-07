package com.gerenciadorDeTarefas.application;

import com.gerenciadorDeTarefas.controller.MenuPrincipalTarefaController;

public class Main {
	public static void main(String[] args) {

		MenuPrincipalTarefaController controller = new MenuPrincipalTarefaController();

		controller.exibirMenuPrincipal();
	}
}
