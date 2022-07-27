

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Slf4j
public class FileSink {

    /**
     * 向文件追加内容
     *
     * @param content  写入的内容
     * @param fileName 文件
     */
    public static void writeFile(String content, String fileName) {

        // 在文件夹目录下新建文件
        File file = new File(fileName);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    log.info("file not exists, create new file");
                }
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            // 写入内容
            osw.write(content);
            // 换行
            osw.write("\r\n");
            log.info("成功向文件 [{}] 写入内容：[{}]", fileName, content);
        } catch (Exception e) {
            log.info("写入文件发生异常", e);
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.info("关闭流异常", e);
            }
        }
    }
    // 将内容 "testFlag_20210112=1" 追加到文件 "D:\\testConfig.properties"
//    public static void main(String[] args) {
//        String content = "testFlag_20210112=1";
//        String filePath = "D:\\testConfig.properties";
//        writeFile(content, filePath);
//    }
}
