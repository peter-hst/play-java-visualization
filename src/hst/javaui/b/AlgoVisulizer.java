package hst.javaui.b;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Peter.Huang on 2020/3/22 11:59
 */
public class AlgoVisulizer {
    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisulizer(int sceneWidth, int sceneHeight, int N) {
        int R = 50;
        circles = new Circle[N];
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener()); // 键盘事件
            frame.addMouseListener(new AlgoMouseListener()); // 鼠标事件
            new Thread(() -> {
                run();
            }).start();
        });

    }

    private void run() {
        while (true) {
            frame.render(circles);
            AlgoVisHelper.pause(25);
            if (isAnimated)
                for (Circle c : circles)
                    c.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ')
                isAnimated = !isAnimated;
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
//            鼠标的坐标系转换
            e.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
//            System.out.println(e.getPoint());
//            判断圆的位置是否包含鼠标点击的位置
            for (Circle c : circles)
                if (c.contain(e.getPoint()))
                    c.isFilled = !c.isFilled;
        }
    }
}
