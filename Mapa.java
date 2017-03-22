import java.util.*;
/**
 * Created by Oscar on 16/03/2017.
 */
class Mapa {
    private List<List<Character>> map = new ArrayList<>();
    private List<Item> teleports = new ArrayList<>();
    private Item bPos;
    private Item wPos;

    Mapa(String mapa) {
        String [] lineMap = mapa.split("\n");
        for(int i = 0; i < lineMap.length; i++){
            this.map.add(new ArrayList<Character>());
            for(int j = 0; j < lineMap[i].length(); j++){
                if (lineMap[i].charAt(j) == 'X') {
                    this.map.get(i).add(' ');
                    this.bPos = new Item(new int[]{i, j});
                    continue;
                } else if (lineMap[i].charAt(j) == '$') {
                    this.wPos = new Item(new int[]{i, j});
                }
                this.map.get(i).add(lineMap[i].charAt(j));
                if (lineMap[i].charAt(j) == 'T') {
                    this.teleports.add(new Item(new int[]{i, j}));
                }
            }
        }
    }

    Item getBPos() {
        if (this.bPos != null) {
            return this.bPos;
        }
        return new Item(new int[]{-1, -1});
    }

    List<Item> getTeleports() {
        return this.teleports;
    }

    char getChar(int[] positions) {
        return this.map.get(positions[0]).get(positions[1]);
    }

    Item getWinner() {
        if (this.wPos != null) {
            return this.wPos;
        }
        return new Item(new int[]{-1, -1});
    }
}