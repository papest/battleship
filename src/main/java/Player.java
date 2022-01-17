public class Player {
    String name;
    Field field;
    public Player(String name) {
        this.name = name;
        field = new Field();
        field.initialize(name);
    }

    public void shot(Player opponent, Game game) {
        Field opponentField = opponent.field;
        ConsoleHelper.writeFields(field, opponentField);
        ConsoleHelper.write(String.format("%s, it's your turn:\n",name));
        Coordinate coordinate = ConsoleHelper.askShotCoordinate(opponentField);
        boolean shotResult = opponentField.shot(coordinate);
        if (shotResult) {
            if (opponentField.checkShipSank(coordinate)) {
                if (!opponentField.toString().contains("O")) {
                    ConsoleHelper.write(field.toString());
                    game.gameIsOver = true;
                    ConsoleHelper.write("You sank the last ship. You won. Congratulations!\n");

                } else {
                    ConsoleHelper.write("You sank a ship!  Press Enter and pass the move to another player\n");
                    ConsoleHelper.waitEnter();
                }

            } else {
                ConsoleHelper.write("You hit a ship! Press Enter and pass the move to another player\n");
                ConsoleHelper.waitEnter();

            }
        } else {
            ConsoleHelper.write("You missed. Press Enter and pass the move to another player\n");
            ConsoleHelper.waitEnter();


        }

    }
}
