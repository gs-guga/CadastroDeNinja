package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_Missoes")

public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    private String ranck;

    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;


    public MissoesModel() {
    }

    public MissoesModel( String nome, String ranck) {
        this.nome = nome;
        this.ranck = ranck;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRanck() {
        return ranck;
    }

    public void setRanck(String ranck) {
        this.ranck = ranck;
    }
}
