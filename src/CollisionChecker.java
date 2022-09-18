public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum, tileNum1;

        switch (entity.direction) {
            case ("up") -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum1 = gp.manager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.manager.tiles[tileNum].collision || gp.manager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
            }
            case ("down") -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum1 = gp.manager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.manager.tiles[tileNum].collision || gp.manager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
            }
            case ("right") -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityRightCol][entityTopRow];
                tileNum1 = gp.manager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.manager.tiles[tileNum].collision || gp.manager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
            }
            case ("left") -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum1 = gp.manager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.manager.tiles[tileNum].collision || gp.manager.tiles[tileNum1].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
}
