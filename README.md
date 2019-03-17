# UI Zadanie1

## Zadanie: Eulerov kôň g)

**Autor:** Tomáš Babjak

**Predmet:** Umelá inteligencia
Letný semester 2018/2019

**Eulerov kôň**

Úlohou je prejsť šachovnicu legálnymi ťahmi šachového koňa tak, aby každé políčko šachovnice bolo prejdené (navštívené) práve raz. Riešenie treba navrhnúť tak, aby bolo možné problém riešiť pre štvorcové šachovnice rôznych veľkostí (minimálne od veľkosti 5 x 5 do 20 x 20) a aby cestu po šachovnici bolo možné začať na ľubovoľnom východziom políčku.

**Problém 3**

Pre riešenie problému Eulerovho koňa existuje veľmi dobrá a pritom jednoduchá heuristika, skúste na ňu prísť sami. Ak sa vám to do týždňa nepodarí, pohľadajte na dostupných informačných zdrojoch heuristiku (z roku 1823!), prípadne konzultujte na najbližšom cvičení cvičiaceho. Implementujte túto heuristiku do algoritmu prehľadávania stromu do hĺbky a pre šachovnicu 8x8 nájdite pre 10 rôznych východzích bodov jedno (prvé) správne riešenie (pre každý východzí bod). Algoritmus s heuristikou treba navrhnúť a implementovať tak, aby bol spustiteľný aj pre šachovnice iných rozmerov než 8x8. Treba pritom zohľadniť upozornenie v Poznámke 1. Je preto odporúčané otestovať implementovaný algoritmus aj na šachovnici rozmerov 7x7, 9x9, prípadne 20x20 (máme úspešne odskúšaný aj rozmer 255x255) a prípadné zistené rozdiely v úspešnosti heuristiky analyzovať a diskutovať.

Zadanie som riešil v programovacom jazyku Java. 
Na vyriešenie problému Eulerovho koňa som použil prehľadávanie do hĺbky s využitím Warnsdorffovej heuristiky.
Šachovnicu, teda hraciu plochu po ktorej sa pohybuje kôň, som prezentoval ako dvojrozmerné pole.
Na začiatku vyhľadávania sa pomocou metódy newBoard() nastaví číslo -1 ako počiatočnú hodnotu každého políčka šachovnice.
Kôň sa môže pohybovať po šachovnici tradične v tvare písmena L a teda má 8 rôznych políčok, na ktoré môže skočiť z aktuálnej pozície.
Ak označíme y, x ako súradnicové osy šachovnice, môžeme každé políčko šachovnice reprezentovať ako dvojicu (x,y) kladných čísel.
Predpokladajme, že kôň sa nachádza na súradniciach (x,y), políčka, na ktoré sa pri ďalšom ťahu môže kôň dostať sú:

A(x+2, y+1),    
B(x+2, y-1),    
C(x-2, y+1),      
D(x-2, y-1),    
E(y+1, y+2),   
F(y+1, y-2),    
G(x-1, y+2),  
H(x-1, y-2).
    
Tieto hodnoty sú nemenné a preto som sa rozhodol uložiť ich do triedy Enum s názvom HopCoordinates(), odkiaľ sa s nimi dá jednoduchšie pracovať a kód je vďaka tomu čistejší a prehľadnejší.

Nie z každej pozície sa však môže kôň pohybovať do všetkých smerov tak, aby zostal na hracej ploche. 
Je potrebné zabezpečiť, aby zostal na šachovnici a to program vykonáva pomocou funkcie isValid a isEmpty. 
Funkcia isEmpty zisťuje, či kôň nenavštívil dané políčko šachovnice a funkcia isValid zabezpečuje navyše aj to, aby kôň zostal na políčku v rámci šachovnice.

