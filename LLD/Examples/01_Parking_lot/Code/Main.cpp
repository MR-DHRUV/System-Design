#include <iostream>
#include <string>
#include "ParkingSpot.h"
#include "EntranceGate.h"
#include "ExitGate.h"
using namespace std;

int main()
{
    ParkingSpotManager *twoWheelerManager = new ParkingSpotManager(10, 20, 4, 50, TWO_WHEELER),
                       *fourWheelerManager = new ParkingSpotManager(10, 20, 4, 100, FOUR_WHEELER, new NearToElevatorStrategy({{0, 0}, {0, 1}}));

    EntranceGate *entranceGate = new EntranceGate();
    entranceGate->registerManager(twoWheelerManager);
    entranceGate->registerManager(fourWheelerManager);

    CostCalculator *costCalculator = new CostCalculator(new HourlyPricingStrategy());
    PaymentProcessor *PaymentProcessor = PaymentProcessor::createPaymentProcessor({new CashPaymentProcessor(),
                                                                                   new CardPaymentProcessor(),
                                                                                   new UpiPaymentProcessor()});

    ExitGate *exitGate = new ExitGate(costCalculator, PaymentProcessor);
    unordered_map<string, Ticket *> tickets;

    // Driver
    int input;
    while(1)
    {
        cout<<"Enter 1 to enter the parking lot"<<endl;
        cout<<"Enter 2 to exit the parking lot"<<endl;  
        cout<<"Enter 0 to exit the program"<<endl;
        cin>>input;

        switch (input)
        {
            case 1: {
                string vehicleType, vehicleNumber;
                cout << "Enter vehicle number: ";
                cin >> vehicleNumber;
                cout << "Enter vehicle type (TWO_WHEELER/FOUR_WHEELER): ";
                cin >> vehicleType;

                VehicleType type = (vehicleType == "TWO_WHEELER") ? TWO_WHEELER : FOUR_WHEELER;
                Vehicle vehicle(vehicleNumber, type);

                Ticket *ticket = entranceGate->processEntry(vehicle);
                if (ticket) {
                    tickets[vehicleNumber] = ticket;
                    cout << "Ticket generated with ID: " << ticket->ticketId << endl;
                } else {
                    cout << "No available parking spot for this vehicle." << endl;
                }
                break;
            }
            case 2: {
                string vehicleNumber;
                cout << "Enter vehicle number to exit: ";
                cin >> vehicleNumber;

                if (tickets.find(vehicleNumber) != tickets.end())
                {
                    Ticket *ticket = tickets[vehicleNumber];
                    PaymentType paymentType;
                    string paymentMethod;

                    cout << "Enter payment method (CASH/CARD/UPI): ";
                    cin >> paymentMethod;
                    if (paymentMethod == "CASH") {
                        paymentType = CASH;
                    } else if (paymentMethod == "CARD") {
                        paymentType = CARD;
                    } else if (paymentMethod == "UPI") {
                        paymentType = UPI;
                    } else {
                        cout << "Invalid payment method." << endl;
                        continue;
                    }

                    if (exitGate->processExit(*ticket, paymentType)) {
                        cout << "Exit successful. Vehicle exited." << endl;
                        delete ticket; // Free the ticket memory
                        tickets.erase(vehicleNumber); // Remove from map
                    } else {
                        cout << "Payment failed. Vehicle cannot exit." << endl;
                    }
                }
                else
                {
                    cout << "Invalid ticket ID." << endl;
                }
                break;
            }
            
        }
    }

    return 0;
}