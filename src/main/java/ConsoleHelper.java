import java.util.NoSuchElementException;
import java.util.Scanner;

class ConsoleHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void askShipCoordinate(Field field, Ship ship) {

        write(field.toString());
        while (true) {
            write(String.format("Enter the coordinates of the %s (%s cells):\n\n> ", ship.model.modelName, ship.model.size));
            try {
                Coordinate firstCoordinate1 = readCoordinate(field);
                Coordinate secondCoordinate2 = readCoordinate(field);
                ship.setCoordinate(firstCoordinate1, secondCoordinate2);
                field.toDrawShip(ship);
                break;

            } catch (WrongCoordinateException e) {
                write(String.format("Error! %s\n", e.getMessage()));

            }
        }
    }

    public static Coordinate askShotCoordinate(Field field) {

        while (true) {
            try {
                write("\n>");
                return readCoordinate(field);
            } catch (WrongCoordinateException e) {
                write(String.format("Error! %s\n", e.getMessage()));
            }

        }
    }

    public static Coordinate readCoordinate(Field field) throws WrongCoordinateException {
        String input;
        Coordinate coordinate;

        try {
            input = scanner.next("[A-" + Character.toString('A' - 1 + field.FIELD_SIZE) + "](\\d{1,3})");
            coordinate = new Coordinate(input);

            if (coordinate.x >= field.FIELD_SIZE || coordinate.x == -1) {
                throw new WrongCoordinateException(" The entered coordinates are outside the game default\n");
            }
        } catch (NoSuchElementException e) {
            scanner.nextLine();
            throw new WrongCoordinateException("The coordinates format is incorrect\n");

        }
        return coordinate;
    }

    public static void write(String s) {
        System.out.print(s);
    }


}
