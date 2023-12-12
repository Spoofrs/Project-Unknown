package inter;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainmenu extends JFrame {

    Font default_button_font = new Font("Serif", Font.PLAIN, 20);
    File menu_music = new File("src/assets/audio/menumusic.wav");
    File buttonhovernoise = new File("src/assets/audio/menuhovernoise.wav");
    File buttonselectnoise = new File("src/assets/audio/buttonselectnoise.wav");
    Clip clip;
    Clip buttonhover;
    Clip buttonselect;
    boolean muted_audio = false;

    public mainmenu() {
        setTitle("Project Unknown");
        setSize(400, 400);
        setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/icon.png")));
        setBackground(Color.darkGray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(menu_music));
            clip.loop(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Images
        JLabel mute_button = new JLabel(new ImageIcon("src/assets/images/unmuted.png"));
        mute_button.setBounds(340, 320, 35, 35);

        mute_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!muted_audio) {
                    clip.stop();
                    BufferedImage bufImg;
                    try {
                        bufImg = ImageIO.read(new File("src/assets/images/muted.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    mute_button.setIcon(new ImageIcon(bufImg));
                    muted_audio = true;
                } else {
                    clip.start();
                    BufferedImage bufImg;
                    try {
                        bufImg = ImageIO.read(new File("src/assets/images/unmuted.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    mute_button.setIcon(new ImageIcon(bufImg));
                    muted_audio = false;
                }
            }
        });

        //Button sounds
        try {
            buttonhover = AudioSystem.getClip();
            buttonhover.open(AudioSystem.getAudioInputStream(buttonhovernoise));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            buttonselect = AudioSystem.getClip();
            buttonselect.open(AudioSystem.getAudioInputStream(buttonselectnoise));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons

        JButton play_button = new JButton("Play");
        play_button.setBounds(130, 125, 120, 50);
        play_button.setFont(default_button_font);
        play_button.setBackground(Color.LIGHT_GRAY);
        play_button.setFocusable(false);

        play_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                play_button.setBackground(Color.DARK_GRAY);
                play_button.setForeground(Color.WHITE);
                buttonhover.start();
                if (!muted_audio)
                    buttonhover.loop(1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                play_button.setBackground(Color.LIGHT_GRAY);
                play_button.setForeground(Color.DARK_GRAY);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!muted_audio)
                    buttonselect.loop(1);
            }
        });

        JButton help_button = new JButton("Help");
        help_button.setBounds(130, 180, 120, 50);
        help_button.setFont(default_button_font);
        help_button.setBackground(Color.LIGHT_GRAY);
        help_button.setFocusable(false);

        help_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_button.setBackground(Color.DARK_GRAY);
                help_button.setForeground(Color.WHITE);
                if (!muted_audio)
                    buttonhover.loop(1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_button.setBackground(Color.LIGHT_GRAY);
                help_button.setForeground(Color.DARK_GRAY);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!muted_audio)
                    buttonselect.loop(1);
            }
        });

        JButton exit_button = new JButton("Exit");
        exit_button.setBounds(130, 235, 120, 50);
        exit_button.setFont(default_button_font);
        exit_button.setBackground(Color.LIGHT_GRAY);
        exit_button.setFocusable(false);

        exit_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exit_button.setBackground(Color.DARK_GRAY);
                exit_button.setForeground(Color.WHITE);
                if (!muted_audio)
                    buttonhover.loop(1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exit_button.setBackground(Color.LIGHT_GRAY);
                exit_button.setForeground(Color.DARK_GRAY);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clip.stop();
                setVisible(false);
                dispose();
            }
        });

        // Text
        JLabel menu_title = new JLabel("Project Unknown");
        menu_title.setBounds(20, 20, 400, 60);
        menu_title.setFont(new Font("Serif", Font.PLAIN, 50));


        // Add components to main menu
        add(play_button);
        add(menu_title);
        add(help_button);
        add(exit_button);
        add(mute_button);
        setVisible(true);

    }

}
