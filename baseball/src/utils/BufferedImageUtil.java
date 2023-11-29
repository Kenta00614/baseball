package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class BufferedImageUtil {
    public static String convert2DataURI(BufferedImage bi, String type) throws IOException {
        if (bi == null || type == null) {
            return ""; // TODO: Default error image
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, type, baos);
        return "data:image/" + type + ";base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
    }
}
