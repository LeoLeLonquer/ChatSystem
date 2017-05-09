CHATSYSTEM - OULIANA BADAOUI & LEO LE LONQUER, TD I-A2

-> Version Requise: JavaSE-1.8

-> COMPILER & EXECUTER: 

-Pour compiler le projet:


Pour executer ce projet: 
o Avec le terminal, positionnez-vous sur le dossier contenant le projet. 

o Avec Eclipse, importer le projet ChatSystem, positionnez vous sur la classe LaunchChatSystem situ�e dans le package Controller. Il suffit ensuite de cliquer sur le bouton "Run" de Eclipse.
Le Chat System contient deux utilisateur au lancement de l'application: 
- l'utilisateur courrant
- un perroquet pour tester la r�ception de messages distants  

-> LISTE DES FONCTIONNALITEES IMPLEMENTEES: 

o Envoi et r�ception de messages � distance 
o Pr�sence de boutons et prise en compte de la touche "entr�e"
o Envoi de fichiers 
o Lancement d'une nouvelle fen�tre en cliquant sur un utilisateur
o Pr�sence d'un perroquet (test) 
o Pr�sence d'une fen�tre de dialogue avant d�connexion


-> LISTE DES FONCTIONNALITEES NON IMPLEMENTEES:  
o Fichier log pour l'historique des conversations 
o Communications avec des groupes d'utilisateurs
o Styles de textes diff�rents dans la zone texte de conversation 


-> LISTE DE TESTS JUNIT

Tests User 
	testGetPseudo : valid�
	testGetUserID :	 valid�
	testGetIP : 	valid�
	testGetStatus :	 valid�
	testGetConv :	 valid�
	testSetIP :	 valid�
	testSetStatus :	 valid�

Test AllDests	
	testAddUser : �chec (Tient aux probl�mes d�impl�mentation du test)
	testAddGroup : valid�
	testRemoveUser : valid�
	testRemoveGroup : �chec (origine inconnue)
	testSearchUser : valid� 
	testSearchGroup : �chec (Tient aux probl�mes d�impl�mentation du test)

Test ConvTest 
	testReadLastMessage : valid�
	testReadMessage : valid�
	testreadAllConv : valid�

TestNetwork 
	testSendTxtMessage : valid�
	testSendFile : erreur timeout (Le syst�me d�envoi de fichier est irr�gulier, il fonctionne pour certains fichiers et pas pour d�autres. Ici le fichier est partiel, donc corrompue. De plus la v�rification dans JUnit de l�existence des fichiers pose probl�me car il ne semble pas �tre reconnus lors des d�clarations de classe File )
	testSendImage : erreur timeout (Ce test fonctionne en v�rit�, l�image est bien transf�r�e sans �tre corrompue dans le dossier courant, mais la classe File ne reconnait pas le fichier)
	testSendSameImage : �chec (Probl�me de reconnaissance de fichier)
	testSendALotOfTxtMessages :�chec (Erreur au niveau de l�impl�mentation du test, mais fonctionne en v�rit�)

D�autres tests de d�connexion ou de connexion �taient pr�vues mais par les difficult�s d�impl�mentation des tests, ils ont �t� abandonn�s. De plus la d�connexion de l�utilisateur principal ou �loign� conduisant � la fermeture de socket TCP ou UDP provoque de nombreuses exceptions sur les InputStream. De nombreux tests on �t� entrepris pour r�soudre ce probl�me, sans r�sultats.

-> Connexion avec d'autres utilisateurs
	La discussion avec d'autres utilisateurs fonctionnent mais l'envoi de fichiers pose encore probl�me, malgr� de nombreuses tentatives. Le crit�re de fin de r�ception de fichier est ce qui semble poser probl�me.

