# Gerenciador de Tarefas
O Gerenciador de Tarefas é um programa desenvolvido em Java que permite aos usuários adicionar, visualizar, modificar, marcar como concluída, desmarcar como concluída, ordenar e remover tarefas. Utilizando o MongoDB como banco de dados para armazenar as tarefas, o programa oferece uma interface gráfica simples e intuitiva para facilitar o gerenciamento de suas atividades diárias.

## Pré-requisitos

### Antes de utilizar o Gerenciador de Tarefas, certifique-se de ter instalado:
* Java Development Kit (JDK) para compilar e executar o código Java.
* Um servidor MongoDB configurado e em execução para armazenar as tarefas.

### Instalação e Execução
* Clone este repositório ou baixe o código-fonte do Gerenciador de Tarefas.
* Certifique-se de ter o JDK instalado e configurado corretamente em seu sistema.
* Inicie o servidor MongoDB.
* Navegue até o diretório do projeto e compile o código-fonte.

## Utilização
Ao iniciar o programa, uma interface gráfica será exibida com as seguintes opções de menu:
* Adicionar Tarefa: Permite adicionar uma nova tarefa ao sistema.
* Visualizar Tarefas não Concluídas: Exibe uma lista de todas as tarefas pendentes.
* Visualizar Tarefas Concluídas: Exibe uma lista de todas as tarefas concluídas.
* Marcar Tarefa como Concluída: Permite marcar uma tarefa como concluída.
* Desmarcar Tarefa como Concluída: Permite desmarcar uma tarefa previamente concluída.
* Modificar Tarefa: Permite modificar os detalhes de uma tarefa existente.
* Ordenar por: Permite ordenar as tarefas por data de vencimento.
* Remover Tarefa: Remove uma tarefa do sistema.
* Sair: Encerra o programa.

## Arquitetura MVC
O Gerenciador de Tarefas segue o padrão de arquitetura Modelo-Visão-Controlador (MVC), onde:
* Modelo (Model): Responsável pela manipulação dos dados das tarefas e interação com o banco de dados MongoDB.
* Visão (View): Interface gráfica do programa, implementada utilizando a biblioteca Swing e JOptionPane.
* Controlador (Controller): Gerencia as interações do usuário com a aplicação e coordena as operações entre a visão e o modelo.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests, relatar problemas ou propor novos recursos.

## Licença
Este projeto está licenciado sob a Licença MIT.
