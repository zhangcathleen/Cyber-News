import tester.Tester;

//keeps track of the drivers and directions of a RoadTrip
class RoadTrip {
  String driver1;
  String driver2;
  ILoDirections directions;

  RoadTrip(String driver1, String driver2, ILoDirections directions) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.directions = directions;
  }

  public ILoRoadTripChunk splitUpTrip(int driveMiles) {
    return this.directions.getDirection(int driveMiles);
  }
}

interface ILoDirections {

  ILoRoadTripChunk getDirection(int driveMiles);

}

class MtLoDirections implements ILoDirections{

  public ILoRoadTripChunk getDirection(int driveMiles) {
    return 0;
  }
}

class ConsLoDirections implements ILoDirections{
  Direction first;
  ILoDirections rest;

  ConsLoDirections(Direction first, ILoDirections rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoRoadTripChunk getDirection(int driveMiles) {
    //switch drivers because given number of miles has been met and there are more miles left
    if (this.first.calcDirection(driveMiles) > 0) {
      return this.first.
    }
    //switch drivers because given number of miles have been met exactly
    if (this.first.calcDirection(driveMiles) = 0) {//switch drivers

    }
    //keep the same driver because given number of miles have not been met yet
    if (this.first.calcDirection(driveMiles) < 0) {

    }
    else {

    }
  }
}

class Direction {
  String description;
  int miles;

  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }

  public int calcDirection(int driveMiles) {
    return driveMiles - this.miles;
  }
}

interface ILoRoadTripChunk {

}

class MtLoRoadTripChunk implements ILoRoadTripChunk {

}

class ConsLoRoadTripChunk implements ILoRoadTripChunk{
  RoadTripChunk first;
  ILoRoadTripChunk rest;

  ConsLoRoadTripChunk(RoadTripChunk first, ILoRoadTripChunk rest) {
    this.first = first;
    this.rest = rest;
  }
}

//a list of Directions of what a driver will drive during the RoadTrip
class RoadTripChunk {
  String driver;
  ILoDirections directions;

  RoadTripChunk(String driver, ILoDirections directions) {
    this.driver = driver;
    this.directions = directions;
  }
}

class ExamplesRoadTrip{
  RoadTrip hazelAndHenry = new RoadTrip("Hazel", "Henry", 
      new ConsLoDirections(new Direction("Make a left at Alberquerque", 13),
          new ConsLoDirections(new Direction("Make a right at the fork", 2), 
              new ConsLoDirections(new Direction("Make a left at the next fork", 3),
                  new ConsLoDirections(new Direction("Take the overpass", 45),
                      new ConsLoDirections(new Direction("Destination on left", 12),
                          new MtLoDirections()))))));

  boolean testExamplesRoadTrip(Tester t) {
    return t.checkExpect(this.hazelAndHenry.splitUpDirections(15), 
        new ConLoRoadTripChunk("Hazel"))
  }

}