Samotné vykonávanie hlavnej časti programu sa začína v metóde findRoute(), kde sa z daného rozsahu, teda vstupného parametra o rozmeroch šachovnice vygenerujú náhodné počiatočné súradnice.
Vytvorí sa nová hracia plocha, teda 2D pole naplnené číslami -1 na každom políčku okrem počiatočného, kde je číslo 1.
Zavolá sa funkcia Warnsdorff() s danými parametrami, v ktorej sa pomocou reverzného hľadania do hĺbky hľadá cesta pre koňa.
Pri práci s touto funkciou sú používané dva ďalšie polia a to lokálne, pre danú pozíciu nemeniace sa pole rozmerov 8x2 pre ukladanie hodnôt políčok na ktoré sa môže kôň z aktuálnej pozície dostať (8 súradníc uložené ako súradnica_y*veľkosť_šachovnice + súradnica_x) a počtu políčok dosiahnuteľných a voľných z daného políčka.
Druhé pole obsahuje možné nasledujúce  súradnice zoradené podľa počtu pozícii, na ktoré sa dá dostať z daného políčka od najmenšieho - t.j. podľa Warnsdorffovej heuristiky.
V cykle while sa kým toto pole nie je zložené iba z čísel -1 opakuje rekurzívne volanie funkcie Warnsdorff a vyhodnocovanie podmienok úspechu resp. neúspechu v hľadaní cesty.
Ak premenná counter, v ktorej sa uchováva dĺžka cesty, ktorú sme s koňom už úspešne prešli, rovná počtu všetkých políčok na šachovnici vráti sa hodnota true, čo znamená, že sa úspešne podarilo prejsť celú šachovnicu a nájsť vhodnú cestu.
Ak nastane stav, že v tabuľke so súradnicami bude na každej pozícii číslo -1 cyklus while sa ukončí a vráti false funkcii, ktorá ju volala a pre túto funkciu to znamená, že tadiaľto cesta nevedie a musí odstrániť súradnice tohto neúspešného pokusu a posunúť ďalšie súradnice s aj vyššími hodnotami na vyskúšanie.
Taktiež číslo na šachovnici, kde nastal neúspešný posun musí vymazať a nastaviť -1 namiesto čísla ťahu.
Týmto rekurzívnym postupom prehľadáva do hĺbky všetky možnosti alebo pokiaľ neprekročí hranicu 1 milión pokusov, v takom prípade vráti false každá rekurzívna funkcia aj keď ešte môžu existovať možné cesty z daného políčka. 

**Používateľská príručka**  

Na vstupe je potrebné zadať 2 čísla:  

Prvá otázka sa týka rozmeru šachovnice, ktorý je buď potrebný zadať ako jedno celé číslo (väčšie ako 4) alebo zadať číslo 0.
V prípade, že zadáte ako prvé čislo 0, vygeneruje sa pre každý skúšobný rozmer (5,6,7,8,10,14,20) náhodných 20 prípadov, ktoré program vyrieši.
V prípade že zadáte vlastné číslo vygeneruje sa 20 náhodných prípadov šachovníc pre daný rozmer.

Druhá otázka sa pýta na potrebu výpisu riešení šachovníc. Ak si chcete napríklad skontrolovať správnosť riešenia zadajte nízky rozmer šachovnice a číslo 1 pre výpis výsledku hľadania cesty.
Ak zvolíte číslo 0, výpis bude obsahovať iba informáciu o počte nájdených resp. nenájdených riešení, alebo koľko riešení bolo nájdený počas prvého pokusu. 

**Testovanie** 

Testovnie správnosti riešenia som preveroval iba pozorovaním výslednej šachovnice, ktorú som si vypísal pomocou funkcie printBoard() a zisťoval som, či sú všetky ťahy správne a či bola prejdená celá hracia plocha.
Tento spôsob testovania som vykonával na šachovniciach rozmerov 5x5, 6x6, 7x7 a 8x8. 
Testovanie funkcionality som prevádzal aj na šachovniciach väčších veľkostí (10x10, 14x14, 20x20) a to pomocou funkcie testScenario(), ktorá pre zadanú vstupnú veľkosť šachovnice vykoná testovanie pre 20 náhodných počiatočných súradníc a vypíše koľko z nich sa podarilo nájsť na prvý pokus, teda pomocou Warnsdorffovej heuristiky, koľko sa podarilo nájsť využitím rekurzie a koľkokrát sa výsledný prechod nepodaril nájsť v určenom limite.

