package hyh.util;

import hyh.global.Variable;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static Logger log = Logger.getLogger("hyh.util.Zip");

    public static boolean fileToZip(String sourcefilepath, String zipfilepath, String filename) {
        File sourcefile = new File(sourcefilepath);

        if (!sourcefile.exists()) {
            Variable.errornum++;
            log.error("path:" + sourcefile + ".No file error\n");
            return false;
        }

        try {
            File file = new File(zipfilepath);
            File zipFile = new File(zipfilepath + filename + ".zip");
            File[] sourcefiles = sourcefile.listFiles();

            if (!file.exists()) {
                file.mkdirs();
            }

            if (zipFile.exists()) {
                zipFile.delete();
            }

            if (null == sourcefiles || sourcefiles.length < 1) {
                Variable.errornum++;
                log.error("path:" + sourcefile + ".No file error\n");
                return false;
            }

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            byte[] bufs = new byte[10240];
            int read;

            for (int i = 0; i < sourcefiles.length; i++) {
                ZipEntry zipEntry = new ZipEntry(sourcefiles[i].getName());
                fis = new FileInputStream(sourcefiles[i]);
                bis = new BufferedInputStream(fis, 10240);

                zos.putNextEntry(zipEntry);

                while ((read = bis.read(bufs, 0, 10240)) != -1) {
                    zos.write(bufs, 0, read);
                }
            }

            bis.close();
            zos.close();
        } catch (Exception e) {
            Variable.errornum++;
            log.error("write zip error\n" + e + "\n");
            return false;
        }

        return true;
    }

}
