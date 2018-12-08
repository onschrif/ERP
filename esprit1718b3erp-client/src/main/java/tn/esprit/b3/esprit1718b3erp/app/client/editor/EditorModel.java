/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b3.esprit1718b3erp.app.client.editor;

/**
 *
 * @author LENOVO
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class EditorModel {

    public void save(TextFile textFile) {
        try {
            Files.write(textFile.getFile(), textFile.getContent(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IOResult<TextFile> load(Path file) {
        try {
            List<String> lines = Files.readAllLines(file);
            return new IOResult<>(true, new TextFile(file, lines));
        } catch (IOException e) {
            e.printStackTrace();
            return new IOResult<>(false, null);
        }
    }

    public void close() {
        System.exit(0);
    }
}