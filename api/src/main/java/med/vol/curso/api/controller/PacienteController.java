package med.vol.curso.api.controller;

import jakarta.validation.Valid;
import med.vol.curso.api.paciente.DadosPaciente;
import med.vol.curso.api.paciente.Paciente;
import med.vol.curso.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosPaciente paciente){
        repository.save(new Paciente(paciente));

    }
}
