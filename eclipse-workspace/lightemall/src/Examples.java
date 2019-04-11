import tester.Tester;
import java.awt.Color;
import javalib.impworld.WorldScene;
import javalib.worldimages.LineImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.StarImage;
import javalib.worldimages.WorldImage;

class Examples {

  LightEmAll first = new LightEmAll();

  GamePiece piece = new GamePiece(0, 0, true, true, true, true, true);
  GamePiece rotate = new GamePiece(0, 0, true, false, true, false, true);
  GamePiece rotate2 = new GamePiece(0, 0, true, false, true, false, true);

  void testBigBang(Tester t) {
    LightEmAll w = first;
    int worldWidth = w.size * w.width;
    int worldHeight = w.size * w.height;
    double tickRate = (1.0 / 28.0);
    w.bigBang(worldWidth, worldHeight, tickRate);
  }

  void testRotate(Tester t) {
    rotate.rotate("LeftButton");
    rotate2.rotate("RightButton");
    t.checkExpect(rotate.top, false);
    t.checkExpect(rotate.right, false);
    t.checkExpect(rotate.left, true);
    t.checkExpect(rotate.bottom, true);
    t.checkExpect(rotate2.top, true);
    t.checkExpect(rotate2.right, true);
    t.checkExpect(rotate2.left, false);
    t.checkExpect(rotate2.bottom, false);

  }

  void testDrawOn(Tester t) {
    WorldImage tile = new RectangleImage(20, 20, "solid", Color.BLACK);
    WorldImage xLine = new LineImage(new Posn(10, 0), Color.yellow);
    WorldImage yLine = new LineImage(new Posn(0, 10), Color.yellow);
    WorldImage star = new StarImage(30, 9, OutlineMode.SOLID, Color.BLUE);
    tile = new OverlayImage(star, tile);
    tile = new OverlayImage(xLine.movePinhole(5, 0), tile);
    tile = new OverlayImage(xLine.movePinhole(-1 * (5), 0), tile);
    tile = new OverlayImage(yLine.movePinhole(0, 5), tile);
    tile = new OverlayImage(yLine.movePinhole(0, -1 * (5)), tile);
    WorldScene stuff = new WorldScene(0, 0);
    stuff.placeImageXY(tile.movePinhole(-10, -10), 0, 0);
    t.checkExpect(piece.drawOn(new WorldScene(0, 0), 0, 0, 20), stuff);
  }

  void testConnectHelpers(Tester t) {
    LightEmAll second = new LightEmAll();
//    t.checkExpect(second.board.get(2).get(2).topPiece, second.board.get(0).get(2).bottomPiece);
//    t.checkExpect(second.board.get(2).get(2).rightPiece, second.board.get(2).get(4).leftPiece);
  }

}
