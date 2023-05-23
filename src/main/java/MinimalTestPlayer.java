/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2020 Caprica Software Limited.
 */

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/**
 * An absolute minimum test player.
 */
public class MinimalTestPlayer extends VlcjTest {

    public static void main(String[] args) throws Exception {

        final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        JFrame f = new JFrame("Test Player");
        f.setIconImage(new ImageIcon(Objects.requireNonNull(MinimalTestPlayer.class.getResource("/icons/icon.png"))).getImage());
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
            }
        });
        f.setContentPane(mediaPlayerComponent);
        f.setVisible(true);

        mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\ivo-p\\dev\\SmartSubtitles\\src\\main\\java\\video.mkv");

        Thread.currentThread().join();
    }
}