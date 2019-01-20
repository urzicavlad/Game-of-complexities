# Game-of-complexities
Descriere: Vrem să implementăm un joc prin care să învățăm mai bine complexitatea pentru niște
algoritmi. Pentru început vrem să construim o bază de date în care să avem numele unor algoritmi și
complexitatea lor (de ex. LSI_adaugareInceput și Teta(1), sau bubbleSort și O(n^2)). Pe parcursul jocului
aplicația selectează aleator N algoritmi și afișează numele lor. Aplicația afișează și cele N complexități
pentru acești algoritmi, dar într-o ordine aleatoare. Jucătorul trebuie să dea ca răspuns perechile
corecte. Scorul jucătorului este numărul de perechi corecte.
Funcționalități:
 Implementați jocul cu 3 niveluri de dificultate: începător (5 algoritmi), avansați (10 algoritmi)
și experți (15 algoritmi). La începutul jocului utilizatorul alege dificultatea, iar la final
aplicația îi afișează scorul.
Exemple:
 Dacă în aplicație avem reținut:
 Interclasare Teta(n+m)
 SelectionSort Teta(n*n)
 LSI_adaugaSfarsit Teta(n)
 LSI_adaugaPozitie O(n)
 LDI_adaugaSfarsit O(1)
 Stiva_sterge O(1)
 VD_adaugaInceput Teta(n)
 Bubblesort O(n*n)
 MergeSort Teta(nlog_2n)
 Dacă aplicația afișează 5 algoritmi aleator cu complexități:
 Stiva_sterge BubbleSort Interclasare LSI_adaugaSfarsit VD_adaugaInceput
 Teta(n) Teta(n) Teta(n+m) Teta(1) O(n*n)
 Dacă utilizatorul răspunde cu
 1 – 4
 2 – 1
 3 – 3
 4 – 2
 5 – 5
 El va avea 2 puncte (primul si al 3-lea sunt ok)