**Možný výstup vlastného testovania v konzole:**
Pri vstupe:
0  
0  

Testing Knights tour on 5x5 chess board with random starting position..  
Solution was found on the first try 7 times, 
solution was found but not on the first try 7 times and 
solution was not found  6 times out of 20. 


Testing Knights tour on 6x6 chess board with random starting position..  
Solution was found on the first try 20 times, 
solution was found but not on the first try 0 times and 
solution was not found  0 times out of 20.


Testing Knights tour on 7x7 chess board with random starting position..  
Solution was found on the first try 10 times, 
solution was found but not on the first try 1 times and 
solution was not found  9 times out of 20.


Testing Knights tour on 8x8 chess board with random starting position..  
Solution was found on the first try 20 times, 
solution was found but not on the first try 0 times and 
solution was not found  0 times out of 20.


Testing Knights tour on 10x10 chess board with random starting position..  
Solution was found on the first try 20 times, 
solution was found but not on the first try 0 times and 
solution was not found  0 times out of 20.


Testing Knights tour on 14x14 chess board with random starting position..  
Solution was found on the first try 20 times, 
solution was found but not on the first try 0 times and 
solution was not found  0 times out of 20.


Testing Knights tour on 20x20 chess board with random starting position..  
Solution was found on the first try 20 times, 
solution was found but not on the first try 0 times and 
solution was not found  0 times out of 20.

**Štatistika vlastného testovania v konzole:**

Pre šachovnice párnych rozmerov našiel program správne riešenie v každom prípade. 
V každom z týchto prípadov našiel riešenie na prvý pokus teda pomocou Warnsdorffej heuristiky okrem prípadu šachovnice rozmerov 8x8.
V tomto prípade našiel riešenie na prvý pokus v 92 zo 100 pokusov.

5x100 pokusov pre jednotlivé rozmery šachovníc:

5x5:
28, 32, 27, 21, 24 - prvý pokus 26,4%  
36, 24, 23, 31, 32 - najdené nie na prvý pokus 29,2%  
36, 44, 50, 48, 44 - nenájdené riešenie 44,4%  

6x6:  
100, 100, 100, 100, 100 - prvy pokus 100%  
0, 0, 0, 0, 0 - najdené nie na prvý pokus 0%  
0, 0, 0, 0, 0 - nenájdené riešenie 0%  

7x7:  
43, 37, 43, 40, 50 - prvy pokus 42,6%  
8, 6, 3, 4, 7 - najdené nie na prvý pokus 5,6%  
49, 57, 54, 56, 43 - nenájdené riešenie 51,8   

**Príklady výstupu krokov Eulerovho koňa: **

Testing Knights tour on 5x5 chess board with random starting position..  
3 18 25 12 1 \
24 13 2 17 22 \
19 4 23 8 11 \
14 9 6 21 16 \
5 20 15 10 7

-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 1 \
-1 -1 -1 -1 -1 

23 10 13 4 21 \
12 5 22 9 14 \
17 24 11 20 3 \
6 1 18 15 8 \
25 16 7 2 19 

-1 -1 -1 -1 -1 \
1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 

25 16 11 6 23 \
10 5 24 17 12 \
15 18 1 22 7 \
4 9 20 13 2 \
19 14 3 8 21 

23 14 5 10 25 \
6 11 24 15 4 \
1 22 13 18 9 \
12 7 20 3 16 \
21 2 17 8 19  

-1 1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 

-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 1 -1 -1 -1 

