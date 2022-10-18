package br.com.projeto.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity // criando a tabela
@Table(name = "clientes") // mudado o nome da tabela
public class Cliente {
    //atributos
    //declarando a chave primaria
    @Id
    //tornando a chave primaria como autoincrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //nome tem que ter pelo meno um caracter
    @NotEmpty(message = "informe um nome")
    private String nome;

    //verifica se é um email valido
    @Email(message = "informe um email valido")
    private String email;

    //getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
