package nk.estoque.application.infraestructure.web.servico;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Servico;
import nk.estoque.domain.servico.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<Servico>> get() {
        return new ResponseEntity<>(servicoService.listaPaginada(), OK) ;
    }

    @PostMapping
    public ResponseEntity<Servico> post(@Valid @RequestBody ServicoPayload servicoPayload) {
        Servico servico = servicoPayload.toServico();
        return new ResponseEntity<>(servicoService.criar(servico), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> put(@PathVariable Long id, @Valid @RequestBody ServicoPayload servicoPayload) {
        Servico servico = servicoPayload.toServico();
        return new ResponseEntity<>(servicoService.atualizarServico(id, servico), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
