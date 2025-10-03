# Talento Tech 25254 - PRE Entrega Proyecto

### Información general:

- Proyecto de consola: simulación de una tienda.

- CRUD de productos con lista de pedidos.

- __Qué se utilizó de lo visto en clases:__ condicionales, bucles, operadores ariméticos y lógicos, 
manejo de cadenas (uso de StringBuilder) y listas, modularización, POO, variables de clase estáticas,
encapsulamiento, herencia y polimorfismo, try/cath y excepciones personalizadas, orden del proyecto
en packages.

- Se ha optado por seguir las sugerencias de las consignas, como el nombre de las clases, su
herencia y packages, además de las opciones de la lista principal.

- Advertencia: el proyecto fue usado para experimentar e ir agregando o modificando contenido a
  medida que se me ocurría, por lo que no está debidamente ordenado. Todo cumpliendo con las
  consignas.

- (Contenido libre de Inteligencia Artificial).

### Funcionamiento del programa:

### Descripción del código:

- Las entidades se encuentran en el package productos.

- __ProductosList__ contiene las listas de productos para que cada lista tenga su propio ancho de 
columnas, permitiendo mostrar los datos en forma de tabla estética y ajustada al tamaño de su
contenido. Cada columna tiene un ancho predeterminado, sino se usa el ancho del dato más largo en
cada columna y se agregan 5 espacios en blanco extra.

- El Main se mantiene limpio, solo invoca a la tienda __Minimercado__. 

- La clase __Minimercado__ también se mantiene limpia, solo llama a las distintas funciones
que permiten su funcionamiento.

- Se creó la clase __Utilidades__ que sólo contiene funciones static que son utilizadas en distintas
clases.

- Para cada una de las opciones principales de la consola se creó una clase aparte para que contenga
toda la lógica de la misma y mantener orden en el código.

- Al ser un proyecto de consola y usar _try_/_catch_, se ha intentado validar todas las entradas.
de esta forma se ha evitado que el programa lance una excepción y finalice abruptamente.