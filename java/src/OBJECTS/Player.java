package OBJECTS;

import java.util.ArrayList;

import processing.core.PVector;

public class Player extends VisualSetup{

    //declare variables.
    public PVector position;
    public long prevTime, timePassed;
    public int diameter, dmg, dmgCooldown;
    private VisualSetup p;



    //constructor
    public Player(int width, int height, int dmg, VisualSetup p){

        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
        this.p = p;
        this.dmg = 150;
        this.dmgCooldown = 500;
        this.prevTime = System.currentTimeMillis();

    }//end method 



    public void updatePlayer(ArrayList<Enemy> enemies){

        //draw player
        p.fill(0, 0, 100);
        p.strokeWeight(3);
        p.stroke(abs(p.frameCount%360), 100, 100);
        p.ellipse(position.x, position.y, diameter, diameter);

        //draw player fire
        fire(enemies);

    }//end method



    public void fire(ArrayList<Enemy> enemies){

        float closest = Float.MAX_VALUE;
        timePassed = System.currentTimeMillis() - prevTime;

        //dummy Enemy object
        Enemy enemy = new Enemy(0, 0, 0, 0, 0, p);

        //find closest enemy to player
        for(Enemy e : enemies){

            if(e.distance < closest && e.health > 0){

                closest = e.distance;
                enemy = e;

            }//end if
        }//end for


        //calculate point of origin for weapon fire
        PVector rectCenter = new PVector(enemy.position.x + enemy.size/2, enemy.position.y + enemy.size/2);
        PVector circleSurf = findCircleSurf(rectCenter, position, (float)diameter/2);

        //draw player fire
        p.line(circleSurf.x, circleSurf.y, rectCenter.x, rectCenter.y);


        //deal damage
        if(timePassed > dmgCooldown){

            prevTime = System.currentTimeMillis();
            enemy.health -= dmg;

        }//end if
    }//end method



    PVector findCircleSurf(PVector rectCenter, PVector circleCenter, float radius){

        PVector offset  = PVector.sub(circleCenter, rectCenter);          
        offset.normalize();
        PVector p2 = PVector.sub(circleCenter, offset.mult(radius));

        //return pVector containing origin of player fire
        return p2;

    }//end method
}//end class
