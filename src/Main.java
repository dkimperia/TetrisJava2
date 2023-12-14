// TetrisGame.java

import control.TetrisController;
import model.TetrisModel;
import view.TetrisView;

public class Main {
    public static void main(String[] args) {
        TetrisModel model = new TetrisModel();
        TetrisView view = new TetrisView(model);
        TetrisController controller = new TetrisController(model);

        view.addKeyListener(controller);
        view.setFocusable(true);
        view.requestFocusInWindow();

        // Game loop
        while (true) {
            model.moveDown();

            view.repaint();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}