CHATSYSTEM - OULIANA BADAOUI & LEO LE LONQUER, TD I-A2

-> Version Requise: JavaSE-1.8

-> COMPILER & EXECUTER: 

-Pour compiler le projet:


Pour executer ce projet: 
o Avec le terminal, positionnez-vous sur le dossier contenant le projet. 

o Avec Eclipse, importer le projet ChatSystem, positionnez vous sur la classe LaunchChatSystem situÈe dans le package Controller. Il suffit ensuite de cliquer sur le bouton "Run" de Eclipse.
Le Chat System contient deux utilisateur au lancement de l'application: 
- l'utilisateur courrant
- un perroquet pour tester la rÈception de messages distants  

-> LISTE DES FONCTIONNALITEES IMPLEMENTEES: 

o Envoi et rÈception de messages ‡ distance 
o PrÈsence de boutons et prise en compte de la touche "entrÈe"
o Envoi de fichiers 
o Lancement d'une nouvelle fenÍtre en cliquant sur un utilisateur
o PrÈsence d'un perroquet (test) 
o PrÈsence d'une fenÍtre de dialogue avant dÈconnexion


-> LISTE DES FONCTIONNALITEES NON IMPLEMENTEES:  
o Fichier log pour l'historique des conversations 
o Communications avec des groupes d'utilisateurs
o Styles de textes diffÈrents dans la zone texte de conversation 


-> LISTE DE TESTS JUNIT

Tests User 
	testGetPseudo : validé
	testGetUserID :	 validé
	testGetIP : 	validé
	testGetStatus :	 validé
	testGetConv :	 validé
	testSetIP :	 validé
	testSetStatus :	 validé

Test AllDests	
	testAddUser : échec (Tient aux problèmes d’implémentation du test)
	testAddGroup : validé
	testRemoveUser : validé
	testRemoveGroup : échec (origine inconnue)
	testSearchUser : validé 
	testSearchGroup : échec (Tient aux problèmes d’implémentation du test)

Test ConvTest 
	testReadLastMessage : validé
	testReadMessage : validé
	testreadAllConv : validé

TestNetwork 
	testSendTxtMessage : validé
	testSendFile : erreur timeout (Le système d’envoi de fichier est irrégulier, il fonctionne pour certains fichiers et pas pour d’autres. Ici le fichier est partiel, donc corrompue. De plus la vérification dans JUnit de l’existence des fichiers pose problème car il ne semble pas être reconnus lors des déclarations de classe File )
	testSendImage : erreur timeout (Ce test fonctionne en vérité, l’image est bien transférée sans être corrompue dans le dossier courant, mais la classe File ne reconnait pas le fichier)
	testSendSameImage : échec (Problème de reconnaissance de fichier)
	testSendALotOfTxtMessages :échec (Erreur au niveau de l’implémentation du test, mais fonctionne en vérité)

D’autres tests de déconnexion ou de connexion étaient prévues mais par les difficultés d’implémentation des tests, ils ont été abandonnés. De plus la déconnexion de l’utilisateur principal ou éloigné conduisant à la fermeture de socket TCP ou UDP provoque de nombreuses exceptions sur les InputStream. De nombreux tests on été entrepris pour résoudre ce problème, sans résultats.

