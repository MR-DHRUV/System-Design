#ifndef DICE_MANAGER_H
#define DICE_MANAGER_H

#include <cstdlib>
#include <vector>
using namespace std;

class Dice
{
    int sides;

public:
    Dice(int sides = 6) : sides(sides) {}

    int roll()
    {
        return rand() % sides + 1;
    }
};

class DiceManager
{
    int diceCount, sides;
    vector<Dice> dices;

public:
    DiceManager(int diceCount = 1, int sides = 6)
        : diceCount(diceCount), sides(sides)
    {
        dices = vector<Dice>(diceCount, Dice(sides));
    }

    int rollAll() {
        int sum = 0;
        for(Dice &dice : dices) {
            sum += dice.roll();
        }
        return sum;
    }
};

#endif // DICE_MANAGER_H
