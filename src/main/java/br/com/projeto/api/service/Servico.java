package br.com.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Mensagem;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // metodo para autenticar e cadastra uma pessoa
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() < 0){
            mensagem.setMensagem("informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    //metodo para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    //metodo para selecionar uma pessoa atraves do id
    public ResponseEntity<?> selecionarPeloId(int id){
        if(acao.countById(id) == 0){
            mensagem.setMensagem("não foi encontrada nenhuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(acao.findById(id), HttpStatus.OK);
        }
    }

    //metodo para editar dados
    public ResponseEntity<?> editar(Pessoa obj){
        if(acao.countById(obj.getId()) == 0){
            mensagem.setMensagem("o id informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(obj.getNome().equals("")){
            mensagem.setMensagem("é necessario informar um nome!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() < 0){
            mensagem.setMensagem("Digite uma idade válida!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    //metodo para remover regitrps 
    public ResponseEntity<?> remover(int id){
        if(acao.countById(id) == 0){
            mensagem.setMensagem("codigo não encontrado, digite um codigo valido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            Pessoa obj = acao.findById(id);
            acao.delete(obj); 
            mensagem.setMensagem("Pessoa removida com sucesso"); // retornando uma mensagem pois o delete é do tipo void
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