23 10 13 4 21 \
12 5 22 9 14 \
17 24 11 20 3 \
6 1 18 15 8 \
25 16 7 2 19 

-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 -1 -1 \
-1 -1 -1 1 -1 

Testing Knights tour on 8x8 chess board with random starting position.. /
45 48 3 18 35 20 5 8 \
2 17 46 49 4 7 34 21 \
47 44 1 36 19 56 9 6 \
16 31 50 57 38 33 22 55 \
43 60 37 32 51 58 39 10 \
30 15 64 59 40 25 54 23 \
61 42 13 28 63 52 11 26 \
14 29 62 41 12 27 24 53 

11 18 49 22 13 16 51 24 \
48 21 12 17 50 23 2 15 \
19 10 47 58 1 14 25 52 \
44 57 20 37 46 55 60 3 \
9 36 45 56 59 30 53 26 \
40 43 38 29 54 61 4 31 \
35 8 41 64 33 6 27 62 \
42 39 34 7 28 63 32 5 

10 41 12 35 8 39 22 37 \
13 34 9 40 23 36 7 30 \
42 11 50 33 56 31 38 21 \
51 14 43 60 49 24 29 6 \
44 59 48 55 32 57 20 25 \
15 52 61 58 47 28 5 2 \
64 45 54 17 62 3 26 19 \
53 16 63 46 27 18 1 4 

57 8 33 36 63 10 31 14 \
34 37 58 9 32 13 64 11 \
7 56 35 50 59 62 15 30 \
38 45 52 55 28 43 12 61 \
53 6 49 44 51 60 29 16 \
46 39 54 27 42 19 22 1 \
5 26 41 48 3 24 17 20 \
40 47 4 25 18 21 2 23 

11 14 63 30 1 16 19 54 \
64 29 12 15 62 53 2 17 \
13 10 61 52 31 18 55 20 \
28 51 32 59 56 49 34 3 \
9 60 27 50 33 58 21 48 \
26 41 38 57 44 47 4 35 \
39 8 43 24 37 6 45 22 \
42 25 40 7 46 23 36 5 

51 2 35 18 55 4 37 20 \
34 17 52 3 36 19 54 5 \
1 50 33 64 53 56 21 38 \
16 61 58 49 32 63 6 47 \
59 12 31 62 57 48 39 22 \
30 15 60 43 40 25 46 7 \
11 42 13 28 9 44 23 26 \
14 29 10 41 24 27 8 45 

7 22 41 26 9 20 17 44 \
40 25 8 21 46 43 10 19 \
23 6 59 42 27 18 45 16 \
58 39 24 63 60 47 28 11 \
5 64 57 50 35 62 15 48 \
38 51 34 61 56 49 12 29 \
33 4 53 36 31 2 55 14 \
52 37 32 3 54 13 30 1 

5 2 7 22 29 26 17 24 \
8 21 4 1 18 23 30 27 \
3 6 19 46 53 28 25 16 \
20 9 54 61 48 51 44 31 \
37 64 47 52 45 62 15 50 \
10 55 38 63 60 49 32 43 \
39 36 57 12 41 34 59 14 \
56 11 40 35 58 13 42 33 

57 16 63 44 27 18 1 4 \
64 43 58 17 62 3 26 19 \
15 56 61 48 45 28 5 2 \
42 59 46 55 36 49 20 25 \
53 14 41 60 47 24 29 6 \
40 11 54 35 50 37 32 21 \
13 52 9 38 23 34 7 30 \
10 39 12 51 8 31 22 33 

4 39 6 21 2 33 16 19 \
7 22 3 38 17 20 1 32 \
40 5 48 23 34 37 18 15 \
49 8 41 58 47 24 31 36 \
42 59 46 53 64 35 14 25 \
9 50 57 60 45 28 63 30 \
56 43 52 11 54 61 26 13 \
51 10 55 44 27 12 29 62 
