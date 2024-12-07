package view.ending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

// 发射粒子类
class LaunchParticle {
    public int x, y;       // 位置
    public int vx, vy;     // 速度
    public boolean exploded = false; // 是否已经爆炸
    public Color color;    // 粒子颜色
    public int targetHeight;  // 目标高度

    public LaunchParticle(int startX, int startY, int velocityX, int velocityY, int targetHeight, Color color) {
        this.x = startX;
        this.y = startY;
        this.vx = velocityX;
        this.vy = velocityY;
        this.targetHeight = targetHeight;
        this.color = color;
    }

    // 更新发射粒子的位置
    public void update() {
        x += vx;
        y += vy;
        vy += 1;  // 模拟重力

        // 当粒子到达目标高度或者开始下落时爆炸
        if (y <= targetHeight || vy >= 0) {
            exploded = true;
        }
    }
}

// 烟花爆炸粒子类
class FireworkParticle {
    public int x, y;       // 位置
    public int vx, vy;     // 速度
    public int life;       // 粒子寿命
    public Color color;    // 粒子颜色

    public FireworkParticle(int x, int y, int vx, int vy, int life, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.life = life;
        this.color = color;
    }

    // 更新爆炸粒子的位置
    public void update() {
        x += vx;
        y += vy;
        vy += 1;  // 模拟重力
        life--;
    }

    public boolean isDead() {
        return life <= 0;
    }
}

// 显示烟花的面板
class FireworkPanel extends JPanel implements ActionListener {
    private final ArrayList<FireworkParticle> particles = new ArrayList<>();
    private final ArrayList<LaunchParticle> launchParticles = new ArrayList<>();
    private final Random random = new Random();
    private Timer timer;
    private int screenHeight;

    // 预定义一组颜色段，确保在任何背景下都好看
    private final Color[] colorPalette = {
            new Color(255, 128, 0),   // 橙色
            new Color(255, 51, 51),   // 红色
            new Color(51, 204, 255),  // 天蓝色
            new Color(51, 255, 51),   // 绿色
            new Color(204, 51, 255),  // 紫色
            new Color(255, 255, 51),  // 黄色
            new Color(255, 102, 178)  // 粉色
    };

    public FireworkPanel(int screenHeight) {
        this.screenHeight = screenHeight;
        // 设置面板为透明
        this.setOpaque(false);
        timer = new Timer(30, this);  // 每 30 毫秒更新一次
        timer.start();
    }

    // 选择随机颜色，从预定义的颜色段中选取
    private Color getRandomColor() {
        return colorPalette[random.nextInt(colorPalette.length)];
    }

    // 添加发射粒子
    public void addLaunchParticle() {
        int width = getWidth();
        int height = getHeight();

        if (width <= 0 || height <= 0) return;

        int startX = random.nextInt(width); // 发射起始位置
        int startY = height; // 从屏幕底部发射
        int targetHeight = (int) (screenHeight * 0.2); // 烟花将在屏幕高度的 4/5 处爆炸
        int velocityY = -(random.nextInt(10) + 50); // 增加初始向上速度，确保高度合适
        Color color = getRandomColor(); // 从预定义颜色中选取

        launchParticles.add(new LaunchParticle(startX, startY, 0, velocityY, targetHeight, color));
    }

    // 添加爆炸粒子，使用极坐标生成圆形分布，增加中心粒子密度
    public void addExplosion(int x, int y, Color color) {
        int numParticles = 200; // 每个烟花的粒子数量
        for (int i = 0; i < numParticles; i++) {
            double angle = 2 * Math.PI * random.nextDouble(); // 每个粒子的角度随机
            int speed = random.nextInt(15) + 5; // 粒子的速度增加随机性，靠近中心的速度会更小
            int vx = (int) (Math.cos(angle) * speed); // 使用极坐标计算x方向速度
            int vy = (int) (Math.sin(angle) * speed); // 使用极坐标计算y方向速度
            int life = random.nextInt(50) + 50; // 粒子寿命
            particles.add(new FireworkParticle(x, y, vx, vy, life, color));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 绘制发射粒子
        for (LaunchParticle launchParticle : launchParticles) {
            g2d.setColor(launchParticle.color);
            g2d.fillOval(launchParticle.x - 3, launchParticle.y - 3, 6, 10); // 缩小发射粒子尺寸，使用半径为3的圆形
        }

        // 绘制爆炸粒子
        for (FireworkParticle particle : particles) {
            g2d.setColor(new Color(particle.color.getRed(), particle.color.getGreen(), particle.color.getBlue(), Math.max(particle.life * 255 / 100, 0)));
            g2d.fillOval(particle.x - 3, particle.y - 3, 6, 6); // 缩小爆炸粒子尺寸，使用半径为3的圆形
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 更新发射粒子
        ArrayList<LaunchParticle> toRemoveLaunch = new ArrayList<>();
        for (LaunchParticle launchParticle : launchParticles) {
            launchParticle.update();
            if (launchParticle.exploded) {
                addExplosion(launchParticle.x, launchParticle.y, launchParticle.color); // 发射粒子爆炸
                toRemoveLaunch.add(launchParticle);
            }
        }
        launchParticles.removeAll(toRemoveLaunch);

        // 更新爆炸粒子
        ArrayList<FireworkParticle> deadParticles = new ArrayList<>();
        for (FireworkParticle particle : particles) {
            particle.update();
            if (particle.isDead()) {
                deadParticles.add(particle);
            }
        }
        particles.removeAll(deadParticles);

        // 持续生成发射粒子，保证烟花不断生成
        if (random.nextInt(15) == 0) { // 增加生成频率
            addLaunchParticle(); // 随机生成多个烟花
        }

        // 重新绘制
        repaint();
    }
}

//public class FireworkDisplay {
//    public static void main(String[] args) {
//        // 获取屏幕大小
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//
//        // 创建全屏的透明窗口
//        JWindow window = new JWindow();
//        window.setSize(screenSize);
//        window.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明
//        window.setLayout(new BorderLayout()); // 使用 BorderLayout 布局
//
//        // 添加烟花面板
//        FireworkPanel panel = new FireworkPanel(screenSize.height); // 传递屏幕高度
//        window.add(panel, BorderLayout.CENTER); // 确保 FireworkPanel 占满整个窗口
//        window.setAlwaysOnTop(true); // 窗口始终在最前面
//        window.setVisible(true);
//
//        // 强制重绘刷新
//        RepaintManager.currentManager(panel).markCompletelyDirty(panel);
//
//        // 启动后立即生成发射粒子
//        panel.addLaunchParticle();
//    }
//}
