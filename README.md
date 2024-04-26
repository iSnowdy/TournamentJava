# Java Project - Martial Arts Tournament

Minijuego de pelea/luchadores 

Descripción 

Se tiene un simulador de pelea entre combatientes. Los jugadores (humanos) pueden elegir entre pelear entre ellos (PvP) o bien contra la máquina (PvE). 

Las peleas se realizarán o bien tomando una serie de letras aleatorias (String) como input o si se puede, detectar teclas de movimiento con las flechas del teclado (Up, Down, Left, Right). Los combatientes dispondrán de un tiempo limitado (cronometrado) para introducir la secuencia correcta. 

En caso de que la secuencia no sea correcta, el combatiente recibirá un daño X que vendrá definido por las estadísticas del personaje (Luchador) que haya creado/seleccionado. Si la secuencia introducida es correcta, el jugador será el que inflija daño a la máquina/jugador contrario.  

Los personajes tendrán estadísticas. Las estadísticas se suben gastando puntos de poder. Para conseguir puntos de poder se ha de luchar. Tanto victorias como derrotas otorgan puntos de poder. Las derrotas otorgan ligeramente más. 

Los luchadores tienen un rango. El ranking está predefinido y se divide en 5 niveles. Los luchadores predefinidos están ya posicionados en el ranking. La máquina no puede subir de ranking. Los jugadores al conseguir victorias ganarán puntos de rangos con los cuales podrán subir en el rango. Si se sufren derrotas, se perderán puntos de rango. 

Habrá un historial. El historial guardará el log de victorias, derrotas, contra quién se ha luchado y qué tipo de oponente (humano/máquina). 

 

 

Luchadores 

Habrá 2 modelos de luchadores. Los predefinidos (ya construidos) y los personalizados. 

Los luchadores predefinidos vendrán cargados a partir de un JSON con sus características. Se deberán de crear 3 tipos de luchadores por Rango. 

Los luchadores personalizados son aquellos que el propio jugador crea con nombre y estadísticas personalizadas. A ser posible, se ha de poder guardar la configuración del luchador para cargarla a posteriori. 

Los luchadores tienen 3 tipos según las estadísticas que posean. 
 
	1. Luchadores centrados en vitalidad o tanques. Serán aquellos que dispongan de más puntos de vitalidad que los otros. Se caracterizan por poder aguantar más golpes. 
	2. Luchadores centrados en fuerza o Glass-Cannons. Serán aquellos que dispongan de más puntos de fuerza que los otros. Se caracterizan por poder infligir una gran cantidad de daño, pero a cambio aguantan poco. 
	3. Luchadores centrados en destreza o ratas. Serán aquellos que dispongan de más puntos de destreza que los otros. Se caracterizan por disponer de más tiempo para poder introducir inputs y tener una mayor probabilidad de esquivar golpes. 
 

 

 

Ranking 

El ranking se verá compuesto de 5 rangos: 
 
	1. Gusano. 
	2. Desecho. 
	3. Aprendiz. 
	4. Luchador. 
	5. Sensei. 
 

Habrá 3 luchadores predefinidos por cada rango. Se empezará con 15 luchadores de base. 

Para subir en el ranking, se deberán de ganar puntos de rango. 

Para ganar puntos de rango los jugadores deberán de ganar peleas contra otros jugadores o bien contra la máquina.  

Se pueden perder puntos de rango si los jugadores pierden contra otros jugadores o bien contra la máquina. 

La máquina no puede perder puntos. 

Los combates serán por defecto contra jugadores/máquina del mismo nivel que el del personaje del jugador. 

Es posible para el jugador combatir contra otros luchadores de mayor rango. Deberá de especificar en este caso si quiere y además contra qué rango. Si gana, obtendrá más puntos de clasificación. Pero si pierde perderá todos sus puntos. 

 

 

Estadísticas 

Los luchadores tendrán 3 estadísticas: Vitalidad, Fuerza y Destreza. 

Vitalidad: determinará la cantidad de puntos de vida que el luchador tiene. A mayor vitalidad, más golpes podrá aguantar antes de caer al suelo. 

Fuerza: determinará la fuerza/puntos de daño que el luchador inflingirá si realiza el minijuego correctamente. 

Destreza: le otorgará al luchador una cantidad x adicional de segundos para realizar el minijuego y además una pequeña posibilidad de esquivar el golpe del rival. 

Las estadísticas de los luchadores se suben usando puntos de poder. 

