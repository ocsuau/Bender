import java.util.Arrays;
/**
 * Created by Oscar on 16/03/2017.
 */
/*CLASE DONDE IREMOS CREANDO LAS INSTANCIAS DONDE ALMACENAREMOS LAS POSICIONES DE LOS ÍTEMS QUE NOS INTERESEN GUARDAR*/
class Position {
    /*VARIABLE DONDE ALMACENAREMOS LAS COORDENADAS DEL OBJETO EN CUESTIÓN*/
    private int [] position = new int [2];

    /*CONSTRUCTOR DONDE ASIGNAMOS LOS PARÁMETROS EN LAS POSICIONES CORRESPONDIENTES DE LA VARIABLE position
    (ELEMENTO 0 -> COORDENADA X / ELEMENTO 1 -> COORDENADA Y)*/
    Position(int[] position) {
            this.position[0] = position[0];
            this.position[1] = position[1];
    }

    /*RETORNAMOS LAS POSICIONES DEL OBJETO EN CUESTIÓN*/
    int[] getPosition() {
        return this.position;
    }

    /*RESETEAMOS LOS VALORES DE LAS POSICIONES DEL OBJETO EN CUESTIÓN*/
    void setPosition(int[] proCoor) {
        this.position[0] = proCoor[0];
        this.position[1] = proCoor[1];
    }

    /*REALIZAMOS OVERRIDE DEL MÉTODO equals YA QUE, EN UN MOMENTO DADO DE NUESTRO PROGRAMA, QUEREMOS COMPARAR INSTANCIAS DE ESTA
    CLASE Y, ADEMÁS, PORQUE UTILIZAMOS LA CLASE COMO ÍNDICE DE UN CONTENEDOR MAP Y ES TIPO DE UN CONJUNTO(SET)*/
    @Override
    public boolean equals(Object o){
        if (o instanceof Position) {
            Position i = (Position) o;
            return Arrays.equals(this.position, i.position);
        }
        return false;
    }


    /*REALIZAMOS OVERRIDE DEL MÉTODO hashCode PORQUE UTILIZAMOS LA CLASE COMO ÍNDICE DE UN CONTENEDOR MAP Y ES TIPO DE UN
    CONJUNTO(SET)*/
    @Override
    public int hashCode() {
        int result = 17;
        int c = Arrays.hashCode(this.position);
        return (31 * result + c);
    }
}