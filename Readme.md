# Personal projects
## List of projects
- Skyjo
### Description of projects
1. Skyjo (short version)
    - *Introduction/Rules*
        For this project, the teacher wanted us to learn to implement a client/server model in a game project. The data are precious, they do not have to be lost, so we do have to add a data base in the project. The chosen game was Skyjo but in a shorter version of the real card game*(https://www.regledujeu.fr/regle-du-skyjo/)*. So for this version, nothing really changes, except that one round in the real game matches to one game in this project.
   - *Functionality/Description*
        The server waits for different clients to connect. A client can connect himself to the server. When a client connects himself, he has to wait for another one to start a game. When two clients are connected, the server launches a game between those two players. During the game, the players can do the different actions available in the game (hit a card, drop a card, etc... ). The server stores informations of the game and the clients in a data base.
   - *Content*
        This Skyjo project is constituted of two main components:
        1. Client/server model:
           - Server: contains the model with the different rules of the game and the server where the different players connect themselves to play the game against another one.
           - Client: contains the view of the game for the different players and updates itself according to the flow of the game.
           - Message: contains the different messages communicated between the server and a client to make work the game. When a client makes an action, the client sends a message to the server and it updates the game according to the received message.
        2. Data base:
           - The data base will contain the name and score of the two players and the winner of the different games.
 
        The project works also with the observer(Client)/observable(Server) model. Each client(observer) is an observer of the model included in the server, so the view of the game can be updated according to the state of the game. 
   - *Libraries/Utils employed* 
      - Javafx libraries and SceneBuilder used to create the display for the client.
      - Jar file used to make the data base work, it is included in the project.
      - SQLite to create the tables of the project
