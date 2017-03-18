import java.util.*;
/**
 * Created by Oscar on 15/03/2017.
 */
public class Bender {
    private Mapa map;
    private Map<Item, Character> items = new HashMap<Item,Character>();
    private Map<Item,Integer> todayNotDrink = new HashMap<Item,Integer>();
    private Movement m = new Movement();
    private Item posNow;

    public Bender(String mapa) {
        this.map = new Mapa(mapa);
        posNow = new Item (this.map.getBPos());
        todayNotDrink.put(posNow,1);
        this.items = this.map.getItems();
    }

    public String run(){
        if(this.posNow.getPosition()[0] == -1 || !this.items.containsValue('$')){ return null; }
        Item proCoor;
        boolean rebootMove = false;
        StringBuilder timeToDrink = new StringBuilder();

        while(true){
            //proCoor = new Item (this.moveRobot(posNow));
            proCoor = new Item(this.m.moving(posNow));
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
            timeToDrink.append(m.getMove().toString());
            if(notExit(proCoor)){
                return null;
            }
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
        return false;
    }

    private void getTeleport(Item proCoor){
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

    private boolean notExit(Item proCoor){
        int quantity = (todayNotDrink.containsKey(proCoor)) ? todayNotDrink.get(proCoor) : 0;
        if (quantity == 4) return true;
        todayNotDrink.put(proCoor,quantity + 1);
        return false;
    }
}
