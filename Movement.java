import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by Oscar on 17/03/2017.
 */
/*CLASE Movement, DONDE GESTIONAREMOS LA DIRECCIÓN QUE DEBE TOMAR BENDER*/
class Movement {

    /*LA VARIABLE move CONTENDRÁ LAS DIRECCIONES QUE PUEDE TOMAR BENDER EN FORMA DE CARÁCTER, LAS CUÁLES LAS DEFINIMOS EN EL CONSTRUCTOR*/
    private List<Character> move = new ArrayList<>();

    /*LA VARIABLE dirNow CONTENDRÁ LA POSICIÓN DE LA DIRECCIÓN CORRESPONDIENTE DE BENDER EN UN MOMENTO DADO*/
    private int dirNow;

    //CONSTRUCTOR//
    Movement() {
        Collections.addAll(this.move, new Character[]{'S', 'E', 'N', 'W'});
    }

    /*getMove RETORNA EL CARÁCTER CORRESPONDIENTE A LA DIRECCIÓN (dirNow) ACTUAL DE BENDER*/
    char getMove() {
        return this.move.get(this.dirNow);
    }

    /*getDirNow RETORNA EL VALOR DE LA DIRECCIÓN ACTUAL DE BENDER*/
    int getDirNow() {
        return this.dirNow;
    }

    /*setDirNow MODIFICA EL VALOR DE LA DIRECCIÓN ACTUAL DE BENDER POR EL VALOR QUE NOS PASAN (COMPROBAMOS QUE EL VALOR QUE NOS
    PASAN ES CORRECTO, YA QUE DICHO VALOR NO PUEDE SER MAYOR QUE 3 PORQUE LO UTILIZAMOS COMO ÍNDICE PARA OBTENER LA DIRECCIÓN EN
    FORMA DE CARÁCTER)*/
    void setDirNow(int pos) {
        this.dirNow = (pos > 3) ? 0 : pos;
    }

    /*EN changeDir MODIFICAMOS EL ORDEN DE LAS DIRECCIONES QUE PUEDE COGER BENDER (AL ENCONTRAR UN INVERSOR, SUSTITUIMOS LA POSICIÓN
    0 POR LA 2 (S por N), Y LA 1 POR LA 3 (W por E). DE ESTA FORMA MANTENEMOS LA MISMA LISTA MODIFICANDO SUS VALORES EN FUNCIÓN DE LOS REQUERIMIENTOS DEL
    ENUNCIADO)*/
    void changeDir() {
        char provisional;
        for(int i = 0; i < 2; i++){
            provisional = this.move.get(i + 2);
            this.move.set(i + 2,this.move.get(i));
            this.move.set(i,provisional);
        }
    }

    /*EN moving RETORNAMOS LA POSICIÓN QUE NOS PASAN INCREMENTANDO/DECREMENTANDO EL EJE X/Y DEPENDIENDO DE LA DIRECCIÓN ACTUAL DE
    BENDER (this.getMove)(NOS PASAN UN OBJETO Position, POR ESO HACEMOS UNA COPIA DE LAS POSICIONES DE DICHO OBJETO PARA PODER TRABAJAR MÁS
    CÓMODAMENTE)*/
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