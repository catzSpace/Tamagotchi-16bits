import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.*;

public class AddSprite {
    public void addSprite(JPanel panel, String path, int x, int y, int width, int height){
        
        ImageIcon originalIcon = new ImageIcon(path);

        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setBounds(x, y, width, height);
        panel.add(label);
    }


    public void addTemporaryGif(JPanel panel, String path, int x, int y, int width, int height, int time, Runnable onComplete) {
        ImageIcon icon = new ImageIcon(path);

        JLabel label = new JLabel(icon) {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, width, height, this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
        };

        label.setBounds(x, y, width, height);
        panel.add(label);
        panel.setComponentZOrder(label, 0);
        panel.repaint();

        Timer timer = new Timer(time, null);
        timer.setRepeats(false);
        timer.addActionListener(e -> {
            timer.stop();
            if (onComplete != null) {
                onComplete.run();
            }
        });
        timer.start();
    }

    
    public void addImageButton(JPanel panel, String imagePath, int x, int y, int width, int height, Runnable onClick) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            if (onClick != null) onClick.run();
        });

        panel.add(button);
        panel.repaint();
    }


    public void addClickableSprite(JPanel panel, String path, int x, int y, int width, int height, Runnable onClick) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();

        JLabel label = new JLabel(new ImageIcon(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        label.setBounds(x, y, width, height);

        // Tamaño ampliado
        int hoverScale = 15;


        label.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onClick.run();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.setIcon(new ImageIcon(originalImage.getScaledInstance(width + hoverScale, height + hoverScale, Image.SCALE_SMOOTH)));
                label.setBounds(x - hoverScale / 2, y - hoverScale / 2, width + hoverScale, height + hoverScale); // centrar
                SoundPlayer musicPlayer = new SoundPlayer();
                musicPlayer.playEffectSound("assets/app/audio/effects/hoverPet.wav");                                             
                panel.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setCursor(Cursor.getDefaultCursor());
                label.setIcon(new ImageIcon(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                label.setBounds(x, y, width, height);
                panel.repaint();
            }

        });

        panel.add(label);
        panel.repaint();
    }


    
    public void addClickableSpriteAnim(JPanel panel, String path, int x, int y, int width, int height, Runnable onClick) {
        ImageIcon icon = new ImageIcon(path);

        JLabel label = new JLabel(icon) {
            @Override
            protected void paintComponent(Graphics g) {
                // Dibuja el GIF escalado, manteniendo la animación
                g.drawImage(icon.getImage(), 0, 0, width, height, this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
        };

        label.setBounds(x, y, width, height);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Efecto de hover
        int hoverScale = 10;
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setBounds(x - hoverScale / 2, y - hoverScale / 2, width + hoverScale, height + hoverScale);
                panel.repaint();
                SoundPlayer musicPlayer = new SoundPlayer();
                musicPlayer.playEffectSound("assets/app/audio/effects/hoverPet.wav");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setBounds(x, y, width, height);
                panel.repaint();
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onClick.run();
            }
        });

        panel.add(label);
        panel.setComponentZOrder(label, 0); // por si otros elementos se superponen
        panel.repaint();
    }
}
