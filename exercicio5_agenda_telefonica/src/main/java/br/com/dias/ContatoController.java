package br.com.dias;

public class ContatoController {
    private Contato[] contatos;
    private int contadordeContato;

    public ContatoController(int maxContato) {

        maxContato = 100;

        contatos = new Contato[maxContato];
        contadordeContato = 0;

    }

    public void addContato(Contato contato) throws AgendaCheiaException {

        if (contadordeContato >= contatos.length) {
            throw new AgendaCheiaException("Agenda Cheia");
        }
        try {
            contatos[contadordeContato] = contato;
            contadordeContato++;
            System.out.println("Contato adicionado com sucesso");
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void listarContato() {

        if (contadordeContato == 0) {
            System.out.println("Agenda Vazia");
        } else {
            for (int i = 0; i < contadordeContato; i++) {
                System.out.println(contatos[i].toString());
            }
        }
    }
    // public void buscarNome(String nome) throws ContatoNaoEncontrado{
    //     for (int i = 0; i < contatos.length; i++) {
    //         if (contadordeContato==0) {
    //             System.out.println("Agenda Vazia");
    //         }else{
    //             if (contatos[i].getNome().equalsIgnoreCase(nome)) {
    //                 System.out.println(contatos[i].toString());
    //             }
    //         }
    //     }
    //     throw new ContatoNaoEncontrado("Contato não encontrado");
    // }
    public Contato buscarContato(String nome) throws ContatoNaoEncontrado{
        for (int i = 0; i < contadordeContato; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                return contatos[i];
            }
        }
        throw new ContatoNaoEncontrado(("Contato não encontrado"));
        
    }
}