import java.util.*;
/**
 * Created by Oscar on 16/03/2017.
 */
/*Clase Mapa, donde construiremos el mapa que pasan a Bender y gestionaremos todos los carácteres que salen en dicho mapa*/
class Mapa {
    //Como variables miembro encontramos:

    //- map         -> Lista bidimensional de carácteres
    private List<List<Character>> map = new ArrayList<>();

    /*-teleports    -> Conjunto donde almacenaremos las posiciones de los Teleports (si existieran en el mapa) (Tipo SET ya que no
                   almacenaremos dos posiciones iguales)*/
    private Set<Position> teleports = new HashSet<>();

    /*- bPos        -> Variable tipo Position donde almacenamos las coordenadas de la posición de Bender en el mapa para
                  posteriormente pasarle dichos valores a la clase Bender*/
    private Position bPos;

    /*- wPos        -> booleano donde reflejamos la existencia/inexistencia del carácter '$' (Comprobamos que en el mapa tengamos el
                  carácter que condiciona la victoria para poder retornar null antes de empezar con el algoritmo del método
                  Bender.run() en caso de no haber dicho carácter*/
    private boolean wPos;

    /*Constructor de Mapa*/
    Mapa(String mapa) {

        /*Pasamos el string que nos pasan a un array de string con el método 'split()'. Posteriormente recorreremos cada carácter
        de cada elemento de dicho array.*/
        String [] lineMap = mapa.split("\n");
        for(int i = 0; i < lineMap.length; i++){

            /*A cada "salto de línea" añadiremos a la lista map una nueva instancia de lista de carácteres (Lista bidimensional), y
            cada carácter la añadiremos a la lista que acabamos de instanciar con pequeñas restricciones*/
            this.map.add(new ArrayList<Character>());
            for(int j = 0; j < lineMap[i].length(); j++){

                /*En caso de que el carácter que estemos tratando sea la 'X' (Bender), en nuestra lista de listas de carácteres añadiremos
                un espacio. No almacenamos el carácter que representa a Bender porque su posición y movimientos ya los controlamos desde
                fuera y añadiendo un espacio nos evitamos problema de incongruencia a la hora de recorrer el mapa.*/

                /*Además, aprovechamos para almacenar la posición de bender en la variable bPos creando una instancia de la clase Position,
                que posteriormente enviaremos a la clase Bender*/
                if (lineMap[i].charAt(j) == 'X') {
                    this.map.get(i).add(' ');
                    this.bPos = new Position(new int[]{i, j});
                    /*Realizo continue para evitar comprobaciones posteriores innecesarias y la introducción involuntaria del carácter
                    "X" a nuestro mapa*/
                    continue;

                /*Si el carácter que estamos comprobando es "$", inicializamos wPos como true. Posteriormente transmitiremos esta información
                a la clase Bender.*/
                } else if (lineMap[i].charAt(j) == '$') {
                    this.wPos = true;

                /*En este punto sabemos que en carácter en cuestión no es ni la "X" ni el "$", por lo tanto, comprobamos si es una "T"
                para almacenar en teleports su posición para su posterior gestión en la clase Bender (Almacenamos las posiciones de los
                dos Teleports para poder ubicar la nueva posición de Bender en caso de que pase por encima de un teleport*/
                } else if (lineMap[i].charAt(j) == 'T') {
                    this.teleports.add(new Position(new int[]{i, j}));
                }

                /*Almacenamos el carácter en cuestión que estamos tratando*/
                this.map.get(i).add(lineMap[i].charAt(j));
            }
        }
    }

    /*Método getBPos(), donde retornamos a la clase Bender la posición del Robot que previamente hemos almacenado*/
    Position getBPos() {

        /*En caso de que en el mapa que nos han pasado como parámetro en nuestro constructor no hubiera el carácter "X", el valor
        de la variable bPos sería null, por lo tanto retornaremos a Bender una instancia de la clase Position con las posiciones
        -1 -1, valores que en la clase Bender comprobaremos para identificar si el mapa es correcto (contiene robot) o no*/
        if (this.bPos != null) {
            return this.bPos;
        }
        return new Position(new int[]{-1, -1});
    }

    /*Método getTeleports, donde retornaremos las posiciones de los teleports que contiene el mapa (En caso de no contener Teleports,
    retornaremos null, pero no es problema, ya que no es incompatible con la ejecución del algoritmo*/
    Set<Position> getTeleports() {
        return this.teleports;
    }

    /*Método getChar, donde retornamos el carácter del mapa que se ubica en las posiciones que nos pasan como parámetro*/
    char getChar(int[] positions) {
        return this.map.get(positions[0]).get(positions[1]);
    }

    /*Método getWinner, donde retornamos wPos que indica si el mapa contiene carácter de victoria o no*/
    boolean getWinner() {
        return this.wPos;
    }
}