package br.edu.exemplo.padaria.service;

import br.edu.exemplo.padaria.entity.Padaria;
import br.edu.exemplo.padaria.repository.PadariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PadariaService {

    private final PadariaRepository repository;

    public PadariaService(PadariaRepository repository) {
        this.repository = repository;
    }

    public List<Padaria> listar() {
        return repository.findAll();
    }

    public Padaria buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Padaria cadastrar(Padaria padaria) {
        padaria.setId(null);
        return repository.save(padaria);
    }

    public Padaria editar(Long id, Padaria dados) {
        Padaria padaria = buscar(id);

        if (padaria == null) {
            return null;
        }

        padaria.setNome(dados.getNome());
        padaria.setEndereco(dados.getEndereco());

        return repository.save(padaria);
    }

    public boolean excluir(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
