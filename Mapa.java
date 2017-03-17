import java.util.*;

/**
 * Created by Oscar on 16/03/2017.
 */
public class Mapa {
    List<List<Character>> map = new ArrayList<List<Character>>();
    Map<Item,Character> items = new HashMap<Item,Character>();
    //Map<int [],Character> items = new HashMap<>();

    public Mapa(String mapa){
        int width = mapa.indexOf("\n");
        int heigth = (mapa.length() % width == 0) ? ((mapa.length() / width) - 1) : mapa.length() / width;
        char [] provisional;

        for (int i = 0, posIni = 0, posFin = width; i < heigth; i++) {
            this.map.add(new ArrayList<Character>());

            provisional = mapa.substring(posIni,posFin).toCharArray();

            for(int j = 0; j < provisional.length; j++){
                this.map.get(i).add((provisional[j] != 'X') ? provisional[j] : ' ');

                if(provisional[j] != ' ' && provisional[j] != '#'){
                    int [] nItem = {i,j};
                    this.items.put(new Item(nItem),provisional[j]);
                    //this.items.put(nItem,provisional[j]);
                }
            }
            posIni = posFin + 1;
            posFin = (mapa.indexOf("\n", posIni) == -1) ? mapa.length() : mapa.indexOf("\n", posIni);
        }
    }

    public int [] getBPos(){

        Set<Item> keys = this.items.keySet();
        //Set<int []> keys = this.items.keySet();
        Iterator it = keys.iterator();

        while(it.hasNext()){
            Item provisional = (Item)it.next();
            if(this.items.get(provisional) == 'X'){
                this.items.remove(provisional);
                return provisional.getPosition();
            /*int [] provisional = (int []) it.next();
            if(this.items.get(provisional) == 'X'){
                this.items.remove(provisional);
                return provisional;*/
            }
        }
        return null;
    }

    public Map<Item,Character> getItems(){
    //public Map<int[],Character> getItems(){
        return this.items;
    }

    public char getChar(int [] positions){
        return this.map.get(positions[0]).get(positions[1]);
    }
}
