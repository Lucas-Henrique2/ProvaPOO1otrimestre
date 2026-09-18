package br.edu.exemplo.padaria.controller;

import br.edu.exemplo.padaria.entity.Padaria;
import br.edu.exemplo.padaria.service.PadariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/padaria")
public class PadariaController {

    private final PadariaService service;

    public PadariaController(PadariaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Padaria>> get() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.listar());
    }

    @PostMapping
    public ResponseEntity<Padaria> post(@RequestBody Padaria padaria) {
        return ResponseEntity.ok(service.cadastrar(padaria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Padaria> getById(@PathVariable Long id) {
        Padaria padaria = service.buscar(id);

        if (padaria == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(padaria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.excluir(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Padaria> put(@PathVariable Long id, @RequestBody Padaria dados) {
        Padaria atualizada = service.editar(id, dados);

        if (atualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(atualizada);
    }
}
