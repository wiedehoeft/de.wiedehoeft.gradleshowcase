# Start der Anwendung
Dieses Kata erstellt eine Konsolenanwendung.
Mit Hilfe von 
```
jar {
        manifest {
            attributes 'Main-Class': mainClassName
        }
    }
```

wird die Main-Klasse referenziert und das Jar kann mit `java -jar` gestartet werden.

Gradle bietet hierfür auch ein `application`-Plugin. Damit lässt sich die 
Anwendung über `./gradlew run` starten. Allerdings sind die hier generierten
Skripte aktuell nicht über die Command-Line ausführbar.
Zudem verwendet der Task statt der Console den Scanner, welcher wiederum nicht
auf eine Usereingabe wartet. Beim direkten Ausführen der Main funktioniert dies.

Deswegen sollte die Anwendung wie folgt gestartet werden:
1) Über die Main-Klasse.
2) Über java -jar.

# Akzeptanztests mit Fit
Hierfür wurde ein separater Gradle-Task erstellt. Dieser kann mit `./gradlew fit` gestartet werden.
Es werden alle Tests der src/test/fit/alltests.html ausgeführt.