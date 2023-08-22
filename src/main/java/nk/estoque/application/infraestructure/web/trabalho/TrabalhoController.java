package nk.estoque.application.infraestructure.web.trabalho;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Trabalho;
import nk.estoque.domain.model.trabalho.TodosTrabalhos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping(path = "/trabalhos")
public class TrabalhoController {

    private final TodosTrabalhos todosTrabalhos;

    public TrabalhoController(TodosTrabalhos todosTrabalhos) {
        this.todosTrabalhos = todosTrabalhos;
    }

    @GetMapping
    public ResponseEntity<List<Trabalho>> get() {
        return new ResponseEntity<>(todosTrabalhos.listaPaginada(), OK);
    }

    @PostMapping
    public ResponseEntity<Trabalho> post(@Valid @RequestBody TrabalhoPayLoad trabalhoPayLoad) {
        Trabalho trabalho = trabalhoPayLoad.toTrabalho();
        return new ResponseEntity<>(todosTrabalhos.criarTrabalho(trabalho), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabalho> put(@PathVariable Long id, @Valid @RequestBody TrabalhoPayLoad trabalhoPayLoad) {
        Trabalho trabalho = trabalhoPayLoad.toTrabalho();
        return new ResponseEntity<>(todosTrabalhos.atualizarTrabalho(id, trabalho), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        todosTrabalhos.deletarTrabalho(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
