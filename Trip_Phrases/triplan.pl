:-op(50, xfy, :).

%Verifica se existe um voo entre 'Place1' e 'Place2' no dia 'Day' com 'Dep_time' e 'Arr_time'
flight(Place1, Place2, Day, Flight_num, Dep_time, Arr_time) :-
  timetable(Place1, Place2, List_of_flights),
  member( Dep_time / Arr_time / Flight_num / Daylist, List_of_flights),
  flyday(Day, Daylist).

flyday(Day, Daylist) :-
  member(Day, Daylist).

flyday(Day, alldays) :-
  member(Day, [mo,tu,we,th,fr,sa,su]).

%Ver departure time do voo
deptime([Place1-Place2 : Flight_num : Dep_time | _], Dep_time).

%voos diretos
route(Place1, Place2, Day, [Place1-Place2 :Flight_num : Dep_time]) :-
  flight(Place1, Place2, Day, Flight_num, Dep_time, _).

route(Place1, Place2, Day, [ Place1-Place3 : Flight_num : Dep_time | Route]) :-
  route(Place3, Place2, Day, Route),
  flight(Place1, Place3, Day, Flight_num, Dep_time, Arr_time),
  deptime(Route, Dep_time2),
  transfer(Arr_time, Dep_time2).


%Calcular o tempo da transferencia maior a 40min
transfer(Hour1:Min1, Hour2:Min2)  :-
  60 * (Hour2 - Hour1) + (Min2 - Min1) >= 40.

plan(C1,C2,C3,S) :-
  perm( [C1,C2,C3],[City1,City2,City3]),
  flight( S, City1, "tu", FN1, Dep1, Arr1),
  flight( City1, City2, "we", FN2, Dep2, Arr2),
  flight( City2, City3, "th", FN3, Dep3, Arr3),
  flight( City3, S, "fr", FN4, Dep4, Arr4),

  write("City1 = "), write(City1), nl,
  write("City2 = "), write(City2), nl,
  write("City3 = "), write(City3), nl,
  write("FN1 = "), write(FN1), nl,
  write("Dep1 = "), write(Dep1), nl,
  write("Arr1 = "), write(Arr1), nl,

  write("FN2 = "), write(FN2), nl,
  write("Dep2 = "), write(Dep2), nl,
  write("Arr2 = "), write(Arr2), nl,

  write("FN3 = "), write(FN3), nl,
  write("Dep3 = "), write(Dep3), nl,
  write("Arr3 = "), write(Arr3), nl,

  write("FN4 = "), write(FN4), nl,
  write("Dep4 = "), write(Dep4), nl,
  write("Arr4 = "), write(Arr4), nl.



member(X, [X|L]).
member(X, [Y|L]):-
  member(X, L).

perm( [], []).
perm( L, [X | R]) :-
  delete( X, L, L1),
  perm( L1, R).

delete( X, [X|L], L).
delete( X, [Y|L], [Y|L1]) :-
  delete( X, L, L1).





timetable(edinburgh,london,
[9:40/10:50/ba4733/alldays,
13:40/14:50/ba4773/alldays,
19:40/20:50/ba4833/[mo,tu,we,th,fr,su]]).

timetable(london,edinburgh,
[9:40/10:50/ba4732/alldays,
11:40/12:50/ba4752/alldays,
18:40/19:50/ba4822/[mo,tu,we,th,fr]]).

timetable(london,ljubljana,
[13:20/16:20/ju201/[fr],
13:20/16:20/ju213/[su]]).

timetable(london,zurich,
[9:10/11:45/ba614/alldays,
14:45/17:20/sr805/alldays]).

timetable(london,milan,
[8:30/11:20/ba510/alldays,
11:00/13:50/az459/alldays]).

timetable(ljubljana,zurich,
[11:30/12:40/ju322/[tu,th]]).

timetable(ljubljana,london,
[11:10/12:20/yu200/[fr],
11:25/12:20/yu212/[su]]).

timetable(milan,london,
[9:10/10:00/az458/alldays,
12:20/13:10/ba511/alldays]).

timetable(milan,zurich,
[9:25/10:15/sr621/alldays,
12:45/13:35/sr623/alldays]).

timetable(zurich,ljubljana,
[13:30/14:40/yu323/[tu,th]]).

timetable(zurich,london,
[9:00/9:40/ba613/[mo,tu,we,th,fr,sa],
16:10/16:55/sr806/[mo,tu,we,th,fr,su]]).

timetable(zurich,milan,
[7:55/8:45/sr620/alldays]).