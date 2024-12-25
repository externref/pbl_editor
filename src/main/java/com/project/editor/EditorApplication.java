package com.project.editor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EditorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EditorApplication.class, args);
    }

}

class RequestData {
    private String path;
    private String code;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}





@RestController
class FileController {

    private final Path uploadsDir = Paths.get("uploads");

    @PostMapping("/read")
    public String readFile(@RequestBody RequestData requestData) throws IOException {
        String filePath = requestData.getPath();
        Path lp = uploadsDir.resolve(filePath);
        System.out.println(lp.toFile().exists());
        
        if (Files.notExists(lp)) {
            return "\"\"";
        } else {
            return "\"" + Files.readString(lp) + "\"";
        }
    }

    @PostMapping("/write")
    public String writeFile(@RequestBody RequestData requestData) throws IOException {
        String filePath = requestData.getPath();
        String code = requestData.getCode();

        Path lp = uploadsDir.resolve(filePath);
        if (Files.notExists(lp)) {
            Files.createFile(lp);
        }
        Files.write(lp, code.getBytes(), StandardOpenOption.WRITE);
        return "handled";
    }
}
