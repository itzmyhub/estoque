package nk.estoque.application.infraestructure.persistence;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodosProdutosAdapterTest {

    //não consegui dar autowired no repository pra fazer testes

//    private ProdutoRepository produtoRepository;
//
//    public TodosProdutosAdapterTest(ProdutoRepository produtoRepository) {
//        this.produtoRepository = produtoRepository;
//    }

//    @Test
//    void deve_retornar_todos_os_produtos() {
//        Produto produto1 = new Produto();
//        produto1.setNome("Evaporador");
//        produtoRepository.save(produto1);
//        TodosProdutosAdapter todosProdutos = new TodosProdutosAdapter(produtoRepository);
//        when(todosProdutos.listaPaginada()).thenReturn(List.of(produto1));
//
//        Produto primeiroProduto = todosProdutos.listaPaginada().get(0);
//
//        assertEquals(produto1.getNome(), primeiroProduto.getNome());
//    }

//    @Test
//    void deve_salvar_um_produto() {
//        TodosProdutosAdapter todosProdutos = new TodosProdutosAdapter(produtoRepository);
//        Produto produto = new Produto();
//        produto.setNome("Evaporador");
//
//        todosProdutos.criar(produto);
//
//        assertEquals(1.0, produtoRepository.count());
//    }

//    @AfterEach
//    void limpar_repositorio(){
//        produtoRepository.deleteAll();
//    }

}
