package cl.duoc.ms.adm.ventas.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class BoletaFileService {
    public File generarBoletaTxt(Long boletaId, String cliente, List<String> productos, double total) throws IOException {
        File file = File.createTempFile("boleta_" + boletaId + "_", ".txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Boleta N°: " + boletaId + "\n");
            writer.write("Cliente: " + cliente + "\n");
            writer.write("Productos:\n");
            for (String prod : productos) {
                writer.write(" - " + prod + "\n");
            }
            writer.write("Total: $" + total + "\n");
        }
        return file;
    }
}
