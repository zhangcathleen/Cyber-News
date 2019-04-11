import java.util.ArrayList;
import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.Posn;

class LightEmAll extends World {
  // a list of columns of GamePieces,
  // i.e., represents the board in column-major order
  ArrayList<ArrayList<GamePiece>> board;
  // a list of all nodes
  ArrayList<GamePiece> nodes;
  // a list of edges of the minimum spanning tree
  ArrayList<Edge> mst;
  // the width and height of the board
  int width;
  int height;
  int size; // the size of one game piece
  // the current location of the power station,
  // as well as its effective radius
  int powerRow;
  int powerCol;
  int radius;
  int diameter;

  LightEmAll(int w, int h, int s) {
    this.width = w;
    this.height = h;
    this.size = s;
    this.powerCol = w / 2;
    this.powerRow = h / 2;
    // make sure to do radius
  }

  LightEmAll() {
    this.width = 19;
    this.height = 9;
    this.size = 59;
    this.powerCol = this.width / 2;
    this.powerRow = this.height / 2;
    ArrayList<ArrayList<GamePiece>> result1 = new ArrayList<ArrayList<GamePiece>>();
    ArrayList<ArrayList<GamePiece>> result = new ArrayList<ArrayList<GamePiece>>();

    // initializes result to an Arraylist<ArrayList<GamePieces>> with the correct
    // dimensions
    for (int ro = 0; ro < this.height; ro++) {
      ArrayList<GamePiece> row = new ArrayList<GamePiece>();
      for (int co = 0; co < this.width; co++) {
        if ((ro % 2) == 0) {
          row.add(new GamePiece(ro, co, false, false, false, true, false));
        }
        else if ((ro % 2) == 1) {
          if ((co % 2) == 0) {
            row.add(new GamePiece(ro, co, false, true, true, false, false));
          }
          else if ((co % 2) == 1) {
            row.add(new GamePiece(ro, co, true, false, true, false, false));
          }
        }
      }
      result.add(row);
    }

    //places the powersource where it i
    for (int ro = 0; ro < this.height; ro++) {
      for (int co = 0; co < this.width; co++) {
        if (co == this.powerCol) {
          if (ro == this.powerRow) {
            result.get(this.powerRow).get(this.powerCol).powerStation = true;
          }
        }
      }
    }

    // connects the fractals with one another
    for (int ro = 0; ro < this.height; ro++) {
      for (int co = 0; co < this.width; co++) {
        if ((ro % 4) == 0) {
          if ((co == 0) || (co == this.width - 1)) {
            result.get(ro).get(co).top = true;
          }
        }
        if ((ro % 4) == 1) {
          if (((co % 4) == 0) || ((co % 4) == 3)) {
            result.get(ro).get(co).bottom = true;
          }
        }
        if ((ro % 4) == 2) {
          if (((co % 4) == 0) || ((co % 4) == 3)) {
            result.get(ro).get(co).top = true;
          }
        }
        if ((ro % 4) == 3) {
          if (((co % 4) == 0) || ((co % 4) == 3)) {
            result.get(ro).get(co).right = true;
            result.get(ro).get(co).left = true;
          }
          if ((co % 4) == 1) {
            result.get(ro).get(co).right = true;
          }
          if ((co % 4) == 2) {
            result.get(ro).get(co).left = true;
          }
        }
      }
    }

    // makes sure that the borders are not weird
    for (int ro = 0; ro < this.height; ro++) {
      for (int co = 0; co < this.width; co++) {
        if (ro == 0) {
          if ((co == 0) || (co == this.width - 1)) {
            result.get(ro).get(co).top = false;
            result.get(ro).get(co).bottom = true;
          }
        }
        if (co == 0) {
          result.get(ro).get(co).left = false;
          result.get(ro).get(co).bottom = true;
        }
        if (co == this.width - 1) {
          if ((ro > 0) && (ro < this.height - 1)) {
            result.get(ro).get(co).bottom = true;
            result.get(ro).get(co).right = false;
            result.get(ro).get(co).top = true;
          }
        }
        if (ro == this.height - 1) {
          if (co == 0) {
            result.get(ro).get(co).left = false;
            result.get(ro).get(co).bottom = false;
            result.get(ro).get(co).right = true;
            result.get(ro).get(co).top = true;
          }
          else if (co == this.width - 1) {
            result.get(ro).get(co).bottom = false;
            result.get(ro).get(co).left = true;
            result.get(ro).get(co).top = true;
            result.get(ro).get(co).right = false;
          }
          else {
            result.get(ro).get(co).bottom = false;
            result.get(ro).get(co).left = true;
            result.get(ro).get(co).right = true;
            result.get(ro).get(co).top = true;
          }
        }
      }
    }
//    connectHelper(result, this.height, this.width);
    this.board = result;
  }

  // this.diameter = result.get(0).get(0).findDiameter(this.board);
  // this.radius = (this.diameter/2) + 1;

  // EFFECT: connects all of the nodes in given arraylist to their adjacent nodes
  // given
  // number of
  // rows and columns
  void connectHelper(ArrayList<ArrayList<GamePiece>> b, int r, int c) {
    for (int ro = 0; ro < r; ro++) {
      for (int co = 0; co < c; co++) {
        if (ro == 0) {
          if (co == 0) { // this is top left
            b.get(ro).get(co).connectPieces(2, 3, b, ro, co);
          }
          else if (co == c - 1) { // this is top right
            b.get(ro).get(co).connectPieces(3, 4, b, ro, co);
          }
          else { // this is top middles
            b.get(ro).get(co).connectPieces(2, 4, b, ro, co);
          }
        }
        else if (ro == r - 1) {
          if (co == 0) { // this is bottom left
            b.get(ro).get(co).connectPieces(1, 2, b, ro, co);
          }
          else if (co == c - 1) { // this is bottom right
            b.get(ro).get(co).connectPieces(1, 1, b, ro, co);
            b.get(ro).get(co).connectPieces(4, 4, b, ro, co);
          }
          else { // this is bottom middles
            b.get(ro).get(co).connectPieces(1, 2, b, ro, co);
            b.get(ro).get(co).connectPieces(4, 4, b, ro, co);
          }
        }
        else {
          if (co == 0) { // this is middle left
            b.get(ro).get(co).connectPieces(1, 3, b, ro, co);
          }
          else if (co == c - 1) { // this is middle right
            b.get(ro).get(co).connectPieces(1, 1, b, ro, co);
            b.get(ro).get(co).connectPieces(3, 4, b, ro, co);
          }
          else { // this is middle middles
            b.get(ro).get(co).connectPieces(1, 4, b, ro, co);
          }
        }
      }
    }
  }

  // EFFECT: rotates the clicked game pieces counter clockwise or clockwise
  // depending on the click
  public void onMouseClicked(Posn position, String type) {
    GamePiece clicked = this.board.get((int) (position.y / this.size))
        .get((int) (position.x / this.size));
    clicked.rotate(type);
  }

  // draws the LightEmAll Game
  public WorldScene makeScene() {
    WorldScene start = new WorldScene(0, 0);

    for (int row = 0; row < board.size(); row++) {
      ArrayList<GamePiece> thisRow = board.get(row);
      for (int column = 0; column < thisRow.size(); column++) {
        GamePiece thisPiece = thisRow.get(column);
        start = thisPiece.drawOn(start, this.size * column, this.size * row, this.size);
      }
    }
    return start;
  }
}
