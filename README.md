PROJEKTDOKUMENTATION

# **Projekt:LAVAKAVA**

![LavaKava](https://umutyesildal.github.io/benimsayfam/image.png)

Lehrveranstaltung: INF202 - Software Engineering

Semester: SS2019/20

Lehrveranstaltungsleiter/in: Dr. Burcu Yildiz

Projektmanager/in: Umut Yunus Yeşildal

**Version: 1.1.3**

**Inhaltsverzeichnis**

* Abkürzungsverzeichnis .................................3
* Abbildungsverzeichnis .................................3
* Tabellenverzeichnis ...................................3
* **1 Lastenheft ........................................4**
* 1.1 Zielbestimmung ....................................4
* 1.2 Produkteinsatz ....................................4
* 1.3 Produktfunktionen .................................4
* 1.4 Qualitätsanforderungen ............................4
* 1.5 Produktdaten ......................................4
* 1.6 Ergänzungen .......................................4
* 1.7 Glossar ...........................................4
* **2 Projektplanung ....................................5**
* 2.1 Projektzieleplan ..................................5
* 2.2 Projektmeilensteinplan ............................5
* 2.3 Projektkostenplan .................................5
* 2.4 Projektrisiken ....................................5
* **3 Projektumsetzung ..................................7**
* 3.1 Einleitung ........................................7
* 3.1.1 Problemstellung .................................7
* 3.1.2 Stand der Technik ...............................7
* 3.1.3 Zielsetzung .....................................7
* 3.2 Anforderungsanalyse und Konzeption ................7
* 3.2.1 Funktionale und nichtfunktionale Anforderungen ..7
* 3.2.2 Anwendungsfälle .................................7
* 3.2.3 Domänenmodell (als UML Klassendiagramm) .........7
* 3.3 Entwicklung und Implementierung ...................7
* 3.3.1 Systemarchitektur ...............................7
* 3.3.2 Methoden und Werkzeuge ..........................7
* 3.3.3 Design-Layouts ..................................7
* 3.4 Zusammenfassung und Ausblick ......................7
* **4 STUDENTLISTE ......................................8**



 # **1 Lastenheft**

**1.1 Zielbestimmung:**

Dieses Projekt zielt darauf ab, menschliche Fehler zu vermeiden, während der Mitarbeiter die erforderlichen Teile ausfüllt. Ohne einige Standards akzeptiert das System keine Eingaben.

**1.2 Produkteinsatz:**

Das Produkt wird von häufig geänderten Betreibern verwendet, die über die Kenntnis von Standards verfügen. Es wird in Fabriken eingesetzt.

**1.3 Produktfunktionen:**

/LF10/ Ein Benutzer kann sich am System anmelden unter Angabe eines Benutzernamens und seines Passwortes.Alle Operatoren werden eine einzigartige Benutzername und Passwort haben.

/LF20/ Ersterfassung,Änderung and Löschung von leere Teile.

/LF30/ Nachdem der Benutzer alle Teile vor dem Speichern ausgefüllt hat, wird der Benutzer gefragt, ob er sicher ist. Nach Abschluss kann der Benutzer nichts mehr ändern.

/LF40/ Bedienerebenen können nur von Administratoren erhöht werden.

/LF50/ Kalenderdaten werden von der Software hinzugefügt, um falsche Informationen zu vermeiden.

/LF60/ Die Suche kann mit der Berichtsnummer erfolgen.

**1.4 Produktdaten:**

/LD10/ Operatorendaten (Name,Nachname,Level,Benutzername,Passwort,).

/LD20/ Customer,Project Name,Inspection Place,Surface Condition,Stage of Examination müssen String sein.

/LD30/Inspection Standart,Evaluation Standart muss im Standart sein.

**1.5 Produktleistungen**

/LL10/ Ein falsches oder nicht kalibriertes Gerät kann nicht ausgewählt werden.

/LL20/ Die Unterschrift des Personals, dessen Zertifikatdatum abgelaufen ist, wird nicht akzeptiert.

/LL30/ Nur Bediener mit Stufe 3 können den Bericht genehmigen.

/LL40/ Wenn die Prüfungstermine nicht übereinstimmen, wird das Programm nicht akzeptiert.

/LL50/ Inspektionsphase kann nicht leer sein.

**1.6 Ergänzungen**

**1.7 Glossar**


**3 Projektumsetzung**

## **3.1 Einleitung**

 **3.1.1 Problemstellung**

Beschreibung des Problems: Falsche Eingaben aufgrund von Ablenkung.

 **3.1.2 Stand der Technik**

Java IDE 8.2 ve HSQLDB


**3.1.3 Zielsetzung**

- Dieses Programm wird von den Bedienern im Werk verwendet. Zielgruppen
 - Fabriken, die von Aufmerksamkeitsfehlern betroffen sind

| Klasse | Leistungen | Funktionen | Daten |
| -------- | ------- | ---------- | -------- |
MainFunktion.java | Main Klasse für Programm | Starten | --
MainFunktion GUI | Homepage GUI | -- | --
Operator.java | Controller für Operatoren | anmelden(),checkIfMatches(),checkVariables | Benutzername,Password,Name,Nachname Operator Stufe 
Bericht.java | Controller für Berichten | checkBericht() | ReportNummer 
Database.java | Database Verbindung von ganze Programm | connect(),getConnection() | Url,User,Pass 

### **3.2 Anforderungsanalyse und Konzeption**

**3.2.1 Funktionale und nichtfunktionale Anforderungen**

- Im Homepage Benutzer muss sich am System anmelden unter Angabe eines Benutzernamens und seines Passwortes.Alle Operatoren werden eine einzigartige Benutzername und Passwort haben.

- Operatoren können leere Teile füllen, ändern und löschen.

- Nachdem der Benutzer alle Teile vor dem Speichern ausgefüllt hat, wird der Benutzer gefragt, ob er sicher ist. Nach Abschluss kann der Benutzer nichts mehr ändern.

- Bedienerebenen können nur von Administratoren erhöht werden.

- Kalenderdaten werden von der Software hinzugefügt, um falsche Informationen zu vermeiden.

- Die Suche kann mit der Berichtsnummer erfolgen.

- Falsch kalibriertes Gerät kann nicht ausgewählt werden.
- Die Unterschrift des Personals, dessen Zertifikatdatum abgelaufen ist, wird nicht akzeptiert.
- Nur Betreiber der Ebene 3 können den Bericht genehmigen.
- Wenn die Daten nicht übereinstimmen, ist sie ungültig.
- Die Audit-Phase darf nicht leer sein.

**3.2.2 Anwendungsfälle** (als UML Anwendungsfalldiagramme)

**3.2.3 Domänenmodell** (als UML Klassendiagramm)


### **4 Stundenliste**

Datum | Dauer | Beschreibung der Aktivität
----- | ----- | -------------------------
18.04.2020 | 2 Stunde | Forschung über Pflichtenheft
19.04.2020 | 4 Stunde | Pflichtenheft beendet
22.04.2020 | 2 Stunde | Lernen JavaFX und Scene Builder
23.04.2020 | 2 Stunde | Scene Builder Manyetik Parçacık Muayene Raporu
24.04.2020 | 2 Stunde | HomePage Scene Builder
25.04.2020 | 3 Stunde | Alle Scene Builder fertig
26.04.2020 | 4 Stunde | Methoden für Scene Switching and etc
27.04.2020 | 5 Stunde | SQLite Database und alle DBMethoden
04.05.2020 | 3 Stunde | JavaFX Aktualisierung(Gui)
07.05.2020 | 5 Stunde | SQLite, JavaFX und neue DB Aktualisierung
08.05.2020 | 2 Stunde | ein paar Änderungen mit SceneBuilder
09.05.2020 | 2 Stunde | Arbeiten über Dokumentation
11.05.2020 | 2 Stunde | JavaFX Aktualisierung
13.05.2020 | 2 Stunde | Arbeiten über Dokumentation
