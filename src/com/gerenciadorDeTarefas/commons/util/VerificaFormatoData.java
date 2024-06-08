package com.gerenciadorDeTarefas.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VerificaFormatoData {

	public static boolean verificaFormatoData(String stringData, String formatoData) {
		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoData);
			LocalDate.parse(stringData, formato);

			return true;
		} catch (DateTimeParseException e) {
			return false;
		}

	}

}
