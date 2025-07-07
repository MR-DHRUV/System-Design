#ifndef EXIT_GATE_H
#define EXIT_GATE_H

#include <string>
#include <iostream>
#include <chrono>
#include "ParkingSpot.h"
#include "EntranceGate.h"

using namespace std;
using namespace std::chrono;

// Strategy Pattern
class PricingStrategy
{
public:
    virtual double calculatePrice(Ticket &ticket) = 0;
};

class MinutelyPricingStrategy : public PricingStrategy
{
public:
    double calculatePrice(Ticket &ticket) override
    {
        auto exitTime = system_clock::now();
        auto duration = duration_cast<minutes>(exitTime - ticket.entryTime).count();
        return duration * ticket.spot.getPrice();
    }
};

class HourlyPricingStrategy : public PricingStrategy
{
public:
    double calculatePrice(Ticket &ticket) override
    {
        auto exitTime = system_clock::now();
        auto duration = duration_cast<hours>(exitTime - ticket.entryTime).count();
        return duration * ticket.spot.getPrice();
    }
};

class CostCalculator
{
    PricingStrategy *pricingStrategy;

public:
    CostCalculator(PricingStrategy *strategy) : pricingStrategy(strategy = new HourlyPricingStrategy) {}

    double calculateCost(Ticket &ticket)
    {
        return pricingStrategy->calculatePrice(ticket);
    }
};

enum PaymentType
{
    CASH,
    CARD,
    UPI,
};

// Chain of Responsibility Pattern
class PaymentProcessor
{
public:
    PaymentProcessor *next;
    PaymentType type;

    bool processPayment(double amount, PaymentType paymentType)
    {
        if (canHandle(paymentType))
            return initiatePayment(amount);
        else if (next)
            return next->processPayment(amount, paymentType);

        return false;
    }

    virtual bool initiatePayment(double amount) = 0;

    bool canHandle(PaymentType paymentType)
    {
        return type == paymentType;
    }

    static PaymentProcessor *createPaymentProcessor(vector<PaymentProcessor *> processors)
    {
        for (int i = 0; i < processors.size() - 1; i++)
            processors[i]->next = processors[i + 1];

        return processors[0];
    }
};

class CashPaymentProcessor : public PaymentProcessor
{
public:
    CashPaymentProcessor()
    {
        type = CASH;
        next = nullptr;
    }

    bool initiatePayment(double amount) override
    {
        cout << "Processing cash payment of: " << amount << endl;
        return true;
    }
};

class CardPaymentProcessor : public PaymentProcessor
{
public:
    CardPaymentProcessor()
    {
        type = CARD;
        next = nullptr;
    }

    bool initiatePayment(double amount) override
    {
        cout << "Processing card payment of: " << amount << endl;
        return true;
    }
};

class UpiPaymentProcessor : public PaymentProcessor
{
public:
    UpiPaymentProcessor()
    {
        type = UPI;
        next = nullptr;
    }

    bool initiatePayment(double amount) override
    {
        cout << "Processing UPI payment of: " << amount << endl;
        return true;
    }
};

class ExitGate
{
    CostCalculator *costCalculator;
    PaymentProcessor *paymentProcessor;

public:
    ExitGate(CostCalculator *calculator, PaymentProcessor *processor)
        : costCalculator(calculator), paymentProcessor(processor) {}

    bool processExit(Ticket &ticket, PaymentType paymentType)
    {
        double cost = costCalculator->calculateCost(ticket);
        if (paymentProcessor->processPayment(cost, paymentType))
        {
            ticket.spot.vaccate();
            return true;
        }

        return false;
    }
};

#endif