package br.com.geovanejunior.espjava.crudcidades.cidade;

import br.com.geovanejunior.espjava.crudcidades.entity.CidadeEntidade;
import br.com.geovanejunior.espjava.crudcidades.repository.CidadeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CidadeController {

    private Set<Cidade> cidades;

    private final CidadeRepository cidadeRep;

    public CidadeController(CidadeRepository cidadeRepository) {

        cidades = new HashSet<>();
        this.cidadeRep = cidadeRepository;
    }

    @GetMapping("/")
    public String listar(Model memoria) {

        cidades.stream().sorted();
        memoria.addAttribute("listaCidades", cidades);

        memoria.addAttribute("listaCidades", cidadeRep
                .findAll()
                .stream()
                .map(cidade ->
                    new Cidade(cidade.getNome(), cidade.getEstado())
                )
                .collect(Collectors.toList()));
        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult validacao, Model memoria) {

        if (validacao.hasErrors()) {

            validacao
                    .getFieldErrors()
                    .forEach(error ->
                            memoria.addAttribute(
                                    error.getField(),
                                    error.getDefaultMessage()
                            )
                    );

            memoria.addAttribute("nomeInformado", cidade.getNome());
            memoria.addAttribute("estadoInformado", cidade.getEstado());
            memoria.addAttribute("listaCidades", cidades);
            return ("/crud");
        } else {

//            var novaCidade = new CidadeEntidade();
//            novaCidade.setNome(cidade.getNome());
//            novaCidade.setEstado(cidade.getEstado());
//
//            cidadeRep.save(novaCidade);
            cidadeRep.save(cidade.clonar());
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome, @RequestParam String estado) {

//        cidades.removeIf(cidadeAtual ->
//                        cidadeAtual.getNome().equals(nome) &&
//                        cidadeAtual.getEstado().equals(estado));

        var cidadeEstadoEncontrada = cidadeRep.findByNomeAndEstado(nome, estado);
        cidadeEstadoEncontrada.ifPresent(cidadeRep::delete);
        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String preparaAlterar(@RequestParam String nome,
                                 @RequestParam String estado,
                                 Model memoria) {
        var cidadeAtual = cidadeRep.findByNomeAndEstado(nome, estado);

        cidadeAtual.ifPresent(cidadeEncontrada -> {
            memoria.addAttribute("cidadeAtual", cidadeEncontrada);
            memoria.addAttribute("listaCidades", cidadeRep.findAll());
        });

        return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual,
                          @RequestParam String estadoAtual,
                          Cidade cidade) {

        var cidadeAtual = cidadeRep.findByNomeAndEstado(nomeAtual, estadoAtual);

        if (cidadeAtual.isPresent()) {

            var cidadeEncontrada = cidadeAtual.get();
            cidadeEncontrada.setNome(cidade.getNome());
            cidadeEncontrada.setEstado(cidade.getEstado());
            cidadeRep.saveAndFlush(cidadeEncontrada);
        }

        return "redirect:/";
    }
}
