package at.vaaniicx.lap.util;

import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Component
public class ImageConversionHelper {

    public static byte[] blobToByteArray(Blob blob) {
        try {
            return blob.getBytes(1, ((int) blob.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    public static Blob byteArrayToBlob(byte[] arr) {
        try {
            return new SerialBlob(arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
