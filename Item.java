import java.util.*;

/**
 * Created by Oscar on 16/03/2017.
 */
public class Item {
    private int [] positions = new int [2];

    Item(int posX, int posY){
        positions[0] = posX;
        positions[1] = posY;
    }

    int [] getPositions(){
        return this.positions;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Item){
            Item i = (Item) o;
            return this.positions[0] == i.positions[0] && this.positions[1] == i.positions[1];
        }
        return false;
    }
}
