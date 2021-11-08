
public class Game {

    Field field;
    boolean gameIsOver;

    public void run() {
        init();
        ConsoleHelper.write("The game starts!\n");
        while (!gameIsOver) {
            shot(field);
        }

    }

    private void init() {
        gameIsOver = false;
        field = new Field();
        field.initialize();

    }

    public void shot(Field field) {
        ConsoleHelper.write(field.toString().replaceAll("O", "~"));
        ConsoleHelper.write("Take a shot!\n");
        Coordinate coordinate = ConsoleHelper.askShotCoordinate(field);
        boolean shotResult = field.shot(coordinate);
        ConsoleHelper.write(field.toString().replaceAll("O", "~"));
        if (shotResult) {
            if (field.checkShipSank(coordinate)) {
                if (!field.toString().contains("O")) {
                    ConsoleHelper.write(field.toString());
                    gameIsOver = true;
                    ConsoleHelper.write("You sank the last ship. You won. Congratulations!\n");

                } else {
                    ConsoleHelper.write("You sank a ship! Specify a new target:\n");
                }

            } else {
                ConsoleHelper.write("You hit a ship! Try again:\n");

            }
        } else {
            ConsoleHelper.write("You missed. Try again:\n");


        }

    }
}
