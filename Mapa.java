import java.util.*;

/**
 * Created by Oscar on 16/03/2017.
 */
public class Mapa {
    List<List<Character>> map = new ArrayList<List<Character>>();
    Map<Item,Character> items = new HashMap<Item,Character>();

    public Mapa(String mapa){
        String [] lineMap = mapa.split("\n");

        for(int i = 0; i < lineMap.length; i++){
            this.map.add(new ArrayList<Character>());
            for(int j = 0; j < lineMap[i].length(); j++){

                this.map.get(i).add((lineMap[i].charAt(j) != 'X') ? lineMap[i].charAt(j) : ' ');

                if(lineMap[i].charAt(j) != ' ' && lineMap[i].charAt(j) != '#'){
                    int [] nItem = {i,j};
                    this.items.put(new Item(nItem),lineMap[i].charAt(j));
                }
            }
        }
    }

    public int [] getBPos(){
        Set<Item> keys = this.items.keySet();
        Iterator it = keys.iterator();
        while(it.hasNext()){
            Item provisional = (Item)it.next();
            if(this.items.get(provisional) == 'X'){
                this.items.remove(provisional);
                return provisional.getPosition();
            }
        }
        return null;
    }

    public Map<Item,Character> getItems(){ return this.items; }

    public char getChar(int [] positions){
        return this.map.get(positions[0]).get(positions[1]);
    }
}
