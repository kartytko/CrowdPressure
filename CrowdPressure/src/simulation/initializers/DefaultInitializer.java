package simulation.initializers;

import simulation.DefaultValuesConfig;
import simulation.Position;
import simulation.pedestrains.Pedestrain;
import simulation.space.Space;
import simulation.space.Wall;

import java.util.LinkedList;
import java.util.Random;


// TO-DO: POSPRZĄTAĆ. Automatyczna inicjalizacja pieszych.
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

        // Pedestrians
        pedestrains_ = new LinkedList<>();
        Random generator = new Random();
        // TO-DO zmienić sztywno wpisane dane na modele

        if(DefaultValuesConfig.VERSION==1){
            Pedestrain pedestrain = new Pedestrain(new Position(300, 300), new Position(500, 500),1);
            Pedestrain pedestrain2 = new Pedestrain(new Position(310, 310), new Position(500, 500),2);
            Pedestrain pedestrain3 = new Pedestrain(new Position(300, 320), new Position(500, 500),3);
            Pedestrain pedestrain7 = new Pedestrain(new Position(301, 320), new Position(500, 500),3);
            Pedestrain pedestrain8 = new Pedestrain(new Position(311, 320), new Position(500, 500),3);
            Pedestrain pedestrain4 = new Pedestrain(new Position(500, 500), new Position(300, 300),3);
            Pedestrain pedestrain5 = new Pedestrain(new Position(511, 501), new Position(300, 300),3);
            Pedestrain pedestrain6 = new Pedestrain(new Position(502, 512), new Position(300, 300),3);
            Pedestrain pedestrain9 = new Pedestrain(new Position(502, 502), new Position(300, 300),3);
            Pedestrain pedestrain10 = new Pedestrain(new Position(512, 502), new Position(300, 300),3);

            pedestrains_.add(pedestrain);
            pedestrains_.add(pedestrain2);
            pedestrains_.add(pedestrain3);
            pedestrains_.add(pedestrain4);
            pedestrains_.add(pedestrain5);
            pedestrains_.add(pedestrain6);
            pedestrains_.add(pedestrain7);
            pedestrains_.add(pedestrain8);
            pedestrains_.add(pedestrain9);
            pedestrains_.add(pedestrain10);
        }
        if(DefaultValuesConfig.VERSION==2){
            //Pedestrain pedestrain = new Pedestrain(new Position(600, 650), new Position(600, 100),1);
            Pedestrain pedestrain = new Pedestrain(new Position(600, 450), new Position(100, 100),1);
            pedestrains_.add(pedestrain);
        }

        if(DefaultValuesConfig.VERSION==3){
            Pedestrain pedestrain = new Pedestrain(new Position(300, 300), new Position(500, 500),1);
            Pedestrain pedestrain2 = new Pedestrain(new Position(380, 310), new Position(500, 500),2);
            Pedestrain pedestrain3 = new Pedestrain(new Position(320, 310), new Position(500, 500),2);
            Pedestrain pedestrain4 = new Pedestrain(new Position(300, 310), new Position(500, 500),2);
            Pedestrain pedestrain5 = new Pedestrain(new Position(350, 320), new Position(500, 500),2);
            Pedestrain pedestrain6 = new Pedestrain(new Position(410, 280), new Position(500, 500),2);
            Pedestrain pedestrain7 = new Pedestrain(new Position(310, 220), new Position(500, 500),2);
            Pedestrain pedestrain8 = new Pedestrain(new Position(420, 320), new Position(500, 500),2);
            Pedestrain pedestrain9 = new Pedestrain(new Position(320, 290), new Position(500, 500),2);
            Pedestrain pedestrain10 = new Pedestrain(new Position(295, 315), new Position(500, 500),2);
            Pedestrain pedestrain11 = new Pedestrain(new Position(345, 315), new Position(500, 500),2);
            Pedestrain pedestrain12 = new Pedestrain(new Position(370, 315), new Position(500, 500),2);
            Pedestrain pedestrain13 = new Pedestrain(new Position(325, 400), new Position(500, 500),2);
            Pedestrain pedestrain14 = new Pedestrain(new Position(375, 300), new Position(500, 500),2);
            Pedestrain pedestrain15 = new Pedestrain(new Position(285, 345), new Position(500, 500),2);
            pedestrains_.add(pedestrain);
            pedestrains_.add(pedestrain2);
            pedestrains_.add(pedestrain3);
            pedestrains_.add(pedestrain4);
            pedestrains_.add(pedestrain5);
            pedestrains_.add(pedestrain6);
            pedestrains_.add(pedestrain7);
            pedestrains_.add(pedestrain8);
            pedestrains_.add(pedestrain9);
            pedestrains_.add(pedestrain10);
            pedestrains_.add(pedestrain11);
            pedestrains_.add(pedestrain12);
            pedestrains_.add(pedestrain13);
            pedestrains_.add(pedestrain14);
            pedestrains_.add(pedestrain15);
        }
        if(DefaultValuesConfig.VERSION==5){
            for(int i=1; i<10; i++){
                pedestrains_.add(new Pedestrain(new Position(321+10*i, 400+(i*7)), new Position(550, 50), 18+i));
                pedestrains_.add(new Pedestrain(new Position(371+10*i, 200-(i*7)), new Position(550, 50), 18+i));
                pedestrains_.add(new Pedestrain(new Position(471+10*i, 400-(i*7)), new Position(550, 50), 18+i));
            }
        }
        if(DefaultValuesConfig.VERSION==4 || DefaultValuesConfig.VERSION==5){
            Pedestrain pedestrain1 = new Pedestrain(new Position(300,450), new Position(550,50),1);
            Pedestrain pedestrain2 = new Pedestrain(new Position(301,455), new Position(550,50),2);
            Pedestrain pedestrain3 = new Pedestrain(new Position(320,500), new Position(550,50),3);
            Pedestrain pedestrain4 = new Pedestrain(new Position(370,350), new Position(550,50),4);
            Pedestrain pedestrain5 = new Pedestrain(new Position(300,250), new Position(550,50),5);
            Pedestrain pedestrain6 = new Pedestrain(new Position(420,150), new Position(550,50),6);
            //Pedestrain pedestrain7 = new Pedestrain(new Position(410,150), new Position(550,50),7);
            //Pedestrain pedestrain8 = new Pedestrain(new Position(400,150), new Position(550,50),8);
            Pedestrain pedestrain9 = new Pedestrain(new Position(420,170), new Position(550,50),9);
            Pedestrain pedestrain10 = new Pedestrain(new Position(410,170), new Position(550,50),10);
            Pedestrain pedestrain11 = new Pedestrain(new Position(400,170), new Position(550,50),11);
            Pedestrain pedestrain12 = new Pedestrain(new Position(430,170), new Position(550,50),12);
            Pedestrain pedestrain13 = new Pedestrain(new Position(440,170), new Position(550,50),13);
            Pedestrain pedestrain14 = new Pedestrain(new Position(450,170), new Position(550,50),14);
            Pedestrain pedestrain15 = new Pedestrain(new Position(460,170), new Position(550,50),15);
            Pedestrain pedestrain16 = new Pedestrain(new Position(470,170), new Position(550,50),16);
            Pedestrain pedestrain17 = new Pedestrain(new Position(480,170), new Position(550,50),17);
            Pedestrain pedestrain18 = new Pedestrain(new Position(490,170), new Position(550,50),18);
            pedestrains_.add(pedestrain1);
            pedestrains_.add(pedestrain2);
            pedestrains_.add(pedestrain3);
            pedestrains_.add(pedestrain4);
            pedestrains_.add(pedestrain5);
            pedestrains_.add(pedestrain6);
            //pedestrains_.add(pedestrain7);
            //pedestrains_.add(pedestrain8);
            pedestrains_.add(pedestrain9);
            pedestrains_.add(pedestrain10);
            pedestrains_.add(pedestrain11);
            pedestrains_.add(pedestrain12);
            pedestrains_.add(pedestrain13);
            pedestrains_.add(pedestrain14);
            pedestrains_.add(pedestrain15);
            pedestrains_.add(pedestrain16);
            pedestrains_.add(pedestrain17);
            pedestrains_.add(pedestrain18);
        }




        // Space
        space_ = new Space();

        if(DefaultValuesConfig.VERSION==0) {
            space_.addWall(new Wall(new Position(0, 200), new Position(450, 220)));
            space_.addWall(new Wall(new Position(550, 220), new Position(1000, 200)));
        }
        if(DefaultValuesConfig.VERSION==1){
            space_.addWall(new Wall(new Position(250, 450), new Position(400, 400)));

        }
        if(DefaultValuesConfig.VERSION==2){
            space_.addWall(new Wall(new Position(450, 400), new Position(650, 350)));
        }
        if(DefaultValuesConfig.VERSION==4 || DefaultValuesConfig.VERSION==5){
            space_.addWall(new Wall(new Position (150, 550), new Position(700, 650)));
            space_.addWall(new Wall(new Position (700, 650), new Position(800, 170)));
            space_.addWall(new Wall(new Position (150, 550), new Position(250, 50)));
            space_.addWall(new Wall(new Position (250, 50), new Position(470, 90)));
            space_.addWall(new Wall(new Position (525, 105), new Position(800, 170)));

        }
        if(DefaultValuesConfig.VERSION==5){
            space_.addWall(new Wall(new Position(502, 97), new Position(490, 150)));
        }
    }
}
