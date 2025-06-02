package cl.duoc.ms.adm.ventas.Controller;

import cl.duoc.ms.adm.ventas.service.BoletaFileService;
import cl.duoc.ms.adm.ventas.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/boletas-archivo")
@RequiredArgsConstructor
public class BoletaFileController {

    private final BoletaFileService boletaFileService;
    private final AwsS3Service awsS3Service;

    // Generar y subir boleta
    @PostMapping("/generar-y-subir")
public ResponseEntity<String> generarYSubirBoleta(
        @RequestParam Long boletaId,
        @RequestParam String cliente,
        @RequestParam List<String> productos,
        @RequestParam double total,
        @RequestParam String bucket) throws IOException {

    File boleta = boletaFileService.generarBoletaTxt(boletaId, cliente, productos, total);
    try (FileInputStream fis = new FileInputStream(boleta)) {
        awsS3Service.uploadInputStream(bucket, boleta.getName(), fis, boleta.length());
    }
    return ResponseEntity.ok("Boleta generada y subida correctamente: " + boleta.getName());
}

    // Modificar (sobrescribir) boleta
    @PostMapping("/modificar")
    public ResponseEntity<String> modificarBoleta(
            @RequestParam String bucket,
            @RequestParam String key,
            @RequestParam("file") MultipartFile file) {
        awsS3Service.upload(bucket, key, file);
        return ResponseEntity.ok("Boleta modificada correctamente: " + key);
    }

    // Descargar boleta
    @GetMapping("/descargar")
    public ResponseEntity<byte[]> descargarBoleta(
            @RequestParam String bucket,
            @RequestParam String key) {
        byte[] fileBytes = awsS3Service.downloadAsBytes(bucket, key);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + key)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileBytes);
    }

    // Borrar boleta
    @DeleteMapping("/borrar")
    public ResponseEntity<String> borrarBoleta(
            @RequestParam String bucket,
            @RequestParam String key) {
        awsS3Service.deleteObject(bucket, key);
        return ResponseEntity.ok("Boleta eliminada correctamente: " + key);
    }
}
