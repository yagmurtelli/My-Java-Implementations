import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ColorMatchGame extends JFrame implements ActionListener {

    private final JButton[] buttons = new JButton[16];
    private final Random random = new Random();
    private final JLabel infoLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel scoreLabel = new JLabel("Skor: 0", SwingConstants.CENTER);
    private final JLabel timeLabel = new JLabel("Sure: 90", SwingConstants.CENTER);

    private int correctIndex;
    private int score = 0;
    private int timeLeft = 90;
    private Timer gameTimer;

    public ColorMatchGame() {
        setTitle("Color Match Game");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);


        JLabel titleLabel = new JLabel("Dogru rengi bul :)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(80, 20, 320, 35);
        add(titleLabel);

        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        infoLabel.setBounds(50, 70, 380, 35);
        add(infoLabel);

        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setBounds(70, 110, 150, 30);
        add(scoreLabel);

        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timeLabel.setBounds(270, 110, 150, 30);
        add(timeLabel);

        JPanel gamePanel = new JPanel(new GridLayout(4, 4, 12, 12));
        gamePanel.setBounds(40, 160, 400, 400);
        gamePanel.setOpaque(false);
        add(gamePanel);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setFocusPainted(false);
            buttons[i].setOpaque(true);
            buttons[i].setContentAreaFilled(true);
            buttons[i].setBorderPainted(true);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            gamePanel.add(buttons[i]);
        }

        createNewRound();
        startTimer();
    }

    private void createNewRound() {
        String[] colorNames = { "Kirmizi", "Mavi", "Yesil", "Turuncu", "Pembe", "Sari" };
        Color[] colors = {
            new Color(220, 70, 70),
            new Color(70, 120, 220),
            new Color(70, 170, 100),
            new Color(240, 150, 60),
            new Color(220, 110, 170),
            new Color(235, 210, 70)
        };

        int target = random.nextInt(colors.length);
        correctIndex = random.nextInt(buttons.length);
        infoLabel.setText("Tiklanacak renk: " + colorNames[target]);

        for (int i = 0; i < buttons.length; i++) {
            if (i == correctIndex) {
                buttons[i].setBackground(colors[target]);
            } else {
                int randomColorIndex;

                do {
                    randomColorIndex = random.nextInt(colors.length);
                } while (randomColorIndex == target);

                buttons[i].setBackground(colors[randomColorIndex]);
            }
        }
    }

    private void startTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timeLabel.setText("Sure: " + timeLeft);

                if (timeLeft <= 0) {
                    gameTimer.stop();
                    for (JButton button : buttons) {
                        button.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(ColorMatchGame.this, "Oyun biter ve Skorun: " + score);
                }
            }
        });

        gameTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timeLeft <= 0) {
            return;
        }

        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                if (i == correctIndex) {
                    score += 15;
                } else {
                    score = Math.max(0, score - 5);
                }

                scoreLabel.setText("Skorun: " + score);
                createNewRound();
                break;
            }
        }
    }

    public static void main(String[] args) {
        ColorMatchGame game = new ColorMatchGame();
        game.setVisible(true);
    }
}
