package OBJECTS;

import java.util.ArrayList;

import processing.core.PVector;

public class Player extends VisualSetup{

    //declare variables.
    public PVector position;
    public long prevTimeDmg, prevtimeRegen;
    public float diameter, health, maxHealth, healthRegen, regenCooldown, damage, damageCooldown,  range, points;
    private VisualSetup v;



    //constructor
    public Player(int width, int height, float maxHealth, float healthRegen, float regenCooldown, float damage, float damageCooldown, float range, VisualSetup applet){

        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
        this.prevTimeDmg = System.currentTimeMillis();
        this.prevtimeRegen = System.currentTimeMillis();
        this.v = applet;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.healthRegen = healthRegen;
        this.regenCooldown = regenCooldown;
        this.damage = damage;
        this.damageCooldown = damageCooldown;
        this.range = range;
        this.points = 0;

    }//end method



    public void updatePlayer(ArrayList<Enemy> enemies){

        //draw player
        v.fill(0, 0, 100);
        v.strokeWeight(16);
        v.stroke(abs(v.frameCount%360), 100, 100);
        v.ellipse(position.x, position.y, diameter, diameter);

        //if enemies exist player considers firing
        if(enemies.size()>0) drawBeam(enemies);
        regenHealth();

    }//end method



    private void regenHealth() {

        long timePassedRegen = System.currentTimeMillis() - prevtimeRegen;

        if(timePassedRegen > regenCooldown && health + healthRegen < maxHealth){

            prevtimeRegen = System.currentTimeMillis();
            health += healthRegen;

        }//end if

    }



    public void drawBeam(ArrayList<Enemy> enemies){

        float closest = Float.MAX_VALUE;

        //dummy Enemy object
        Enemy enemy = null;

        //find closest enemy to player
        for(Enemy e : enemies){

            if(e.distance < closest && e.health > 0){

                closest = e.distance;
                enemy = e;

            }//end if
        }//end for
        
        //only fire if enemy is in range
        if(enemy.distance < range){
            //calculate point of origin for weapon fire
            PVector rectCenter = new PVector(enemy.position.x + enemy.size/2, enemy.position.y + enemy.size/2);
            PVector circleSurf = findCircleSurf(rectCenter, position, (float)diameter/2);

            //draw player fire
            v.strokeWeight(10);
            v.line(circleSurf.x, circleSurf.y, rectCenter.x, rectCenter.y);

            dealDmg(enemy);

        }//end if
    }//end method



    public void dealDmg(Enemy enemy){

        long timePassedDmg = System.currentTimeMillis() - prevTimeDmg;

            //deal damage
            if(timePassedDmg > damageCooldown){

                prevTimeDmg = System.currentTimeMillis();
                enemy.health -= damage;

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
