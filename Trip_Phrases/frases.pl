frase(phrase(X,Y))-->frase_nome(X), frase_verbal(Y).
frase(phrase(X,Y))-->frase_nome_p(X), frase_verbal_p(Y).

frase_nome_v(X) --> frase_nome(X).
frase_nome_v_p(X) --> frase_nome_p(X).

frase_nome(frase_nome(X,Y))--> determinante_m(X), nome_m(Y).
frase_nome(frase_nome(X,Y))--> determinante_f(X), nome_f(Y).
frase_nome(frase_nome(X)) --> nome_f(X).
frase_nome(frase_nome(X)) --> nome_m(X).

frase_nome_v(frase_nome_v(X,Y,Z)) --> preposicao(X),determinante_f(Y), nome_f(Z).
frase_nome_v(frase_nome_v(X,Y,Z)) --> preposicao(X),determinante_m(Y), nome_m(Z).
frase_nome_v(frase_nome_v(X,Y)) --> preposicao_f(X), nome_f(Y).
frase_nome_v(frase_nome_v(X,Y)) --> preposicao_m(X), nome_m(X).
frase_nome_v(frase_nome_v(X,Y)) --> contracao_f(X), nome_f(X).
frase_nome_v(frase_nome_v(X,Y)) --> contracao_m(X), nome_m(X).

frase_nome_p(frase_nome(X,Y))--> determinante_f_p(X), nome_f_p(Y).
frase_nome_p(frase_nome(X,Y))--> determinante_m_p(X), nome_m_p(Y).
frase_nome_p(frase_nome(X)) --> nome_f_p(X).
frase_nome_p(frase_nome(X)) --> nome_m_p(X).

frase_nome_v_p(frase_nome_v(X,Y,Z)) --> preposicao(X),determinante_f_p(Y), nome_f_p(Z).
frase_nome_v_p(frase_nome_v(X,Y,Z)) --> preposicao(X),determinante_m(Y), nome_m(Z).
frase_nome_v_p(frase_nome_v(X,Y)) --> preposicao_f(X), nome_f_p(Y).
frase_nome_v_p(frase_nome_v(X,Y)) --> preposicao_m(X), nome_m_p(X).
frase_nome_v_p(frase_nome_v(X,Y)) --> contracao_f_p(X), nome_f_p(X).
frase_nome_v_p(frase_nome_v(X,Y)) --> contracao_m_p(X), nome_m_p(X).

frase_verbal(frase_verbal(X,Y)) --> verbo(X), frase_nome_v(Y).
frase_verbal(frase_verbal(X)) --> verbo(X).
frase_verbal(frase_verbal(X,Y)) --> verbo(V), frase_nome_v_p(Y).
frase_verbal(frase_verbal(X,Y)) --> verbo(V), frase_nome_v(Y).

frase_verbal_p(frase_verbal_p(X)) --> verbo_p(X).
frase_verbal_p(frase_verbal_p(X,Y)) --> verbo_p(V), frase_nome_v(Y).
frase_verbal_p(frase_verbal_p(X,Y)) --> verbo_p(V), frase_nome_v_p(Y).






determinante_f(determinante_f("A"))-->["A"].
determinante_f(determinante_f(a))-->[a].

determinante_m(determinante_m("O"))-->["O"].
determinante_m(determinante_m(o))-->[o].

determinante_f_p(determinante_f_p("As"))-->["As"].
determinante_f_p(determinante_f_p(as))-->[as].

determinante_m_p(determinante_m_p("Os"))-->["Os"].
determinante_m_p(determinante_m_p(os))-->[os].

nome_f(nome_f(menina))-->[menina].
nome_f(nome_f(floresta)) --> [floresta].
nome_f(nome_f(mae)) --> [mae].
nome_f(nome_f(vida)) --> [vida].
nome_f(nome_f(noticia)) --> [noticia].
nome_f(nome_f(cidade)) --> [cidade].
nome_f(nome_f(porta)) --> [porta].

nome_f_p(nome_f_p(lagrimas)) --> [lagrimas].

nome_m(nome_m(menino))-->[menino].
nome_m(nome_m(tempo))-->[tempo].
nome_m(nome_m(cacador))-->[cacador].
nome_m(nome_m(rosto))-->[rosto].
nome_m(nome_m(rio))-->[rio].
nome_m(nome_m(mar))-->[mar].
nome_m(nome_m(vento))-->[vento].
nome_m(nome_m(martelo))-->[martelo].
nome_m(nome_m(cachorro))-->[cachorro].
nome_m(nome_m(tambor))-->[tambor].
nome_m(nome_m(sino))-->[sino].

nome_m_p(nome_m_p(tambores))-->[tambores].
nome_m_p(nome_m_p(lobos))-->[lobos].

preposicao(preposicao(para)) -->[para].
preposicao(preposicao(com))--> [com].

preposicao_m(preposicao(pelo))-->[pelo].

preposicao_f(preposicao(pela))-->[pela].

contracao_f(contracao_f(na)) --> [na].

contracao_f_p(contracao_f_p(nas)) --> [nas].

contracao_m(contracao_m_s(no)) --> [no].

contracao_m_p(contracao_m_p(nos)) --> [nos].

verbo(verbo(corre)) --> [corre].
verbo(verbo(correu)) --> [correu].
verbo(verbo(bateu)) --> [bateu].

verbo_p(verbo_p(corriam)) --> [corriam].
verbo_p(verbo_p(bateram)) --> [bateram].
