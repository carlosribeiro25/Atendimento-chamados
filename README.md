# 📞 Sistema de Gerenciamento de Chamados – Central de Atendimento

## 📌 Sobre o projeto

Este projeto consiste em um sistema de gerenciamento de chamados para uma Central de Atendimento, desenvolvido em **Java**.

A aplicação simula o funcionamento de uma central de suporte técnico, permitindo cadastrar chamados, classificá-los como comuns ou urgentes e realizar o atendimento utilizando múltiplas threads.

O principal objetivo da atividade é aplicar conceitos fundamentais da programação orientada a objetos e de programação concorrente, simulando uma situação real de gerenciamento de solicitações de suporte técnico.

## 🎯 Objetivos

- Desenvolver um sistema de gerenciamento de chamados.
- Aplicar conceitos de Programação Orientada a Objetos (POO).
- Trabalhar com herança e sobrescrita de métodos.
- Utilizar coleções para armazenar e gerenciar chamados.
- Implementar tratamento de exceções para validar os dados.
- Aplicar conceitos de programação concorrente com Threads.
- Garantir a sincronização do atendimento entre diferentes atendentes.
- Implementar prioridade no atendimento de chamados urgentes.

## 🛠️ Tecnologias utilizadas

- **Java:** linguagem utilizada no desenvolvimento.
- **ArrayList:** armazenamento dos chamados cadastrados.
- **List:** interface utilizada para manipulação da coleção.
- **Runnable:** implementação das tarefas executadas pelas threads.
- **Thread:** execução simultânea dos atendentes.
- **Synchronized:** controle de acesso concorrente ao método responsável por selecionar o próximo chamado.

## 🧠 Conceitos aplicados

### 1. Programação Orientada a Objetos

O sistema foi desenvolvido utilizando classes e objetos para representar os elementos da central de atendimento.

As principais classes são:

| Classe | Responsabilidade |
|---|---|
| `Chamado` | Representa um chamado comum. |
| `ChamadoUrgente` | Representa um chamado com prioridade de atendimento. |
| `Central` | Gerencia os chamados e controla a fila de atendimento. |
| `Atendente` | Representa um funcionário responsável por atender os chamados. |
| `Main` | Executa e inicializa o sistema. |

### 2. Herança e Polimorfismo

A classe `ChamadoUrgente` herda da classe `Chamado`, reaproveitando seus atributos e métodos.

Além disso, o método `tipo()` é sobrescrito utilizando `@Override`, permitindo diferenciar os chamados comuns dos urgentes.

- Chamados comuns retornam `COMUM`.
- Chamados urgentes retornam `URGENTE`.

### 3. Encapsulamento

Os atributos da classe `Chamado` são privados (`private`), garantindo maior controle sobre o acesso e a manipulação das informações.

O acesso ao estado de atendimento é realizado por meio do método `isAtendido()`.

### 4. Tratamento de exceções

O construtor da classe `Chamado` realiza validações para impedir a criação de chamados inválidos.

São verificadas as seguintes condições:

- Número do chamado menor ou igual a zero.
- Descrição nula.
- Descrição vazia ou contendo apenas espaços.

Quando alguma dessas condições é identificada, é lançada uma exceção `IllegalArgumentException`.

### 5. Coleções

A classe `Central` utiliza uma coleção `ArrayList`, declarada por meio da interface `List`, para armazenar todos os chamados cadastrados.

Isso permite adicionar, percorrer e localizar os chamados que ainda não foram atendidos.

### 6. Programação concorrente (Threads)

O sistema utiliza duas threads para simular o trabalho simultâneo de dois atendentes:

- Ana;
- Leo.

A classe `Atendente` implementa a interface `Runnable`, permitindo que cada atendente execute sua própria rotina de atendimento.

Os métodos `start()` e `join()` são utilizados para iniciar as threads e aguardar a conclusão de suas execuções.

### 7. Sincronização

O método `proximo()` da classe `Central` utiliza a palavra-chave `synchronized`.

Isso impede que duas threads executem simultaneamente a seleção e a marcação do mesmo chamado, evitando que um chamado seja atribuído a mais de um atendente.

### 8. Prioridade de atendimento

O sistema implementa uma lógica de prioridade na qual os chamados urgentes são atendidos antes dos chamados comuns.

A seleção acontece em duas etapas:

1. A central percorre a lista procurando chamados urgentes ainda pendentes.
2. Caso não existam chamados urgentes disponíveis, procura chamados comuns pendentes.

Dessa forma, os chamados urgentes possuem prioridade sobre os demais.

## ⚙️ Funcionamento do sistema

Ao executar a aplicação, o sistema realiza as seguintes operações:

