------------------------------------
-- Créer les tables Game et Player
------------------------------------

CREATE TABLE Game (
	id	     	INTEGER        PRIMARY KEY	NOT NULL,
   	player1	     	INTEGER		            	NOT NULL,
	player2	     	INTEGER		            	NOT NULL,
	scorePlayer1 	INTEGER                    	NOT NULL,
	scorePlayer2 	INTEGER                    	NOT NULL,
	winner       	INTEGER                         NOT NULL
	);

CREATE TABLE Player (
	id      INTEGER  PRIMARY KEY    	NOT NULL, 
        name    TEXT             	      	NOT NULL,
	CONSTRAINT fk_player1 FOREIGN KEY (id) REFERENCES Game (player1)
	CONSTRAINT fk_player2 FOREIGN KEY (id) REFERENCES Game (player2)
        );