Un luchador personalizado tendrá todas las estadísticas en 1 y una cantidad fija de puntos de poder con los que el usuario podrá subir las estadísticas de la forma que le parezca. 

Los luchadores predefinidos tienen unas estadísticas fijas. No las podrán subir a menos que sea el jugador el que seleccione a dicho luchador predefinido y lo utilice. 

Los puntos de poder para subir las estadísticas de los luchadores se podrán obtener luchando, ya sea PvE o PvP. Tanto las victorias como las derrotas otorgarán puntos de poder. Las derrotas otorgan más puntos de poder que las victorias. 

 

Relación puntos de poder y estadísticas: 

 

 

Jugadores 

Los jugadores (humanos) se les dará primero que nada la opción de luchar contra otro jugador o contra la máquina. 

Los jugadores, una vez elegido el tipo de contrincante, deberán de crear un luchador personalizado (nombre, estadísticas) o seleccionar uno de los luchadores predefinidos. 

Es posible para el jugador elegir un luchador predefinido del rango 1-5. Pero si crea uno personalizado, deberá empezar desde el rango 1.  

Si se elige combatir contra otro jugador, una vez que el Jugador 1 haya terminado la configuración básica de su personaje, se le dará paso al Jugador 2 para que también lo haga. 

Si se elige combatir contra la máquina, se le asignará un oponente al Jugador 1. El oponente será en base a la dificultad que el jugador haya elegido. Por defecto será un luchador predefinido del mismo rango que el del jugador. Si elige combatir contra uno de mayor rango, se le asignará un luchador predefinido de la máquina del rango seleccionado. 

 

 

Máquina  

Las acciones de la máquina estarán todas randomizadas dentro de unos valores predefinidos. 

Acciones posibles a llevar a cabo por la máquina: 
 
	- Minijuego. Según el rango del luchador de la máquina, tendrá un % determinado de tener éxito. 
	- En caso de que haga falta, la selección del minijuego de piedra, papel o tijeras también será aleatoria. 
	-  

 

 

Minijuego 

El minijuego mostrará por pantalla una serie de inputs (flechas direccionales) y el jugador tendrá que introducir esa serie exactamente como es en un tiempo cronometrado. 

Si no hay fallo alguno en el input por parte del jugador, podrá infligir daño al oponente. Debido a las características de la estadística Destreza, aunque se acierte el minijuego, habrá una posibilidad de que no inflija daño en base a un % determinado por el nivel de Destreza. 

Si hay algún fallo en el input por parte del jugador, tomará daño del rival y el turno irá hacia este. 

Si el tiempo llega a su fin, se dará contará como fallo. 

Si la vida del contrincante llega a 0, contará como victoria. Ganará puntos de rango y puntos de poder. 

Si la vida del jugador llega a 0, contará como derrota. Perderá puntos de rango (todos si es contra un adversario de mayor rango) y ganará puntos de poder (más que con una victoria). 

 

Otros 

El orden será determinado por un minijuego de piedra, papel o tijeras. Podrá darse PvE o PvP. 

Historial. Consistirá en un log de las victorias, derrotas y luchadores (especificará si PvE o PvP) del jugador/luchador. 

Implementar un sistema de recompensas por logros, como alcanzar cierto número de victorias consecutivas o derrotar a oponentes de mayor rango. 

Desarrollar un algoritmo para que la IA de la máquina sea desafiante pero justa. Puedes ajustar la dificultad basándote en el rango del jugador y su historial de batalla. 

Feedback. 

Asegúrate de incluir validación de entrada para garantizar que las estadísticas introducidas por el jugador para los luchadores personalizados estén dentro de rangos razonables y no provoquen desequilibrios en el juego. 

Considerar la posibilidad de que los jugadores puedan ver el progreso a través de los diferentes rangos y los luchadores asociados a cada uno. 

Aunque no se utilice una interfaz gráfica, puedes diseñar una salida en consola que muestre el ranking actualizado de los luchadores. 

Proporciona opciones para que los jugadores puedan ver su posición en el ranking y el progreso hacia el siguiente rango. 

 

 

Flujo de juego claro y fluido. Diseña un flujo de juego intuitivo y fácil de entender que guíe a los jugadores a través de las diferentes opciones y acciones disponibles. 

 

Interrogantes 

Cómo registrar los inputs direccionales. 

Cómo guardar luchadores predefinidos. 

Cómo guardar luchadores personalizados. 

Historial. Cómo especificar si es PvE o PvP. Cómo guardar los registros. 

 
