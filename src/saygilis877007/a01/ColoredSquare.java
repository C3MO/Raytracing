package saygilis877007.a01;




import cgtools.Vec3;
import saygilis877007.Image;

import java.io.IOException;

/**
 * Created by home on 10.10.18.
 */
public class ColoredSquare {
    final int width = 160;
    final int height = 90;
    Vec3 color;

    Image image = new Image(width, height);

        ColoredSquare(Vec3 color) {
            this.color = color;
        }
    public void setPixel(int x, int y, Vec3 color) {
                for (int i = 1; i < height; i++){
                for (int j = 1; j < width; j++){
                    if ( i+j % 2 == 0 ) {
                        color = Vec3.white;
                    } else {
                        color = Vec3.black;
                    }
                }
            }

            }
    }

