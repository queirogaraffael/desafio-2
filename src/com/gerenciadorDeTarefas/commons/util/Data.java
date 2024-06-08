package com.gerenciadorDeTarefas.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.gerenciadorDeTarefas.model.entities.Tarefa;

public class Data {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static boolean verificaFormatoData(String stringData) {
		try {
			LocalDate.parse(stringData, formatter);

			return true;
		} catch (DateTimeParseException erro) {
			return false;
		}

	}

	public static void solicitarDataValida(Tarefa tarefa) {
		Object[] opcoes = { "Sim", "Nao" };

		int opcaoData = JOptionPane.showOptionDialog(null, "Deseja adicionar uma data especifica ?", "Data",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

		if (opcaoData == 0) {
			String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
			boolean formatoAprovado = Data.verificaFormatoData(dataString);

			while (!formatoAprovado) {
				JOptionPane.showMessageDialog(null, "Formato da data incorreto. Tente novamente!");
				dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
				formatoAprovado = Data.verificaFormatoData(dataString);
			}
			tarefa.setData(dataString);

		} else {
			tarefa.setData(LocalDate.now().format(formatter));
		}
	}

	public static boolean dataJaPassou(String dataString) {
		try {
			LocalDate data = LocalDate.parse(dataString, formatter);
			LocalDate hoje = LocalDate.now();
			return data.isBefore(hoje);
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
