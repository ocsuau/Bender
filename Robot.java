/**
 * Created by Oscar on 16/03/2017.
 */
public class Robot {
    private Inversor i = new Inversor();
    private int posNow;

    public int getPos(){
        return this.posNow;
    }

    public void setPosNow(int pos){

        this.posNow = (pos > 3) ? 0 : pos;
    }

    public int [] moveRobot(int [] pos){
        int [] retoorn = new int [2];
        System.arraycopy(pos,0,retoorn, 0,2);
        switch (i.getMove(this.posNow)){
            case S:
                retoorn[0]++;
                break;
            case N:
                retoorn[0]--;
                break;
            case W:
                retoorn[1]--;
                break;
            default:
                retoorn[1]++;
        }
        return retoorn;
    }

    public String getMove(){
        return i.getMove(this.posNow).toString();
    }
    void changeDir(){
        i.changeDir();
    }
}
