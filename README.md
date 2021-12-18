# Multiplicação de Matrizes

## Autor

* Aroldo Felix Pereira Junior (junioraroldo37@gmail.com)

## Objetivos

* Implementar soluções de multiplicação de matrizes de maneira sequencial e concorrente utilizando Threads
* Realizar comparação entre soluções em relação ao tempo de execução

## Metodologia

* Foram utilizadas matrizes quadradas com dimensões que vão de 4x4 até 2048x2048, todas com potência de base 2
* Para verificar mais fielmente os tempos de execução, para cada matriz 2<sup>x</sup>, {x ∈ Z | 2 <= x <= 11}, foram
  rodados 20 testes.

## Execução

O programa possui um único método Main. Este aceita argumentos como `4 S` ou `4 C`.

* O primeiro argumento indica a ordem da matriz quadrada (que é também o nome do arquivo salvo em resources)
    * Ex.: A4x4.txt e B4x4.txt
* O segundo argumento indica se a execução vai ser Sequencial (S) ou Concorrente(C)