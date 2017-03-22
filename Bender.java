import java.util.*;
/**
 * Created by Oscar on 15/03/2017.
 */
public class Bender {
    private Mapa map;
    private List<Item> teleports = new ArrayList<Item>();
    private Map<Item,Integer> todayNotDrink = new HashMap<Item,Integer>();
    private Movement m = new Movement();
    private Item posNowBender;
    private Item posNowWinner;

    public Bender(String mapa) {
        this.map = new Mapa(mapa);
        posNowBender = this.map.getBPos();
        todayNotDrink.put(posNowBender, 1);
        this.teleports = this.map.getTeleports();
        this.posNowWinner = this.map.getWinner();
    }

    public String run(){
        if (this.posNowBender.getPosition()[0] == -1 || this.posNowWinner.getPosition()[0] == -1) {
            return null;
        }
        Item proCoor;
        boolean rebootMove = false;
        StringBuilder timeToDrink = new StringBuilder();

        while(true){
            proCoor = new Item(this.m.moving(posNowBender));
            if(this.map.getChar(proCoor.getPosition()) == '#'){
                if(rebootMove){
                    this.m.setDirNow(0);
                    rebootMove = false;
                    continue;
                }
                this.m.setDirNow(this.m.getDirNow() + 1);
                continue;
            }
            this.posNowBender.setPosition(proCoor.getPosition());
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
        char c = this.map.getChar(proCoor.getPosition());
        switch (c) {
            case 'I':
                this.m.changeDir();
                this.m.setDirNow(0);
                break;
            case '$':
                return true;
            case 'T':
                this.getTeleport(proCoor);
                break;
        }
        return false;
    }

    private void getTeleport(Item proCoor){
        for (Item i : this.teleports) {
            if (!i.equals(proCoor)) {
                this.posNowBender.setPosition(i.getPosition());
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