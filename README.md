# Multi-Threaded Order Processing Engine

A Java command-line application that simulates concurrent order intake, queue management, and order fulfillment using core concurrency utilities.

## Features
- **Concurrency & Multithreading:** Utilizes `ExecutorService` and `BlockingQueue` implementing a robust Producer-Consumer architecture.
- **Thread Safety:** Synchronized file writing ensures non-corrupted logging across concurrent worker threads.
- **File I/O:** Automatically logs processed transactions to `logs/order_log.txt`.

## Prerequisites
- Java Development Kit (JDK 8 or higher)
- Terminal / Command Line access

## How to Build and Run (Command Line)

1. Open project directory:
   ```bash
   cd java-order-processing-system
