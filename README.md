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
TOPLAM | 107 Stunde | -
