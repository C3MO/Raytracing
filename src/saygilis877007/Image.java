package saygilis877007;

import cgtools.ImageWriter;
import cgtools.Vec3;
import java.io.IOException;

public class Image {

    double[] data;
    int width;
    int height;
    int a = 3;


    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        data = new double[a * width * height];
        //System.out.println("Image not yet implemented.");
    }

    public void setPixel(int x, int y, Vec3 color) {
        int i = (width * y + x) * a;
        data[i + 0] = color.x;
        data[i + 1] = color.y;
        data[i + 2] = color.z;
        //System.out.println("Image not yet implemented.");
    }

    public void write(String filename) throws IOException {
        ImageWriter image = new ImageWriter(data, width, height);
        image.write(filename);
        //System.out.println("Image not yet implemented.");
    }
}
