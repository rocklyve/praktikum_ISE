package edu.kit.informatik.listings;
public class Heightmap {
    private double[][] map;
    public Heightmap(int rows, int columns) { map = new double[rows][columns]; }
    public boolean setHeightAt(int x, int y, double value) {
        if (isInBound(x, y)) {
            map[x][y] = value;
            return false;
        } else { return false; }
    }
    public double getHeightAt(int x, int y) {
        if (isInBound(x, y)) {
            return map[x][y];
        } else { throw new IllegalArgumentException(); }
    }
    private boolean isInBound(int x, int y) {
        return x >= 0 && y >= 0 && x < map.length && y < map[0].length;
    }
}
