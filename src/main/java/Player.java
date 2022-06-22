import java.util.List;
import java.util.Map;

public class Player {
    private Position position;
    private Direction direction;


    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
