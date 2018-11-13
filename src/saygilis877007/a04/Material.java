package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 07.11.18.
 */
public interface Material {



    Vec3 emittedRadiance (Ray r, Hit h);
    Ray scatteredRay(Ray r, Hit h);
    Vec3 albedo (Ray r, Hit h);

}
//---------definitionen------------
//Oberflächeneigenschaften
//* Emission, Albedo, Reflektierter Strahl

//Dynamische Parameter
//* Eintreffender Strahl
//* Trefferpunkt

/*
Beim Path Tracing wird jeder Strahl, der in die Szene geschossen wird,
beim Auftreffen auf Oberflächen reflektiert, gebrochen oder absorbiert,
wobei jedes Mal (außer im Falle der Absorption) mindestens ein zufälliger Strahl generiert wird,
der das Integral der Rendergleichung nähert. Der Anfangsstrahl sucht sich so seinen Weg (path) durch die Szene.
Je mehr Anfangsstrahlen man verwendet, desto mehr nähert man sich dem idealen Bild an. Die Fehler der Näherung äußern sich als Varianz, was Bildrauschen entspricht.
Der Unterschied zum diffusen Raytracing liegt darin, dass beim Path Tracing die vollständige Rendergleichung mittels zufällig
generierten Strahlen auf allen – auch auf diffusen – Oberflächen gelöst und somit die globale Beleuchtung (Global Illumination) simuliert wird.
 */