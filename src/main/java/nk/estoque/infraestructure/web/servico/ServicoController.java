package nk.estoque.infraestructure.web.servico;

import jakarta.validation.Valid;
import nk.estoque.application.servico.dto.ServicoDTO;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.application.servico.service.ServicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/servicos")
@CrossOrigin(origins = "http://localhost:3039", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<Page<ServicoDTO>> get(@PageableDefault Pageable pageable, ServicoFilter filter) {
        Page<Servico> servicos = servicoService.listaPaginada(pageable, filter);
        Page<ServicoDTO> dtoPage = servicos.map(ServicoDTO::fromDomain);
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> post(@Valid @RequestBody ServicoDTO payload) {
        Servico servico = payload.toDomain();
        Servico salvo = servicoService.criar(servico);
        return new ResponseEntity<>(ServicoDTO.fromDomain(salvo), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> put(@PathVariable Long id, @Valid @RequestBody ServicoDTO payload) {
        Servico servico = payload.toDomain();
        Servico atualizado = servicoService.atualizarServico(id, servico);
        return ResponseEntity.ok(ServicoDTO.fromDomain(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
