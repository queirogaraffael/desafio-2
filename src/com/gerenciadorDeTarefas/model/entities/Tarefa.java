package com.gerenciadorDeTarefas.model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tarefa {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private String id;
	private String titulo;
	private String descricao;
	private LocalDate data;
	private Boolean status;

	public Tarefa() {
	}

	public Tarefa(String id, String titulo, String descricao, String data, Boolean status) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = LocalDate.parse(data, formatter);
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getDataString() {
		return data.format(formatter);
	}

	public void setData(String data) {

		this.data = LocalDate.parse(data, formatter);
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String retornaStatusFormatado() {
		if (status == true) {
			return "concluida";
		} else {
			return "inconcluida";
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Titulo: ").append(titulo).append("\n").append("Descricao: ").append(descricao).append("\n");

		if (data != null) {
			sb.append("Data: ").append(data.format(formatter)).append("\n");
		}

		sb.append("Status: ").append(retornaStatusFormatado());

		return sb.toString();

	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(data, other.data);
	}

}
