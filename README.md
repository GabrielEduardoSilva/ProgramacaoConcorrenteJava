
# AT2/N1 - ATIVIDADE PRÁTICA COLETIVA
> Experimento de uso de threads em Java.

## Visão geral

O experimento consiste em um projeto em Java que lê 320 arquivos 320 contendo temperaturas diárias de 320 cidades de diversos países. Após a leitura de cada arquivo, o programa deve calcular as temperaturas média, máxima e mínima por mês, para cada cidade. Cada versão do experimento consiste na execução de 10 repetições/rodadas de um algoritmo composto por:
* Leitura dos dados de cada cidade;
* Processamento do dado recebido, cálculo das temperaturas média, mínima e máxima por cidade por mês;
* Armazenamento dos dados gerado na memória;
* Exibição dos dados gerados no console.

O objetivo do experimento é computar o tempo de execução de cada rodada e, ao fim das 10 rodadas, calcular o tempo médio para execução(soma dos tempos de cada rodada dividido por 10).

No gráfico abaixo estão os resultados do experimento:

![image](https://github.com/user-attachments/assets/abb34658-2e7d-4b49-8e2d-3917896480cc)

O gráfico demonstra que o uso de threads melhora a eficiência do algoritmo. Porém, o uso excessivo de threads causa uma sobrecarga no gerenciamento dessas tarefas que resulta em uma lentidão e aumento do tempo necessário para a sua execução. Ou seja, usar muitas threads para tarefas simples não tem um bom custo-benefício, já que o sistema passa mais tempo gerenciando as threads do que executando a tarefa em si. Esse gerenciamento inclui alocar recursos para cada thread, sincronizar as threads para evitar que elas tentem acessar o mesmo recurso simultaneamente, e a troca de contexto entre threads, que envolve interromper uma thread para permitir que outra tenha acesso à CPU.


## Configurações

Experimento realizado no IntelliJ IDEA 2024.1.1 (Ultimate Edition)

Configurações do projeto:

<img width="698" alt="image" src="https://github.com/user-attachments/assets/7e4e14ee-9b16-490e-91b4-fd18dd5493bc">

Configurações do sistema operacional:

<img width="292" alt="image" src="https://github.com/user-attachments/assets/933dc0be-d8d9-40db-a4cf-86a3645ab11f">


## Arquivo PDF do relatório

[Relatório Prática Coletiva.pdf](https://github.com/user-attachments/files/17098161/Relatorio.Pratica.Coletiva.pdf)

