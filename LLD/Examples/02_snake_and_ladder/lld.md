```mermaid
%%{init: {'theme':'forest'}}%%
classDiagram
    
    class Player {
        +String id
        +String name
        Position position
    }

    class Dice {
        +int sides
        +int roll()
    }

    class DiceManager {
        +int diceCount
        +list~Dice~ diceList
        +addDice(Dice dice)
        +removeDice(Dice dice)
        +rollAll()
    }

    DiceManager o-- Dice : manages

    class Jumper {
        <<abstract>>
        +Position start
        +Position end
        +Jump()
    }

    Jumper <|-- Snake
    Jumper <|-- Ladder

    class Board {
        List~BoardCell~ cells
        +Position size

        +Board(size)
        +Board(List~List~BoardCell~~ board)
    }

    Board o-- BoardCell : contains

    class BoardCell {
        +Position position
        +int displayPosition
        +Jumper jumper
    }

    BoardCell o-- Jumper : has

    class WinStrategy {
        <<interface>>
        +boolean checkWin(Player player, Board board)
        +boolean isGameOver(Game game)
    }

    WinStrategy <|-- DefaultWinStrategy
    WinStrategy <|-- AtmostFivePlayersWinStrategy
    WinStrategy <|-- AtleastFivePlayersWinStrategy

    class Game {
        +String id
        +Position startPosition
        +Position winPosition
        +int diceCount
        +int maxPlayers
        +int snakeCount
        +int ladderCount
        +Position boardSize
        +Board board
        +DiceManager diceManager
        +WinStrategy winStrategy
        +Deque~Player~ players

        +startGame()
        +stopGame()
        +playTurn()
        +addPlayer(Player player)
        +removePlayer(Player player)
    }

    Game o-- Board : has
    Game o-- Player : manages
    Game o-- WinStrategy : uses
    Game o-- DiceManager : uses

```mermaid
%%{init: {'theme':'forest'}}%%
classDiagram
    
    class Player {
        +String id
        +String name
        Position position
    }

    class Dice {
        +int sides
        +int roll()
    }

    class DiceManager {
        +int diceCount
        +list~Dice~ diceList
        +addDice(Dice dice)
        +removeDice(Dice dice)
        +rollAll()
    }

    DiceManager o-- Dice : manages

    class Jumper {
        <<abstract>>
        +Position start
        +Position end
        +Jump()
    }

    Jumper <|-- Snake
    Jumper <|-- Ladder

    class Board {
        List~BoardCell~ cells
        +Position size

        +Board(size)
        +Board(List~List~BoardCell~~ board)
    }

    Board o-- BoardCell : contains

    class BoardCell {
        +Position position
        +int displayPosition
        +Jumper jumper
    }

    BoardCell o-- Jumper : has

    class WinStrategy {
        <<interface>>
        +boolean checkWin(Player player, Board board)
        +boolean isGameOver(Game game)
    }

    WinStrategy <|-- DefaultWinStrategy
    WinStrategy <|-- AtmostFivePlayersWinStrategy
    WinStrategy <|-- AtleastFivePlayersWinStrategy

    class Game {
        +String id
        +Position startPosition
        +Position winPosition
        +int diceCount
        +int maxPlayers
        +int snakeCount
        +int ladderCount
        +Position boardSize
        +Board board
        +DiceManager diceManager
        +WinStrategy winStrategy
        +Deque~Player~ players

        +startGame()
        +stopGame()
        +playTurn()
        +addPlayer(Player player)
        +removePlayer(Player player)
    }

    Game o-- Board : has
    Game o-- Player : manages
    Game o-- WinStrategy : uses
    Game o-- DiceManager : uses

