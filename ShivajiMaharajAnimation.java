import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.*;

public class ShivajiMaharajAnimation extends JPanel implements Runnable {

    private Thread animationThread;
    private double swordAngle = -0.5;
    private double cloudX = 0;
    private double flagWave = 0;
    private double glow = 0;
    private boolean running = true;

    private final Random random = new Random();
    private final int WIDTH = 1200;
    private final int HEIGHT = 700;

    public ShivajiMaharajAnimation() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    public void run() {

        while (running) {

            // Sword animation
            swordAngle += 0.035;

            if (swordAngle > 0.55) {
                swordAngle = -0.55;
            }

            // Clouds movement
            cloudX += 0.7;

            if (cloudX > WIDTH + 200) {
                cloudX = -250;
            }

            // Flag movement
            flagWave += 0.08;

            // Golden glow
            glow += 0.08;

            repaint();

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        drawSky(g2);
        drawStars(g2);
        drawClouds(g2);
        drawMountains(g2);
        drawFort(g2);
        drawFlags(g2);
        drawGround(g2);
        drawShivajiMaharaj(g2);
        drawTorch(g2, 180, 490);
        drawTorch(g2, 1020, 490);
        drawTitle(g2);

        g2.dispose();
    }

    // --------------------------------------------------
    // SKY
    // --------------------------------------------------

    private void drawSky(Graphics2D g2) {

        GradientPaint sky = new GradientPaint(
                0, 0,
                new Color(15, 25, 70),
                0, HEIGHT,
                new Color(180, 90, 40)
        );

        g2.setPaint(sky);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
    }

    // --------------------------------------------------
    // STARS
    // --------------------------------------------------

    private void drawStars(Graphics2D g2) {

        g2.setColor(new Color(255, 230, 150));

        int[] starsX = {
                70, 140, 230, 320, 410,
                510, 620, 730, 820, 930,
                1080, 1140
        };

        int[] starsY = {
                70, 110, 55, 140, 80,
                120, 65, 100, 50, 130,
                75, 120
        };

        for (int i = 0; i < starsX.length; i++) {

            int size = 2 + (int)(Math.abs(Math.sin(glow + i)) * 3);

            g2.fillOval(
                    starsX[i],
                    starsY[i],
                    size,
                    size
            );
        }
    }

    // --------------------------------------------------
    // CLOUDS
    // --------------------------------------------------

    private void drawClouds(Graphics2D g2) {

        drawCloud(g2, cloudX, 130);
        drawCloud(g2, cloudX - 400, 190);
        drawCloud(g2, cloudX + 400, 90);
    }

    private void drawCloud(Graphics2D g2, double x, int y) {

        g2.setColor(new Color(255, 255, 255, 80));

        g2.fillOval((int)x, y, 100, 45);
        g2.fillOval((int)x + 40, y - 25, 90, 65);
        g2.fillOval((int)x + 90, y, 110, 45);
    }

    // --------------------------------------------------
    // MOUNTAINS
    // --------------------------------------------------

    private void drawMountains(Graphics2D g2) {

        Polygon mountain1 = new Polygon();

        mountain1.addPoint(0, 430);
        mountain1.addPoint(180, 220);
        mountain1.addPoint(350, 430);

        mountain1.addPoint(520, 200);
        mountain1.addPoint(750, 430);

        mountain1.addPoint(930, 230);
        mountain1.addPoint(1200, 430);

        g2.setColor(new Color(35, 35, 45));
        g2.fillPolygon(mountain1);

        // Snow / highlights

        g2.setColor(new Color(120, 100, 90));

        Polygon peak1 = new Polygon();
        peak1.addPoint(180, 220);
        peak1.addPoint(145, 270);
        peak1.addPoint(180, 255);
        peak1.addPoint(210, 275);

        g2.fillPolygon(peak1);

        Polygon peak2 = new Polygon();
        peak2.addPoint(520, 200);
        peak2.addPoint(475, 270);
        peak2.addPoint(520, 245);
        peak2.addPoint(565, 275);

        g2.fillPolygon(peak2);
    }

    // --------------------------------------------------
    // FORT
    // --------------------------------------------------

    private void drawFort(Graphics2D g2) {

        // Fort wall

        g2.setColor(new Color(70, 45, 35));

        g2.fillRect(280, 390, 640, 140);

        // Fort top

        g2.setColor(new Color(90, 55, 40));

        for (int x = 280; x < 920; x += 45) {

            g2.fillRect(x, 370, 30, 30);
        }

        // Main gate

        g2.setColor(new Color(30, 20, 18));

        g2.fillArc(
                520,
                405,
                160,
                150,
                0,
                180
        );

        g2.fillRect(520, 480, 160, 50);

        // Fort towers

        drawTower(g2, 260, 330);
        drawTower(g2, 870, 330);
    }

    private void drawTower(Graphics2D g2, int x, int y) {

        g2.setColor(new Color(65, 42, 32));

        g2.fillRect(x, y, 70, 200);

        g2.setColor(new Color(85, 55, 40));

        for (int i = 0; i < 70; i += 25) {

            g2.fillRect(
                    x + i,
                    y - 20,
                    18,
                    25
            );
        }

        g2.setColor(Color.BLACK);

        g2.fillOval(
                x + 22,
                y + 55,
                25,
                45
        );
    }

    // --------------------------------------------------
    // FLAGS
    // --------------------------------------------------

    private void drawFlags(Graphics2D g2) {

        drawFlag(g2, 295, 230);
        drawFlag(g2, 905, 230);
        drawFlag(g2, 580, 180);
    }

    private void drawFlag(
            Graphics2D g2,
            int x,
            int y
    ) {

        // Pole

        g2.setColor(new Color(90, 60, 30));

        g2.fillRect(
                x,
                y,
                7,
                180
        );

        // Saffron flag

        Path2D flag = new Path2D.Double();

        flag.moveTo(x + 7, y);
        flag.curveTo(
                x + 55,
                y + 15 + Math.sin(flagWave) * 8,
                x + 80,
                y + 25,
                x + 120,
                y + 15
        );

        flag.lineTo(
                x + 100,
                y + 55
        );

        flag.curveTo(
                x + 60,
                y + 35,
                x + 40,
                y + 40,
                x + 7,
                y + 45
        );

        flag.closePath();

        g2.setColor(new Color(230, 120, 20));

        g2.fill(flag);
    }

    // --------------------------------------------------
    // GROUND
    // --------------------------------------------------

    private void drawGround(Graphics2D g2) {

        GradientPaint ground = new GradientPaint(
                0,
                520,
                new Color(35, 35, 30),
                0,
                HEIGHT,
                Color.BLACK
        );

        g2.setPaint(ground);

        g2.fillRect(
                0,
                520,
                WIDTH,
                180
        );

        // Grass

        g2.setColor(new Color(45, 70, 35));

        for (int x = 0; x < WIDTH; x += 15) {

            int height = 5 + random.nextInt(12);

            g2.drawLine(
                    x,
                    550,
                    x + 3,
                    550 - height
            );
        }
    }

    // --------------------------------------------------
    // SHIVAJI MAHARAJ
    // --------------------------------------------------

    private void drawShivajiMaharaj(Graphics2D g2) {

        int cx = 600;
        int baseY = 570;

        // Shadow

        g2.setColor(new Color(0, 0, 0, 130));

        g2.fillOval(
                cx - 100,
                baseY - 5,
                200,
                30
        );

        // Body / royal dress

        g2.setColor(new Color(45, 30, 25));

        Polygon body = new Polygon();

        body.addPoint(cx - 45, baseY - 190);
        body.addPoint(cx + 45, baseY - 190);
        body.addPoint(cx + 70, baseY - 70);
        body.addPoint(cx + 35, baseY);
        body.addPoint(cx - 35, baseY);
        body.addPoint(cx - 70, baseY - 70);

        g2.fillPolygon(body);

        // Golden waist belt

        g2.setColor(new Color(218, 170, 50));

        g2.fillRect(
                cx - 50,
                baseY - 95,
                100,
                15
        );

        // Legs

        g2.setColor(new Color(35, 25, 22));

        g2.fillRect(
                cx - 45,
                baseY - 5,
                30,
                75
        );

        g2.fillRect(
                cx + 15,
                baseY - 5,
                30,
                75
        );

        // Boots

        g2.setColor(Color.BLACK);

        g2.fillOval(
                cx - 55,
                baseY + 55,
                50,
                25
        );

        g2.fillOval(
                cx + 15,
                baseY + 55,
                50,
                25
        );

        // Head

        g2.setColor(new Color(175, 115, 75));

        g2.fillOval(
                cx - 32,
                baseY - 245,
                64,
                70
        );

        // Beard

        g2.setColor(new Color(35, 25, 20));

        Polygon beard = new Polygon();

        beard.addPoint(cx - 25, baseY - 195);
        beard.addPoint(cx + 25, baseY - 195);
        beard.addPoint(cx + 10, baseY - 170);
        beard.addPoint(cx, baseY - 160);
        beard.addPoint(cx - 10, baseY - 170);

        g2.fillPolygon(beard);

        // Eyes

        g2.setColor(Color.BLACK);

        g2.fillOval(
                cx - 20,
                baseY - 215,
                8,
                6
        );

        g2.fillOval(
                cx + 12,
                baseY - 215,
                8,
                6
        );

        // Moustache

        g2.fillArc(
                cx - 25,
                baseY - 205,
                30,
                20,
                0,
                -180
        );

        g2.fillArc(
                cx - 5,
                baseY - 205,
                30,
                20,
                0,
                -180
        );

        // Turban

        g2.setColor(new Color(180, 115, 25));

        g2.fillOval(
                cx - 42,
                baseY - 270,
                84,
                55
        );

        g2.setColor(new Color(220, 170, 50));

        g2.fillRect(
                cx - 35,
                baseY - 235,
                70,
                18
        );

        // Turban jewel

        g2.setColor(new Color(240, 200, 70));

        g2.fillOval(
                cx - 8,
                baseY - 252,
                16,
                16
        );

        // Left arm

        g2.setColor(new Color(175, 110, 70));

        g2.setStroke(
                new BasicStroke(
                        18,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.drawLine(
                cx - 45,
                baseY - 175,
                cx - 90,
                baseY - 110
        );

        // Right arm

        g2.drawLine(
                cx + 45,
                baseY - 175,
                cx + 85,
                baseY - 120
        );

        // Sword

        drawSword(
                g2,
                cx + 85,
                baseY - 120
        );
    }

    // --------------------------------------------------
    // SWORD
    // --------------------------------------------------

    private void drawSword(
            Graphics2D g2,
            int x,
            int y
    ) {

        Graphics2D swordGraphics =
                (Graphics2D) g2.create();

        swordGraphics.translate(x, y);

        swordGraphics.rotate(swordAngle);

        // Handle

        swordGraphics.setColor(
                new Color(120, 70, 20)
        );

        swordGraphics.fillRect(
                -8,
                -10,
                16,
                45
        );

        // Guard

        swordGraphics.setColor(
                new Color(220, 170, 50)
        );

        swordGraphics.fillRect(
                -35,
                25,
                70,
                10
        );

        // Blade

        Polygon blade = new Polygon();

        blade.addPoint(-8, 35);
        blade.addPoint(8, 35);
        blade.addPoint(14, -150);
        blade.addPoint(0, -180);
        blade.addPoint(-14, -150);

        swordGraphics.setColor(
                new Color(210, 210, 220)
        );

        swordGraphics.fillPolygon(blade);

        // Blade highlight

        swordGraphics.setColor(
                new Color(255, 255, 255, 180)
        );

        swordGraphics.drawLine(
                0,
                30,
                5,
                -145
        );

        swordGraphics.dispose();
    }

    // --------------------------------------------------
    // TORCH
    // --------------------------------------------------

    private void drawTorch(
            Graphics2D g2,
            int x,
            int y
    ) {

        // Stick

        g2.setColor(
                new Color(90, 55, 25)
        );

        g2.fillRect(
                x,
                y,
                12,
                90
        );

        // Fire glow

        int glowSize =
                35 + (int)(Math.abs(Math.sin(glow)) * 10);

        g2.setColor(
                new Color(255, 170, 20, 50)
        );

        g2.fillOval(
                x - glowSize / 2,
                y - glowSize / 2,
                glowSize,
                glowSize
        );

        // Fire

        Path2D fire = new Path2D.Double();

        fire.moveTo(x + 6, y);
        fire.curveTo(
                x - 15,
                y - 25,
                x + 5,
                y - 45,
                x + 8,
                y - 65
        );

        fire.curveTo(
                x + 25,
                y - 40,
                x + 20,
                y - 20,
                x + 6,
                y
        );

        fire.closePath();

        g2.setColor(
                new Color(255, 120, 10)
        );

        g2.fill(fire);

        // Inner fire

        g2.setColor(
                new Color(255, 220, 80)
        );

        g2.fillOval(
                x,
                y - 35,
                12,
                30
        );
    }

    // --------------------------------------------------
    // TITLE
    // --------------------------------------------------

    private void drawTitle(Graphics2D g2) {

        String title = "छत्रपती शिवाजी महाराज";

        g2.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        42
                )
        );

        FontMetrics fm =
                g2.getFontMetrics();

        int x =
                (WIDTH - fm.stringWidth(title)) / 2;

        // Golden glow

        g2.setColor(
                new Color(255, 200, 50, 70)
        );

        g2.drawString(
                title,
                x + 3,
                650 + 3
        );

        // Main text

        g2.setColor(
                new Color(255, 215, 80)
        );

        g2.drawString(
                title,
                x,
                650
        );
    }

    // --------------------------------------------------
    // MAIN METHOD
    // --------------------------------------------------

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame(
                            "Chhatrapati Shivaji Maharaj Animation"
                    );

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setResizable(false);

            ShivajiMaharajAnimation animation =
                    new ShivajiMaharajAnimation();

            frame.add(animation);

            frame.pack();

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
    }
}