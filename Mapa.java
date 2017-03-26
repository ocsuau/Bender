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
                  posteriormente pasarle dichos valores a la clase Bender.

                  Puede darse el caso de que en el mapa no haya ningún carácter 'X', por eso inicializamos por defecto esta variable
                  con una nueva instancia de la clase Position y las coordenadas -1, -1, valores que en la clase Bender comprobaremos
                  para determinar si el mapa contiene o no el carácter 'X'*/
    private Position bPos = new Position(new int[]{-1, -1});

    /*- wPos        -> booleano donde reflejamos la existencia/inexistencia del carácter '$' (Comprobamos que en el mapa tengamos el
                  carácter que condiciona la victoria para poder retornar null antes de empezar con el algoritmo del método
                  Bender.run() en caso de no haber dicho carácter*/
    private boolean wPos;


    /*Constructor de Mapa*/
    Mapa(String mapa) {
        /*Creamos repeatW, variable auxiliar para comprobar que no tenemos más de un carácter '$'*/
        boolean repeatW = true;

        /*Pasamos el string que nos pasan a un array de string con el método 'split()'. Posteriormente recorreremos cada carácter
        de cada elemento de dicho array.*/
        String [] lineMap = mapa.split("\n");
        for(int i = 0; i < lineMap.length; i++){

            /*A cada "salto de línea" añadiremos a la lista map una nueva instancia de lista de carácteres (Lista bidimensional), y
            cada carácter la añadiremos a la lista que acabamos de instanciar con pequeñas restricciones*/
            this.map.add(new ArrayList<Character>());
            for(int j = 0; j < lineMap[i].length(); j++){
                if (lineMap[i].charAt(j) == 'X') {

                    /*En caso de que el carácter que estemos tratando sea la 'X' (Bender), en nuestra lista de listas de carácteres
                    añadiremos un espacio. No almacenamos el carácter que representa a Bender porque su posición y movimientos ya
                    los controlamos desde fuera y añadiendo un espacio nos evitamos problemas de incongruencia a la hora de recorrer
                    el mapa.*/
                    this.map.get(i).add(' ');

                    /*Seguidamente comprobaremos si en el mapa tenemos más de una 'X'.
                    Como hemos definido en la declaración de las variables miembro de esta clase, bPos contiene una instancia de la
                    clase Position con las coordenadas -1 -1.

                    Por lo tanto, si, al querer guardar las posiciones de Bender, la segunda coordenada de bPos es distinta a -1,
                    significa que antes ya hemos almacenado su posición y, por lo tanto, tenemos el carácter 'X' repetido.

                    En este caso resetearemos las coordenadas de bPos con los valores -1 y 0 (ponemos -1 en la coordenada X para
                    que, en la clase Bender, se sepa que debe retornar null, y ponemos 0 en la coordenada Y para que, en las futuras
                    comprobaciones, se siga entendiendo que hay 'X' repetidas)

                    En cambio, si la coordenada Y de bPos es igual a -1, significa que aún no habiamos introducido ninguna
                    coordenada y, por lo tanto, es la primera 'X' que encontramos, reseteamos la posición de bPos con las coordenadas
                    correctas*/
                    this.bPos.setPosition((this.bPos.getPosition()[1] == -1) ? new int[]{i, j} : new int[]{-1, 0});

                    /*Realizo continue para evitar comprobaciones posteriores innecesarias y la introducción involuntaria del carácter
                    "X" a nuestro mapa*/
                    continue;
                } else if (lineMap[i].charAt(j) == '$') {
                    /*Si el carácter que estamos comprobando es "$" comprobamos el valor de repeatW. Si vale true, significa que
                    el carácter '$' que estamos tratando es el primero que hemos encontrado y, por lo tanto, no tenemos porque
                    pensar que está repetido, así que asignamos a this.wPos el valor true, y a repeatW le asignamos el valor false.*/
                    if (repeatW) {
                        this.wPos = true;
                        repeatW = false;
                    }

                    /*En cambio, si repeatW es false, significa que antes ya hemos registrado la entrada de un carácter '$', así que
                    asignamos a this.wPos false para que en la clase Bender se sepa que hay más de un carácter '$' y, por lo tanto,
                    debe retornar null.*/
                    else {
                        this.wPos = false;
                    }

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

        /*Retornamos el valor de bPos*/
            return this.bPos;
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