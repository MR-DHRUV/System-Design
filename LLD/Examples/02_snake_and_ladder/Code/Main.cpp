#include <iostream>
#include <cstdlib>
#include <ctime>
#include "Game.h"
using namespace std;

int main()
{
    // Seed the random number generator
    srand(static_cast<unsigned int>(time(nullptr)));

    Game game = Game();

    int numPlayers;
    cout << "Enter number of players (2-5): ";

    cin >> numPlayers;

    for (int i = 0; i < numPlayers; ++i)
        game.addPlayer();

    cin.clear(); // Clear the input buffer
    game.startGame();

    return 0;
}