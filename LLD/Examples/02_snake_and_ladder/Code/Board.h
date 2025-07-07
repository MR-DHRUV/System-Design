#ifndef BOARD_H
#define BOARD_H

#include "Player.h"
#include <vector>
#include <cstdlib>
#include <string>
#include <iostream>
#include <unordered_set>

using namespace std;

class Jumper
{
protected:
    int start;
    int end;

public:
    Jumper(int start, int end) : start(start), end(end) {}

    virtual int jump() = 0;
};

class Snake : public Jumper
{
public:
    Snake(int start, int end) : Jumper(start, end) {}

    int jump()
    {
        return end;
    }
};

class Ladder : public Jumper
{
public:
    Ladder(int start, int end) : Jumper(start, end) {}

    int jump()
    {
        return end;
    }
};

class BoardCell
{
    Jumper *jumper;

public:
    int position;

    BoardCell(int position = -1)
        : position(position), jumper(NULL) {}

    void setJumper(Jumper *newJumper)
    {
        jumper = newJumper;
    }

    bool hasJumper() const
    {
        return jumper != NULL;
    }

    int jump()
    {
        if (jumper)
        {
            return jumper->jump();
        }
        return position;
    }
};

class Board
{
    vector<BoardCell> cells;
    int snakeCount, ladderCount, totalCells;

public:
    // Random initialization of snakes and ladders
    Board(int totalCells = 100, int snakeCount = 0, int ladderCount = 0)
        : totalCells(totalCells), snakeCount(snakeCount), ladderCount(ladderCount)
    {
        cells.resize(totalCells + 1);
        cells[0] = BoardCell(-1);

        for (int i = 1; i <= totalCells; ++i)
            cells[i] = BoardCell(i);

        int start, end, sc = snakeCount, lc = ladderCount;
        while (sc)
        {
            start = rand() % totalCells;
            end = rand() % totalCells;

            if (start > end && !cells[start].hasJumper() && !cells[end].hasJumper())
            {
                cells[start].setJumper(new Snake(start, end));
                sc--;
            }
        }

        while (lc)
        {
            start = rand() % totalCells;
            end = rand() % totalCells;

            if (start < end && !cells[start].hasJumper() && !cells[end].hasJumper())
            {
                cells[start].setJumper(new Ladder(start, end));
                lc--;
            }
        }

        cout << "Board initialized of size " << totalCells << " with " << snakeCount << " snakes and " << ladderCount << " ladders." << endl;
    }

    BoardCell getCell(int position)
    {
        if (position < 1 || position > totalCells)
        {
            return cells[totalCells]; // Return the last cell if out of bounds
        }

        return cells[position];
    }
};

#endif // BOARD_H
