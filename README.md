PROJEKTDOKUMENTATION

# **Projekt:LAVAKAVA**

![LavaKava](https://umutyesildal.github.io/benimsayfam/image.png)

Lehrveranstaltung: INF202 - Software Engineering

Semester: SS2019/20

Lehrveranstaltungsleiter/in: Dr. Burcu Yildiz

Projektmanager/in: Umut Yunus Yeşildal

**Version: 1.1.3**





 # **1 Lastenheft**

**1.1 Zielbestimmung:**

Dieses Projekt arbeitet mit kooperierenden Systemen innerhalb des Projekts. Es gibt eine Datenbank, die mit dem Softwareprogramm verbunden ist. Mit dieser Datenbanksoftware können bestimmte Informationen wie Berichte und Bediener erstellt, gelöscht oder bearbeitet werden. Dieses Projekt zielt darauf ab, menschliches Versagen bei der Erstellung von Berichten zu vermeiden .

**1.2 Produkteinsatz:**

Das Produkt wird von häufig geänderten Betreibern verwendet, die über die Kenntnis von Standards verfügen. Es wird in Fabriken eingesetzt.

**1.3 Produktfunktionen:**

/LF10/ Ein Benutzer kann sich am System anmelden unter Angabe eines Benutzernamens und seines Passwortes.Alle Benutzern werden eine einzigartige Benutzername und Passwort haben. 

/LF20/Auf der ersten Seite kann mit der Anmeldeschaltfläche ein neuer Benutzer erstellt werden.

/LF30/Nachdem sich der Benutzer angemeldet hat, kann der Benutzer die Seiten "Berichte, Firmen und Personen, Operatoren" aufrufen.

/LF40/Auf der Berichtsseite kann der Benutzer alle Berichtsnamen, Berichtsnummern und Kundeninformationen anzeigen. Oder der Benutzer kann einen neuen Bericht erstellen.

/LF50/Auf der Seite zur Berichterstellung kann der Benutzer Hilfe mit der Schaltfläche "Hilfe erhalten" erhalten. Nachdem der Benutzer die erforderlichen Lücken ausgefüllt hat, muss er die Schaltfläche "Fülle die Lücken" verwenden. Der Benutzer kann den Bericht als PDF oder Excel erstellen.

/LF60/ Nachdem der Benutzer alle Teile vor dem Speichern ausgefüllt hat, wird der Benutzer gefragt, ob er sicher ist. Nach Abschluss kann der Benutzer nichts mehr ändern. 

/LF70/Auf der Seite "Firmen und Ausrüstüng" kann der Benutzer der Datenbank neue Firmen hinzufügen oder neue Ausrüstung. Der Benutzer kann auch YuzeyDurumu und MuayeneAsamasi hinzufügen.

/LF80/Auf der Seite "Operatoren" kann der Benutzer operator hinzufügen,operator löschen,operator bearbeiten,operator auflisten.

/LF90/ Kalenderdaten werden von der Software hinzugefügt, um falsche Informationen zu vermeiden. 

/LF100/ Die Suche kann mit der Berichtsnummer erfolgen.

**1.4 Qualitätsanforderungen**
| Produktsqulität | Sehr gut | Gut | Normal |Irrelevant|
| -------- | ------- | ---------- | -------- | -------- |
| Funktionalität | X | -- | -- | -- |
| Zuverlässigkeit | -- | X | -- | -- |
| Benutzbarkeit | -- | X | -- | -- |
| Effizienz | -- | -- | X | -- |
| Änderbarkeit | -- | -- | -- | X |
| Übertragbarkeit | X | -- | -- | -- |
| Sicherheit | -- | -- | X | -- |

**1.5 Produktdaten:**
/LD10/Benutzern 	-Username: String,PK,Unique 
			-Password: String
			
 /LD20/Operatoren	-ID: Integer PK,Unique
			-Name,Nachname,Unterschrift: String
			-Level: Integer
			
/LD30/Ausrüstung 	-ekipmanName: String,PK,Unique
			-KutupMesafesi:Integer
			-MPTasiyiciOrtam,MiknatislamaTeknigi,IVIsikSiddeti,IsikMesafesi: String
			
/LD40/Firmen 		-ID: Integer,PK,Unique
			-FirmName,Adress,IsEmri,TeklifNo:String
			
/LD50/Reporten	-berichtNummer: Integer,PK,Unique
			-berichtName,kundeName: String 
			
/LD60/yuzeyDurumu	-yuzeyDurumu: String,PK,Unique

/LD70/muayeneAsamasi	-muayeneAsamasi:String,PK,Unique

**1.6 Produktleistungen**
 /LL10/Beim Hinzufügen eines Operators muss die Operator-ID leer sein.
 
/LL20/Beim Löschen durch den Bediener wird ein neues Dialogfenster geöffnet. Die ID muss angegeben werden. 

/LL30/ Beim Bearbeiten eines Operators müssen alle Lücken ausgefüllt werden.  

/LL40/Alle Lücken müssen beim Hinzufügen von Firmen und Ausrüstung gefüllt werden 

/LL50/Alle Lücken ohne ZD müssen gefüllt sein at Bericht Seite.

/LL60/Alle ChoiceBox müssen ausgewählt sein und danach muss auf die Schaltfläche "Fülle die Lücke" geklickt werden.  

**1.7 Ergänzungen**

**1.8 Glossar**


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
FirmenAusrustung | Firmen und Ausrustung Addieren | FirmaEkle(),Ausrustung()Ekle,yuzeyDurumuEkle(),muayeneAsamasiEkle() | Name,Kutupmesafesi,mptasiyiciortam,miknatislamaTeknigi....
Operator.java | Controller für Operatoren | PersonSil(),personDuzenle(),initialize(),handeDisplayTables() | Name,Nachname Operator Stufe,Unterschrift 
Bericht.java | Controller für Berichten | initialize(),saveDatabase(),ChoiceBoxPopulate(),datumFinder(),savePDF(),saveExcel() | projeAdi,Musteri,raporNo,Cihaz...
Database.java | Database Verbindung von ganze Programm | connect(),getConnection() ,AddFirm(),AddYuzeyDurumu(),addOperator,AddBericht()... | Url,User,Pass 

### **3.2 Anforderungsanalyse und Konzeption**

**3.2.1 Funktionale und nichtfunktionale Anforderungen**

- Im Homepage Benutzer muss sich am System anmelden unter Angabe eines Benutzernamens und seines Passwortes.Alle Operatoren werden eine einzigartige Benutzername und Passwort haben. 

- Operatoren können leere Teile füllen, ändern und löschen.

- Bedienerebenen können nur von Administratoren erhöht werden. 

- Kalenderdaten werden von der Software hinzugefügt, um falsche Informationen zu vermeiden.

-Falsch kalibriertes Gerät kann nicht ausgewählt werden.

-Die Unterschrift des Personals, dessen Zertifikatdatum abgelaufen ist, wird nicht akzeptiert.

-Wenn die Daten nicht übereinstimmen, ist sie ungültig.

-Wenn nicht alle erforderlichen Lücken gefüllt sind, ist PDF nicht besonders gut.

-Result muss mit OK oder RED angegeben werden.

-Die Suche kann mit der OperatorID.

-In Hinzufügen des operators id muss leer sein.


**3.2.2 Anwendungsfälle** (als UML Anwendungsfalldiagramme)
![Anmeldung](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/anmeldung.jpeg)
![LoggedInPage](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/loggedinPage.png)
![Operatoren](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Operatoren.jpeg)
![Firmen](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Firmen.jpeg)
![Ausrustung](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Ausrustung.jpeg)
![MuayeneAsamasi](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/muayeneAsamasi.jpeg)
![yuzeyDurumu](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/YuzeyDurumu.jpeg)
![bericht](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/GenelBericht.jpeg)
![bericht1](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Berichte1.jpeg)
![bericht2](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/bericht2.jpeg)
![Anmeldung](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/anmeldung.jpeg)
![bericht3](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Bericht3.jpeg)
**3.2.3 Domänenmodell** (als UML Klassendiagramm)
![tamuml](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/TamUML.png)
**3.3 Entwicklung und Implementierung**
**3.3.1 Systemarchitektur**
**3.3.2 Methoden und Werkzeuge**

Als Programmiersprache wurde Java verwendet, und die Codes wurden unter Berücksichtigung des OOP-Layouts geschrieben. Das JavaFX-Framework wurde verwendet. GUI-Designs wurden durch Koordination des Scene Builder und von JavaFX erstellt.Für Datenbank SQLite ist benutzt. Es wurden Bibliotheken wie Apache-POI, PDFBOX, JDBC verwendet. Sie wurden verwendet, um PDF- und Excel-Dateien zu erstellen und eine Verbindung zur Datenbank herzustellen. Github wurde zur Versionskontrolle verwendet.

**3.3.3 Design-Layouts**
![homepage](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/HomePage.JPG)
![signuppage](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/SignUpPage.JPG)
![loggedinpage](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/LoggedInPage.JPG)
![operatoren](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Operatoren.JPG)
![firmenundausrustung](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/FirmenUndAusrustung.JPG)
![bericht](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Bericht.JPG)
![bericht2.1](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/bericht1kisim.JPG)
![bericht2.2](https://github.com/umutyesildal/inf202repo/blob/master/umlundDesign/Bericht2kisim.JPG)

### **4 Stundenliste**
Datum | Dauer | Beschreibung der Aktivität
----- | ----- | -------------------------
18.04.2020 | 2 Stunde | Forschung über Pflichtenheft
19.04.2020 | 4 Stunde | Pflichtenheft çalışmaları tamamlandı.
22.04.2020 | 2 Stunde | JavaFX ve Scene Builder'ın nasıl çalıştığı ve birbirleriyle bağlantısı öğrenildi ve bağlanıldı.
23.04.2020 | 4 Stunde | Scene Builder bericht 1 çalışmaları yapıldı.GridLine ile benzeri çizildi gerekli yerlere ChoiceBox,gerekli yerlere TextField koyuldu.
24.04.2020 | 2 Stunde | SceneBuilder ile HomePage girişi yapıldı.Log In ve Sign Up için butonlar yapıldı.
25.04.2020 | 3 Stunde | Scene Builder ile Log In Page yapıldı.
26.04.2020 | 4 Stunde | Scene Switching ve Buton çalışması hakkında araştırmalar yapıldı ve geliştirmeler eklendi.
27.04.2020 | 5 Stunde | SQLite Database araştırması yapıldı ve Database methodları yazıldı.Java ile SQLite birleştirildi.
04.05.2020 | 3 Stunde | Scene Builder'da database ve log in page ile ilgili geliştirmeler yapıldı.
07.05.2020 | 5 Stunde | SQLite Database'i hakkında iyileştirmeler yapıldı ve database bağlantılarıyla ilgili sorunlar çözüldü.
08.05.2020 | 2 Stunde | ein paar Änderungen mit SceneBuilder
09.05.2020 | 2 Stunde | Arbeiten über Dokumentation
11.05.2020 | 2 Stunde | JavaFX ile ilgili geliştirmeler.
13.05.2020 | 2 Stunde | Github üzerinde çalışmalar.Readme kısmının düzenlenmesi.
13.05.2020 | 4 Stunde | Operatorler için ayrı bir Scene oluşturulup içine tableview ve TextFieldların konulması.Database'i tableviewe aktarılması ile ilgili çalışmalar ve araştırmalar.
14.05.2020 | 4 Stunde | Operator Scene'inde methodların çalıştırılması(ekleme,silme,düzenleme)
16.05.2020 | 3 Stunde | Operator için ayrı bir java class'ı oluşturulup bunların setter getter'larının tanımlanması.Tableview ile bunların birleştirilmesi.
17.05.2020 | 4 Stunde | Operator Scene'inde database'den gelen bilgilerin gösterilmesi.
19.05.2020 | 3 Stunde | Rapor için farklı bir scene oluşturulması.3 sekme ile ayrılıp,textfield ve choicebox'ların tanımlanması.Girilmesi, düzenlenmesi. 
20.05.2020 | 4 Stunde | Log In ve Sign Up için database oluşturulması ve giriş kısmının şifresi ve username'i doğru değilse giriş yapılmasının engellenmesi.
21.05.2020 | 2 Stunde | Raporlar için ve Sign Up kısmı için ayrı bir fxml dosyasının oluşturulması gerekli değişikliklerin yapılması.
23.05.2020 | 4 Stunde | Scene'den PDF ve Excel'e aktarım için araştırmaların yapılması ve planın ona göre ayarlanması.Buna göre en son yapılan Rapor sayfasının geçerli olmayacağını belirleyip,ilk rapor sayfasına geçiş yapılması.
24.05.2020 | 2 Stunde | Gridline ile çizilmiş Rapor FXML dosyasına Scene Builder üzerinden gerekli yazılar yazıldı.
26.05.2020 | 3 Stunde | Projekt Dokumentation hakkında araştırma yapıldı ve UML ile ilgili değişiklikler yapıldı.
27.05.2020 | 2 Stunde | Database ve Kodlar hakkında geliştirmeler.
29.05.2020 | 3 Stunde | Bütün variable'lerin nasıl olması gerektiği ve Textfield,Choicebox içeriklerinin nasıl olması gerektiği düşünülüp,plan yapıldı.Variable'ların FXML'e ve Controller'a  girişi yapıldı ve tanıtıldı.
01.06.2020 | 3 Stunde | muayeneAsamasi ve yuzeyDurumu'nun seçeneklerinin farklı bir yerden eklenilebilmesi sağlanıldı.Gerekli yerlere günün tarihi otomatik gelmesi sağlandı.Firmen FXML dosyasında butonlar eklenildi ve fonksiyonel hale getirildi.Gerekli ChoiceBox'lara gerekli girişler yapıldı.
02.06.2020 | 3 Stunde | Database ile ilgili testler yapıldı ve çalışmayan methodlar değiştirildi.
02.06.2020 | 3 Stunde | Rapor ile ilgili geliştirmeler yapıldı.Muayene Kapsami ChoiceBox ile değiştirildi.Rapor Database'i oluşturuldu ve Rapor scene oluşturuldu ve database aktarımı gerçekleştirildi.
03.06.2020 | 4 Stunde | Database'lerin hepsi birleştirildi ve table'lara ayrıldı.berichtNummer için bir fonksiyon yazıldı ve işlendi.Database'e kayıtlar için hepsine bir notification koyuldu ve bir kaç bug çözüldü.
07.06.2020| 3 Stunde | Firmen ve Operatoren kısmına Dialog box eklendi clear yapıldı ve bir kaç fonksiyon daha eklendi.
15.06.2020| 2 Stunde | Dokumentation üzerinde değişiklikler ve choiceboxların populate edilmesi.
17.06.2020| 3 Stunde | ChoiceBox'lardan alınan bilgilerin textfieldlara yazılmasının fonksiyonlarının girilmesi.Buttonla bunları yapılması.
18.06.2020| 2 Stunde | Genel ölçüde neyin nasıl yapılması gerektiğiyle ilgili dialoglar eklendi.
18.06.2020| 2 Stunde | Dokumentation için Design Layoutların resimleri alındı.Dokumentation düzenlendi.2. Kısımla ilgili değişiklikler yapıldı.LF LD LL kısımları düzenlendi.
19.06.2020| 2 Stunde | Sonuc kısmının OK RED sonuçlarına göre düzenlenmesi eğer yanlışsa error çıkması yapıldı.
19.06.2020| 2 Stunde | Zorunlu Kısımların boş kalmaması için fonksiyon yazıldı.
20.06.2020| 3 Stunde | PDF'e kopyalamaya yapıldı pdfbox ile.
TOPLAM | 112 Stunde | -
