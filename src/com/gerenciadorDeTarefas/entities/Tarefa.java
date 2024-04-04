package com.gerenciadorDeTarefas.entities;

import java.util.Objects;

public class Tarefa {
	private String tarefa;

	public Tarefa() {
	}

	public Tarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tarefa);
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
		return Objects.equals(tarefa, other.tarefa);
	}

	@Override
	public String toString() {
		return tarefa;
	}
	
	

}
