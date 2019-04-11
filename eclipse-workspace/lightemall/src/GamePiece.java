import java.awt.Color;
import java.util.ArrayList;

import javalib.impworld.WorldScene;
import javalib.worldimages.LineImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.StarImage;
import javalib.worldimages.WorldImage;

class GamePiece {
  // in logical coordinates, with the origin
  // at the top-left corner of the screen
  int row;
  int col;
  // whether this GamePiece is connected to the
  // adjacent left, right, top, or bottom pieces
  boolean left;
  boolean right;
  boolean top;
  boolean bottom;
  // whether the power station is on this piece
  boolean powerStation;
  GamePiece leftPiece;
  GamePiece rightPiece;
  GamePiece topPiece;
  GamePiece bottomPiece;

  GamePiece(int row, int col, boolean l, boolean r, boolean t, boolean b, boolean power) {
    this.left = l;
    this.right = r;
    this.bottom = b;
    this.top = t;
    this.powerStation = power;
    this.row = row;
    this.col = col;
  }

  // EFFECT: numbering the nodes in given the ArrayList surrounding this node 1-8
  // starting
  // from the top left,
  // adds nodes from current up to and including stop to this node's list of
  // surrounding nodes
  void connectPieces(int current, int stop, ArrayList<ArrayList<GamePiece>> nodes, int row,
      int column) {
    if (current <= stop) {
      if (current == 1) {
        this.topPiece = (nodes.get(row - 1).get(column));
        this.connectPieces(current + 1, stop, nodes, row, column);
      }
      else if (current == 2) {
        this.rightPiece = (nodes.get(row).get(column + 1));
        this.connectPieces(current + 1, stop, nodes, row, column);
      }
      else if (current == 3) {
        this.bottomPiece = (nodes.get(row + 1).get(column));
        this.connectPieces(current + 1, stop, nodes, row, column);
      }
      else if (current == 4) {
        this.leftPiece = (nodes.get(row).get(column - 1));
        this.connectPieces(current + 1, stop, nodes, row, column);
      }
    }
  }

  // EFFECT: rotates this game piece to the counter-clockwise
  void rotate(String type) {
    if (type.equals("LeftButton")) {
      boolean tempTop = this.top;
      this.top = this.right;
      this.right = this.bottom;
      this.bottom = this.left;
      this.left = tempTop;
    }
    if (type.equals("RightButton")) {
      boolean tempTop = this.top;
      this.top = this.left;
      this.left = this.bottom;
      this.bottom = this.right;
      this.right = tempTop;
    }
  }

  // draws this game piece on the given world scene with given x y and size
  WorldScene drawOn(WorldScene start, int x, int y, int tileSize) {
    WorldScene iterate = start;
    WorldImage tile = new RectangleImage(tileSize, tileSize, "solid", Color.BLACK);
    WorldImage xLine = new LineImage(new Posn(tileSize / 2, 0), Color.yellow);
    WorldImage yLine = new LineImage(new Posn(0, tileSize / 2), Color.yellow);
    WorldImage star = new StarImage(30, 9, OutlineMode.SOLID, Color.BLUE);
    if (this.powerStation) {
      tile = new OverlayImage(star, tile);
    }
    if (this.left) {
      tile = new OverlayImage(xLine.movePinhole(tileSize / 4, 0), tile);
    }
    if (this.right) {
      tile = new OverlayImage(xLine.movePinhole(-1 * (tileSize / 4), 0), tile);
    }
    if (this.top) {
      tile = new OverlayImage(yLine.movePinhole(0, tileSize / 4), tile);
    }
    if (this.bottom) {
      tile = new OverlayImage(yLine.movePinhole(0, -1 * (tileSize / 4)), tile);
    }
    iterate.placeImageXY(tile.movePinhole(-tileSize * .5, -tileSize * .5), x, y);
    return iterate;
  }

  public void isPowerSource() {
    this.powerStation = true;
    
  }

//  // finds the diameter of this board
//  public int findDiameter(ArrayList<ArrayList<GamePiece>> board) {
//    return bfs(this, new Queue<GamePiece>(), board);
//  }
//
//  // does the breadth first search 
//  int bfs(GamePiece from, Queue<GamePiece> workList, ArrayList<ArrayList<GamePiece>> board) {
//    Deque<GamePiece> alreadySeen = new Deque<GamePiece>();
//    
//    int d = 0;
//    workList.add(from);
//    while (!workList.isEmpty()) {
//      GamePiece next = workList.remove();
//      if (alreadySeen.seenAll(board)) {
//        return d; // returns the depth
//      }
//      else if (alreadySeen.contains(next)) {
//        // do nothing: we've already seen this one
//      }
//      else {
//        // add all the neighbors of next to the worklist for further processing
//        for (Edge e : next.outEdges) {
//          workList.add(e.to);
//          
//        }
//        // add next to alreadySeen, since we're done with it
//        alreadySeen.addAtHead(next);
//      }
//    }
//    // We haven't found the to vertex, and there are no more to try
//    return 1;
//  }
//  }
}