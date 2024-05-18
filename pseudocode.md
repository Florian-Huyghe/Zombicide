# Pseudo code de notre creation de carte.

## constructeur
```
un Board qui prend en parametre le nbLigne et nbColonne
   initialise le nbLigne par le parametre
   initialise le nbColonne par le parametre
   initialise le carte  avec un tableau de nouvelle Cellule de taille [nbLigne][nbColonne]
   initialise le generateur aleatoir  nouveau Random()
   initialise le tabelau de Carte()par des room 
   lance l'algorithme de separation par des rues recursivement 
   initialise le placement des Room()
   initialise les portes de chaque cellule
   - (à venir):bouche d'egout, creation et placement zombie et joueur...
```
## nos methodes
```
initCarte():
   Pour chaque ligne de 0 à nbLigne:
       Pour chaque colonne de 0 à nbColonne:
           this.carte[i][j] = nouvelle Room()

creerCarte():
   this.create(0, 0, this.nbLigne - 1, this.nbColonne - 1)

create(rowStart, colStart, rowEnd, colEnd):
   Calculer rowLength et colLength de la section
   Si rowLength > 4 et colLength > 4:
       Appeler this.createStreetInCross(rowStart, colStart, rowEnd, colEnd) pour la section concerneé
   Sinon si rowLength > 4:
      Appler this.createStreetInRow(rowStart, colStart, rowEnd, colEnd) pour la sections concerneé
   Sinon si colLength > 4:
      appeler this.createStreetInColumn(rowStart, colStart, rowEnd, colEnd) pour la section concerneé

createStreetInRow(rowStart, colStart, rowEnd, colEnd):
   Générer une position aléatoire pour la rue dans la rangée
   Placer des rues dans la rangée aléatoire
   Si la différence entre rowEnd et rowStart est suffisamment grande:
       Appeler recurisvement this.create pour les sections au-dessus et en-dessous de la rue

createStreetInColumn(rowStart, colStart, rowEnd, colEnd):
   Générer une position aléatoire pour la rue dans la colonne
   Placer des rues dans la colonne aléatoire
   Si la différence entre colEnd et colStart est suffisamment grande:
       Appeler recursivement this.create pour les sections à gauche et à droite de la rue

createStreetInCross(rowStart, colStart, rowEnd, colEnd):
   Générer des positions aléatoires pour la rue dans la rangée et la colonne
   Placer des rues dans la rangée et la colonne aléatoires
   Appeler this.create pour les quatre sections autour de la rue

placeRoom():
   Appeler this.placeContinental() et this.placeMedical()

placeContinental():
   Générer des positions aléatoires pour les salles continentales
   Si la cellule à la position aléatoire peut être remplacée:
       Remplacer la cellule par une salle continentale
   Sinon:
       Appeler récursivement this.placeContinental()

placeMedical():
   Générer des positions aléatoires pour les salles médicales
   Si la cellule à la position aléatoire peut être remplacée:
       Remplacer la cellule par une salle médicale
   Sinon:
       Appeler récursivement this.placeMedical()

```