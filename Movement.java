import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by Oscar on 17/03/2017.
 */
class Movement {
    enum TMove{
        S,
        E,
        N,
        W
    }

    private List<TMove> move = new ArrayList<>();
    private int dirNow;

    Movement() {
        Collections.addAll(this.move,TMove.values());
    }

    TMove getMove() {
        return this.move.get(this.dirNow);
    }

    int getDirNow() {
        return this.dirNow;
    }

    void setDirNow(int pos) {
        this.dirNow = (pos > 3) ? 0 : pos;
    }

    void changeDir() {
        TMove provisional;
        for(int i = 0; i < 2; i++){
            provisional = this.move.get(i + 2);
            this.move.set(i + 2,this.move.get(i));
            this.move.set(i,provisional);
        }
    }

    int[] moving(Item posNow) {
        int[] retoorn = new int[posNow.getPosition().length];
        System.arraycopy(posNow.getPosition(), 0, retoorn, 0, 2);
        switch (this.getMove()) {
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
}