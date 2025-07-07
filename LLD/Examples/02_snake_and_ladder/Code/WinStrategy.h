#ifndef WIN_STRATEGY_H
#define WIN_STRATEGY_H

#include "Player.h"
#include <vector>

class WinStrategy
{
public:
    int winningPosition;
    vector<Player> winners;

    WinStrategy(int winningPosition) : winningPosition(winningPosition) {}

    virtual bool checkWin(Player player) = 0;
    virtual bool isGameOver() = 0;

    vector<Player> getWinners()
    {
        return winners;
    }
};

class DefaultWinStrategy : public WinStrategy
{
public:
    DefaultWinStrategy(int winningPosition) : WinStrategy(winningPosition) {}

    bool checkWin(Player player)
    {
        if (player.getPosition() >= winningPosition)
        {
            cout<< "-------Player " << player.getName() << " has reached the winning position!----------" << endl;
            winners.push_back(player);
            return true;
        }

        return false;
    }

    bool isGameOver()
    {
        return winners.size() == 1;
    }
};

class atLeastFivePlayerWinStrategy : public WinStrategy
{
public:
    atLeastFivePlayerWinStrategy(int winningPosition) : WinStrategy(winningPosition) {}

    bool checkWin(Player player)
    {
        if (player.getPosition() >= winningPosition)
        {
            winners.push_back(player);
            return true;
        }

        return false;
    }

    bool isGameOver()
    {
        return winners.size() == 2;
    }
};

#endif // WIN_STRATEGY_H
