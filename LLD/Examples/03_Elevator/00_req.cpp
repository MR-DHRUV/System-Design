#include <bits/stdc++.h>
using namespace std;

// Scenario
// Multiple elevators in a building
// Enternal and internal request handling
// Minimize wait time for users
// optimal selection of elevator

// Objects
// Elevator car
// External button
// Internal button
// Elevator Manager

// Design Patterns
// Observer pattern for notifying when elevator moves
// Singleton pattern for ElevatorManager
// Strategy pattern for different elevator selection
// Strategy pattern for different elevator dispatching strategies

enum Direction
{
    UP,
    DOWN,
    NONE,
};

enum Status
{
    IDLE,
    MOVING,
};

class ElevatorObservers
{
public:
    virtual void notify(int elevatorId, int currentFloor, Status status) = 0;
};

class ElevatorSchedulingStrategy
{
public:
    virtual void nextMove(int &currentFloor, Status &status, Direction &direction, set<int> &requests) = 0;
};

class LookElevatorSchedulingStratagey : public ElevatorSchedulingStrategy
{
public:
    void nextMove(int &currentFloor, Status &status, Direction &direction, set<int> &requests) override
    {
        if (requests.empty())
        {
            status = IDLE;
            direction = NONE;
            return;
        }

        status = MOVING;
        if (direction == NONE)
        {
            // Determine direction based on first request
            if (*requests.begin() > currentFloor)
                direction = UP;
            else
                direction = DOWN;
        }

        // Continue in the same direction
        if (direction == UP)
        {
            auto it = requests.lower_bound(currentFloor);
            if (it != requests.end())
            {
                currentFloor = *it;
                requests.erase(it);
            }
            else
            {
                direction = DOWN;
                return nextMove(currentFloor, status, direction, requests);
            }
        }
        else if (direction == DOWN)
        {
            auto it = requests.upper_bound(currentFloor);
            if (it != requests.begin())
            {
                --it; // Get the last request below current floor
                currentFloor = *it;
                requests.erase(it);
            }
            else
            {
                direction = UP;
                return nextMove(currentFloor, status, direction, requests);
            }
        }
    }
};

class Elevator
{
    int id, currentFloor;
    Status status;
    set<int> requests;
    Direction direction;
    vector<ElevatorObservers *> observers;
    ElevatorSchedulingStrategy *schedulingStrategy;

public:
    Elevator(int id, int currentFloor, ElevatorSchedulingStrategy *strategy)
        : id(id), currentFloor(currentFloor), status(IDLE), direction(NONE), schedulingStrategy(strategy) {}

    void addObserver(ElevatorObservers *observer)
    {
        observers.push_back(observer);
    }

    void removeObserver(ElevatorObservers *observer)
    {
        observers.erase(remove(observers.begin(), observers.end(), observer), observers.end());
    }

    Status getStatus() const
    {
        return status;
    }

    Direction getDirection() const
    {
        return direction;
    }

    int getCurrentFloor() const
    {
        return currentFloor;
    }

    int getId() const
    {
        return id;
    }

    void pressInternalButton(int floor)
    {
        requests.insert(floor);
    }

    void handleExternalRequest(int floor)
    {
        requests.insert(floor);
    }

    void notifyObservers()
    {
        for (auto &observer : observers)
            observer->notify(id, currentFloor, status);
    }

    void nextMove()
    {
        schedulingStrategy->nextMove(currentFloor, status, direction, requests);
        notifyObservers();
    }
};

class ElevatorSelectionStrategy
{
public:
    virtual void selectElevator(int floor, Direction direction, vector<Elevator> &elevators) = 0;
};

class OptimalElevatorSelectionStrategy : public ElevatorSelectionStrategy
{
    Elevator *getNearestIdleElevator(int &floor, vector<Elevator> &elevators)
    {
        Elevator *selectedElevator = nullptr;
        for (auto &elevator : elevators)
        {
            if (elevator.getStatus() == IDLE && (!selectedElevator || abs(elevator.getCurrentFloor() - floor) < abs(selectedElevator->getCurrentFloor() - floor)))
                selectedElevator = &elevator;
        }

        return selectedElevator;
    }

    Elevator *getOptimalMovingElevator(int &floor, Direction &direction, vector<Elevator> &elevators)
    {
        Elevator *selectedElevator = nullptr;

        for (auto &elevator : elevators)
        {
            if (elevator.getStatus() == MOVING && elevator.getDirection() == direction &&
                ((direction == UP && elevator.getCurrentFloor() < floor) || (direction == DOWN && elevator.getCurrentFloor() > floor)))
            {
                selectedElevator = &elevator;
                break;
            }
        }

        return selectedElevator;
    }

