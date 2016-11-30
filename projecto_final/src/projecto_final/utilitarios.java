
package projecto_final;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class utilitarios {
    
    public static Scanner scan = new Scanner(System.in);

    /*
    ===============================
        Datas
    ===============================
    */

    /**
    * @return boolean -  Recebe uma data no formato de 
    **/
    public static boolean dataValida(String data) {
        String valores[] = data.split("/");
        
        if(valores.length != 3) {
            return false;
        }
        
        if(( valores[0].toString().length() > 2 )
            || ( valores[1].toString().length() > 2 ) 
            || valores[2].toString().length() > 4) {
            
            return false;
        }
        
        return true;
    }
    
    /**
    * @return Calcula a idade mediante data nascimento
    **/
    
    public static int calcIdade(String data, String hoje) {
        String dataNasimento[] = data.split("/");
        String dataHoje[] = hoje.split("/");
        
        int idade = Integer.parseInt(dataHoje[2]) - Integer.parseInt(dataNasimento[2]) -1;
        
        if(( Integer.parseInt(dataHoje[1]) >=  Integer.parseInt(dataNasimento[1]) )
                && 
            ( Integer.parseInt(dataHoje[0]) >=  Integer.parseInt(dataNasimento[0]) ) ) {
            
            idade++;
        }
        return idade;
    }
    
    /*
    ===============================
        Apelidos
    ===============================
    */
    
    
    /**
    * @return string 
    * Devolve nome e apelido de uma string com o nome completo
    **/
    public static String reduzirNome(String nome) {
        String nomeReduzido[] = nome.trim().split(" ");
        String texto = nomeReduzido[0] + " " + nomeReduzido[nomeReduzido.length -1];
        return texto;
    }
    
    
    /**
    * @return void --  
    * Listar paginado - Socios
    **/
    
    public static void listarPaginado(String socios[][], int pos, int paginacao) {
        int pagina = 1;
        
        System.out.println("\n--SOCIOS--");
        String msg = "==============\nPagina: " + pagina + "\n";
        
        System.out.println(msg);
        for(int i = 0; i < pos; i++) {
            for(int j = 0; j < socios[0].length; j++) {
                System.out.printf("%15s \t\t", socios[i][j]);
            }
            
            System.out.println();
            
            //Handler da paginacao
            if ((i+1) % paginacao == 0 && (i + 1) != pos) {
                System.out.println("\n -- -- Pressione ENTER para ir para a Pagina Seguinte -- --");
                pagina++;
                msg = "==============\nPagina: " + pagina + "\n";
                scan.nextLine();
                System.out.println(msg);
            }
        }
    }
    
    
    //****DEBUG****
    
    /**
    * @return void --  
    * Listar paginado - Provas
    **/
    public static void listarPaginado(int provas[][], int pos, int paginacao) {
        int pagina = 1;
        System.out.println("\n--PROVAS--");
        String msg = "==============\nPagina: " + pagina + "\n";
        
        System.out.println(msg);
        for(int i = 0; i < pos; i++) {
            for(int j = 0; j < provas[0].length; j++) {
                System.out.printf("%15s \t\t", provas[i][j]);
            }
            
            System.out.println();
            
            //Handler da paginacao
            if ((i+1) % paginacao == 0 && (i + 1) != pos) {
                System.out.println("\n -- -- Pressione ENTER para ir para a Pagina Seguinte -- --");
                pagina++;
                msg = "==============\nPagina: " + pagina + "\n";
                scan.nextLine();
                System.out.println(msg);
            }
        }
    }
    
    //validacao de linha texto -- GLOBAL CONNECTOR --
    public static boolean validacao(String dados, int nDadosAtleta, String socios[][], int posicao) {
        String arrValidacao[] = dados.split(";");
      
        if (arrValidacao.length != nDadosAtleta) {
            return false;
        }
        
        //estrutura para 4 campos dados
        String nif = arrValidacao[0].trim();
        String nome =  arrValidacao[1].trim();
        String data = arrValidacao[2].trim();
        String sexo = arrValidacao[3].trim();
                
        //valida se o NIF tem 9 chars
        if(!nifValido(nif)) {
            return false;
        }
        
        //valida se o nome contem nome e apelido
        if(!nomeValido(nome)) {
            return false;
        }
        
        //valida estrutura de datas
        if(!validaData(data)) {
            return false;
        }
        
        //valida sexo
        if(!(sexo.equalsIgnoreCase("masculino") || sexo.equalsIgnoreCase("feminino"))) {
            return false;
        }
        
        if(indexOf(socios, nif, posicao) != -1) {
            return false;
        }
        
        return true;
    }
    
    
    //
    public static String normalizarString(String dados) {
        String arrValidacao[] = dados.split(";");
        
        String nif = arrValidacao[0].trim();
        String nome =  arrValidacao[1].trim();
        String data = arrValidacao[2].trim();
        String sexo = arrValidacao[3].trim();
        
        return nif + ";" + nome + ";" + data + ";" + sexo;
    }
    
    //valida nif
    public static boolean nifValido(String nif) {
        if(nif.length() != 9) {
            return false;
        }
        return true;
    }
    
    //valida nome
    public static boolean nomeValido(String nome) {
        if(nome.split(" ").length < 2) {
            return false;
        }
        return true;
    }
    
    
    
     /**
     * @return um boolean, com indicacao se existe ou nao
     * no nosso calendario
     */
    public static boolean validaData (String data) {
        String arrValidacao[] = data.split("/");
        
        if (arrValidacao.length != 3) {
            return false;
        }
        
        int dia = Integer.parseInt(arrValidacao[0]);
        int mes = Integer.parseInt(arrValidacao[1]);
        int ano = Integer.parseInt(arrValidacao[2]);
        
        int diasMes[] = { 
            31, 28 + bisexto(ano), 31, 30,
            31, 30, 31, 31,
            30, 31, 30, 31
            };
        
        if((mes < 1) || (mes > 12)) {
            return false;
        }
        
        if(dia > diasMes[mes -1]) {
            return false;
        }
        
        return true;
    }
    
    /**
    * @return determina se e ano bisexto ou nao
    **/
    public static int bisexto(int ano) {
        if (( ano % 4 == 0 && ano % 100 != 0 ) || ano % 400 == 0) {
            return 1;
        }
        return 0; 
    }
    
    /**
    * @return procura e devolve posicao de um determinado elemento
    **/
    public static int indexOf(String socios[][], String socio, int posicao) {
        for (int i =0; i < posicao; i++) {
            if(socios[i][0].equals(socio)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
    * @return void --
    * muda o nome ou data de nascimento
    **/
    public static String alterarDados(String socios[][], String socio, int posicao) {
        //ver se o socio existe
        if(indexOf(socios, socio, posicao) == -1){
            return "Nao existe o nif que introduziu";
        }
        
        int opcao = 0;
        
        String msg = "Qual e a opcao:\n\t"
                + "1 - Alterar Datas\n\t"
                + "2 - Alterar Nome\n\t"
                + "3 - Alterar Ambos";
        do {
            System.out.println(msg);
            opcao = Integer.parseInt(scan.nextLine());
        } while(opcao > 3 || opcao < 1);
        
        switch(opcao) {
            case 1:
                mudaData(socios, socio, posicao);
                break;
            case 2:
                mudaNome(socios, socio, posicao);
                break;
            case 3:
                mudaData(socios, socio, posicao);
                mudaNome(socios, socio, posicao);
                break;
        }
        
        return "Realizado com Sucesso";
    }
    
    /**
    * @return void --
    * muda o nome 
    **/
    public static void mudaNome(String socios[][], String socio, int posicao) {
        String nome = "";
        
        do {
            System.out.println("Introduza o novo nome (Pelo menos nome e apelido)");
            nome = scan.nextLine();
        } while (!nomeValido(nome));
        
        int posSocio = indexOf(socios, socio, posicao);
        socios[posSocio][1] = nome;
    }
    
    /**
    * @return void --
    * muda a data 
    **/
    public static void mudaData(String socios[][], String socio, int posicao) {
        String data = "";
        String msg = "Insira uma data no formato dd/mm/aaaa \n\t"
                + "(d) - dia | (m) - mes | (a) - ano";
        do {
            System.out.println(msg);
            data = scan.nextLine();
        } while (!validaData(data));
        
        int posSocio = indexOf(socios, socio, posicao);
        socios[posSocio][2] = data;
    }
    
    
    //remove um socio com base no seu nif caso exista
    public static boolean removerSocio(String socios[][], String socio, int posicao) {
        int index = indexOf(socios, socio, posicao);
        if(index == -1) {
            System.out.println("O socio introduzido nao existe");
            return false;
        }
        
        String tmp[];
        
        for(int i = index; i < posicao; i++) {
            for(int j = i+1; j < posicao;j ++) {
                tmp = socios[i];
                socios[i] = socios[j];
                socios[j] = tmp;
            }
        }
        
        return true;
    }
    
    //inicia a arr das provas com os valores a -1
    public static void iniciaProvas(int provas[][], int posicao) {
        for(int j=0; j < provas[0].length; j++) {
            provas[posicao][j] = -1;
        }
    }
    
    //inscricao numa determinada prova
    public static void inscricao(String socios[][], int provas[][], String nif, int prova, int posicao) {
        int index = indexOf(socios, nif, posicao);
        provas[index][prova - 1] = 0;
    }
    
    //regista o tempo na arr de um determinado SOCIO
    public static boolean registaTempo(String socios[][], int provas[][], String dados, int posicao, int nProvas) {
        String elementos[] = dados.split(";");
        
        int inicioNif = elementos[0].length() - 9;
        String nif = elementos[0].trim().substring(inicioNif);
        
        System.out.println(nif + "-" + (elementos[1]));
     
        int prova = Integer.parseInt(elementos[0].substring(0,2));
        int index = indexOf(socios, nif, posicao);
        
        //triamos que nao temos inputs invalidos de provas
        if(prova > nProvas) {
            return false;
        }
        
        //garantir que nao existem tempos ja inseridos
        if(index == -1 || provas[index][prova - 1] > 0) {
            return false;
        }
        
        provas[index][prova - 1] = Integer.parseInt(elementos[1].trim());
        
        return true;
    }
    
}
