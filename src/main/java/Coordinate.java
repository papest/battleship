public class Coordinate {
    int y;
    int x;

    public Coordinate(String input) {
        y = input.charAt(0) - 'A';
        x = Integer.parseInt(input.substring(1)) - 1;
    }
    public Coordinate(int x,int y){}
}
