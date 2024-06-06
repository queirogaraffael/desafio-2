package com.gerenciadorDeTarefas.model.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.bson.types.ObjectId;

public class Tarefa {

	private ObjectId id;
	private String titulo;
	private String descricao;
	private LocalDate data;
	private Boolean statusConcluida;

	public Tarefa() {
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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

	public Boolean getStatusConcluida() {
		return statusConcluida;
	}

	public void setStatusConcluida(Boolean statusConcluida) {
		this.statusConcluida = statusConcluida;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (data != null) {

			return sb.append("Titulo: ").append(titulo).append("\n").append("Descricao: ").append(descricao)
					.append("\n").append("Data: ").append(data.toString()).toString();

		} else {
			return sb.append("Titulo: ").append(titulo).append("\n").append("Descricao: ").append(descricao).toString();
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo);
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
		return Objects.equals(titulo, other.titulo);
	}

}
