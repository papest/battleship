enum ShipModel {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLE_SHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    String modelName;
    int size;

    ShipModel(String modelName, int size) {
        this.modelName = modelName;
        this.size = size;
    }

}
