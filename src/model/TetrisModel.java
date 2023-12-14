// TetrisModel.java

package model;

import common.Observer;
import common.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TetrisModel {
    private List<Observer> observers = new ArrayList<>();
    private int[][] grid;
    private Piece currentPiece;
    private int currentRow;
    private int currentCol;

    public TetrisModel() {
        grid = new int[20][10];
        generateRandomPiece();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    private void generateRandomPiece() {
        Random random = new Random();
        int pieceType = random.nextInt(7);

        switch (pieceType) {
            case 0:
                currentPiece = new Piece(new int[][]{{1, 1, 1, 1}});
                break;
            case 1:
                currentPiece = new Piece(new int[][]{{1, 1}, {1, 1}});
                break;
            case 2:
                currentPiece = new Piece(new int[][]{{1, 1, 1}, {0, 1, 0}});
                break;
        }


        currentRow = 0;
        currentCol = (grid[0].length - currentPiece.getCols()) / 2;


        if (isCollision()) {

            grid = new int[20][10];
            generateRandomPiece();
        }
    }

    private boolean isCollision() {
        int[][] shape = currentPiece.getShape();
        int rows = shape.length;
        int cols = shape[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (shape[i][j] == 1) {
                    int newRow = currentRow + i;
                    int newCol = currentCol + j;


                    if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                        return true;
                    }


                    if (grid[newRow][newCol] == 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void moveLeft() {
        currentCol--;
        if (isCollision()) {
            currentCol++;
        }
        notifyObservers();
    }

    public void moveRight() {
        currentCol++;
        if (isCollision()) {
            currentCol--;
        }
        notifyObservers();
    }

    public void moveDown() {
        currentRow++;
        if (isCollision()) {
            currentRow--;
            placePiece();
        }
        notifyObservers();
    }

    public void stopMoveDown() {

    }

    public void rotate() {
        currentPiece.rotate();
        if (isCollision()) {
            currentPiece.rotateBack();
        }
        notifyObservers();
    }

    private void placePiece() {

        int[][] shape = currentPiece.getShape();
        int rows = shape.length;
        int cols = shape[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (shape[i][j] == 1) {
                    grid[currentRow + i][currentCol + j] = 1;
                }
            }
        }


        generateRandomPiece();
    }

    public int[][] getGrid() {
        return grid;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }
}
