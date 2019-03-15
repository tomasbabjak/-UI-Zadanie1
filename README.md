# UI Zadanie1
=======

## Zadanie: Eulerov k�� g)
=======

**Autor:** Tom� Babjak
**Predmet:** Umel� inteligencia
Letn� semester 2018/2019

**Eulerov k��**
�lohou je prejs� �achovnicu leg�lnymi �ahmi �achov�ho ko�a tak, aby ka�d� pol��ko �achovnice bolo prejden� (nav�t�ven�) pr�ve raz. Rie�enie treba navrhn�� tak, aby bolo mo�n� probl�m rie�i� pre �tvorcov� �achovnice r�znych ve�kost� (minim�lne od ve�kosti 5 x 5 do 20 x 20) a aby cestu po �achovnici bolo mo�n� za�a� na �ubovo�nom v�chodziom pol��ku.
**Probl�m 3**
Pre rie�enie probl�mu Eulerovho ko�a existuje ve�mi dobr� a pritom jednoduch� heuristika, sk�ste na �u pr�s� sami. Ak sa v�m to do t��d�a nepodar�, poh�adajte na dostupn�ch informa�n�ch zdrojoch heuristiku (z roku 1823!), pr�padne konzultujte na najbli��om cvi�en� cvi�iaceho. Implementujte t�to heuristiku do algoritmu preh�ad�vania stromu do h�bky a pre �achovnicu 8x8 n�jdite pre 10 r�znych v�chodz�ch bodov jedno (prv�) spr�vne rie�enie (pre ka�d� v�chodz� bod). Algoritmus s heuristikou treba navrhn�� a implementova� tak, aby bol spustite�n� aj pre �achovnice in�ch rozmerov ne� 8x8. Treba pritom zoh�adni� upozornenie v Pozn�mke 1. Je preto odpor��an� otestova� implementovan� algoritmus aj na �achovnici rozmerov 7x7, 9x9, pr�padne 20x20 (m�me �spe�ne odsk��an� aj rozmer 255x255) a pr�padn� zisten� rozdiely v �spe�nosti heuristiky analyzova� a diskutova�.

Zadanie som rie�il v programovacom jazyku Java. 

Na vyrie�enie probl�mu Eulerovho ko�a som pou�il preh�ad�vanie do h�bky s vyu�it�m Warnsdorffovej heuristiky.
�achovnicu, teda hraciu plochu po ktorej sa pohybuje k��, som prezentoval ako dvojrozmern� pole.
Na za�iatku vyh�ad�vania sa pomocou met�dy newBoard() nastav� ��slo -1 ako po�iato�n� hodnotu ka�d�ho pol��ka �achovnice.
K�� sa m��e pohybova� po �achovnici tradi�ne v tvare p�smena L a teda m� 8 r�znych pol��ok, na ktor� m��e sko�i� z aktu�lnej poz�cie.
Ak ozna��me y, x ako s�radnicov� osy �achovnice, m��eme ka�d� pol��ko �achovnice reprezentova� ako dvojicu (x,y) kladn�ch ��sel.
Predpokladajme, �e k�� sa nach�dza na s�radniciach (x,y), pol��ka, na ktor� sa pri �al�om �ahu m��e k�� dosta� s�:
    A(x+2, y+1),
    B(x+2, y-1),
    C(x-2, y+1),
    D(x-2, y-1),
    E(y+1, y+2),
    F(y+1, y-2),
    G(x-1, y+2),
    H(x-1, y-2).
Tieto hodnoty s� nemenn� a preto som sa rozhodol ulo�i� ich do triedy Enum s n�zvom HopCoordinates(), odkia� sa s nimi d� jednoduch�ie pracova� a k�d je v�aka tomu �istej�� a preh�adnej��.

Nie z ka�dej poz�cie sa v�ak m��e k�� pohybova� do v�etk�ch smerov tak, aby zostal na hracej ploche. 
Je potrebn� zabezpe�i�, aby zostal na �achovnici a to program vykon�va pomocou funkcie isValid a isEmpty. 
Funkcia isEmpty zis�uje, �i k�� nenav�t�vil dan� pol��ko �achovnice a funkcia isValid zabezpe�uje navy�e aj to, aby k�� zostal na pol��ku v r�mci �achovnice.

