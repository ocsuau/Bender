import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Oscar on 15/03/2017.
 */
public class BenderTest {
    @Test
    public void test01() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSS", bender.run());
    }

    @Test
    public void test02() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#   $ #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEE", bender.run());
    }

    @Test
    public void test03() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#    $#\n" +
                "#     #\n" +
                "# X   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEENNN", bender.run());
    }

    @Test
    public void test04() {
        String mapa = "" +
                "#######\n" +
                "#    $#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNN", bender.run());
    }


    @Test
    public void test05() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X###\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWW", bender.run());
    }

    @Test
    public void test06() {
        String mapa = "" +
                "###########\n" +
                "#T        #\n" +
                "#  ########\n" +
                "#$        #\n" +
                "#   X     #\n" +
                "# #####  T#\n" +
                "#       ###\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("EEEEESSS", bender.run());
    }

    @Test
    public void test07() {
        String mapa = "" +
                "###########\n" +
                "#T       $#\n" +
                "#  ########\n" +
                "#         #\n" +
                "#   X     #\n" +
                "#         #\n" +
                "#       ###\n" +
                "#     T   #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSEEEEEEEEEE", bender.run());
    }


    @Test
    public void test08() {
        String mapa = "" +
                "###########\n" +
                "######    #\n" +
                "#$   #    #\n" +
                "#### #    #\n" +
                "#  # #    #\n" +
                "#  #X#    #\n" +
                "#  #I#    #\n" +
                "#  ###    #\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SNNNNWWW", bender.run());
    }

    @Test
    public void test09() {

        String mapa = "" +
                "#######\n" +
                "#T    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $ T#\n" +
                "#  X###\n" +
                "#I    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWWNNNNNNNNWWWWWW", bender.run());
    }

    @Test
    public void test10() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWWWSSSSS", bender.run());
    }

    @Test
    public void test11() {
        String mapa = "" +
                "#######\n" +
                "# #  I#\n" +
                "#     #\n" +
                "#  T  #\n" +
                "#  #  #\n" +
                "#T X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWSSS", bender.run());
    }

    @Test
    public void test12() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $  #\n" +
                "#  X  #\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SNN", bender.run());
    }

    @Test
    public void test13() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$ X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWWWSSSS", bender.run());
    }

    //Irregular
    @Test
    public void test14() {
        String mapa = "" +
                "    #######\n" +
                "    # X  T#\n" +
                "    #     #\n" +
                "#####     #\n" +
                "#T  $     #\n" +
                "#####     #\n" +
                "    #     #\n" +
                "    #######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEEENNNNNEEE", bender.run());
    }

    //Irregular
    @Test
    public void test15() {
        String mapa = "" +
                "#######\n" +
                "# X  T#\n" +
                "##    #####\n" +
                "#T       $#\n" +
                "##    #####\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEEENNNNNEEEEEEEE", bender.run());
    }

    //Irregular
    @Test
    public void test16() {
        String mapa = "" +
                "############\n" +
                "#          #\n" +
                "#      #####\n" +
                "#$     #\n" +
                "#### T #######\n" +
                "   #      X ##\n" +
                "   # T       #\n" +
                "   ###   #####\n" +
                "     #I  #\n" +
                "     #####";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWWWWWWSSEESSSSNNNNNNNWWWWWSS", bender.run());
    }

    //Irregular sin salida
    @Test
    public void test17() {
        String mapa = "" +
                "############\n" +
                "##   I     #\n" +
                "#      #####\n" +
                "#$     #\n" +
                "#### T #######\n" +
                "   #      X ##\n" +
                "   # T       #\n" +
                "   ###   #####\n" +
                "     #I  #\n" +
                "     #####";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //Sin salida
    @Test
    public void test18() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //Sin X
    @Test
    public void test19() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //Sin $
    @Test
    public void test20() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //Doble mapa
    @Test
    public void test21(){
        String mapa = "" +
                "###\n" +
                "#X#\n" +
                "#T#\n" +
                "###\n" +
                "   ###\n" +
                "   #T#\n" +
                "   #$#\n" +
                "   ###";
        Bender bender = new Bender(mapa);
        assertEquals("SS",bender.run());
    }

    //Doble mapa con T e I
    @Test
    public void test22() {
        String mapa = "" +
                "###\n" +
                "#$#\n" +
                "#X#\n" +
                "#T#\n" +
                "###\n" +
                "   ###\n" +
                "   #T#\n" +
                "   #I#\n" +
                "   # #\n" +
                "   ###";
        Bender bender = new Bender(mapa);
        assertEquals("SSSNNNN", bender.run());
    }

    //Irregular
    @Test
    public void test23() {
        String mapa = "" +
                "     #####\n" +
                "     #I  #\n" +
                "######   ######\n" +
                "#    #       I#\n" +
                "# X           #\n" +
                "######   ######\n" +
                "     #  $#\n" +
                "     #####";
        Bender bender = new Bender(mapa);
        assertEquals("EEEEEEEEEEENWWWWWWWNNSSSSSEE", bender.run());
    }

    //Irregular
    @Test
    public void test24() {
        String mapa = "" +
                "#######\n" +
                "#T    #\n" +
                "#     #############\n" +
                "#     #           #\n" +
                "#     #$         T#\n" +
                "#     #############\n" +
                "#  X###\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SNNNNNNWWWWWWWWWWWW", bender.run());
    }

    //Sólo una T
    @Test
    public void test25() {
        String mapa = "" +
                "#######\n" +
                "# #  I#\n" +
                "#     #\n" +
                "#  T  #\n" +
                "#  #  #\n" +
                "#  X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //Sin salida
    @Test
    public void test26() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#$   X#\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //"Especialito"
    @Test
    public void test27() {
        String mapa = "" +
                "#######\n" +
                "# #   #\n" +
                "#  X  #\n" +
                "#  I $#\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SNNSSEE", bender.run());
    }

    //Mapa con más movimientos que posiciones posibles
    @Test
    public void test28() {
        String mapa = "" +
                "####\n" +
                "#X$#\n" +
                "#I #\n" +
                "####";
        Bender bender = new Bender(mapa);
        assertEquals("SNSEN", bender.run());
    }

    //Mapa con más de una X
    @Test
    public void test29() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#    X#\n" +
                "#     #\n" +
                "#  $  #\n" +
                "#  X  #\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    //Mapa con más de un $
    @Test
    public void test30() {
        String mapa = "" +
                "#######\n" +
                "# $   #\n" +
                "#$    #\n" +
                "#     #\n" +
                "#  $  #\n" +
                "#  X  #\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    @Test
    public void test31() {
        String mapa = "" +
                "   #######\n" +
                "   # XTI #\n" +
                "   #    $#\n" +
                "####    #####\n" +
                "#          T#\n" +
                "####     ####\n" +
                "   #    I#\n" +
                "   #     #\n" +
                "   #######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSSEEENNNWWWWWWWEEEEEEEEEEEES", bender.run());
    }
}
