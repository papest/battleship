
public class Game {
    Player player1;
    Player player2;
    boolean gameIsOver;

    public void run() {
        init();
        ConsoleHelper.write("The game starts!\n");
        Player player = player1;
        Player opponent = player2;

        while (!gameIsOver) {
            player.shot(opponent, this);
            if (player.equals(player1)) {
                player = player2;
                opponent = player1;
            } else {
                player = player1;
                opponent = player2;
            }

        }

    }

    private void init() {
        gameIsOver = false;
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");


    }


}
