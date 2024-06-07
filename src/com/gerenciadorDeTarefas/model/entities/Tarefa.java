package com.gerenciadorDeTarefas.model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {

	private String id;
	private String titulo;
	private String descricao;
	private LocalDate data;
	private Boolean status;

	public Tarefa() {
	}

	public Tarefa(String id, String titulo, String descricao, LocalDate data, Boolean status) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
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

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getstatus() {
		return status;
	}

	public void setstatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Titulo: ").append(titulo).append("\n").append("Descricao: ").append(descricao);

		if (data != null) {
			sb.append("\n").append("Data: ").append(data.toString()).toString();
		}
		
		sb.append("\n").append("Status: ").append(status);

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
