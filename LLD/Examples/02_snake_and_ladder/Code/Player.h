#ifndef PLAYER_H
#define PLAYER_H

#include <string>

using namespace std;

class Player
{
    string id, name;
    int position;

public:
    Player(string id, string name, int position = 0)
        : id(id), name(name), position(position) {}

    string getId() const
    {
        return id;
    }

    string getName() const
    {
        return name;
    }

    int getPosition() const
    {
        return position;
    }

    void setPosition(const int &newPosition)
    {
        position = newPosition;
    }
};

#endif // PLAYER_H