1. Cria uma instância da Central de Atendimento.
2. Cadastra seis chamados, sendo três comuns e três urgentes.
3. Realiza um teste de validação tentando criar um chamado com descrição vazia.
4. Captura a exceção gerada e apresenta uma mensagem de erro.
5. Exibe a lista inicial de chamados cadastrados.
6. Inicializa duas threads, representando os atendentes Ana e Leo.
7. Os atendentes solicitam novos chamados à central.
8. A central disponibiliza primeiro os chamados urgentes.
9. Após o atendimento dos urgentes, os chamados comuns são processados.
10. Quando não existem mais chamados pendentes, as threads encerram suas execuções.

## 📋 Chamados cadastrados

O sistema inicia com os seguintes chamados:

| Número | Descrição | Tipo |
|---|---|---|
| 1 | Computador sem internet | Comum |
| 2 | Servidor indisponível | Urgente |
| 3 | Mouse com defeito | Comum |
| 4 | Sistema de matrículas fora do ar | Urgente |
| 5 | Projetor falhando | Comum |
| 6 | Computador do Adm não está conectando à internet | Urgente |

## ▶️ Como executar o projeto

### Pré-requisitos

- Java JDK instalado.
- Uma IDE de desenvolvimento Java, como IntelliJ IDEA, Eclipse ou Visual Studio Code, ou um terminal com acesso ao compilador Java.

### 1. Clone o repositório

```bash
git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
```

### 2. Entre na pasta do projeto

```bash
cd SEU-REPOSITORIO
```

### 3. Compile o código

```bash
javac Main.java
```

### 4. Execute a aplicação

```bash
java Main
```

## 💻 Exemplo de saída

A saída pode variar na ordem dos atendentes, pois o sistema utiliza threads. Entretanto, os chamados urgentes devem ser disponibilizados antes dos comuns.

```text
Falha ao realizar chamado: O numero ou a descrição do chamado não podem estar vazio.

===
 LISTA DE CHAMADOS PARA A CENTRAL DE ATENDIMENTOS.
#1 [COMUM] Computador sem internet - pendente
#2 [URGENTE] Servidor indisponivel - pendente
#3 [COMUM] Mouse com defeito - pendente
#4 [URGENTE] Sistema de matriculas fora do ar - pendente
#5 [COMUM] Projetor falhando - pendente
#6 [URGENTE] Computador do Adm nao estar conectando a internet. - pendente

=== ORDEM DE ATENDIMENTO ===
Ana atendeu: #2 [URGENTE] Servidor indisponivel - atendido
Leo atendeu: #4 [URGENTE] Sistema de matriculas fora do ar - atendido
Ana atendeu: #6 [URGENTE] Computador do Adm nao estar conectando a internet. - atendido
Leo atendeu: #1 [COMUM] Computador sem internet - atendido
Ana atendeu: #3 [COMUM] Mouse com defeito - atendido
Leo atendeu: #5 [COMUM] Projetor falhando - atendido

Reinicie: sem banco, os chamados voltam ao estado inicial.
```

*Observação: a distribuição dos chamados entre Ana e Leo pode mudar a cada execução, devido à concorrência entre as threads. A ordem de prioridade, entretanto, é mantida no momento em que cada chamado é selecionado.*

## 🗂️ Estrutura do projeto

```text
📁 Central-de-Atendimento
 └── 📄 Main.java
```

O arquivo `Main.java` contém todas as classes necessárias para a execução da aplicação.

## 💾 Persistência de dados

O projeto não utiliza banco de dados ou qualquer mecanismo de persistência.

Os chamados são armazenados temporariamente em memória, utilizando uma coleção `ArrayList`.

Isso significa que, ao encerrar e executar novamente o programa, os chamados são recriados e retornam ao estado inicial, sem manter o histórico dos atendimentos anteriores.

## 🚀 Possíveis melhorias futuras

- Implementar banco de dados para persistência dos chamados.
- Criar uma interface gráfica para facilitar a utilização.
- Permitir o cadastro de novos chamados durante a execução.
- Implementar diferentes níveis de prioridade.
- Registrar o horário de abertura e conclusão dos chamados.
- Criar relatórios de produtividade dos atendentes.
- Permitir a identificação de cada atendente responsável pelo chamado.

## 📚 Considerações finais

O desenvolvimento desta atividade possibilitou colocar em prática conceitos importantes da linguagem Java, especialmente Programação Orientada a Objetos, tratamento de exceções, coleções e programação concorrente.

A implementação da prioridade de atendimento e da sincronização entre threads demonstra como diferentes recursos da linguagem podem ser combinados para resolver problemas que acontecem em situações reais.

O projeto também reforça a importância do controle de acesso a recursos compartilhados em aplicações concorrentes, garantindo que os chamados sejam distribuídos corretamente entre os atendentes.

---

**Desenvolvido como atividade prática de programação em Java.**

