class Ship {
    ShipModel model;
    int x;
    int y;
    boolean horizontal;

    public Ship(ShipModel model) {
        this.model = model;
    }

    public void setCoordinate(Coordinate firstCoordinate, Coordinate secondCoordinate) throws WrongCoordinateException {
        int y1 = firstCoordinate.y;
        int y2 = secondCoordinate.y;
        int x1 = firstCoordinate.x;
        int x2 = secondCoordinate.x;
        if ((y1 == y2 && x2 - x1 + 1 == model.size) || (x1 == x2 && (y2 - y1 + 1 == model.size))) {
            this.x = x1;
            this.y = y1;
        } else if ((y1 == y2 && x1 - x2 + 1 == model.size) || (x1 == x2 && (y1 - y2 + 1 == model.size))) {
            this.x = x2;
            this.y = y2;
        } else {
            throw new WrongCoordinateException("Incorrect size or rotation of the ship\n");
        }
        if (y1 == y2) {
            horizontal = true;
        }


    }

    public boolean isOccupied(Coordinate coordinate) {
        if (horizontal && coordinate.y != y || ((!horizontal) && coordinate.x != x)) {
            return false;
        } else {
            return (horizontal && coordinate.x >= x && coordinate.x < x + model.size) || ((!horizontal) && coordinate.y >= y && coordinate.y < y + model.size);
        }
    }
}

