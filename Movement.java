import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by Oscar on 17/03/2017.
 */
/*Clase Movement, donde gestionaremos la dirección que debe tomar Bender*/
class Movement {

    /*La variable move contendrá las direcciones que puede tomar Bender en forma de carácter, las cuáles las definimos en el constructor*/
    private List<Character> move = new ArrayList<>();

    /*La variable dirNow contendrá la posición de la dirección correspondiente de Bender en un momento dado*/
    private int dirNow;

    //CONSTRUCTOR//
    Movement() {
        Collections.addAll(this.move, new Character[]{'S', 'E', 'N', 'W'});
    }

    /*El método getMove() retorna el carácter correspondiente a la dirección (dirNow) actual de Bender*/
    char getMove() {
        return this.move.get(this.dirNow);
    }

    /*El método getDirNow retorna el valor de la dirección actual de Bender*/
    int getDirNow() {
        return this.dirNow;
    }

    /*El método setDirNow modifica el valor de la dirección actual de Bender por el valor que nos pasan (comprobamos que el valor
    que nos pasan es correcto, ya que dicho valor no puede ser mayor que 3 ni menor que 0 porque lo utilizamos como índice para
    obtener la dirección en forma de carácter*/
    void setDirNow(int newDir) {
        this.dirNow = (newDir > 3 || newDir < 0) ? 0 : newDir;
    }

    /*En el método changeDir modificamos el orden de las direcciones que puede coger Bender (al encontrar un inversor, sustituimos
    la posición 0 por la 2 (S por N), y la 1 por la 3 (W por E). De esta forma mantenemos la misma lista modificando sus valores
    en función de los requerimientos del enunciado*/
    void changeDir() {
        char provisional;
        for(int i = 0; i < 2; i++){
            provisional = this.move.get(i + 2);
            this.move.set(i + 2,this.move.get(i));
            this.move.set(i,provisional);
        }
    }

    /*En el método moving retornamos la posición que nos pasan incrementando/decrementando el eje X/Y dependiendo de la dirección
    actual de Bender(this.getMove) (nos pasan un objeto Position, por eso hacemos una copia de las posiciones de dicho objeto para
    poder trabajar más cómodamente*/
    int[] moving(Position posNow) {
        int[] retoorn = new int[posNow.getPosition().length];
        System.arraycopy(posNow.getPosition(), 0, retoorn, 0, 2);
        switch (this.getMove()) {
            case 'S':
                retoorn[0]++;
                return retoorn;
            case 'N':
                retoorn[0]--;
                return retoorn;
            case 'W':
                retoorn[1]--;
                return retoorn;
            default:
                retoorn[1]++;
                return retoorn;
        }
    }
}