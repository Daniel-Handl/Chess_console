package com.company;
import java.util.Scanner;
import java.awt.*;

public class Main {
    public static MyFrame frame = new MyFrame();
    public static String player_name = "White";
    public static  boolean player = true;
    public static boolean turn = true;
    public static boolean selected = false;
    public static boolean win = false;
    public static Point selected_piece = new Point(0,0);
    public static GameBoard b = new GameBoard(8);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MouseEvents mouseEvents = new MouseEvents();
             frame.paint(frame.getGraphics());
    }
    public static void NextMove(Point point){
        if (!selected || (GameBoard.board[point.X][point.Y] != null && GameBoard.board[point.X][point.Y].Player == player)){
            if (GameBoard.board[point.X][point.Y] != null && GameBoard.board[point.X][point.Y].Player == player){
                System.out.println(player_name + " selected: " + GameBoard.board[point.X][point.Y] + point);
                selected_piece = point;
                selected = true;
                turn = true;
                frame.paint(frame.getGraphics());
            }
        }else{
            if (GameBoard.board[selected_piece.X][selected_piece.Y].ValMove(point)){
                Chess_piece p = GameBoard.board[selected_piece.X][selected_piece.Y];
                p.Position = point;
                System.out.println(selected_piece + " " + p + " moved to " + point);
                GameBoard.board[point.X][point.Y] = p;
                if (p.getClass() == Pawn.class) {
                    if ((point.Y == 0 && p.Player)||(point.Y == 7 && !p.Player)) {
                        new Queen(point, p.Player);
                    }
                }
                GameBoard.board[selected_piece.X][selected_piece.Y] = null;
                selected = false;
                if (b.WinCheck(player)){

                    System.out.println(player_name + " has won!!!");
                    win = true;
                }
                player = !player;
                frame.paint(frame.getGraphics());
                if (player) player_name = "White";
                else  player_name = "Black";
                System.out.println(player_name + "'s turn");
            }
        }

    }
}

abstract class Chess_piece {
    public Point Position;
    public boolean Player;
    //true = white, false = black
    public Chess_piece(Point position, boolean player){
        Position = position;
        Player = player;
        GameBoard.board[Position.X][Position.Y] = this;
    }
    public boolean ValMove(Point p){
        return true;
    }
    @Override
    public String toString() {
        return  " ";
    }

}

class Pawn extends Chess_piece {

    public Pawn(Point position, boolean player){
        super(position, player);
    }
    public boolean ValMove(Point p){
        if ( GameBoard.BoundCheck(p.X,p.Y) && (GameBoard.board[p.X][p.Y] == null || GameBoard.board[p.X][p.Y].Player!=this.Player)){
            if ((Position.Y == 1 && !Player)||(Position.Y == 6 && Player)) {
                if ((this.Player && p.Y == Position.Y-2 || !this.Player && p.Y == Position.Y+2) && p.X == Position.X && GameBoard.board[p.X][p.Y] == null) return true;
            }
            if (Math.abs(Position.X-4) == 2 && Math.abs(Position.Subtract(p).Y) == 2 && p.X == Position.X) return true;
            if ((this.Player && p.Y == Position.Y-1 || !this.Player && p.Y == Position.Y+1) && p.X == Position.X && GameBoard.board[p.X][p.Y] == null) return true;
            if ((this.Player && p.Y == Position.Y-1 || !this.Player && p.Y == Position.Y+1) && Math.abs(Position.Subtract(p).X) == 1 && GameBoard.board[p.X][p.Y] != null &&  GameBoard.board[p.X][p.Y].Player == !this.Player) return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if (this.Player) return  "♙";
        else return  "♟";
    }
}
class Rook extends Chess_piece {

    public Rook(Point position, boolean player){
        super(position, player);
    }
    public boolean ValMove(Point p){
        if ((Position.X == p.X || Position.Y == p.Y) && GameBoard.BoundCheck(p.X,p.Y) && (GameBoard.board[p.X][p.Y] == null || GameBoard.board[p.X][p.Y].Player!=this.Player)) return true;
        else return false;
    }
    @Override
    public String toString() {
        if (this.Player) return  "♖";
        else return  "♜";
    }
}
class Knight extends Chess_piece {

    public Knight(Point position, boolean player){
        super(position, player);
    }
    public boolean ValMove(Point p){
        if ( GameBoard.BoundCheck(p.X,p.Y) && (GameBoard.board[p.X][p.Y] == null || GameBoard.board[p.X][p.Y].Player!=this.Player)){
        if ((Math.abs(Position.Subtract(p).X) == 2 && Math.abs(Position.Subtract(p).Y) == 1 || Math.abs(Position.Subtract(p).Y) == 2 && Math.abs(Position.Subtract(p).X) == 1 )) return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if (this.Player) return  "♘";
        else return  "♞";
    }
}
class Bishop extends Chess_piece {

    public Bishop(Point position, boolean player){
        super(position, player);
    }
    public boolean ValMove(Point p){
        if ( GameBoard.BoundCheck(p.X,p.Y) && (GameBoard.board[p.X][p.Y] == null || GameBoard.board[p.X][p.Y].Player!=this.Player)) {
            if (Math.abs(Position.Subtract(p).X) == Math.abs(Position.Subtract(p).Y)) return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if (this.Player) return  "♗";
        else return  "♝";
    }
}
class Queen extends Chess_piece {

    public Queen(Point position, boolean player){
        super(position, player);
    }
    public boolean ValMove(Point p){
        if ( GameBoard.BoundCheck(p.X,p.Y) && (GameBoard.board[p.X][p.Y] == null || GameBoard.board[p.X][p.Y].Player!=this.Player)){
            if (Math.abs(Position.Subtract(p).X)==Math.abs(Position.Subtract(p).Y)) return true;
            if (Position.X == p.X || Position.Y == p.Y) return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if (this.Player) return  "♕";
        else return  "♛";
    }
}
class King extends Chess_piece {

