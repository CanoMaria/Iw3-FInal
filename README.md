# iw3project# Final de IW3

Se requiere la creación de un sistema informático que permita dar soporte a la
administración de órdenes de carga de gas líquido, con la particularidad que parte de la API
contempla la recepción de datos en tiempo real y parte recepciona datos de sistemas
externos mediante una interfaz de integración.
Descripción del proceso:

Los datos de orden de carga los envia un sistema externo (por ejemplo SAP).
Lo básico de las entidades expuestas se detalla más adelante. El vínculo de
esas entidades con nuestro sistema es un código externo, que debe ser un
string para abarcar más opciones sin quedar "prendido" a ellas.
1. Número de orden, Camión, Chofer, Cliente, Producto, fecha prevista de carga
(turno), preset (preset, es la cantidad de kilogramos que deben cargar en el camión)
. Una vez recibido estos datos del sistema externo, nuestra orden queda en estado
1, a la espera del pesaje inicial
2. En el momento del turno, el camión arriba a la planta, primero se pesa el camión
vacío, eso se anota en la orden, ese valor se denomina pesaje inicial. Además se
genera una password de activación que es un número entero de 5 dígitos y debe
adjuntarse a la orden. Una vez recibido el pesaje inicial, nuestra orden queda en
estado 2. Este dato (pesaje inicial on tara) se recibe de un sistema externo que
controla la balanza (sistema TMS).
3. Los puntos 1 y 2 se refieren a sistemas externos y permiten establecer los valores
base de la orden, el password permite activar un instrumento que junto al preset,
encenderá una bomba y un caudalímetro (mide la cantidad de líquido que pasa por
un caño por unidad de tiempo, hay caudalímetros másicos que calculan el peso del
líquido, en nuestro caso se utiliza un caudalímetro másico, las unidades son
kilogramos), ese aparato enciende la bomba y corta cuando llega al preset, en otras
palabras, carga el camión de forma automática. En este punto es necesario recibir la
información de detalle de carga (detalle de la orden), los datos se reciben con cierta
frecuencia, que no tiene porqué ser la misma frecuencia de almacenamiento, sin
embargo debe almacenar el último valor de cada atributo del detalle a nivel de la
orden. Por ejemplo, asumamos que se reciben datos cada 1 segundo, y que se
define la frecuencia de almacenamiento cada 10 segundos, al cabo de 1 minuto se
habrán recibido 60 registros, cada uno habrá aportado la actualización instantánea,
sin embargo, sólo se almacenarán 6 registros en detalle. Se descarta cualquier
detalle cuyo caudal sea <= 0 o cuya masa acumulada sea menor que el valor de
masa anterior o 0.
Datos detalle:
a. Masa acumulada (el último valor es el que se toma como valor de carga, es
siempre creciente por orden la unidad de medida es kilogramos)
b. Densidad del producto (entre 0 y 1), la unidad de medida es kilogramos por
metro cúbico.
c. Temperatura del producto, la unidad de medida es grados centígrados
d. Caudal (kilogramos por hora)

4. Se debe crear un endpoint que cierre la orden y que no permita que se cargue más
detalle, esto deja a la orden en estado 3.
5. Una vez finalizada la carga e informado el cierre de la orden, debe existir un servicio
que permita recibir el pesaje final del camión, este servicio deja la orden en estado 4,
además retorna una serie de valores que constituyen una conciliación*1:
a. Pesaje inicial
b. Pesaje final
c. Producto cargado (último valor de masa acumulada)
d. Neto por balanza: Pesaje final - Pesaje inicial (b - a)
e. Diferencia entre balanza y caudalímetro: Neto por balanza - Producto
cargado (d - c)
f. Promedio de temperatura (calculado en base al detalle almacenado)
g. Promedio de densidad (calculado en base al detalle almacenado)
h. Promedio de caudal (calculado en base al detalle almacenado)

*1 La conciliación se puede pedir en cualquier momento, pero sólo para órdenes en estado
En términos generales existen las siguientes entidades de interés (*opcional):
Camión:

- patente
- descripción *
- cisternado: volumen de cada cisterna, por ejemplo cisterna 1-> 10000 lts, cisterna 2-> 5000 lts, total 15000 lts
Chofer:
- nombre
- apellido
- documento *

Cliente:
- razón social
- contacto *
Producto:
- nombre
- descripción *

Orden:
- Número de orden: se trata de un número entero que identifica unívocamente a las órdenes
- Camión, Ciente, Chofer y Producto

- Fecha/Hora de recepción (del sistema externo, punto 1)
- Fecha/Hora de recepción de pesaje inicial
- Fecha/Hora de inicio del proceso de carga (primer detalle válido)
- Fecha/Hora de fin del proceso de carga (primer detalle válido)
- Fecha/Hora de recepción de pesaje final
- Datos de carga (solo mientras la orden se encuentra en estado 2):
  - Última Masa acumulada
  -Última Densidad del producto
  - Última Temperatura del producto
  - Último Caudal
-Datos de carga (una vez recibido el pesaje final)
  -Última Masa acumulada (no varía)
  -Promedio Densidad del producto
  -Promedio Temperatura del producto
  -Promedio Caudal

-Se pueden agregar las propiedades que se consideren necesarias para mejorar el modelo.
Manejar concepto de código externo en cada caso de las entidades recibidas de sistemas
externos. (interfaces de acceso al modelo con sistemas externos)
