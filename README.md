# Projeto de Cálculo de Notas para Android com Jetpack Compose

Este é um projeto simples, desenvolvido para atividade de matérias envolvendo código mobile em Kotlin, que utiliza Programação Orientada a Objetos e Jetpack Compose.

## Funcionalidades

Cadastro do Nome do Aluno: Permite a inserção do nome completo do aluno.
Lançamento de Notas: Campo para inserir três notas parciais (TP1, TP2, TP3).
Cálculo da Média: A média aritmética das três notas é calculada e exibida automaticamente.

Avaliação de Desempenho: Exibe o status final do aluno com base na média:
Reprovado: Média < 6.0
Aprovado: Média ≥ 6.0 e ≤ 9.0
Ótimo Aproveitamento: Média > 9.0
Limpeza de Dados: Um botão para limpar todos os campos e recomeçar.

## Tecnologias Utilizadas

Linguagem: Kotlin
Interface Gráfica: Jetpack Compose;
Gerenciamento de Estado: Funções reativas do Compose, como remember e mutableStateOf;
Estrutura de Dados: O projeto utiliza uma data class para modelar a entidade Aluno, com uma MutableList<Double> para gerenciar as notas.

### Desenvolvido por:

Caio Emanuel Bronescheki de Moraes | DSM-5.