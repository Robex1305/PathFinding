public enum Direction {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);


    private int dX;
    private int dY;

    public int getDX(){
        return dX;
    }

    public int getDY(){
        return dY;
    }
    Direction(int dX, int dY){
        this.dX = dX;
        this.dY = dY;
    }

    public Direction opposite(){
        switch (this) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
        }
        throw new UnsupportedOperationException("Cannot process oppsoite of " + this);
    }

}
