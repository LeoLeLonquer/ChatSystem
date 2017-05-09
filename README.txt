CHATSYSTEM - OULIANA BADAOUI & LEO LE LONQUER, TD I-A2

-> Version Requise: JavaSE-1.8

-> COMPILER & EXECUTER: 

-Pour compiler le projet:


Pour executer ce projet: 
o Avec le terminal, positionnez-vous sur le dossier contenant le projet. 

o Avec Eclipse, importer le projet ChatSystem, positionnez vous sur la classe LaunchChatSystem située dans le package Controller. Il suffit ensuite de cliquer sur le bouton "Run" de Eclipse.
Le Chat System contient deux utilisateur au lancement de l'application: 
- l'utilisateur courrant
- un perroquet pour tester la réception de messages distants  

-> LISTE DES FONCTIONNALITEES IMPLEMENTEES: 

o Envoi et réception de messages à distance 
o Présence de boutons et prise en compte de la touche "entrée"
o Envoi de fichiers 
o Lancement d'une nouvelle fenêtre en cliquant sur un utilisateur
o Présence d'un perroquet (test) 
o Présence d'une fenêtre de dialogue avant déconnexion


-> LISTE DES FONCTIONNALITEES NON IMPLEMENTEES:  
o Fichier log pour l'historique des conversations 
o Communications avec des groupes d'utilisateurs
o Styles de textes différents dans la zone texte de conversation 


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
	testAddUser : échec (Tient aux problèmes dÕimplémentation du test)
	testAddGroup : validé
	testRemoveUser : validé
	testRemoveGroup : échec (origine inconnue)
	testSearchUser : validé 
	testSearchGroup : échec (Tient aux problèmes dÕimplémentation du test)

Test ConvTest 
	testReadLastMessage : validé
	testReadMessage : validé
	testreadAllConv : validé

TestNetwork 
	testSendTxtMessage : validé
	testSendFile : erreur timeout (Le système dÕenvoi de fichier est irrégulier, il fonctionne pour certains fichiers et pas pour dÕautres. Ici le fichier est partiel, donc corrompue. De plus la vérification dans JUnit de lÕexistence des fichiers pose problème car il ne semble pas être reconnus lors des déclarations de classe File )
	testSendImage : erreur timeout (Ce test fonctionne en vérité, lÕimage est bien transférée sans être corrompue dans le dossier courant, mais la classe File ne reconnait pas le fichier)
	testSendSameImage : échec (Problème de reconnaissance de fichier)
	testSendALotOfTxtMessages :échec (Erreur au niveau de lÕimplémentation du test, mais fonctionne en vérité)

DÕautres tests de déconnexion ou de connexion étaient prévues mais par les difficultés dÕimplémentation des tests, ils ont été abandonnés. De plus la déconnexion de lÕutilisateur principal ou éloigné conduisant à la fermeture de socket TCP ou UDP provoque de nombreuses exceptions sur les InputStream. De nombreux tests on été entrepris pour résoudre ce problème, sans résultats.

-> Connexion avec d'autres utilisateurs
	La discussion avec d'autres utilisateurs fonctionnent mais l'envoi de fichiers pose encore problème, malgré de nombreuses tentatives. Le critère de fin de réception de fichier est ce qui semble poser problème.

