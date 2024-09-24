package com.example;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Curso {

    //Atributed
    private String nomeCurso;
    private List<Aluno> alunos;
    private Professor professor;

    //Constructor

    public Curso(String nomeCurso){

        this.nomeCurso = nomeCurso;
        alunos = new ArrayList<>();
    }
    // METODOS

    // Add Professor
    public void addProf(Professor professor){
        this.professor = professor;
    }

    // Add Aluno
    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    //Informaçoes do curso

    public void infoCurso(){
        System.out.println("Curso " + nomeCurso);
        System.out.println("Professor: " + professor.getNome());
        System.out.println("Alunos Matriculados:");
        for (Aluno aluno : alunos) {
            System.out.println("Aluno: "+aluno.getNome()+ "RA:"+ aluno.getMatricula());
        }
    }
    // Metodo de lançar notas
    public void atribuirNotaNome(String nomeAluno, double notaAluno){
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                aluno.setNota(notaAluno);
                return;
            }
        }
        System.out.println("Aluno não Encontrado");
    }
    // Exibir resultadofinal
    public void exibeResultadoFinal(){
        for (Aluno aluno : alunos) {
            System.out.println(aluno.exibirInfo());
            System.out.println(aluno.avaliarDesempenho());
        }
    }
}
