package simulation.initializers;

import simulation.DefaultValuesConfig;
import simulation.Position;
import simulation.pedestrains.Pedestrain;
import simulation.space.Space;
import simulation.space.Wall;

import java.util.LinkedList;
import java.util.Random;


public class DefaultInitializer {

    private LinkedList<Pedestrain> pedestrains_;
    public LinkedList<Pedestrain> getPedestrains_() {
        return pedestrains_;
    }
    public Space getSpace_() {
        return space_;
    }
    private Space space_;

    public DefaultInitializer(LinkedList<Pedestrain> pedestrains, Space space){
        pedestrains_ = pedestrains;
        space_ = space;
    }

    public DefaultInitializer(){
        pedestrains_ = new LinkedList<>();
        space_ = new Space();

        // Pedestrians
        switch(DefaultValuesConfig.VERSION) {
            case 1:
                pedestrains_.add(new Pedestrain(new Position(300, 300), new Position(500, 500), 1));
                pedestrains_.add(new Pedestrain(new Position(310, 310), new Position(500, 500), 2));
                pedestrains_.add(new Pedestrain(new Position(300, 320), new Position(500, 500), 3));
                pedestrains_.add(new Pedestrain(new Position(301, 320), new Position(500, 500), 4));
                pedestrains_.add(new Pedestrain(new Position(311, 320), new Position(500, 500), 5));
                pedestrains_.add(new Pedestrain(new Position(500, 500), new Position(300, 300), 6));
                pedestrains_.add(new Pedestrain(new Position(511, 501), new Position(300, 300), 7));
                pedestrains_.add(new Pedestrain(new Position(502, 512), new Position(300, 300), 8));
                pedestrains_.add(new Pedestrain(new Position(502, 502), new Position(300, 300), 9));
                pedestrains_.add(new Pedestrain(new Position(512, 502), new Position(300, 300), 10));
                break;
            case 3:
                pedestrains_.add(new Pedestrain(new Position(300, 300), new Position(500, 500),1));
                pedestrains_.add(new Pedestrain(new Position(380, 310), new Position(500, 500),2));
                pedestrains_.add(new Pedestrain(new Position(320, 310), new Position(500, 500),3));
                pedestrains_.add(new Pedestrain(new Position(300, 310), new Position(500, 500),4));
                pedestrains_.add(new Pedestrain(new Position(350, 320), new Position(500, 500),5));
                pedestrains_.add(new Pedestrain(new Position(410, 280), new Position(500, 500),6));
                pedestrains_.add(new Pedestrain(new Position(310, 220), new Position(500, 500),7));
                pedestrains_.add(new Pedestrain(new Position(420, 320), new Position(500, 500),8));
                pedestrains_.add(new Pedestrain(new Position(320, 290), new Position(500, 500),9));
                pedestrains_.add(new Pedestrain(new Position(295, 315), new Position(500, 500),10));
                pedestrains_.add(new Pedestrain(new Position(345, 315), new Position(500, 500),11));
                pedestrains_.add(new Pedestrain(new Position(370, 315), new Position(500, 500),12));
                pedestrains_.add(new Pedestrain(new Position(325, 400), new Position(500, 500),13));
                pedestrains_.add(new Pedestrain(new Position(375, 300), new Position(500, 500),14));
                pedestrains_.add(new Pedestrain(new Position(285, 345), new Position(500, 500),15));
                break;

            case 6:
                pedestrains_.add(new Pedestrain(new Position(367.0, 186.0), new Position(550, 50),1));
                pedestrains_.add(new Pedestrain(new Position(410.0, 215.0), new Position(550, 50),2));
                pedestrains_.add(new Pedestrain(new Position(410.0, 181.0), new Position(550, 50),3));
                pedestrains_.add(new Pedestrain(new Position(433.0, 152.0), new Position(550, 50),4));
                pedestrains_.add(new Pedestrain(new Position(471.0, 135.0), new Position(550, 50),5));
                pedestrains_.add(new Pedestrain(new Position(475.0, 184.0), new Position(550, 50),6));
                pedestrains_.add(new Pedestrain(new Position(443.0, 237.0), new Position(550, 50),7));
                pedestrains_.add(new Pedestrain(new Position(394.0, 261.0), new Position(550, 50),8));
                pedestrains_.add(new Pedestrain(new Position(457.0, 295.0), new Position(550, 50),9));
                pedestrains_.add(new Pedestrain(new Position(490.0, 250.0), new Position(550, 50),10));
                pedestrains_.add(new Pedestrain(new Position(498.0, 199.0), new Position(550, 50),11));
                pedestrains_.add(new Pedestrain(new Position(520.0, 150.0), new Position(550, 50),12));
                pedestrains_.add(new Pedestrain(new Position(545.0, 119.0), new Position(550, 50),13));
                pedestrains_.add(new Pedestrain(new Position(576.0, 136.0), new Position(550, 50),14));
                pedestrains_.add(new Pedestrain(new Position(584.0, 206.0), new Position(550, 50),15));
                pedestrains_.add(new Pedestrain(new Position(528.0, 233.0), new Position(550, 50),16));
                pedestrains_.add(new Pedestrain(new Position(534.0, 291.0), new Position(550, 50),17));
                pedestrains_.add(new Pedestrain(new Position(568.0, 265.0), new Position(550, 50),18));
                pedestrains_.add(new Pedestrain(new Position(596.0, 232.0), new Position(550, 50),19));
                pedestrains_.add(new Pedestrain(new Position(637.0, 220.0), new Position(550, 50),20));
                pedestrains_.add(new Pedestrain(new Position(686.0, 258.0), new Position(550, 50),21));
                pedestrains_.add(new Pedestrain(new Position(659.0, 320.0), new Position(550, 50),22));
                pedestrains_.add(new Pedestrain(new Position(589.0, 386.0), new Position(550, 50),23));
                pedestrains_.add(new Pedestrain(new Position(533.0, 400.0), new Position(550, 50),24));
                pedestrains_.add(new Pedestrain(new Position(437.0, 433.0), new Position(550, 50),25));
                pedestrains_.add(new Pedestrain(new Position(373.0, 439.0), new Position(550, 50),26));
                pedestrains_.add(new Pedestrain(new Position(333.0, 417.0), new Position(550, 50),27));
                pedestrains_.add(new Pedestrain(new Position(339.0, 249.0), new Position(550, 50),28));
                pedestrains_.add(new Pedestrain(new Position(380.0, 299.0), new Position(550, 50),29));
                pedestrains_.add(new Pedestrain(new Position(315.0, 337.0), new Position(550, 50),30));
                pedestrains_.add(new Pedestrain(new Position(398.0, 372.0), new Position(550, 50),31));
                pedestrains_.add(new Pedestrain(new Position(337.0, 283.0), new Position(550, 50),32));
                pedestrains_.add(new Pedestrain(new Position(391.0, 327.0), new Position(550, 50),33));
                pedestrains_.add(new Pedestrain(new Position(464.0, 336.0), new Position(550, 50),34));
                pedestrains_.add(new Pedestrain(new Position(496.0, 396.0), new Position(550, 50),35));
                pedestrains_.add(new Pedestrain(new Position(257.0, 440.0), new Position(550, 50),36));
                pedestrains_.add(new Pedestrain(new Position(326.0, 476.0), new Position(550, 50),37));
                break;
            case 7:
                pedestrains_.add(new Pedestrain(new Position(433.0, 279.0), new Position(510, 50),1));
                pedestrains_.add(new Pedestrain(new Position(426.0, 221.0), new Position(510, 50),2));
                pedestrains_.add(new Pedestrain(new Position(476.0, 226.0), new Position(510, 50),3));
                pedestrains_.add(new Pedestrain(new Position(518.0, 230.0), new Position(510, 50),4));
                pedestrains_.add(new Pedestrain(new Position(512.0, 203.0), new Position(510, 50),5));
                pedestrains_.add(new Pedestrain(new Position(440.0, 181.0), new Position(510, 50),6));
                pedestrains_.add(new Pedestrain(new Position(496.0, 345.0), new Position(510, 50),7));
                pedestrains_.add(new Pedestrain(new Position(457.0, 146.0), new Position(510, 50),8));
                pedestrains_.add(new Pedestrain(new Position(506.0, 144.0), new Position(510, 50),9));
                pedestrains_.add(new Pedestrain(new Position(489.0, 287.0), new Position(510, 50),10));
                pedestrains_.add(new Pedestrain(new Position(393.0, 306.0), new Position(510, 50),11));
                pedestrains_.add(new Pedestrain(new Position(469.0, 329.0), new Position(510, 50),12));
                pedestrains_.add(new Pedestrain(new Position(521.0, 310.0), new Position(510, 50),13));
                pedestrains_.add(new Pedestrain(new Position(411.0, 247.0), new Position(510, 50),14));
                pedestrains_.add(new Pedestrain(new Position(460.0, 248.0), new Position(510, 50),15));
                pedestrains_.add(new Pedestrain(new Position(515.0, 256.0), new Position(510, 50),16));
                pedestrains_.add(new Pedestrain(new Position(515.0, 383.0), new Position(510, 50),17));
                pedestrains_.add(new Pedestrain(new Position(519.0, 266.0), new Position(510, 50),18));
                pedestrains_.add(new Pedestrain(new Position(417.0, 258.0), new Position(510, 50),19));
                pedestrains_.add(new Pedestrain(new Position(463.0, 262.0), new Position(510, 50),20));
                pedestrains_.add(new Pedestrain(new Position(376.0, 308.0), new Position(510, 50),21));
                pedestrains_.add(new Pedestrain(new Position(417.0, 330.0), new Position(510, 50),22));
                pedestrains_.add(new Pedestrain(new Position(467.0, 347.0), new Position(510, 50),23));
                pedestrains_.add(new Pedestrain(new Position(479.0, 177.0), new Position(510, 50),24));
                pedestrains_.add(new Pedestrain(new Position(510.0, 357.0), new Position(510, 50),25));
                pedestrains_.add(new Pedestrain(new Position(460.0, 362.0), new Position(510, 50),26));
                pedestrains_.add(new Pedestrain(new Position(399.0, 361.0), new Position(510, 50),27));
                pedestrains_.add(new Pedestrain(new Position(300.0, 460.0), new Position(510, 50),28));
                pedestrains_.add(new Pedestrain(new Position(363.0, 412.0), new Position(510, 50),29));
                pedestrains_.add(new Pedestrain(new Position(454.0, 420.0), new Position(510, 50),30));
                pedestrains_.add(new Pedestrain(new Position(345.0, 346.0), new Position(510, 50),31));
                pedestrains_.add(new Pedestrain(new Position(423.0, 364.0), new Position(510, 50),32));
                break;
            case 8:
                pedestrains_.add(new Pedestrain(new Position(353.0, 394.0), new Position(525, 245),1));
                pedestrains_.add(new Pedestrain(new Position(424.0, 407.0), new Position(525, 245),2));
                pedestrains_.add(new Pedestrain(new Position(373.0, 369.0), new Position(525, 245),3));
                pedestrains_.add(new Pedestrain(new Position(408.0, 383.0), new Position(525, 245),4));
                pedestrains_.add(new Pedestrain(new Position(453.0, 382.0), new Position(525, 245),5));
                pedestrains_.add(new Pedestrain(new Position(485.0, 376.0), new Position(525, 245),6));
                pedestrains_.add(new Pedestrain(new Position(444.0, 344.0), new Position(525, 245),7));
                pedestrains_.add(new Pedestrain(new Position(406.0, 325.0), new Position(525, 245),8));
                pedestrains_.add(new Pedestrain(new Position(418.0, 315.0), new Position(525, 245),9));

                pedestrains_.add(new Pedestrain(new Position(452.0, 262.0), new Position(370, 385),10));
                pedestrains_.add(new Pedestrain(new Position(485.0, 277.0), new Position(370, 385),11));
                pedestrains_.add(new Pedestrain(new Position(520.0, 323.0), new Position(370, 385),12));
                pedestrains_.add(new Pedestrain(new Position(492.0, 250.0), new Position(370, 385),13));
                pedestrains_.add(new Pedestrain(new Position(449.0, 223.0), new Position(370, 385),14));
                pedestrains_.add(new Pedestrain(new Position(523.0, 217.0), new Position(370, 385),15));
                pedestrains_.add(new Pedestrain(new Position(588.0, 269.0), new Position(370, 385),16));
                pedestrains_.add(new Pedestrain(new Position(564.0, 234.0), new Position(370, 385),17));
                pedestrains_.add(new Pedestrain(new Position(545.0, 269.0), new Position(370, 385),18));
                break;
            default:
                    break;
        }


        // Space
        if(DefaultValuesConfig.VERSION==6|| DefaultValuesConfig.VERSION==7){
            space_.addWall(new Wall(new Position (150, 550), new Position(700, 650)));
            space_.addWall(new Wall(new Position (700, 650), new Position(800, 170)));
            space_.addWall(new Wall(new Position (150, 550), new Position(250, 50)));
            space_.addWall(new Wall(new Position (250, 50), new Position(470, 90)));
            space_.addWall(new Wall(new Position (525, 105), new Position(800, 170)));

        }
        if(DefaultValuesConfig.VERSION==6){
            space_.addWall(new Wall(new Position(502, 97), new Position(490, 150)));
        }
        if(DefaultValuesConfig.VERSION==7){
            space_.addWall(new Wall(new Position(470, 90), new Position(459, 118)));
            space_.addWall(new Wall(new Position(525, 105), new Position(523, 140)));
        }
        if(DefaultValuesConfig.VERSION==8){
            space_.addWall(new Wall(new Position(259, 182), new Position(422, 289)));
            space_.addWall(new Wall(new Position(489, 339), new Position(684, 491)));
        }
    }
}
