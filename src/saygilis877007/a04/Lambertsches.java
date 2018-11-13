package saygilis877007.a04;
import cgtools.Random;
import cgtools.Vec3;

/**
 * Created by home on 07.11.18.
 */
public class Lambertsches implements Material {

    private Vec3 alb;                   //Oberfläche wird diffus reflektiert
                                        //alb = albedo die Konstante Abschwächung
    Lambertsches(Vec3 alb) {            //Albedo bestimmt die Farbe der Oberfläche unter weißem Licht
        this.alb = alb;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return Vec3.vec3(0);
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 drnd = Vec3.vec3(Random.random(), Random.random(), Random.random());

        while (drnd.length() > 1 || drnd.length() < -1) drnd = Vec3.vec3(Random.random(), Random.random(), Random.random());

        return new Ray(h.position, Vec3.normalize(Vec3.add(Vec3.normalize(h.normal), drnd)));
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return alb;
    }
}
//-----Definition-----
//Emission
//* keine

//Albedo
//* BRDF konstant über die gesamte Oberfläche
//* Farbe der Oberfläche

//Reflektierter Strahl
//* Ursprung: ist der Trefferpunkt
//* Richtung: ist Zufällig mit der Kosinusverteilung


//Lambertsches Kosinusgesetz
//* Intensität vom einfallenden Licht ist abhängig vom Einfallswinkel
//Einfallswinkel und einfallender Lichtenergie
//Es beschreibt die perfekten diffuser Oberflächen

//Spiegelung ist genau eine Richtung
//* perfekt glatte spiegelnde oberfläche (Einfallswinkel = Ausfallwinkel

//-----------------------------------
//-----Diffuse Reflexion-------------
//Streuung unendlich viele Richtungen
//* Diffuse Oberfläche (raue oberflächen nicht glatte)
//* Ausfallswinkel zufällig für jeden Trefferpunkt (Kosinusverteilung)