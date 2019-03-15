# UI Zadanie1
=======

## Zadanie: Eulerov kôò g)
=======

**Autor:** Tomáš Babjak
**Predmet:** Umelá inteligencia
Letnı semester 2018/2019

**Eulerov kôò**
Úlohou je prejs šachovnicu legálnymi ahmi šachového koòa tak, aby kadé políèko šachovnice bolo prejdené (navštívené) práve raz. Riešenie treba navrhnú tak, aby bolo moné problém rieši pre štvorcové šachovnice rôznych ve¾kostí (minimálne od ve¾kosti 5 x 5 do 20 x 20) a aby cestu po šachovnici bolo moné zaèa na ¾ubovo¾nom vıchodziom políèku.
**Problém 3**
Pre riešenie problému Eulerovho koòa existuje ve¾mi dobrá a pritom jednoduchá heuristika, skúste na òu prís sami. Ak sa vám to do tıdòa nepodarí, poh¾adajte na dostupnıch informaènıch zdrojoch heuristiku (z roku 1823!), prípadne konzultujte na najblišom cvièení cvièiaceho. Implementujte túto heuristiku do algoritmu preh¾adávania stromu do håbky a pre šachovnicu 8x8 nájdite pre 10 rôznych vıchodzích bodov jedno (prvé) správne riešenie (pre kadı vıchodzí bod). Algoritmus s heuristikou treba navrhnú a implementova tak, aby bol spustite¾nı aj pre šachovnice inıch rozmerov ne 8x8. Treba pritom zoh¾adni upozornenie v Poznámke 1. Je preto odporúèané otestova implementovanı algoritmus aj na šachovnici rozmerov 7x7, 9x9, prípadne 20x20 (máme úspešne odskúšanı aj rozmer 255x255) a prípadné zistené rozdiely v úspešnosti heuristiky analyzova a diskutova.

Zadanie som riešil v programovacom jazyku Java. 

Na vyriešenie problému Eulerovho koòa som pouil preh¾adávanie do håbky s vyuitím Warnsdorffovej heuristiky.
Šachovnicu, teda hraciu plochu po ktorej sa pohybuje kôò, som prezentoval ako dvojrozmerné pole.
Na zaèiatku vyh¾adávania sa pomocou metódy newBoard() nastaví èíslo -1 ako poèiatoènú hodnotu kadého políèka šachovnice.
Kôò sa môe pohybova po šachovnici tradiène v tvare písmena L a teda má 8 rôznych políèok, na ktoré môe skoèi z aktuálnej pozície.
Ak oznaèíme y, x ako súradnicové osy šachovnice, môeme kadé políèko šachovnice reprezentova ako dvojicu (x,y) kladnıch èísel.
Predpokladajme, e kôò sa nachádza na súradniciach (x,y), políèka, na ktoré sa pri ïalšom ahu môe kôò dosta sú:
    A(x+2, y+1),
    B(x+2, y-1),
    C(x-2, y+1),
    D(x-2, y-1),
    E(y+1, y+2),
    F(y+1, y-2),
    G(x-1, y+2),
    H(x-1, y-2).
Tieto hodnoty sú nemenné a preto som sa rozhodol uloi ich do triedy Enum s názvom HopCoordinates(), odkia¾ sa s nimi dá jednoduchšie pracova a kód je vïaka tomu èistejší a preh¾adnejší.

Nie z kadej pozície sa však môe kôò pohybova do všetkıch smerov tak, aby zostal na hracej ploche. 
Je potrebné zabezpeèi, aby zostal na šachovnici a to program vykonáva pomocou funkcie isValid a isEmpty. 
Funkcia isEmpty zisuje, èi kôò nenavštívil dané políèko šachovnice a funkcia isValid zabezpeèuje navyše aj to, aby kôò zostal na políèku v rámci šachovnice.

Samotné vykonávanie hlavnej èasti programu sa zaèína v metóde findRoute(), kde sa z daného rozsahu, teda vstupného parametra o rozmeroch šachovnice vygenerujú náhodné poèiatoèné súradnice.
Vytvorí sa nová hracia plocha, teda 2D pole naplnené èíslami -1 na kadom políèku okrem poèiatoèného, kde je èíslo 1.
Zavolá sa funkcia Warnsdorff() s danımi parametrami, v ktorej sa pomocou reverzného h¾adania do håbky h¾adá cesta pre koòa.
Pri práci s touto funkciou sú pouívané dva ïalšie polia a to lokálne, pre danú pozíciu nemeniace sa pole rozmerov 8*2 pre ukladanie hodnôt políèok na ktoré sa môe kôò z aktuálnej pozície dosta (8 súradníc uloené ako súradnica_y*ve¾kos_šachovnice + súradnica_x) a poètu políèok dosiahnute¾nıch a vo¾nıch z daného políèka.
Druhé pole obsahuje moné nasledujúce  súradnice zoradené pod¾a poètu pozícii, na ktoré sa dá dosta z daného políèka od najmenšieho - t.j. pod¾a Warnsdorfovej heuristiky.
V cykle while sa kım toto pole nie je zloené iba z èísel -1 opakuje rekurzívne volanie funkcie Warnsdorff a vyhodnocovanie podmienok úspechu resp. neúspechu v h¾adaní cesty.
Ak premenná counter, v ktorej sa uchováva dåka cesty, ktorú sme s koòom u úspešne prešli, rovná poètu všetkıch políèok na šachovnici vráti sa hodnota true, èo znamená, e sa úspešne podarilo prejs celú šachovnicu a nájs vhodnú cestu.
Ak nastane stav, e v tabu¾ke so súradnicami bude na kadej pozícii èíslo -1 cyklus while sa ukonèí a vráti false funkcii, ktorá ju volala a pre túto funkciu to znamená, e tadia¾to cesta nevedia a musí odstráni súradnice tohto neúspešného pokusu a posunú ïalšie súradnice s aj vyššími hodnotami na vyskúšanie.
Taktie èíslo na šachovnici, kde nastal neúspešnı posun musí vymaza a nastavi -1 namiesto èísla ahu.
Tımto rekurzívnym postupom preh¾adáva do håbky všetky monosti alebo pokia¾ neprekroèí hranicu 1 milión pokusov, v takom prípade vráti false kadá rekurzívna funkcia aj keï ešte môu existova moné cesty z daného políèka. 

Testovnie správnosti riešenia som preveroval iba pozorovaním vıslednej šachovnice, ktorú som si vypísal pomocou funkcie printBoard() a zisoval som, èi sú všetky ahy správne a èi bola prejdená celá hracia plocha.
Tento spôsob testovania som vykonával na šachovniciach rozmerov 5x5, 6x6, 7x7 a 8x8. 
Testovanie funkcionality som prevádzal aj na šachovniciach väèších ve¾kostí (10x10, 14x14, 20x20) a to pomocou funkcie testScenario(), ktorá pre zadanú vstupnú ve¾kos šachovnice vykoná testovanie pre 20 náhodnıch poèiatoènıch súradníc a vypíše ko¾ko z nich sa podarilo nájs na prvı pokus, teda pomocou Warnsdorffovej heuristiky, ko¾ko sa podarilo nájs vyuitím rekurzie a ko¾kokrát sa vıslednı prechod nepodaril nájs v urèenom limite.

Vıstup testovania v konzole:

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