```mermaid
%%{init: {'theme':'forest'}}%%
classDiagram
    
    class Player {
        +String id
        +String name
        Position position
    }

    class Dice {
        +int sides
        +int roll()
    }

    class DiceManager {
        +int diceCount
        +list~Dice~ diceList
        +addDice(Dice dice)
        +removeDice(Dice dice)
        +rollAll()
    }

    DiceManager o-- Dice : manages

    class Jumper {
        <<abstract>>
        +Position start
        +Position end
        +Jump()
    }

    Jumper <|-- Snake
    Jumper <|-- Ladder

    class Board {
        List~BoardCell~ cells
        +Position size

        +Board(size)
        +Board(List~List~BoardCell~~ board)
    }

    Board o-- BoardCell : contains

    class BoardCell {
        +Position position
        +int displayPosition
        +Jumper jumper
    }

    BoardCell o-- Jumper : has

    class WinStrategy {
        <<interface>>
        +boolean checkWin(Player player, Board board)
        +boolean isGameOver(Game game)
    }

    WinStrategy <|-- DefaultWinStrategy
    WinStrategy <|-- AtmostFivePlayersWinStrategy
    WinStrategy <|-- AtleastFivePlayersWinStrategy

    class Game {
        +String id
        +Position startPosition
        +Position winPosition
        +int diceCount
        +int maxPlayers
        +int snakeCount
        +int ladderCount
        +Position boardSize
        +Board board
        +DiceManager diceManager
        +WinStrategy winStrategy
        +Deque~Player~ players

        +startGame()
        +stopGame()
        +playTurn()
        +addPlayer(Player player)
        +removePlayer(Player player)
    }

    Game o-- Board : has
    Game o-- Player : manages
    Game o-- WinStrategy : uses
    Game o-- DiceManager : uses
`
```mermaid
%%{init: {'theme':'forest'}}%%
classDiagram
    
    class Player {
        +String id
        +String name
        Position position
    }

    class Dice {
        +int sides
        +int roll()
    }

    class DiceManager {
        +int diceCount
        +list~Dice~ diceList
        +addDice(Dice dice)
        +removeDice(Dice dice)
        +rollAll()
    }

    DiceManager o-- Dice : manages

    class Jumper {
        <<abstract>>
        +Position start
        +Position end
        +Jump()
    }

    Jumper <|-- Snake
    Jumper <|-- Ladder

    class Board {
        List~BoardCell~ cells
        +Position size

        +Board(size)
        +Board(List~List~BoardCell~~ board)
    }

    Board o-- BoardCell : contains

    class BoardCell {
        +Position position
        +int displayPosition
        +Jumper jumper
    }

    BoardCell o-- Jumper : has

    class WinStrategy {
        <<interface>>
        +boolean checkWin(Player player, Board board)
        +boolean isGameOver(Game game)
    }

    WinStrategy <|-- DefaultWinStrategy
    WinStrategy <|-- AtmostFivePlayersWinStrategy
    WinStrategy <|-- AtleastFivePlayersWinStrategy

    class Game {
        +String id
        +Position startPosition
        +Position winPosition
        +int diceCount
        +int maxPlayers
        +int snakeCount
        +int ladderCount
        +Position boardSize
        +Board board
        +DiceManager diceManager
        +WinStrategy winStrategy
        +Deque~Player~ players

        +startGame()
        +stopGame()
        +playTurn()
        +addPlayer(Player player)
        +removePlayer(Player player)
    }

    Game o-- Board : has
    Game o-- Player : manages
    Game o-- WinStrategy : uses
    Game o-- DiceManager : uses
``
```mermaid
%%{init: {'theme':'forest'}}%%
classDiagram
    
    class Player {
        +String id
        +String name
        Position position
    }

    class Dice {
        +int sides
        +int roll()
    }

    class DiceManager {
        +int diceCount
        +list~Dice~ diceList
        +addDice(Dice dice)
        +removeDice(Dice dice)
        +rollAll()
    }

    DiceManager o-- Dice : manages

    class Jumper {
        <<abstract>>
        +Position start
        +Position end
        +Jump()
    }

    Jumper <|-- Snake
    Jumper <|-- Ladder

    class Board {
        List~BoardCell~ cells
        +Position size

        +Board(size)
        +Board(List~List~BoardCell~~ board)
    }

    Board o-- BoardCell : contains

    class BoardCell {
        +Position position
        +int displayPosition
        +Jumper jumper
    }

    BoardCell o-- Jumper : has

    class WinStrategy {
        <<interface>>
        +boolean checkWin(Player player, Board board)
        +boolean isGameOver(Game game)
    }

    WinStrategy <|-- DefaultWinStrategy
    WinStrategy <|-- AtmostFivePlayersWinStrategy
    WinStrategy <|-- AtleastFivePlayersWinStrategy

    class Game {
        +String id
        +Position startPosition
        +Position winPosition
        +int diceCount
        +int maxPlayers
        +int snakeCount
        +int ladderCount
        +Position boardSize
        +Board board
        +DiceManager diceManager
        +WinStrategy winStrategy
        +Deque~Player~ players

        +startGame()
        +stopGame()
        +playTurn()
        +addPlayer(Player player)
        +removePlayer(Player player)
    }

    Game o-- Board : has
    Game o-- Player : manages
    Game o-- WinStrategy : uses
    Game o-- DiceManager : uses
```
```