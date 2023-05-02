package dio.projeto.contatos.controller;

import dio.projeto.contatos.entity.Contato;
import dio.projeto.contatos.service.ContatoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato salvar(Contato contato){
        return contatoService.novoContato(contato);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> listaContato(){
        return contatoService.listaContato();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato buscarContatoPorId (@PathVariable("id") Long id){
        return contatoService.buscarporId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerContato (@PathVariable("id") Long id){
        contatoService.buscarporId(id).map(contato -> {
            contatoService.removerporId(contato.getId());
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarContato(@PathVariable("id") Long id, @RequestBody Contato contato){
        contatoService.buscarporId(id)
                .map(contatoBase ->{
                    modelMapper.map(contato, contatoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado."));
    }
}
