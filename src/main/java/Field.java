import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Field {
    int FIELD_SIZE = 10;
    char[][] field;
    ArrayList<Ship> ships = new ArrayList<>(Arrays.asList(new Ship(ShipModel.AIRCRAFT_CARRIER),
            new Ship(ShipModel.BATTLE_SHIP), new Ship(ShipModel.SUBMARINE), new Ship(ShipModel.CRUISER),
            new Ship(ShipModel.DESTROYER)));

    Field() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        IntStream.range(0, FIELD_SIZE).forEach(a -> IntStream.range(0, FIELD_SIZE).forEach(b -> field[a][b] = '~'));

    }

    public void toDrawShip(Ship ship) throws WrongCoordinateException {
        if (checkField(ship)) {
            if (ship.horizontal) {
                IntStream.range(ship.x, ship.x + ship.model.size).forEach(i -> field[ship.y][i] = 'O');
            } else {
                IntStream.range(ship.y, ship.y + ship.model.size).forEach(j -> field[j][ship.x] = 'O');

            }
        } else {
            throw new WrongCoordinateException("Wrong ship location!\n");
        }

    }

    private boolean checkField(Ship ship) {
        if (ship.horizontal) {
            return IntStream.rangeClosed(Math.max(ship.y - 1, 0), Math.min(ship.y + 1, FIELD_SIZE - 1)).mapToObj(a ->
                    IntStream.rangeClosed(Math.max(ship.x - 1, 0), Math.min(ship.x + ship.model.size, FIELD_SIZE - 1)).mapToObj(b -> field[a][b])).reduce(Stream.of(), Stream::concat).noneMatch(c -> c == 'O');
        } else {
            return IntStream.rangeClosed(Math.max(ship.x - 1, 0), Math.min(ship.x + 1, FIELD_SIZE - 1)).mapToObj(a ->
                    IntStream.rangeClosed(Math.max(ship.y - 1, 0), Math.min(ship.y + ship.model.size, FIELD_SIZE - 1)).mapToObj(b -> field[b][a])).reduce(Stream.of(), Stream::concat).noneMatch(c -> c == 'O');
        }


    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(" " + IntStream.rangeClosed(1, FIELD_SIZE)
                .mapToObj(i -> " " + i).collect(Collectors.joining())).append("\n");
        for (char c = 'A'; c != 'A' + FIELD_SIZE; ++c) {
            result.append(c);
            for (char ceil : field[c - 'A']) {
                result.append(" ").append(ceil);
            }
            result.append("\n");
        }


        return result.toString();
    }

    public void initialize() {
        ships.forEach(ship -> ConsoleHelper.askShipCoordinate(this, ship));
        ConsoleHelper.write(this.toString());
    }


    public boolean shot(Coordinate coordinate) {
        if (field[coordinate.y][coordinate.x] == 'O' || field[coordinate.y][coordinate.x] == 'X') {
            field[coordinate.y][coordinate.x] = 'X';
            return true;
        } else {
            field[coordinate.y][coordinate.x] = 'M';
            return false;
        }
    }

    public boolean checkShipSank(Coordinate coordinate) {
        Ship downedShip;
        downedShip = ships.stream().filter(ship -> ship.isOccupied(coordinate)).findFirst().get();
        if (downedShip.horizontal) {
            return IntStream.range(downedShip.x, downedShip.x + downedShip.model.size).mapToObj(i -> field[downedShip.y][i]).noneMatch(z -> z == 'O');
        } else {
            return IntStream.range(downedShip.y, downedShip.y + downedShip.model.size).mapToObj(j -> field[j][downedShip.x]).noneMatch(z -> z == 'O');
        }


    }
}

