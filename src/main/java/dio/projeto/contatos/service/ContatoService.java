package dio.projeto.contatos.service;

import dio.projeto.contatos.entity.Contato;
import dio.projeto.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato novoContato (Contato contato){
        return contatoRepository.save(contato);
    }

    public List<Contato> listaContato(){
        return contatoRepository.findAll();
    }

    public Optional<Contato> buscarporId (Long id){
        return contatoRepository.findById(id);
    }

    public void removerporId (Long id){
        contatoRepository.deleteById(id);
    }


}

