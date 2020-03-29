package hst.javaui.b;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Peter.Huang on 2020/3/21 21:55
 */
public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        setResizable(false);
//        setSize(canvasWidth, canvasHeight);
        AlgoCanvas canvas = new AlgoCanvas();
//        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight)); // 因为在AlgoCanvas类中重写了getPreferredSize方法,这行可以去掉
        setContentPane(canvas);
        pack(); // 作用是让 JFrame 根据 Pane 的尺寸重新适应窗口的尺寸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private Circle[] circles;

    public void render(Circle[] circles) {
        this.circles = circles;
        repaint(); // JPanel自带的方法,他默认回调paintComponent(Graphics g)方法重绘图形
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // UI双缓存设置, 默认JPanel是开启状态的
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
//            g.drawOval(50, 50, 300, 300);
            int strokeWidth = 5;
            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);


            // 绘制图形
//            AlgoVisHelper.setColor(g2d, Color.BLUE);
//            AlgoVisHelper.fillCircle(g2d, getPreferredSize().width / 2, getPreferredSize().height / 2, getPreferredSize().height / 2 - 70);
//            AlgoVisHelper.setColor(g2d, Color.RED);
//            AlgoVisHelper.setStrokeWidth(g2d, strokeWidth);
//            AlgoVisHelper.strokeCircle(g2d, getPreferredSize().width / 2, getPreferredSize().height / 2, getPreferredSize().height / 2 - 70);
            AlgoVisHelper.setStrokeWidth(g2d, 1);
            AlgoVisHelper.setColor(g2d, Color.RED);
            Arrays.stream(circles).parallel().forEach(c -> {
                        if (c.isFilled) AlgoVisHelper.fillCircle(g2d, c.x, c.y, c.getR());
                        else AlgoVisHelper.strokeCircle(g2d, c.x, c.y, c.getR());
                    }
            );
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
