package miage.pds.api.jdatour;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    public static byte[] toByteArray (InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int                   nRead  = 0;
        byte[]                tmp    = new byte[16384];

        while ((nRead = inputStream.read(tmp, 0, tmp.length)) != -1) {
            buffer.write(tmp, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }
}
