package com.company;
import java.awt.*;
import java.awt.event.*;

public class MouseEvents implements MouseListener {

        public Point Position = new Point(0,0);
        public MouseEvents(){
                Main.frame.addMouseListener(this);
        }


        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {
                Position = new Point(e.getPoint().x,e.getPoint().y);
                Main.NextMove(new Point(Position.X/(Main.frame.getWidth()/8),Position.Y/(Main.frame.getHeight()/8)));
        }


}
