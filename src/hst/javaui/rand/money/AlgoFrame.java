package hst.javaui.rand.money;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Peter.Huang on 2020/3/21 21:55
 * 视图层/数据层
 */
public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack(); // 作用是让 JFrame 根据 Pane 的尺寸重新适应窗口的尺寸

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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

    // 数据对象
    private int[] money;

    //    TODO 设置自己的数据
    public void render(int[] money) {
        this.money = money;
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
            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制图形
            // TODO 绘制自己的数据data
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
            int w = canvasWidth / money.length;
            for (int i = 0; i < money.length; i++) {
                AlgoVisHelper.fillRectangle(g2d, i * w + 1, canvasHeight - money[i], w - 1, money[i]);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
