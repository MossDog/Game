package OBJECTS;

import processing.data.Table;
import processing.data.TableRow;

public class HUD {

    VisualSetup v;
    Table statsTable;
    
    public HUD(VisualSetup v_){
        this.v = v_;
    }

    public void setStatsTable(Table stats){
        statsTable = stats;
    }

    public void display(){
        int xOffset = (int)((v.width/3) * 0.5f);
        int yOffset = v.statsTable.getColumnCount()%2 == 0 ? v.height/(v.statsTable.getColumnCount()/2) : v.height/((v.statsTable.getColumnCount()/2)+1); //if list of upgrades is odd then ensure there is enough space for the last item.
        v.strokeWeight(2);
        v.stroke(255);
        v.fill(0);
        v.rect(v.width * 2/3, 0, (v.width/3)-1, v.height-1, 10);

        v.textSize(20);
        v.textAlign(processing.core.PApplet.CENTER);
        v.stroke(255);
        v.fill(255);

        v.pushMatrix();
        v.translate(v.width * 2/3, 0);

        TableRow statsRow = v.statsTable.getRow(0);
        TableRow priceRow = v.statsTable.getRow(1);
        TableRow multRow = v.statsTable.getRow(2);

        for(int i = 0; i < v.statsTable.getColumnCount(); i++){
            String column = v.statsTable.getColumnTitle(i);
            v.text("PRICE: " + priceRow.getInt(column) + "\nUPGRADE "+ column.toUpperCase() +"\n"+statsRow.getInt(column)+" +"+multRow.getString(column), xOffset/2, yOffset/2);
            if(i%2 == 0) v.translate(xOffset, 0);
            else v.translate(-xOffset, yOffset);
        }

        v.popMatrix();
    }

    /*private void debugHUD() {

        textSize(28);
        textAlign(LEFT);
        stroke(255);
        fill(255);
        text("Player Health: " + player.health, 0, 28);
        text("Player Max Health: " + player.maxHealth, 0, 28 * 2);
        text("Player Health Regen: " + player.healthRegen, 0, 28 * 3);
        text("Player Regen Cooldown: " + player.regenCooldown, 0, 28 * 4);
        text("Player Damage: " + player.damage, 0, 28 * 5);
        text("Player Damage Cooldown: " + player.damageCooldown, 0, 28 * 6);
        text("Player Points: " + player.points, 0, 28 * 7);

        text("Money: " + money, 0, 28 * 9);
        text("State: " + state, 0, 28 * 10);
        text("Round: " + round, 0, 28 * 11);

        text("enemies.size(): " + enemies.size(), 0, 28 * 13);
        text("Example Enemy Health: " + random(10, 15) + (round * 2.25f), 0, 28 * 14);
        text("Example Enemy Damage: " + random(5, 10) + (round * 1.25f), 0, 28 * 15);
        text("Example Boss Health: " + random(30, 50) + (round * 2.25f), 0, 28 * 16);
        text("Example Boss Damage: " + random(7.5f, 15) + (round * 1.25f), 0, 28 * 17);
        text("Enemy Speed: " + 700, 0, 28 * 18);

    }*/
}
