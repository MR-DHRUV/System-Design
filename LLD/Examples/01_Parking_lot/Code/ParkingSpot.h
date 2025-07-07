#ifndef PARKING_SPOT_H
#define PARKING_SPOT_H

#include <string>
#include <vector>
#include <unordered_map>
using namespace std;

enum VehicleType
{
    TWO_WHEELER,
    FOUR_WHEELER,
};

class Vehicle
{
    string vehicleNumber;
    VehicleType vehicleType;

public:
    Vehicle(const string &number, VehicleType type) : vehicleNumber(number), vehicleType(type) {}

    string getVehicleNumber() const { return vehicleNumber; }
    VehicleType getVehicleType() const { return vehicleType; }
};

class ParkingSpot
{
    double price;

public:
    bool isEmpty;
    Vehicle *vehicle;
    int row, column, level;
    ParkingSpot(int r, int c, int l, int p)
        : row(r), column(c), level(l), price(p), vehicle(nullptr), isEmpty(true) {}

    bool park(Vehicle *v)
    {
        if (isEmpty)
        {
            vehicle = v;
            isEmpty = false;
            return true;
        }
        return false;
    }

    void vaccate()
    {
        if (!isEmpty)
        {
            delete vehicle;
            vehicle = nullptr;
            isEmpty = true;
        }
    }

    int getPrice() const { return price; }
    Vehicle *getVehicle() const { return vehicle; }
};

// Strategy Pattern
class ParkingStrategy
{
public:
    virtual ParkingSpot *findSpot(vector<ParkingSpot> &spots) = 0;
};

class NearToEntranceStrategy : public ParkingStrategy
{
public:
    ParkingSpot *findSpot(vector<ParkingSpot> &spots) override
    {
        for (auto &spot : spots)
        {
            if (spot.isEmpty)
            {
                return &spot;
            }
        }
        return nullptr;
    }
};

class NearToElevatorStrategy : public ParkingStrategy
{
    vector<pair<int, int>> elevatorLocations;

    int calculateDistance(ParkingSpot &spot)
    {
        int dist = 1e9;

        for (const auto &elevator : elevatorLocations)
        {
            int elevatorRow = elevator.first;
            int elevatorColumn = elevator.second;
            int distance = abs(spot.row - elevatorRow) + abs(spot.column - elevatorColumn);
            dist = min(dist, distance);
        }

        return dist;
    }

public:
    NearToElevatorStrategy(vector<pair<int, int>> locations) : elevatorLocations(locations) {}

    ParkingSpot *findSpot(vector<ParkingSpot> &spots) override
    {
        ParkingSpot *bestSpot = nullptr;
        int minDistance = 1e9;

        for (auto &spot : spots)
        {
            if (spot.isEmpty)
            {
                int distance = calculateDistance(spot);
                if (distance < minDistance)
                {
                    minDistance = distance;
                    bestSpot = &spot;
                }
            }
        }

        return bestSpot;
    };
};

class ParkingSpotManager
{
    vector<ParkingSpot> spots;

public:
    VehicleType handledType;
    ParkingStrategy *strategy;

    ParkingSpotManager(int rows, int columns, int levels, int pricePerSpot, VehicleType type, ParkingStrategy *strat = new NearToEntranceStrategy())
        : handledType(type), strategy(strat)
    {
        for (int l = 0; l < levels; ++l)
            for (int r = 0; r < rows; ++r)
                for (int c = 0; c < columns; ++c)
                    spots.emplace_back(r, c, l, pricePerSpot);
    }

    ParkingSpotManager(vector<ParkingSpot> &existingSpots, VehicleType type, ParkingStrategy *strat = new NearToEntranceStrategy())
        : spots(existingSpots), handledType(type), strategy(strat) {}
    
    ParkingSpot *findSpot()
    {
        return strategy->findSpot(spots);
    }

    void vaccateSpot(ParkingSpot *spot)
    {
        if (spot)
        {
            spot->vaccate();
        }
    }

    ParkingSpot *parkVehicle(Vehicle *vehicle)
    {
        if (vehicle->getVehicleType() != handledType)
        {
            return nullptr; // This manager does not handle this vehicle type
        }

        ParkingSpot *spot = findSpot();
        if (spot && spot->park(vehicle))
        {
            return spot;
        }

        return nullptr; // No available spot found
    }
};

// Factory Pattern
class ParkingLotManagerFactory
{
    unordered_map<VehicleType, ParkingSpotManager *> managers;
public:
    ParkingSpotManager *getManager(VehicleType type)
    {
        return managers[type];
    }

    void registerManager(ParkingSpotManager *manager)
    {
        managers[manager->handledType] = manager;
    }
};

#endif