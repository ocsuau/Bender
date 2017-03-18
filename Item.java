import java.util.*;
/**
 * Created by Oscar on 16/03/2017.
 */
public class Item{
    private int [] position = new int [2];

    public Item(int [] position){
        if(position == null){
            position = new int [2];
            position[0] = -1;
            position[1] = -1;
        }
        this.position[0] = position[0];
        this.position[1] = position[1];
    }

    public int [] getPosition(){
        return this.position;
    }

    public void setPosition(int [] proCoor){
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

