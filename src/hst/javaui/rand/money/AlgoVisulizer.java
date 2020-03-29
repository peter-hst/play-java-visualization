package hst.javaui.rand.money;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

/**
 * Created by Peter.Huang on 2020/3/22 11:59
 * 控制层
 */
public class AlgoVisulizer {
    public static int DELAY = 40;  // 1000 / 40 = 25帧

    //    TODO 创建自己的数据
    private int[] money; // 数据
    private AlgoFrame frame; // 视图

    public AlgoVisulizer(int sceneWidth, int sceneHeight) {
//        初始化数据
        money = new int[100]; // 模拟100个人
        for (int i = 0; i < money.length; i++) money[i] = 100; //初始每个人都有100元

//        初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });

    }

    //    动画逻辑
    private void run() {
        while (true) {
//            排序渲染
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);

//            为了加速效果, 设置为每50轮迭代, 渲染一次界面
            for (int k = 0; k < 50; k++) {
                for (int i = 0; i < money.length; i++) {
//                    为了公平,去掉没有钱的人,可以为负债
//                    if (money[i] > 0) { // 保证有钱
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
//                    }
                }
            }
        }
    }

    //    TODO 根据情况决定是否加入键盘鼠标事件监听器
    private class AlgoKeyListener extends KeyAdapter {

    }

    private class AlgoMouseListener extends MouseAdapter {
    }


    public static void main(String[] args) {
        // write your code here
        int sceneWidth = 1000;
        int sceneHeight = 800;
        AlgoVisulizer visulizer = new AlgoVisulizer(sceneWidth, sceneHeight);
    }
}
