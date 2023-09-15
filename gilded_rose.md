# Spécifications du Système de la Rose Dorée

## Caractéristiques Communes des Articles
- `sellIn`: Délai en jours pour vendre l'article.
- `quality`: Valeur indiquant la qualité de l'article.
- Chaque jour, `sellIn` et `quality` sont réduits.
- La `quality` ne peut jamais être négative.
- La `quality` ne peut jamais dépasser 50 (sauf pour "Sulfuras").

## Règles par Type d'Article

### Règles Générales
- Après la date de péremption (`sellIn` < 0), la `quality` diminue deux fois plus vite.

### Aged Brie
- La `quality` augmente au fil du temps.

### Sulfuras
- Pas de date de péremption (`sellIn`).
- La `quality` reste fixe à 80.

### Backstage Passes
- La `quality` augmente avec le temps.
- Augmentation de 2 points quand `sellIn` <= 10.
- Augmentation de 3 points quand `sellIn` <= 5.
- La `quality` devient 0 après la date de péremption.

### Conjured
- La `quality` diminue deux fois plus rapidement que les articles normaux.

## Contraintes de Développement
- Ne pas modifier la classe `Item` ou ses propriétés.
- Possibilité d'ajouter une méthode `updateQuality` et des propriétés statiques à la classe `Item`.
