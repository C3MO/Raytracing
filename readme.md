# Computergrafik I

## Musterprojekt für die Abgabe der Übungsaufgaben

Dieses Projekt ist Teil der Unterrichtsmaterialien für das Modul *Computergrafik* im *Bachelor Medieninformatik*. Es gibt die Projektstruktur für die Abgabe der Übungsaufgaben verbindlich vor.

## Alle Lösungen

Hier entsteht eine Liste aller abgegebenen Lösungen.

-   [Aufgabe 1: Kreisscheibe auf Hintergrund](doc/a01.md)

## Struktur

Das Repository enthält über den Verlauf des Semesters alle Lösungsabgaben für eine Person. Jede Lösung einer Aufgabe besteht aus mindestens zwei Komponenten:

1.  Eine **Dokumentation** der Lösung

    Die Erläuterungen befindet sich in einer Datei im Verzeichnis `doc`, und werden im [Markdown Format](https://daringfireball.net/projects/markdown/) verfasst. Jede Doku enthält als erstes mindestens ein aussagekräftiges Bild, das mit der Lösung erzeugt wurde. Danach werden die wichtigsten Details des Lösungsansatzes, unter Nennung aller externen Quellen, *kurz* erläutert.

    Die Datei wird nach der *Id* der Aufgabenstellung benannt. Für die erste Aufgabe lautet der Dateiname z.B. [`a01.md`](doc/a01.md).

2.  Ein Java-Paket mit der **Implementierung** der Lösung

    Im Verzeichnis `src` befindet sich für jede Lösung ein Java-Paket mit einer ausführbaren `Main` Klasse. Die Ausführung des Programms ohne weitere Argumente erzeugt das in der Dokumentation dargestellte Bild. Der Name des Pakets setzt sich aus dem Namen der Person, der Matrikelnummer und der *Id* der Aufgabe zusammen. Das Paket für die erste Aufgabe lautet für den Dozenten beispielsweise `tramberend7215.a01`.

    Im Paket `tramberend7215` können (und sollten) sich natürlich weitere Pakete befinden, die von mehreren Lösungen gemeinsam verwendet werden.

Die termingerechte Abgabe einer Lösung wird durch das Setzen eines *Tags* auf den entsprechenden *Commit* des Repositories dokumentiert. Der Name des *Tags* leitet sich aus der *Id* der Aufgabe ab, und lautet für die erste Aufgabe z.B. `abgabe-a01`.

**Bitte entfernen Sie diesen Einführungstext vor Abgabe der ersten Aufgabe.**

<!-- vim: set spelllang=de: -->
