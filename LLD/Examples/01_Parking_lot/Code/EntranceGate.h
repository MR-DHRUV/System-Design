#ifndef ENTRANCE_GATE_H
#define ENTRANCE_GATE_H

#include <string>
#include <chrono>
#include "ParkingSpot.h"

using namespace std;
using namespace std::chrono;

class Ticket
{
public:
    string ticketId;
    Vehicle vehicle;
    ParkingSpot spot;
    time_point<system_clock> entryTime;

    Ticket(const Vehicle &v, const ParkingSpot &s)
        : vehicle(v), spot(s), entryTime(system_clock::now())
    {
        ticketId = to_string(duration_cast<seconds>(entryTime.time_since_epoch()).count());
    }
};

class EntranceGate
{
    ParkingLotManagerFactory manager;

public:
    void registerManager(ParkingSpotManager *manager)
    {
        this->manager.registerManager(manager);
    }

    ParkingSpot *allocateSpot(const Vehicle &vehicle)
    {
        ParkingSpotManager *spotManager = manager.getManager(vehicle.getVehicleType());
        if (spotManager)
            return spotManager->parkVehicle(new Vehicle(vehicle));
        
        return nullptr;
    }

    Ticket* generateTicket(const Vehicle &vehicle, ParkingSpot *spot)
    {
        return new Ticket(vehicle, *spot);
    }

    Ticket* processEntry(const Vehicle &vehicle)
    {
        ParkingSpot *spot = allocateSpot(vehicle);
        if (spot)
            return generateTicket(vehicle, spot);

        return nullptr;
    }
};

#endif