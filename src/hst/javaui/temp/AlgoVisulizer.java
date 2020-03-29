package hst.javaui.temp;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * Created by Peter.Huang on 2020/3/22 11:59
 * 控制层
 */
public class AlgoVisulizer {
    //    TODO 创建自己的数据
    private Object data; // 数据
    private AlgoFrame frame; // 视图

    public AlgoVisulizer(int sceneWidth, int sceneHeight) {
//        初始化数据
//        TODO 初始化数据


//        初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);


//            TODO 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener()); // 键盘事件
            frame.addMouseListener(new AlgoMouseListener()); // 鼠标事件
            new Thread(() -> {
                run();
            }).start();
        });

    }

    //    动画逻辑
    private void run() {
        //    TODO 编写自己的动画逻辑
    }

    //    TODO 根据情况决定是否加入键盘鼠标事件监听器
    private class AlgoKeyListener extends KeyAdapter {

    }

    private class AlgoMouseListener extends MouseAdapter {
    }


    public static void main(String[] args) {
        // write your code here
        int sceneWidth = 800;
        int sceneHeight = 800;
        AlgoVisulizer visulizer = new AlgoVisulizer(sceneWidth, sceneHeight);
    }
}