Samotn� vykon�vanie hlavnej �asti programu sa za��na v met�de findRoute(), kde sa z dan�ho rozsahu, teda vstupn�ho parametra o rozmeroch �achovnice vygeneruj� n�hodn� po�iato�n� s�radnice.
Vytvor� sa nov� hracia plocha, teda 2D pole naplnen� ��slami -1 na ka�dom pol��ku okrem po�iato�n�ho, kde je ��slo 1.
Zavol� sa funkcia Warnsdorff() s dan�mi parametrami, v ktorej sa pomocou reverzn�ho h�adania do h�bky h�ad� cesta pre ko�a.
Pri pr�ci s touto funkciou s� pou��van� dva �al�ie polia a to lok�lne, pre dan� poz�ciu nemeniace sa pole rozmerov 8*2 pre ukladanie hodn�t pol��ok na ktor� sa m��e k�� z aktu�lnej poz�cie dosta� (8 s�radn�c ulo�en� ako s�radnica_y*ve�kos�_�achovnice + s�radnica_x) a po�tu pol��ok dosiahnute�n�ch a vo�n�ch z dan�ho pol��ka.
Druh� pole obsahuje mo�n� nasleduj�ce  s�radnice zoraden� pod�a po�tu poz�cii, na ktor� sa d� dosta� z dan�ho pol��ka od najmen�ieho - t.j. pod�a Warnsdorfovej heuristiky.
V cykle while sa k�m toto pole nie je zlo�en� iba z ��sel -1 opakuje rekurz�vne volanie funkcie Warnsdorff a vyhodnocovanie podmienok �spechu resp. ne�spechu v h�adan� cesty.
Ak premenn� counter, v ktorej sa uchov�va d�ka cesty, ktor� sme s ko�om u� �spe�ne pre�li, rovn� po�tu v�etk�ch pol��ok na �achovnici vr�ti sa hodnota true, �o znamen�, �e sa �spe�ne podarilo prejs� cel� �achovnicu a n�js� vhodn� cestu.
Ak nastane stav, �e v tabu�ke so s�radnicami bude na ka�dej poz�cii ��slo -1 cyklus while sa ukon�� a vr�ti false funkcii, ktor� ju volala a pre t�to funkciu to znamen�, �e tadia�to cesta nevedia a mus� odstr�ni� s�radnice tohto ne�spe�n�ho pokusu a posun�� �al�ie s�radnice s aj vy���mi hodnotami na vysk��anie.
Taktie� ��slo na �achovnici, kde nastal ne�spe�n� posun mus� vymaza� a nastavi� -1 namiesto ��sla �ahu.
T�mto rekurz�vnym postupom preh�ad�va do h�bky v�etky mo�nosti alebo pokia� neprekro�� hranicu 1 mili�n pokusov, v takom pr�pade vr�ti false ka�d� rekurz�vna funkcia aj ke� e�te m��u existova� mo�n� cesty z dan�ho pol��ka. 

Testovnie spr�vnosti rie�enia som preveroval iba pozorovan�m v�slednej �achovnice, ktor� som si vyp�sal pomocou funkcie printBoard() a zis�oval som, �i s� v�etky �ahy spr�vne a �i bola prejden� cel� hracia plocha.
Tento sp�sob testovania som vykon�val na �achovniciach rozmerov 5x5, 6x6, 7x7 a 8x8. 
Testovanie funkcionality som prev�dzal aj na �achovniciach v���ch ve�kost� (10x10, 14x14, 20x20) a to pomocou funkcie testScenario(), ktor� pre zadan� vstupn� ve�kos� �achovnice vykon� testovanie pre 20 n�hodn�ch po�iato�n�ch s�radn�c a vyp�e ko�ko z nich sa podarilo n�js� na prv� pokus, teda pomocou Warnsdorffovej heuristiky, ko�ko sa podarilo n�js� vyu�it�m rekurzie a ko�kokr�t sa v�sledn� prechod nepodaril n�js� v ur�enom limite.

V�stup testovania v konzole:

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
