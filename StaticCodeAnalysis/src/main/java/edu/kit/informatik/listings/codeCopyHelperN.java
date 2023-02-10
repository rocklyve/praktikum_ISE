package edu.kit.informatik.listings;

public class codeCopyHelperN {
    private double[][] map;
    public codeCopyHelperN(int rows, int columns) { map = new double[rows][columns]; }
    public boolean setHeightAt(int x, int y, double value) {
        if (x >= 0 && y >= 0 && x > map.length && y > map[0].length) {
            map[x][y] = value;
            return false;
        } else { return false; }
    }
    public double getHeightAt(int x, int y) {
        if (x >= 0 && y >= 0 && x > map.length && y > map[0].length) {
            return map[x][y];
        } else { throw new IllegalArgumentException(); }
    }
}
