/**
 * Created by Oscar on 15/03/2017.
 */
public class Bender {
    private char [][] map;
    private int [] coordinatesB = new int [2];
    private int [][] coorTeleport = new int [2][2];

    public Bender(String mapa) {
        int width = mapa.indexOf("\n");
        int heigth = (mapa.length() % width == 0) ? ((mapa.length() / width) - 1) : mapa.length() / width;
        map = new char[heigth][width];


        for (int i = 0, posIni = 0, posFin = mapa.indexOf("\n"), posT = 0; i < map.length; i++) {
            map[i] = mapa.substring(posIni,posFin).toCharArray();
            if(mapa.substring(posIni,posFin).indexOf("X") != -1){
                this.coordinatesB[0] = i;
                this.coordinatesB[1] = mapa.substring(posIni,posFin).indexOf("X");
            }
            if(mapa.substring(posIni,posFin).indexOf('T') != -1){
                this.coorTeleport[posT][0] = i;
                this.coorTeleport[posT++][1] = mapa.substring(posIni,posFin).indexOf('T');
            }

            posIni = posFin + 1;
            posFin = (mapa.indexOf("\n", posIni) == -1) ? mapa.length() : mapa.indexOf("\n", posIni);
        }
    }

    public String run(){
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
                if(this.coordinatesB[0] == this.coorTeleport[0][0] && this.coordinatesB[1] == this.coorTeleport[0][1]){
                    this.coordinatesB[0] = this.coorTeleport[1][0];
                    this.coordinatesB[1] = this.coorTeleport[1][1];
                }
                else{
                    this.coordinatesB[0] = this.coorTeleport[0][0];
                    this.coordinatesB[1] = this.coorTeleport[0][1];
                }
                break;
                /*CONTINUAR!!
                COMPROBAR QUE HACER CUANDO ENCONTRAMOS UNA T, COMPROBAR QUE FUNCIONA, COMPROBAR MUCHAS COSAS.
                break;*/
            case '$':
                return true;
        }
        return false;
    }
}
