import java.util.Arrays;
/**
 * Created by Oscar on 16/03/2017.
 */
/*Clase Position, donde iremos creando las instancias donde almacenaremos las posiciones de los ítems que nos interesen guardar*/
class Position {
    /*Variable donde almacenaremos las coordenadas del objeto en cuestión*/
    private int [] position = new int [2];

    /*Constructor donde asignamos los parámetros en las posiciones correspondientes de la variable position
    (elemento 0 -> coordenada x / elemento 1 -> coordenada y)*/
    Position(int[] position) {
            this.position[0] = position[0];
            this.position[1] = position[1];
    }

    /*Retornamos las posiciones del objeto en cuestión*/
    int[] getPosition() {
        return this.position;
    }

    /*Reseteamos los valores de las posiciones del objeto en cuestión*/
    void setPosition(int[] proCoor) {
        this.position[0] = proCoor[0];
        this.position[1] = proCoor[1];
    }

    /*Realizamos override del método equals ya que, en un momento dado de nuestro programa, queremos comparar instancias de esta
    clase y, además, porque utilizamos la clase como índice de un contenedor map y es tipo de un conjunto(set)*/
    @Override
    public boolean equals(Object o){
        if (o instanceof Position) {
            Position i = (Position) o;
            return Arrays.equals(this.position, i.position);
        }
        return false;
    }

    /*Realizamos override del método hashCode porque utilizamos la clase como índice de un contenedor map y es tipo de un
    conjunto(set)*/
    @Override
    public int hashCode() {
        int result = 17;
        int c = Arrays.hashCode(this.position);
        return (31 * result + c);
    }
}