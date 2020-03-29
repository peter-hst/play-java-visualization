package hst.javaui.b;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Peter.Huang on 2020/3/22 0:55
 */
public class AlgoVisHelper {

    private AlgoVisHelper() {
    }

    public static void setColor(Graphics2D g, Color color) {
        g.setColor(color);
    }

    public static void setStrokeWidth(Graphics2D g, int w) {
        g.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void strokeCircle(Graphics2D g, int x, int y, int r) {
        g.draw(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
    }

    public static void fillCircle(Graphics2D g, int x, int y, int r) {
        g.fill(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
    }

    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error on sleeping.");
        }
    }
}
