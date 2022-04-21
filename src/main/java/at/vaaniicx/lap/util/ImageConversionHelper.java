package at.vaaniicx.lap.util;

import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Component
public class ImageConversionHelper {

    private ImageConversionHelper() {
        // Privater Konstruktor
    }

    /**
     * Wandelt ein Blob-Objekt in ein Byte-Array um.
     *
     * @param blob - Bild/Blob, das umgewandelt werden soll
     * @return Blob als Byte-Array
     */
    public static byte[] blobToByteArray(Blob blob) {
        try {
            return blob.getBytes(1, ((int) blob.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    /**
     * Wanelt ein Byte-Array in ein Blob-Objekt um.
     *
     * @param arr - Byte-Array, das umgewandelt werden soll
     * @return Byte-Array als Blob
     */
    public static Blob byteArrayToBlob(byte[] arr) {
        try {
            return new SerialBlob(arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
