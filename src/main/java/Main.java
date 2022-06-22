public class Main {


    static Player player;
    static Position target;
    //static Character dontKnowYetIcon = '▒';

    static MovementUtils movementUtils;
    static final Character[][] map = {
            {'░','░','░','░','░','▓','▓','▓','▓','░','░','░','▓','░','░'},
            {'░','▓','▓','▓','░','░','░','░','▓','░','▓','░','░','░','░'},
            {'░','▓','░','▓','▓','▓','▓','░','▓','░','▓','░','▓','▓','▓'},
            {'░','▓','░','░','░','░','░','░','▓','░','▓','░','░','░','░'},
            {'░','▓','░','▓','▓','▓','▓','░','▓','░','▓','▓','░','▓','▓'},
            {'░','░','░','▓','░','░','░','░','░','░','░','░','░','▓','░'},
            {'░','▓','░','▓','░','▓','░','░','░','░','▓','░','░','▓','░'},
            {'░','░','░','▓','░','▓','▓','▓','▓','▓','▓','▓','░','▓','░'},
            {'░','░','░','▓','░','▓','░','░','░','░','░','░','░','░','░'},
    };

    public static void main(String[] args) throws InterruptedException {
        //////////////   INIT CODE   ////////////////
        player = new Player();
        player.setPosition(new Position(0,0));
        player.setDirection(Direction.RIGHT);
        target = new Position(5, 3);
        movementUtils = new MovementUtils(map, player, target);
        //////////////   END INIT CODE   //////////////

        while(!movementUtils.targetReached()){
            Thread.sleep(400);
            //Default direction
            movementUtils.movePlayer();
            printMap(map);
        }
        System.out.println("Victory!!!");
    }

    public static void printMap(Character[][] map){
        for (Position position : MovementUtils.pathHistory) {
            map[position.getY()][position.getX()] = (""+position.getTimesVisited()).charAt(0);
        }
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map[i].length; j++) {
                Position current = new Position(j, i);
                if(player.getPosition().equals(current)){
                    System.out.print(MovementUtils.playerIcon + "" + MovementUtils.playerIcon);
                }
                else if(target.equals(current)){
                    System.out.print(MovementUtils.targetIcon + "" + MovementUtils.targetIcon);
                }
                else{
                    System.out.print(map[i][j] + "" + map[i][j]);
                }
            }
        }
        System.out.println();
    }
}

