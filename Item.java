import java.util.*;
/**
 * Created by Oscar on 16/03/2017.
 */
class Item {
    private int [] position = new int [2];

    Item(int[] position) {
            this.position[0] = position[0];
            this.position[1] = position[1];
    }

    int[] getPosition() {
        return this.position;
    }

    void setPosition(int[] proCoor) {
        this.position[0] = proCoor[0];
        this.position[1] = proCoor[1];
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Item){
            Item i = (Item) o;
            return Arrays.equals(this.position, i.position);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        int c = Arrays.hashCode(this.position);
        return (31 * result + c);
    }
}