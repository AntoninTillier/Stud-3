package com.aq.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mouseListener implements MouseListener, MouseMotionListener {
    public boolean clic = false;
    public boolean tire = false;
    public int x = 0;
    public int y = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        clic = true;
        tire = true;
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clic = false;
        tire = false;
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}