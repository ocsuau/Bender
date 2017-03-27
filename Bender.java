import java.util.*;
/**
 * Created by Oscar on 15/03/2017.
 */
/*Clase Bender, donde gestionamos al robot*/
public class Bender {

    /*Variable map, donde guardaremos una instancia de la clase Mapa para gestionar el mapa*/
    private Mapa map;

    /*Variable teleports, conjunto donde almacenaremos las posiciones de los teleports que pueda tener el mapa*/
    private Set<Position> teleports;

    /*Variable noExit, donde iremos almacenando las posiciones por las que pasa el robot y la cantidad de veces que pasa por cada
    posición para comprobar si el mapa tiene solución*/
    private Map<Position, Integer> notExit = new HashMap<>();

    /*Variable m, instancia de la clase Movement para gestionar los movimientos del robot*/
    private Movement m = new Movement();

    /*Variable posNowBender, instancia de la clase Position donde almacenamos la posición inicial del robot, valores que nos transmite
    la clase Mapa*/
    private Position posNowBender;

    //Constructor donde:
    public Bender(String mapa) {

    /*- Inicializamos la variable map pasándole como parámetro el mapa que nos han pasado*/
        this.map = new Mapa(mapa);

    /*- Inicializamos la variable posNowBender, donde almacenamos la posición inicial del robot, que será igual al valores que nos
    retorne el método getBPos() de la clase Mapa*/
        this.posNowBender = this.map.getBPos();

    /*- Inicializamos la variable notExit, donde introducimos el primer elemento cuyo índice será la posición inicial de Bender
    y cuyo valor será 1 (Iniciamos el contador de veces que ha pasado Bender pos esa posición)*/
        this.notExit.put(posNowBender, 1);

    /*- Inicializamos la variable teleports, donde almacenamos la posición de los teleports del mapa, en caso de que existan (teleports
    puede contener valores nulos, pero no afectan al desarrollo del software)*/
        this.teleports = this.map.getTeleports();
    }

    /*Método run(), donde iniciamos el cálculo del recorrido de Bender*/
    public String run(){

        /*En primer lugar comprobamos que en el mapa que nos han pasado exista el carácter "X", (Comprobación realizada en clase Mapa)
        el carácter "$" (Comprobación realizada en clase Mapa) y que el número de teleports sea igual a 2 SIEMPRE Y CUANDO
        el número de teleports sea distinto a 0 (Tener un mapa sin teleports no es un error, pero tener mapa con teleports y que
        esa cantidad sea distinta a dos si es error basándonos en los requerimientos del enunciado)*/
        if (this.posNowBender.getPosition()[0] == -1 || !this.map.getWinner() || (this.teleports.size() != 2 && this.teleports.size() != 0)) {
            return null;
        }

        /*Variable proCoor donde almacenaremos la Posición en la que debería estar el robot en caso de moverse en la dirección que
        lo corresponda en dicho momento*/
        Position proCoor;

        /*Variable rebootMove, que nos ayudará a saber cuando reiniciar el orden de direcciones del robot (Cada vez que choque
        contra una pared POR PRIMERA VEZ CONSECUTIVA)*/
        boolean rebootMove = false;

        /*Variable timeToDrink, donde iremos almacenando las direcciones que va tomando Bender en forma de StringBuilder*/
        StringBuilder timeToDrink = new StringBuilder();

        //Iniciamos el algoritmo principal
        while(true){

            /*Almacenamos en proCoor la posición en la que debería estar Bender teniendo en cuenta su dirección actual. (Realizamos
            dicha operación a través del método moving de la clase Movement). Seguidamente comprobamos en la clase mapa si en la
            posición resultanto existe una pared.*/
            proCoor = new Position(this.m.moving(posNowBender));
            if(this.map.getChar(proCoor.getPosition()) == '#'){

                /*Si encontramos una pared, comprobamos si es la primera vez que choca con dicha pared de manera consecutiva gracias
                a la variable rebootMove (De esta manera evitamos que, al chocar con la pared, Bender tome siempre la dirección sur/norte)*/
                if(rebootMove){

                    /*En caso de chocar por primera vez consecutiva con la pared, reiniciamos el valor que indica la dirección que debe
                    tomar Bender*/
                    this.m.setDirNow(0);
                    rebootMove = false;
                } else {

                    /*En caso negativo, incrementamos el valor que indica su dirección*/
                    this.m.setDirNow(this.m.getDirNow() + 1);
                }
                /*Realizamos continue para evitar comprobaciones innecesarias y porque, si no lo hicieramos, podríamos comprobar
                varias veces sobre que carácter está Bender sin haberse modivo, y, por consecuencia, aplicar cambios que ya se han
                aplicado en la iteración anterior cuando realmente no se debería aplicar*/
                continue;
            }

            /*En este punto, la posición que debería tomar Bender no etá ocupada por una pared, así que reseteamos su posición,
            pasamos el valor de rebootMove a true para reiniciar la cuenta de choques consecutivos con una pared y añadimos
            al StringBuilder a retornar (timeToDrink) el carácter correspondiente a la dirección que ha tomado Bender.*/
            this.posNowBender.setPosition(proCoor.getPosition());
            rebootMove = true;
            timeToDrink.append(m.getMove());

            /*Comprobamos si el mapa tiene salida o no en el método notExit() en función de la cantidad de veces que hemos pasado
            sobre una posición (almacenando dicha cantidad en la variables notExit)*/
            if (notExit(proCoor)) {
                return null;
            }

            /*Comprobamos si en la posición en la que está Bender es un espacio. En caso afirmativo, realizamos un continue para
            evitar comprobaciones innecesarias.*/
            if (this.map.getChar(proCoor.getPosition()) == ' ') {
                continue;
            }

            /*En caso negativo, comprobaremos sobre qué carácter está Bender, aplicando los cambios correspondientes.
            En caso de que dicho carácter sea '$', el método changeStat nos retornará true, y, por lo tanto, retornaremos
            timeToDrink en forma de String*/
            else if (changeStat(proCoor)) {
                return timeToDrink.toString();
            }
        }
    }

