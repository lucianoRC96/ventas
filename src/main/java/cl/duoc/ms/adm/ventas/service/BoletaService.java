package cl.duoc.ms.adm.ventas.service;

import org.springframework.stereotype.Service;

import cl.duoc.ms.adm.ventas.model.Boleta;
import cl.duoc.ms.adm.ventas.repository.BoletaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoletaService {
    private final BoletaRepository boletaRepository;

    public BoletaService(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    public List<Boleta> listarBoletas() {
        return boletaRepository.findAll();
    }

    public Boleta guardarBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public Optional<Boleta> buscarPorId(Long id) {
        return boletaRepository.findById(id);
    }

    
    public void eliminarPorId(Long id) {
        boletaRepository.deleteById(id);
    }
}

