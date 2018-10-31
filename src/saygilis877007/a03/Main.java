package saygilis877007.a03;
import cgtools.Vec3;
import java.io.IOException;
import saygilis877007.Image;

/**
 * Created by home on 30.10.18.
 */
public class Main {

    static int width = 1280;
    static int height = 720;
    static int n = 15;


    public static void main(String[] args){
        Lochkamera kamera = new Lochkamera(Math.PI / 2.0, height, width);
        Kugel kugel = new Kugel(80, new Vec3(0, 0, -180));


        String filename = "doc/03-one-sphere.png";
        try {
            raytrace(kugel, kamera, n).write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }


    static Vec3 gamma(Vec3 color, double gamma) {
        color.x = Math.pow(color.x, 1.0 / gamma);
        color.y = Math.pow(color.y, 1.0 / gamma);
        color.z = Math.pow(color.z, 1.0 / gamma);

        return color;
    }

    public static Image raytrace(Kugel kreis, Lochkamera kamera, int n){
        Image image = new Image(width, height);

        for (int i = 0; i != width; i++) {
            for (int j = 0; j != height; j++) {
                Vec3 color = new Vec3(0,0,0);

                for(int xi = 0; xi < n; xi++){
                    for(int yi = 0; yi < n; yi++){

                        double rx = cgtools.Random.random();
                        double ry = cgtools.Random.random();

                        double xs = (i + (xi + rx) / n);
                        double ys = (j + (yi + ry) / n);

                        Ray ray = kamera.generateRay(xs, ys);
                        Hit hit = kreis.intersect(ray);

                        if(hit!=null){
                            color = Vec3.add(hit.n, color);
                        }else{
                            color = Vec3.add(Vec3.white, color);
                        }
                    }
                }

                color = Vec3.divide(color, (n*n));
                image.setPixel(i, j, gamma(color, 2.2));
            }
        }

        return image;
    }
}
