
# Real-Time Stock Price Notification System

## Overview

This project is a robust backend system designed to handle real-time stock price notifications. The system is built using Java Spring Boot and MySQL and integrates with the Alpha Vantage API to fetch intraday stock data, ensuring accurate and timely information for various stock symbols. Users can subscribe to and unsubscribe from stock symbols, receiving personalized notifications.

## Features

- **Real-Time Stock Data:** Integrated with the Alpha Vantage API to fetch intraday stock prices for various stock symbols.
- **Subscription Management:** Users can subscribe to and unsubscribe from specific stock symbols, receiving personalized notifications based on their preferences.
- **Efficient Notification System:** Utilized Redis to manage notification frequencies, ensuring no duplicate notifications are sent within an hour.
- **Event-Driven Architecture:** Built a mechanism to listen for price changes and trigger notifications to subscribed users efficiently.
- **Scalable and Fault-Tolerant:** Implemented using Kafka to ensure reliable and scalable message brokering for notifications.

## Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** MySQL
- **Caching:** Redis
- **Message Brokering:** Kafka
- **API Integration:** Alpha Vantage API

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- MySQL
- Redis
- Kafka
- Alpha Vantage API Key

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/stock-price-notification.git
   cd stock-price-notification
   ```

2. **Configure the application:**
   - Update the `application.properties` file with your MySQL, Redis, Kafka configurations, and Alpha Vantage API key.

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

### Usage

- **Subscribe to a Stock Symbol:**
  - Use the `/subscribe` endpoint to subscribe to a stock symbol.
  
- **Unsubscribe from a Stock Symbol:**
  - Use the `/unsubscribe` endpoint to unsubscribe from a stock symbol.
  
- **Receive Notifications:**
  - Notifications will be triggered and sent based on price changes for subscribed stock symbols.


## Contact

- **Author:** Danish Sarwar
- **Email:** danish.sharwar@gmail.com
