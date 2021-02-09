Trabalho realizado por Bruno Silva(up201503818) e Diogo Lourenço(up201508214)

Programas criados no sistema operativo Linux Ubuntu 16.04.4 LTS
Utilizada linguagem Java versão 1.8.0_151
SWI-Prolog version 7.6.4 for amd64


Problema 1:

Prolog

1 - Para iniciar o programa é preciso abrir prolog desde o terminal ("swipl").

2 - Tendo Prolog aberto no terminal para compilar e executar o programa deve escrever "[triplan].".

3 - Use a função flight(Origem, Destino, Dia, Flight_Num, Dep_time, Arr_time) para saber os dias em que existe um voo direto entre as duas cidades dadas.

 Exemplo: "flight(edinburgh, london, Day, _, _, _)."  

4 - Use a função route(Origem, Destino, Dia, Rota) para encontrar uma rota de voos entre as duas cidades dadas.

 Exemplo: "route(edinburgh, zurich, th, Route)."

5 - Use a função plan(Cidade1, Cidade2, Cidade3, Origem) para calcular uma rota de voos partindo desde a cidade Origem uma terça-feira, visitando as cidades 1,2 e 3 e voltando para Origem uma sexta-feira.

 Exemplo: "plan(milan, ljubljana, zurich, london)."

Problema 2

Java:

1 - Para executar o programa primeiro deve compilar o arquivo Phrases.java, escrevendo "javac Phrases.java" no terminal.

2 - Para executar o programa já compilado escreva "java Phrases" no terminal.

3 - Uma vez executado o programa basta dar como input a frase que se quer analisar.

4 - O programa dara como output a frase com a divisão gramatical feita.

Prolog

1 - Para iniciar o programa é preciso abrir prolog desde o terminal ("swipl").

2 - Tendo Prolog aberto no terminal para compilar e executar o programa deve escrever "[frases].".

3 - Usar a função frase(X, [palavras], []) para fazer a divisão gramatical da frase da forma:

Exemplo: "frase(X, ["A", menina, correu], [])."

