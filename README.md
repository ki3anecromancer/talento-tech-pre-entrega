# Talento Tech 25254 - PRE Entrega Proyecto

## Funcionamiento del programa:
#### Opción 1: Agregar producto
1. Permite crear un nuevo producto manualmente, la ID es autogenerada.
2. Agrega 10 productos ya precargados para probar el programa rápido.

#### Opción 2: Lista productos
1. Muestra una tabla con todos los productos, ordenada por cuándo fueron agregados.

#### Opción 3: Buscar/Actualizar producto
1. Muestra en forma de tabla todos los productos de la lista que contengan la palabra elegida.
2. Muestra en forma de tabla el producto con la ID elegida.
3. Muestra en forma de tabla todos los productos que coincidan con el tipo elegido.
4. Permite actualizar los datos de un producto excepto su ID. Se elige el producto a través de su ID.

#### Opción 4: Eliminar producto
1. Permite eliminar un producto elegido por su ID. Si el producto estaba en la lista de pedidos,
también es eliminado de la misma.

#### Opción 5: Crear pedido
1. Permite crear un pedido con la ID de un producto. El stock solicitado es restado del producto.
Si se crea un nuevo pedido con una misma ID, entonces se suma al pedido ya existente.

#### Opción 6: Lista pedidos
1. Permite modificar un pedido por su ID, sólo se puede cambiar el stock solicitado, y la cantidad
se refleja en el stock del producto requerido.
2. Permite borrar un pedido por su ID. Si es borrado, el stock vuelve al producto.

## Información general:

- Proyecto de consola: simulación de una tienda.

- CRUD de productos con lista de pedidos.

- Advertencia: el proyecto fue usado para experimentar e ir agregando o modificando contenido a
  medida que se me ocurría, por lo que no está debidamente ordenado.

- (Contenido libre de Inteligencia Artificial).

## Descripción del código:

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