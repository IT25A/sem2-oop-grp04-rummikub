Requirements:
class Game{
    Methods:
        - createGame(List<playerIds>) : create
        - loadGame(gameId) : get
}
class GameAction{
}
enum class GameStatus {
     {IN_PROGRESS, FINISHED}
}
class GameState {
    Attributes: 
        _string_ gameId, 
        _GameStatus_ status, 
        

class Tile {
    // Attributes: 
        var color:TileColor = ""
        var TileNumber:int? = min:1, max:13, nullable // JOKER = null;
    // Methods:
}

enum class TileColor {
    RED,
    BLACK,
    BLUE,
    YELLOW, 
    JOKER
}

enum class TileSetType {
    RUN,
    GROUP
}

TileSet {
    //Attributes:
        setType:TileSetType = "RUN" or "GROUP"
        tiles : List<Tile>
    //Methods:
        setType(tiles) {
            count and check tiles in set
            min of 3, either RUN or Group
        
        }
}