    public King(Point position, boolean player){
        super(position, player);
    }
    public boolean ValMove(Point p){
        if ( GameBoard.BoundCheck(p.X,p.Y) && (GameBoard.board[p.X][p.Y] == null || GameBoard.board[p.X][p.Y].Player!=this.Player)) {
            if ((Math.abs(Position.Subtract(p).X) < 2 && Math.abs(Position.Subtract(p).Y) < 2)) return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if (this.Player) return  "♔";
        else return  "♚";
    }
}


class GameBoard {
    public static Chess_piece[][] board;
    public static int Size;
    public GameBoard(int size){
        Size = size;
        boolean player = false;
        board =  new Chess_piece[Size][Size];
        for (int y = 0; y < Size; y++) {
            if (y > 4) player = true;
            for (int x = 0; x < Size; x++) {
                board[x][y] = null;
                if (Math.abs(y - (Size-1-y)) == 7){
                    if (Math.abs(x - (Size-1-x)) == 7) board[x][y] = new Rook(new Point(x,y),player);
                    else if (Math.abs(x - (Size-1-x)) == 5) board[x][y] = new Knight(new Point(x,y),player);
                    else if (Math.abs(x - (Size-1-x)) == 3) board[x][y] = new Bishop(new Point(x,y),player);
                    else if (x == 3) board[x][y] = new Queen(new Point(x,y),player);
                    else if (x == 4) board[x][y] = new King(new Point(x,y),player);
                }
                if (Math.abs(y - (Size-1-y)) == 5) board[x][y] =  new Pawn(new Point(x,y),player);
            }
        }


    }
    public void Move(Chess_piece a,Point b){
        board[b.X][b.Y] = a;
        if (a.getClass() == Pawn.class) {
            if ((a.Position.Y == 0 && a.Player)||(a.Position.Y == 7 && !a.Player)) {
                board[b.X][b.Y] = new Queen(a.Position, a.Player);
            }
        }
        board[b.X][b.Y].Position = b;
        board[a.Position.X][a.Position.Y] = null;
    }
    public boolean WinCheck(boolean Player){
        int nKings = 0;
        boolean fincheck = true;
        Point KingPos = new Point(0,0);
        int[][] CheckArr = new int[Size][Size];
        for (Chess_piece[] row: board) {
            for (Chess_piece piece:row) {
                if (piece != null && piece.getClass()==King.class) nKings++;
            }
        }
        if (nKings<2)return true;
        //vytvoř nový pole kam zapíšu omezení krále způsobený hráči pak zkontroluj uvěznění v tom poli
        for (int y = 0;y < Size;y++){
            for (int x = 0;x < Size;x++){
                if (board[x][y] != null){
                    if (board[x][y].getClass() == King.class && board[x][y].Player != Player) KingPos = new Point(x,y);
                    if (!KingPos.Equals(board[x][y].Position) && board[x][y].Player != Player) CheckArr[x][y] = 1;
                    else{
                        for (int k = 0;k < Size;k++){
                            for (int j = 0;j < Size;j++){
                                if (board[x][y].ValMove(new Point(j,k))) CheckArr[j][k] = 1;
                            }
                        }
                    }
                }
            }
        }
     //   if (BoundCheck(KingPos.X-1,KingPos.Y) && CheckArr[KingPos.X-1][KingPos.Y] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X-1,KingPos.Y-1) && CheckArr[KingPos.X-1][KingPos.Y-1] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X,KingPos.Y-1) && CheckArr[KingPos.X][KingPos.Y-1] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X+1,KingPos.Y-1) && CheckArr[KingPos.X+1][KingPos.Y-1] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X+1,KingPos.Y) && CheckArr[KingPos.X+1][KingPos.Y] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X+1,KingPos.Y+1) && CheckArr[KingPos.X+1][KingPos.Y+1] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X,KingPos.Y+1) && CheckArr[KingPos.X][KingPos.Y+1] != 1) fincheck = false;
     //   else if (BoundCheck(KingPos.X-1,KingPos.Y+1) && CheckArr[KingPos.X-1][KingPos.Y+1] != 1) fincheck = false;

        for (int x = -1; x < 2; x++){
            for (int y = -1; y < 2; y++){
                if (BoundCheck(KingPos.X+x,KingPos.Y+y) && CheckArr[KingPos.X+x][KingPos.Y+y] != 1) fincheck = false;
            }
        }

        return fincheck;
    } //fix it
    public static boolean BoundCheck(int x,int y){
        if (x < 0 || x >= Size || y < 0 || y >= Size) return false;
        else return true;
    }
    @Override
    public String toString() {
        String s = "  1 2 3 4 5 6 7 8\n";
        for (int y = 0; y < Size; y++) {
            s += (y+1)+" ";
            for (int x = 0; x < Size; x++) {
                if (board[x][y] == null) s += "  ";
                else s += board[x][y]+" ";
            }
            s += "\n";
        }
        return  s;
    }
}

class Point{
    public int X;
    public int Y;
    public Point(int x,int y){
        X=x;
        Y=y;
    }
    public Point(int c){
    X = Translate(c).X;
    Y = Translate(c).Y;
    }
    public Point Subtract(Point p){
        return new Point(X-p.X,Y-p.Y);
    }
    public static Point Translate(int c){
        int y = c % 10;
        int x = (c-y)/10;
        return new Point(x-1,y-1);
    }
    public boolean Equals(Point p){
        if (p.X == this.X && p.Y == this.Y) return true;
        else return false;
    }
    @Override
    public String toString() {
        return "x: " + X + ", y: " + Y;
    }
}