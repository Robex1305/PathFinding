import java.util.*;
import java.util.stream.Collectors;

public class MovementUtils {
    private Character[][] map;
    private Player player;
    private Position target;

    public static List<Position> pathHistory;

    static Character freeSpaceIcon = '░';
    static Character checkIcon = ' ';
    static Character playerIcon = '^';
    static Character targetIcon = '$';

    public MovementUtils(Character[][] map, Player player, Position target) {
        this.map = map;
        this.player = player;
        this.target = target;
        pathHistory = new ArrayList<>();
    }

    public boolean targetReached() {
        return player.getPosition().equals(target);
    }

    public void movePlayer() {
        player.setDirection(getNextDirection());
        Position nextPosition = player.getPosition().apply(player.getDirection());
        Position nextPositionNew = new Position(nextPosition.getX(), nextPosition.getY());
        if(pathHistory.contains(nextPositionNew)) {
            pathHistory.get(pathHistory.indexOf(nextPositionNew)).addTimesVisited();
        } else {
            pathHistory.add(nextPositionNew);
        }
        player.setPosition(nextPosition);
    }

    public Direction getNextDirection(){
        int lowestAmountOfVisit = 1000;
        Direction preferedDirection = null;
        Position futurePosition = null;

        for (Direction direction : Direction.values()) {
            if(direction.opposite().equals(player.getDirection())){
                continue;
            }

            if(playerCanMove(direction)){
                Position futurePlayerLocation = new Position(player.getPosition().getX(), player.getPosition().getY());
                futurePlayerLocation.apply(direction);

                Position foundPosition = null;
                //If the next position have been visited
                if(pathHistory.contains(futurePlayerLocation) ){
                    foundPosition = pathHistory.get(pathHistory.indexOf(futurePlayerLocation));
                    //If the amount of visit on this position is the lowest
                    if(foundPosition.getTimesVisited() < lowestAmountOfVisit){
                        // Then it's times visited becomes the lowest
                        lowestAmountOfVisit = foundPosition.getTimesVisited();
                        // We will prefer a direction where position have been visited the less
                        preferedDirection = direction;
                        futurePosition = futurePlayerLocation;
                    }
                    else if(foundPosition.getTimesVisited() == lowestAmountOfVisit && foundPosition.getCreationTime().before(futurePosition.getCreationTime())){
                            preferedDirection = direction;
                    }
                    //If the position haven't been visited yet
                } else {
                    //We take it directly
                    preferedDirection = direction;
                    futurePosition = futurePlayerLocation;
                    break;
                }
            }
        }
        //No choices other than backward
        if(preferedDirection == null){
            preferedDirection = player.getDirection().opposite();
        }
        return preferedDirection;
    }
    public boolean playerCanMove(Direction direction) {
        Position nextPos = new Position(player.getPosition().getX(), player.getPosition().getY()).apply(direction);
        if(nextPos.getX() < 0 || nextPos.getY() < 0 || map.length <= nextPos.getY() || map[nextPos.getY()].length <= nextPos.getX()){
            return false;
        }
        Character c = map[nextPos.getY()][nextPos.getX()];
        if(c.equals(freeSpaceIcon) || c.equals(targetIcon) || Arrays.asList('0','1','2','3','4','5','6','7','8','9').contains(c)){
            return true;
        }
        return false;
    }

}
