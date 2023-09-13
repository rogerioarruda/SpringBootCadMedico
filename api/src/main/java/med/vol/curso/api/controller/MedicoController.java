package med.vol.curso.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import med.vol.curso.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico json) {

        repository.save(new Medico(json));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @GetMapping("/ativos")
    public Page<DadosListagemMedico> listarAtivos(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao);
    }

    @PatchMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable("id") long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}