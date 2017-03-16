import java.util.*;

/**
 * Created by Oscar on 15/03/2017.
 */
public class Inversor {
    enum TMove{
        S,
        E,
        N,
        W
    }

    List<TMove> move = new ArrayList<>();

    Inversor(){
        Collections.addAll(this.move,TMove.values());
    }

    TMove getMove(int move){
        return this.move.get(move);
    }

    void changeDir(){
        TMove provisional;
        for(int i = 0; i < 2; i++){
            provisional = this.move.get(i + 2);
            this.move.set(i + 2,this.move.get(i));
            this.move.set(i,provisional);
        }
    }
}
