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

    /*Variable noExit, donde iremos almacenando las posiciones y direcciones por las que pasa el robot para comprobar si el mapa
    tiene solución*/
    private Map<Position, Set<Character>> notExit = new HashMap<>();

    /*Variable m, instancia de la clase Movement para gestionar los movimientos del robot*/
    private Movement m = new Movement();

    /*Variable posNowBender, instancia de la clase Position donde almacenamos la posición inicial del robot, valores que nos transmite
    la clase Mapa*/
    private Position posNowBender;

    //Constructor donde:
    public Bender(String mapa) {

    /*- Inicializamos la variable map pasándole como parámetro el mapa que nos han pasado*/
        this.map = new Mapa(mapa);

    /*- Inicializamos la variable posNowBender, donde almacenamos la posición inicial del robot, que será igual al valores que nos retorne el método getBPos() de la clase Mapa*/
        this.posNowBender = this.map.getBPos();

    /*- Inicializamos la variable notExit, donde introducimos el primer elemento cuyo índice será la posición inicial de Bender
    y cuyo valor será una nueva instancia de Lista SET de carácteres. Posteriormente, en dicha instancia introducimos su primer valor,
    que será la dirección inicial del robot (RAZONAMIENTO DEL USO DE ESTE MAPA EN LA DOCUMENTACIÓN)*/
        this.notExit.put(posNowBender, new HashSet<Character>());
        this.notExit.get(posNowBender).add(this.m.getMove());

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
            sobre una posición y la direcciones que estaba tomando Bender al momento de pasar sobre dichas posiciones
            (almacenándolas en la variables notExit)*/
            if(notExit(proCoor)){
                return null;
            }

            /*Comprobamos si en la posición en la que está Bender es un espacio. En caso afirmativo, realizamos un continue para
            evitar comprobaciones innecesarias.*/
            if(this.map.getChar(proCoor.getPosition()) == ' '){
                continue;
            }

            /*En caso negativo, comprobaremos sobre qué carácter está Bender, aplicando los cambios correspondientes.
            En caso de que dicho carácter sea '$', el método changeStat nos retornará true, y, por lo tanto, retornaremos
            timeToDrink en forma de String*/
            else if(changeStat(proCoor)){
                return timeToDrink.toString();
            }
        }
    }

    private boolean changeStat(Position proCoor) {
        char c = this.map.getChar(proCoor.getPosition());
        switch (c) {
            case 'I':
                this.m.changeDir();
                this.m.setDirNow(0);
                break;
            case 'T':
                this.getTeleport(proCoor);
                break;
            case '$':
                return true;
        }
        return false;
    }


    private void getTeleport(Position proCoor) {
        for (Position i : this.teleports) {
            if (!i.equals(proCoor)) {
                this.posNowBender.setPosition(i.getPosition());
                break;
            }
        }
    }

    private boolean notExit(Position proCoor) {
        if (this.notExit.containsKey(proCoor)) {
            if (this.notExit.get(proCoor).contains(this.m.getMove())) {
                return true;
            }
        } else {
            this.notExit.put(proCoor, new HashSet<Character>());
        }
        this.notExit.get(proCoor).add(this.m.getMove());
        return false;
    }
}