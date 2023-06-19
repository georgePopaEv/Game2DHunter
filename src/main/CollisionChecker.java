package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY  + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY  + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol   = entityLeftWorldX/gp.tileSize;
        int entityRightCol  = entityRightWorldX/gp.tileSize;
        int entityTopRow    = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tmanager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tmanager.mapTileNum[entityTopRow][entityRightCol];
                if (gp.tmanager.tile[tileNum1].collision || gp.tmanager.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tmanager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tmanager.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tmanager.tile[tileNum1].collision || gp.tmanager.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tmanager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tmanager.mapTileNum[entityBottomRow][entityLeftCol];
                if (gp.tmanager.tile[tileNum1].collision || gp.tmanager.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tmanager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gp.tmanager.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tmanager.tile[tileNum1].collision || gp.tmanager.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
