import java.util.*;
/**
 * Created by Oscar on 15/03/2017.
 */
public class Bender {
    Mapa map;
    Map<Item, Character> items = new HashMap<Item,Character>();
    //Map<int [],Character> items = new HashMap<>();
    private Movement m = new Movement();
    Item posNow;

    public Bender(String mapa) {
        this.map = new Mapa(mapa);
        posNow = new Item (this.map.getBPos());
        this.items = this.map.getItems();
    }

    public String run(){
        Item proCoor;
        boolean rebootMove = false;
        StringBuilder timeToDrink = new StringBuilder();
        while(true){
            proCoor = new Item (this.moveRobot(posNow));
            if(this.map.getChar(proCoor.getPosition()) == '#'){
                if(rebootMove){
                    this.m.setDirNow(0);
                    rebootMove = false;
                    continue;
                }
                this.m.setDirNow(this.m.getDirNow() + 1);
                continue;
            }
            this.posNow.setPosition(proCoor.getPosition());
            rebootMove = true;
            timeToDrink.append(this.getMove());
            if(this.map.getChar(proCoor.getPosition()) == ' '){
                continue;
            }
            else if(changeStat(proCoor)){
                return timeToDrink.toString();
            }
        }
    }

    private boolean changeStat(Item proCoor){
        if(this.items.get(proCoor) == 'I'){
            this.m.changeDir();
            this.m.setDirNow(0);
        }
        else if(this.items.get(proCoor) == '$'){
            return true;
        }
        else if(this.items.get(proCoor) == 'T'){
            this.getTeleport(proCoor);
        }
        //if(this.map.containsItem(this.map.getChar(proCoor))){}
        /*Item provCoor = new Item(proCoor[0],proCoor[1]);
        if(this.map.containsItem(provCoor) && this.map.getItemChar(provCoor) == 'I'){
            this.changeDir();
            this.setDirNow(0);
        }
        else if(this.map.getItemChar(provCoor) == '$'){
            return true;
        }
        else {
            this.posNow = map.calcTeleport(provCoor);
            Item newProvCoor = (this.items.get("T").equals(provCoor)) ? this.items.get("T2") : this.items.get("T");
            this.items.put("X",newProvCoor);
        }*/
        return false;
    }



    private int [] moveRobot(Item posNow){
        int [] retoorn = new int [posNow.getPosition().length];
        System.arraycopy(posNow.getPosition(),0,retoorn,0,2);
        switch (m.getMove()){
            case S:
                retoorn[0]++;
                return retoorn;
            case N:
                retoorn[0]--;
                return retoorn;
            case W:
                retoorn[1]--;
                return retoorn;
            default:
                retoorn[1]++;
                return retoorn;
        }
    }

    private String getMove(){
        return m.getMove().toString();
    }

    private void changeDir(){
        m.changeDir();
    }

    private void getTeleport(Item proCoor){
        /*Set<int []> keys = this.items.keySet();
        Iterator itKeys = keys.iterator();
        while(itKeys.hasNext()){
            int [] provition = (int []) itKeys.next();
            if(this.items.get(provition) == 'T' && !provition.equals(proCoor)){
                this.posNow.setPosition(provition);*/
        Set<Item> keys = this.items.keySet();
        Iterator itKeys = keys.iterator();
        while(itKeys.hasNext()){
            Item provition = (Item) itKeys.next();
            if(this.items.get(provition) == 'T' && !provition.equals(proCoor)){
                this.posNow.setPosition(provition.getPosition());
                break;
            }
        }
    }
}