    /*Método changeStat, donde evaluamos sobre qué carácter ha pasado Bender. (En este punto sabemos que Bender no está encima de
    un espacio, por lo tanto sólo puede ser uno de los carácteres especiales)*/
    private boolean changeStat(Position proCoor) {

        /*Almacenamos el carácter del mapa en la posición equivalente a la posición actual de Bender.*/
        char c = this.map.getChar(proCoor.getPosition());
        switch (c) {

            /*Si el carácter es una I, cambiamos el orden de preferencias de dirección de Bender a través del método changeDir de la
            variable m (instancia de la clase Movement) y reiniciamos el índice del movimiento que le toca hacer a Bender a través del
            método setDirNow de la misma variable*/
            case 'I':
                this.m.changeDir();
                this.m.setDirNow(0);
                break;

            /*Si el carácter es una T, accedemos al método getTeleport para evaluar la nueva posición de Bender*/
            case 'T':
                this.getTeleport(proCoor);
                break;

            /*Finalmente si el carácter es un $, significa que Bender ha llegado a la condición de victoria y retornamos true*/
            case '$':
                return true;
        }

        /*Llegados a éste punto significa que Bender no ha llegado a la condición de victoria, por lo tanto retornamos false.*/
        return false;
    }

    /*Método getTeleport, donde comparamos las posiciones de los teleports del mapa con la posición actual de Bender, (La comparación
    la hacemos a través del método equals de la clase Position) de tal forma que si las posiciones no coinciden, la nueva posición
    de Bender será la posición del teleport que estemos tratando en ése momento.*/
    private void getTeleport(Position proCoor) {
        for (Position i : this.teleports) {
            if (!i.equals(proCoor)) {
                this.posNowBender.setPosition(i.getPosition());
                return;
            }
        }
    }

    /*Método notExit, donde evaluamos si el mapa tiene solución o no.*/
    private boolean notExit(Position proCoor) {
        /*Si la posición que queremos evaluar ya existe como índice de nuestro mapa, comprobamos el valor que indexa. En caso de que
        el valor sea 4, significa que el Bender ya ha pasado 5 veces por la misma posición y, por lo tanto, no tiene solución, porque
        está repitiendo caminos. (El límite está en 4 ya que son el número de posibles direcciones que puede tomar Bender) En dicho caso
        retornamos true*/
        if (this.notExit.containsKey(proCoor)) {
            if (this.notExit.get(proCoor) == 4) {
                return true;
            }
            /*En este punto sabemos que la posición que estamos tratando existe como índice pero el valor que indexa no es igual a 4, por
            lo tanto, introducimos en el mapa la misma posición incrementando el valor en 1*/
            this.notExit.put(proCoor, this.notExit.get(proCoor) + 1);
        } else {
            /*En este punto sabemos que la posición que nos han pasado no existe como índice en nuestro mapa, así que lo introducimos
            indicando como valor un 1*/
            this.notExit.put(proCoor, 1);
        }
        /*Retornamos false para indicar que el mapa aún podría tener solución*/
        return false;
    }
}