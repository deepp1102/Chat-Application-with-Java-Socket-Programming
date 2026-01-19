# Real-Time Chat Application using Java Sockets

A simple multi-client real-time chat application built using **Java Socket Programming** and **Multithreading**.  
This project demonstrates core networking concepts such as TCP/IP communication, thread handling, synchronization, and client-server architecture.

---

## 🚀 Features

- Multi-client chat using TCP sockets  
- One thread per connected client  
- Real-time message broadcasting  
- Graceful client disconnect handling  
- Thread-safe client management  
- Console-based interface (easy to test)

---

## 🧠 Core Concepts Covered

- TCP Handshake and Socket Communication  
- ServerSocket and Socket usage  
- Multithreading with `Runnable` and `Thread`  
- Synchronization using thread-safe collections  
- Blocking I/O and concurrent message handling  

---

## ⚙️ How to Compile and Run (macOS / Linux)

### 1. Compile All Files

javac Server.java ClientHandler.java Client.java
THIS WILL GENERATE:
Server.class
ClientHandler.class
Client.class

2. Run the Server (Terminal 1):
java Server
-TERMINAL WILL SHOW SOMETHING LIKE THIS:
Chat Server started on port 6000

3. Run Clients (Terminal 2, Terminal 3, …)
In each new terminal:
java Client
You will see:
Enter your username:
---

## 📂 Project Structure
