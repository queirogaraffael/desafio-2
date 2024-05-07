package com.gerenciadorDeTarefas.entities;

import java.time.LocalDate;

public class Tarefa {
	private String titulo;
	private String descricao;
	private LocalDate data;
	private Boolean statusConcluida;

	public Tarefa() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getStatusConcluida() {
		return statusConcluida;
	}

	public void setStatusConcluida(Boolean statusConcluida) {
		this.statusConcluida = statusConcluida;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (data == null) {
			return sb.append(titulo).append("\n").append(descricao).toString();
		} else {
			return sb.append(titulo).append("\n").append(descricao).append("\n").append(data.toString()).toString();
		}

	}

}
