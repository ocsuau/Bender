import java.util.*;
/**
 * Created by Oscar on 15/03/2017.
 */
public class Bender {
    private Mapa map;
    private Set<Position> teleports = new HashSet<Position>();
    private Map<Position, Set<Character>> todayNotDrink = new HashMap<Position, Set<Character>>();
    private Movement m = new Movement();
    private Position posNowBender;

    public Bender(String mapa) {
        this.map = new Mapa(mapa);
        this.posNowBender = this.map.getBPos();
        this.todayNotDrink.put(posNowBender, new HashSet<Character>());
        this.todayNotDrink.get(posNowBender).add(this.m.getMove());
        this.teleports = this.map.getTeleports();
    }

    public String run(){
        if (this.posNowBender.getPosition()[0] == -1 || !this.map.getWinner() || (this.teleports.size() != 2 && this.teleports.size() != 0)) {
            return null;
        }
        Position proCoor;
        boolean rebootMove = false;
        StringBuilder timeToDrink = new StringBuilder();

        while(true){
            proCoor = new Position(this.m.moving(posNowBender));
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
            timeToDrink.append(m.getMove());
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

    private boolean changeStat(Position proCoor) {
        char c = this.map.getChar(proCoor.getPosition());
        switch (c) {
            case 'I':
                this.m.changeDir();
                this.m.setDirNow(0);
                break;
            case 'T':
                this.getTeleport(proCoor);
                break;
            case '$':
                return true;
        }
        return false;
    }

    private void getTeleport(Position proCoor) {
        for (Position i : this.teleports) {
            if (!i.equals(proCoor)) {
                this.posNowBender.setPosition(i.getPosition());
                break;
            }
        }
    }

    private boolean notExit(Position proCoor) {
   /*     int quantity = (todayNotDrink.containsKey(proCoor)) ? todayNotDrink.get(proCoor) : 0;
        if (quantity == 4) return true;
        todayNotDrink.put(proCoor,quantity + 1);
        return false;*/
        if (this.todayNotDrink.containsKey(proCoor)) {
            if (this.todayNotDrink.get(proCoor).contains(this.m.getMove())) {
                return true;
            }
            this.todayNotDrink.get(proCoor).add(this.m.getMove());
        } else {
            this.todayNotDrink.put(proCoor, new HashSet<Character>());
            this.todayNotDrink.get(proCoor).add(this.m.getMove());
        }
        return false;
    }
}