    Elevator *getMovingElevatorTowardsFloor(int &floor, Direction &direction, vector<Elevator> &elevators)
    {
        Elevator *selectedElevator = nullptr;

        for (auto &elevator : elevators)
        {
            if ((elevator.getCurrentFloor() > floor && direction == DOWN) || (elevator.getCurrentFloor() < floor && direction == UP))
            {
                selectedElevator = &elevator;
                break;
            }
        }

        return selectedElevator;
    }

public:
    void selectElevator(int floor, Direction direction, vector<Elevator> &elevators) override
    {
        // Preference list
        // 1. Moving elevator such that it is moving towards the requested floor in requested direction
        // 2. Idle elevator nearby
        // 3. Moving elevator such that it is moving towards the requested floor
        // 4. Assign any randomly

        Elevator *selectedElevator = getOptimalMovingElevator(floor, direction, elevators);
        if (!selectedElevator)
            selectedElevator = getNearestIdleElevator(floor, elevators);
        if (!selectedElevator)
            selectedElevator = getMovingElevatorTowardsFloor(floor, direction, elevators);
        if (!selectedElevator)
            selectedElevator = &elevators[rand() % elevators.size()];

        selectedElevator->handleExternalRequest(floor);
    }
};

class ElevatorManager
{
    static ElevatorManager *instance;
    ElevatorSelectionStrategy *selectionStrategy;

public:
    vector<Elevator> elevators;

    static ElevatorManager *getInstance()
    {
        if (!instance)
            instance = new ElevatorManager();
        return instance;
    }

    void setElevatorSelectionStrategy(ElevatorSelectionStrategy *strategy)
    {
        selectionStrategy = strategy;
    }

    void addElevator(Elevator elevator)
    {
        elevators.push_back(elevator);
    }

    void requestElevator(int floor, Direction direction)
    {
        if (selectionStrategy)
            selectionStrategy->selectElevator(floor, direction, elevators);
        else
            cout << "No selection strategy set! Please select a strategy first" << endl;
    }

    void moveElevators()
    {
        for (auto &elevator : elevators)
            elevator.nextMove();
    }
};

ElevatorManager *ElevatorManager::instance = nullptr;

class ExternalButton
{
    int floor;
    ElevatorManager *manager;

public:
    ExternalButton(int floor, ElevatorManager *manager) : floor(floor), manager(manager) {}

    void press(Direction direction)
    {
        manager->requestElevator(floor, direction);
    }
};

void printElevatorStatus(const vector<Elevator> &elevators)
{
    for (const auto &elevator : elevators)
    {
        cout << "Elevator ID: " << elevator.getId()
             << ", Current Floor: " << elevator.getCurrentFloor()
             << ", Status: " << (elevator.getStatus() == IDLE ? "IDLE" : "MOVING")
             << ", Direction: " << (elevator.getDirection() == UP ? "UP" : (elevator.getDirection() == DOWN ? "DOWN" : "NONE")) << endl;
    }
}

int main()
{
    // Initialize random seed
    srand(time(0));

    // Get the singleton instance of ElevatorManager
    ElevatorManager *manager = ElevatorManager::getInstance();

    // Set the elevator selection strategy
    manager->setElevatorSelectionStrategy(new OptimalElevatorSelectionStrategy());

    // Create elevators with Look strategy
    ElevatorSchedulingStrategy *lookStrategy = new LookElevatorSchedulingStratagey();

    // Add 3 elevators at different initial floors
    manager->addElevator(Elevator(1, 0, lookStrategy));
    manager->addElevator(Elevator(2, 5, lookStrategy));

    while (1)
    {
        int choice;
        cout << "1. Request Elevator\n2. Go to floor using internal button\n3. Move elevators\n4.Get Current Status\n5. Exit\nEnter your choice: ";
        cin >> choice;

        switch (choice)
        {
        case 1:
        {
            int floor;
            Direction direction;
            cout << "Enter floor number: ";
            cin >> floor;
            cout << "Enter direction (0 for UP, 1 for DOWN): ";
            int dir;
            cin >> dir;
            direction = (dir == 0) ? UP : DOWN;

            manager->requestElevator(floor, direction);
            break;
        }
        case 2:
        {
            int elevatorId, floor;
            cout << "Enter elevator ID: ";
            cin >> elevatorId;
            cout << "Enter floor number: ";
            cin >> floor;

            // Find the elevator and press the internal button
            for (auto &elevator : manager->elevators)
            {
                if (elevator.getId() == elevatorId)
                {
                    elevator.pressInternalButton(floor);
                    break;
                }
            }
            break;
        }
        case 3:
            manager->moveElevators();
            printElevatorStatus(manager->elevators);
            break;
        case 4:
            printElevatorStatus(manager->elevators);
            break;
        case 5:
            exit(0);
        default:
            cout << "Invalid choice! Please try again." << endl;
        }
    }

    return 0;
}
