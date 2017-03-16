import java.util.*;

/**
 * Created by Oscar on 15/03/2017.
 */
public class Bender {
    private List<List<Character>> mapa = new ArrayList<List<Character>>();
    private Map<String,Item> items = new HashMap<String , Item>();

    public Bender(String mapa) {
        int width = mapa.indexOf("\n");
        int heigth = (mapa.length() % width == 0) ? ((mapa.length() / width) - 1) : mapa.length() / width;
        char [] provisional;

        for (int i = 0, posIni = 0, posFin = width; i < heigth; i++) {
            this.mapa.add(new ArrayList<Character>());

            provisional = mapa.substring(posIni,posFin).toCharArray();

            for(int j = 0; j < provisional.length; j++){
                this.mapa.get(i).add((provisional[j] != 'X') ? provisional[j] : ' ');

                if(provisional[j] != ' ' && provisional[j] != '#'){
                    if(this.items.containsKey(new String(""+provisional[j]))){
                        this.items.put("T2",new Item(i,j));
                    }
                    else{
                        this.items.put(new String(new String(""+provisional[j])), new Item(i,j));
                    }
                }
            }
            posIni = posFin + 1;
            posFin = (mapa.indexOf("\n", posIni) == -1) ? mapa.length() : mapa.indexOf("\n", posIni);
        }
    }

    public String run(){
        Robot b = new Robot();
        int [] proCoor;
        boolean rebootMove = false;
        StringBuilder timeToDrink = new StringBuilder();
        while(true){
            proCoor = b.moveRobot(this.items.get("X").getPositions());
            if(this.mapa.get(proCoor[0]).get(proCoor[1]) == '#'){
                if(rebootMove){
                    b.setPosNow(0);
                    rebootMove = false;
                    continue;
                }
                b.setPosNow(b.getPos() + 1);
                continue;
            }
            this.items.put("X",new Item(proCoor[0],proCoor[1]));
            rebootMove = true;
            timeToDrink.append(b.getMove());
            if(this.mapa.get(proCoor[0]).get(proCoor[1]) == ' '){
                continue;
            }
            else if(changeStat(proCoor,b)){
                return timeToDrink.toString();
            }
        }
    }

    private boolean changeStat(int [] proCoor, Robot b){
        Item provCoor = new Item(proCoor[0],proCoor[1]);
        if(this.items.containsKey("I") && this.items.get("I").equals(provCoor)){
            b.changeDir();
            b.setPosNow(0);
        }
        else if(this.items.containsKey("$") && this.items.get("$").equals(provCoor)){
            return true;
        }
        else {
            Item newProvCoor = (this.items.get("T").equals(provCoor)) ? this.items.get("T2") : this.items.get("T");
            this.items.put("X",newProvCoor);
        }
        return false;
    }
    /*public String run(){
        Robot b = new Robot();
        int [] provisionalCoordinates;
        char proValue;//, lastProValue = ' ';
        boolean rebootMove = false;
        StringBuilder timeToDrink = new StringBuilder();
        while(true){
            provisionalCoordinates = b.moveRobot(this.coordinatesB);
            if(map[provisionalCoordinates[0]][provisionalCoordinates[1]] == '#'){
                if(rebootMove){
                    b.setPosNow(0);
                    rebootMove = false;
                    continue;
                }
                b.setPosNow(b.getPos() + 1);
                continue;
            }
            else{
                proValue = map[provisionalCoordinates[0]][provisionalCoordinates[1]];
                map[provisionalCoordinates[0]][provisionalCoordinates[1]] = 'X';
                map[this.coordinatesB[0]][this.coordinatesB[1]] = ' ';
                this.coordinatesB = provisionalCoordinates;
                timeToDrink.append(b.getMove());
                //lastProValue = proValue;
                rebootMove = true;


                if(changeStat(proValue,b)){
                    return timeToDrink.toString();
                }
            }

        }
    }

    private boolean changeStat(char proValue, Robot b) {
        switch (proValue){
            case 'I':
                b.changeDir();
                b.setPosNow(0);
                break;
            case 'T':
                int posx = this.coordinatesB[0];
                int posy = this.coordinatesB[1];
                if(this.coordinatesB[0] == this.coorTeleport[0][0] && this.coordinatesB[1] == this.coorTeleport[0][1]){
                    this.coordinatesB[0] = this.coorTeleport[1][0];
                    this.coordinatesB[1] = this.coorTeleport[1][1];
                }
                else{
                    this.coordinatesB[0] = this.coorTeleport[0][0];
                    this.coordinatesB[1] = this.coorTeleport[0][1];
                }
                map[posx][posy] = 'T';
                break;
            case '$':
                return true;
        }
        return false;
    }*/
}
