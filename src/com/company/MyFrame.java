package com.company;
import javax.swing.*;
import  java.awt.*;


public class MyFrame extends JFrame {

    MyPanel panel;
    public MyFrame(){
        panel  = new MyPanel(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

}
 class MyPanel extends  JPanel{
    double width;
    double height;
    MyPanel(int width, int height){
        this.height = height;
        this.width = width;
        this.setPreferredSize(new Dimension(width,height));
    }
    public void paint(Graphics g){
        int cx;
        int cy;
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(6));
        g2d.setFont(new Font(g2d.getFont().getFontName(),g2d.getFont().getStyle(),50));
        for (int i = 0; i < 9; i++){
            cx = (int)(i*(width/8)+3);
            g2d.drawLine(cx,3,cx,(int)height+3);
            g2d.drawLine(3,cx,(int)width+3,cx);
        }
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                if (GameBoard.board[x][y] != null){
                    g2d.setPaint(new Color(60,60,60,100));
                    if (Main.selected && GameBoard.board[x][y].Position.Equals(Main.selected_piece)){
                        for (int j = 0; j < 8; j++){
                            for (int k = 0; k < 8; k++){
                                cx = (int)((j + 0.5)*(width/8)+3-(width/8/2));
                                cy = (int)((k + 0.5)*(width/8)+3-(width/8/2));
                                if (GameBoard.board[x][y].ValMove(new Point(j,k))) g2d.fillRect(cx,cy,(int)width/8,(int)width/8);
                            }
                        }
                    }
                    g2d.setPaint(Color.black);
                    double piece_height = g2d.getFont().createGlyphVector(g2d.getFontMetrics().getFontRenderContext(), GameBoard.board[x][y].toString()).getVisualBounds().getHeight();
                    double piece_width = g2d.getFont().createGlyphVector(g2d.getFontMetrics().getFontRenderContext(), GameBoard.board[x][y].toString()).getVisualBounds().getWidth();
                    cx = (int)((x + 0.5)*(width/8)+3-10 - piece_width/2);
                    cy = (int)((y + 0.5)*(width/8)+3+piece_height/2);
                    g2d.drawString(GameBoard.board[x][y].toString(),cx,cy);
                }
            }
        }
    }
}
