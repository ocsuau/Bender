import java.util.*;
/**
 * Created by Oscar on 16/03/2017.
 */
class Mapa {
    private List<List<Character>> map = new ArrayList<>();
    private Set<Position> teleports = new HashSet<>();
    private Position bPos;
    private Position wPos;

    Mapa(String mapa) {
        String [] lineMap = mapa.split("\n");
        for(int i = 0; i < lineMap.length; i++){
            this.map.add(new ArrayList<Character>());
            for(int j = 0; j < lineMap[i].length(); j++){
                if (lineMap[i].charAt(j) == 'X') {
                    this.map.get(i).add(' ');
                    this.bPos = new Position(new int[]{i, j});
                    continue;
                } else if (lineMap[i].charAt(j) == '$') {
                    this.wPos = new Position(new int[]{i, j});
                }
                this.map.get(i).add(lineMap[i].charAt(j));
                if (lineMap[i].charAt(j) == 'T') {
                    this.teleports.add(new Position(new int[]{i, j}));
                }
            }
        }
    }

    Position getBPos() {
        if (this.bPos != null) {
            return this.bPos;
        }
        return new Position(new int[]{-1, -1});
    }

    Set<Position> getTeleports() {
        return this.teleports;
    }

    char getChar(int[] positions) {
        return this.map.get(positions[0]).get(positions[1]);
    }

    Position getWinner() {
        if (this.wPos != null) {
            return this.wPos;
        }
        return new Position(new int[]{-1, -1});
    }
}