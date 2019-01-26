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

  public ILoRoadTripChunks splitUpTrip(int driveMiles) {
    return this.directions.getDirection(driveMiles, this);
  }
}

interface ILoDirections {

  ILoRoadTripChunks getDirection(int driveMiles, RoadTrip rt);

}

class MtLoDirections implements ILoDirections{

  public ILoRoadTripChunks getDirection(int driveMiles) {
    return new MtLoRoadTripChunks();
  }
}

class ConsLoDirections implements ILoDirections{
  Direction first;
  ILoDirections rest;

  ConsLoDirections(Direction first, ILoDirections rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoRoadTripChunks getDirection(int driveMiles) {
    //switch drivers because given number of miles has been met and there are more miles left
    if (this.first.calcDirection(driveMiles) > 0) {
      return new ConsLoRoadTripChunks(this.first.makeDirection(driveMiles), 
          this.rest.getDirection(driveMiles));
    }
    //keep the same driver because given number of miles have not been met yet
    if (this.first.calcDirection(driveMiles) < 0) {

    }

    //switch drivers because given number of miles have been met exactly
    if (this.first.calcDirection(driveMiles) = 0) {
      
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
  
  public Direction makeDirection(driveMiles)
}

interface ILoRoadTripChunks {

}

class MtLoRoadTripChunks implements ILoRoadTripChunks {

}

class ConsLoRoadTripChunks implements ILoRoadTripChunks{
  RoadTripChunk first;
  ILoRoadTripChunks rest;

  ConsLoRoadTripChunks(RoadTripChunk first, ILoRoadTripChunks rest) {
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
          new ConsLoDirections(new Direction("Make a right a the fork", 2),
              new ConsLoDirections(new Direction("Make a left at the next fork", 3),
                  new ConsLoDirections(new Direction("Take the overpass", 45),
                      new ConsLoDirections(new Direction("Destination on left", 12),
                          new MtLoDirections()))))));


  boolean testExamplesRoadTrip(Tester t) {
    return t.checkExpect(this.hazelAndHenry.splitUpTrip(15),
        new ConsLoRoadTripChunks(new RoadTripChunk("Hazel", 
            new ConsLoDirections(new Direction("Make a left at Alberquerque", 13),
                new ConsLoDirections(new Direction("Make a right at the fork", 2),
                    new ConsLoDirections(new Direction("Switch with Henry", 0),
                        new MtLoDirections())))),
            new ConsLoRoadTripChunks(new RoadTripChunk("Henry",
                new ConsLoDirections(new Direction("Make a left at the next fork", 3),
                    new ConsLoDirections(new Direction("Switch with Hazel", 12),
                        new MtLoDirections()))),
                new ConsLoRoadTripChunks(new RoadTripChunk("Hazel",
                    new ConsLoDirections(new Direction("Switch with Henry", 15),
                        new MtLoDirections())),
                    new ConsLoRoadTripChunks(new RoadTripChunk("Henry",
                        new ConsLoDirections(new Direction("Switch with Hazel", 15),
                            new MtLoDirections())),
                        new ConsLoRoadTripChunks(new RoadTripChunk("Hazel",
                            new ConsLoDirections(new Direction("Take the overpass", 3),
                                new ConsLoDirections(new Direction("Destination on left", 12),
                                    new MtLoDirections()))),
                            new MtLoRoadTripChunks()))))));




  }


}