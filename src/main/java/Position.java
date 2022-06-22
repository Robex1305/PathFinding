import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class Position {
    private int x;
    private int y;

    private int timesVisited;
    private Date creationTime;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        this.timesVisited = 0;
        this.creationTime = new Date();
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void addX(int x){
        this.x += x;
    }

    public Date getCreationTime(){
        return creationTime;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addY(int y){
        this.y += y;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(int timesVisited) {
        this.timesVisited = timesVisited;
    }

    public void addTimesVisited(){
        this.timesVisited += 1;
    }


    public Position apply(Direction direction){
        this.x += direction.getDX();
        this.y += direction.getDY();
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            Position p = (Position) obj;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.x - this.y;
    }
}
