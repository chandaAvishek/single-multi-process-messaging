# Documentation
## Player.java
### Responsibilities:
- Message Handling: Sends and receives messages.

- Counter Management: Tracks the number of messages sent and received.

- Stop Condition: Determines when to stop based on sent and received message counts.

### Methods:
- sendMessage(Message message): Sends a message to another player.

- receiveMessage(Message message): Processes received messages.

- shouldStop(): Checks if the stop condition is met.

- getSentCounter() and getReceivedCounter(): Returns the counters for sent and received messages.

## Message.java
### Responsibilities:
- Data Encapsulation: Holds the content of a message.

### Methods:
- getContent(): Returns the message content.

- Message(String content): Constructor to initialize a message with content.

## SingleProcessApp.java
### Responsibilities:
- Initialization: Creates instances of players and simulates communication between them in the same process.

- Message Loop: Manages the loop where messages are sent and received between players.

### Methods:
- main(String[] args): Entry point for the application, sets up and runs the message loop.

## MultiProcessApp.java
### Responsibilities:
- Process Management: Manages separate processes for each player.

- Socket Communication: Handles inter-process communication using sockets.

- Message Exchange: Facilitates sending and receiving messages between processes.

### Methods:
- main(String[] args): Entry point that determines the role (initiator or responder) and starts the process.

- initiateCommunication(Player player): Initiates communication from the initiator process.

- respondToCommunication(Player player): Handles communication from the responder process.

## pom.xml
### Responsibilities:
- Project Configuration: Defines the project structure, dependencies, and build settings.

- Plugin Configuration: Configures Maven plugins for compilation and execution.

## run.sh
### Responsibilities:
- Script Execution: Provides a shell script to run the application in both single and multi-process modes.

- Process Orchestration: Manages the execution of Maven commands to start the processes.

By documenting each class's responsibilities, we ensure clarity and maintainability of the codebase.