#ifndef GAME_H
#define GAME_H

#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>
#include <deque>
#include "Player.h"
#include "Board.h"
#include "DiceManager.h"
#include "WinStrategy.h"

using namespace std;

class Game
{
    string id;
    int boardSize, startPosition, snakeCount, ladderCount, diceCount, diceSides, minPlayers, maxPlayers;
    WinStrategy *winStrategy;
    DiceManager diceManager;
    Board board;
    deque<Player> players;

public:
    Game(
        int boardSize = 100,
        int startPosition = 1,
        int snakeCount = 5,
        int ladderCount = 7,
        int diceCount = 1,
        int diceSides = 6,
        int maxPlayers = 5,
        int minPlayers = 2,
        WinStrategy *winStrategy = new DefaultWinStrategy(100)) : boardSize(boardSize),
                                                                  startPosition(startPosition),
                                                                  snakeCount(snakeCount),
                                                                  ladderCount(ladderCount),
                                                                  diceCount(diceCount),
                                                                  diceSides(diceSides),
                                                                  minPlayers(minPlayers),
                                                                  maxPlayers(maxPlayers),
                                                                  winStrategy(winStrategy),
                                                                  id("game_" + to_string(rand() % 10000)),
                                                                  board(boardSize, snakeCount, ladderCount)
    {
        diceManager = DiceManager(diceCount, diceSides);

        cout << "Game initialized with board size: " << boardSize
             << ", snakes: " << snakeCount
             << ", ladders: " << ladderCount
             << ", dice count: " << diceCount
             << ", dice sides: " << diceSides
             << ", max players: " << maxPlayers
             << endl;
    }

    void addPlayer()
    {
        if (players.size() < maxPlayers)
        {
            string id = "Player_" + to_string(rand() % 10000), name;
            cout << "Enter player Name: ";
            cin >> name;

            players.push_back(Player(id, name, startPosition));
            cout << "Player " << name << " added to the game." << endl;
        }
        else
        {
            cout << "Cannot add player, maximum players reached." << endl;
        }
    }

    bool checkMinPlayers() const
    {
        if (players.size() < minPlayers)
        {
            cout << "Not enough players to start the game. At least " << minPlayers << " players are required." << endl;
            return false;
        }

        return true;
    }

    void removePlayer()
    {
        if (players.empty())
        {
            cout << "No players to remove." << endl;
            return;
        }
        else
        {
            string name;
            cout << "Enter player Name to remove: ";
            cin >> name;

            int idx = -1;
            for (int i = 0; i < players.size(); ++i)
            {
                if (players[i].getName() == name)
                {
                    idx = i;
                    break;
                }
            }

            if (idx != -1)
            {
                players.erase(players.begin() + idx);
                cout << "Player " << name << " removed from the game." << endl;
            }
            else
            {
                cout << "Player " << name << " not found." << endl;
            }

            checkMinPlayers();
        }
    }

    void startGame()
    {
        if (!checkMinPlayers())
        {
            return;
        }

        while (!winStrategy->isGameOver())
        {
            Player curr = players.front();
            players.pop_front();

            cout << "Current Player: " << curr.getName() << " at position " << curr.getPosition() << endl;
            cout<< "Press Enter to roll the dice...";
            cin.ignore();

            int roll = diceManager.rollAll(), nextPosition = curr.getPosition() + roll;
            cout << "Player " << curr.getName() << " rolled a total of " << roll << "." << endl;

            nextPosition = board.getCell(nextPosition).jump();
            curr.setPosition(nextPosition);

            cout << "Player " << curr.getName() << " moved to position " << nextPosition << "." << endl;
            if (winStrategy->checkWin(curr))
                cout << "Player " << curr.getName() << " has reached the winning position!" << endl;

            players.push_back(curr);
        }

        if (winStrategy->getWinners().empty())
            cout << "No winners this time!" << endl;
        else if (winStrategy->winners.size() == 1)
            cout << "Game Over! The winner is: " << winStrategy->winners.front().getName() << endl;
        else
        {
            cout << "Game Over! The winners are: ";
            for (Player &winner : winStrategy->getWinners())
                cout << winner.getName() << " ";
            cout << endl;
        }
    }
};

#endif // GAME_H
