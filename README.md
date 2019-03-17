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

**Testovanie** 

Testovnie správnosti riešenia som preveroval iba pozorovaním výslednej šachovnice, ktorú som si vypísal pomocou funkcie printBoard() a zisťoval som, či sú všetky ťahy správne a či bola prejdená celá hracia plocha.
Tento spôsob testovania som vykonával na šachovniciach rozmerov 5x5, 6x6, 7x7 a 8x8. 
Testovanie funkcionality som prevádzal aj na šachovniciach väčších veľkostí (10x10, 14x14, 20x20) a to pomocou funkcie testScenario(), ktorá pre zadanú vstupnú veľkosť šachovnice vykoná testovanie pre 20 náhodných počiatočných súradníc a vypíše koľko z nich sa podarilo nájsť na prvý pokus, teda pomocou Warnsdorffovej heuristiky, koľko sa podarilo nájsť využitím rekurzie a koľkokrát sa výsledný prechod nepodaril nájsť v určenom limite.

**Výstup vlastného testovania v konzole:**

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

**Používateľská príručka**

Na vstupe je potrebné zadať 2 čísla:

Prvá otázka sa týka rozmeru šachovnice, ktorý je buď potrebný zadať ako jedno celé číslo (väčšie ako 4) alebo zadať číslo 0.
V prípade, že zadáte ako prvé čislo 0, vygeneruje sa pre každý skúšobný rozmer (5,6,7,8,10,14,20) náhodných 20 prípadov, ktoré program vyrieši.
V prípade že zadáte vlastné číslo vygeneruje sa 20 náhodných prípadov šachovníc pre daný rozmer.

Druhá otázka sa pýta na potrebu výpisu riešení šachovníc. Ak si chcete napríklad skontrolovať správnosť riešenia zadajte nízky rozmer šachovnice a číslo 1 pre výpis výsledku hľadania cesty.
Ak zvolíte číslo 0, výpis bude obsahovať iba informáciu o počte nájdených resp. nenájdených riešení, alebo koľko riešení bolo nájdený počas prvého pokusu. 
