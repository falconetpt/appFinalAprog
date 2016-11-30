package projecto_final;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Projecto_final {
    /*
    ====================================
        Variaveis globais da aplicacao
    ====================================
    */
    public static final int nAtletas = 100;
    public static final int nDadosAtleta = 4;
    public static final int nProvas = 5;
    public static final int paginacao = 4;
    public static final String templateFileTempos = "Tempos";
    
    public static final File fileInscricoes = new File("inscricoes.txt");
    public static Scanner scan = new Scanner(System.in);
    
    //Variaveis para contruir a data do dia actual
    public static final Date today = new Date();
    public static final String hoje = today.getDate() + "/" 
            + today.getMonth() + "/" + (today.getYear() + 1900);
    
    
    /*
     =====================================
                *****MAIN*****
     =====================================
    */
    public static void main(String[] args) throws FileNotFoundException {
        String[][] socios = new String[nAtletas][nDadosAtleta];
        int[][] provas = new int[nAtletas][nProvas];
        int posicao = 0;
        int opcao=0;

        do {
            //menu
            System.out.print("\n========MENU========\n"
                            + "Escolha a opção que pretende:\n"
                            + "\t1 - Ler ficheiro com inscrições de sócios.\n"
                            + "\t2 - Visualizar a informação dos sócios.\n"
                            + "\t3 - Atualizar a informação de um sócio.\n"
                            + "\t4 - Ler ficheiro com informação de inscrições.\n"
                            + "\t5 - Ler ficheiro com informação de tempos.\n"
                            + "\t6 - Listagem para ecrã/ficheiro da informação.\n"
                            + "\t7 - Remover um sócio e toda a sua informação.\n"
                            + "\t8 - Melhores e piores tempos por prova.\n"
                            + "\t9 - Percentagem dos atletas femininos em prova.\n"
                            + "\t10 - Listagem de tempos em ficheiro.\n\n"
                            + "\t0 - Terminar. \n");
            
            
            opcao=Integer.parseInt(scan.nextLine());
            switch (opcao) {
                case 1: 
                    System.out.println("Insira o nome do ficheiro a inserir:");
                    String fileSocios = scan.nextLine();
                    posicao=inserirInscricoes(socios, provas, posicao, fileSocios); //envia matriz para guardar a info do fich, return numSocios 
                    break;
                case 2:  
                    //print(tabela,numSocios); //envia matriz para fazer o print ??? printar só os 4 prim campos ou tudo?
                    if(posicao > 0) {
                        utilitarios.listarPaginado(socios, posicao, paginacao);
                    } else {
                        System.out.println("(i) Deve escolher a opcao 1 antes de listar conteudos");
                    }
                    break;
                case 3:  
                    //atualizarSocio(tabela,numSocios); //envia matriz para atualizar
                    System.out.println("Insira o nif do socio para o qual pretende alterar dados");
                    String nifActualizar = scan.nextLine();
                    System.out.println("\n -- " + utilitarios.alterarDados(socios, nifActualizar , posicao) + " -- \n");
                    break;
                case 4:  
                    //lerInscricoes(tabela,numSocios); //envia matriz para passar a info do ficheiro
                    System.out.println("Insira o nome do ficheiro de inscricoes a inserir:");
                    String fileInscricoes = scan.nextLine();
                    int provaInscrever = pedeProva(socios, provas, posicao);
                    inscricoes(socios, provas, posicao, fileInscricoes, provaInscrever);
                    break;
                case 5:  
                    //lerTempos(tabela,numSocios);
                    int provaTempos = pedeProva(socios, provas, posicao);
                    inserirTempos (socios, provas, posicao, templateFileTempos + provaTempos + ".txt", provaTempos);
                    
                    break;
                case 6:  
                    //printOuGuardar(tabela,numSocios);
                    break;
                case 7:  
                    //numSocios=removerSocio(tabela,numSocios);//atualiza o numSocios   
                    System.out.println("Insira o nif do socio para o qual pretende alterar dados");
                    String nifRemover = scan.nextLine();
                    if(utilitarios.removerSocio(socios, nifRemover, posicao)) {
                        posicao--;
                    }
                    break;
                case 8:  
                    //melhoresPiores(tabela,numSocios);//calcula media dos tempos e mostra top e bottom
                    break;
                case 9:  
                    //estatisticas(tabela,numSocios); //% incr --> %mulheres E após a prova %inscr que Desist ou Faltaram
                    break;
                case 10: 
                    //guardarInfo(tabela, numSocios);//criar ficheiro Runers2016 com toda a info
                    break;
                case 0: 
                    break;
                default:
                    break;
            }
            
            //menu de pausa
            if (opcao != 0) {
                System.out.println("\nPressione -ENTER- para voltar ao menu");
                scan.nextLine();
            }
        } while( opcao != 0 );
                
        /*
        socios[0] = "443543678;Ana Rita Gomes Costa;31/1/1999;feminino".split(";");
        socios[1] = "443543674;Ana Gomes Costa;31/1/1979;feminino".split(";");
        socios[2] = "443543676;Ana Rita ;31/1/1999;feminino".split(";");
        */
        
        //posicao = inserirInscricoes(socios, posicao, "inscricoes.txt");
        
        //utilitarios.iniciaProvas(provas, posicao);
        
        
        utilitarios.listarPaginado(provas, posicao, paginacao);
        
        /*
        utilitarios.iniciaProvas(provas, 3);
        
        utilitarios.inscricao(socios, provas, "443543674", 1, 3);
        utilitarios.inscricao(socios, provas, "443543678", 2, 3);
        utilitarios.inscricao(socios, provas, "443543676", 3, 3);
        utilitarios.inscricao(socios, provas, "443543674", 4, 3);
        
        utilitarios.registaTempo(socios, provas, "01F443543676;50", 3);
       
        utilitarios.listarPaginado(provas, 3, 2);
        */
       
        //ler dados do ficheiro -- OK
        /*
        Scanner fileIO = new Scanner(new FileReader(fileInscricoes));
        while (fileIO.hasNextLine()) {
            String i = fileIO.nextLine();
            if(utilitarios.validacao(i, nDadosAtleta)) {
                System.out.println(utilitarios.normalizarString(i));
            }
        }
        fileIO.close();
        
        //*/
    } 
    
    public static int inserirInscricoes(String[][] socios, int provas[][], int posicao, String nomeFicheiro) throws FileNotFoundException {
        Scanner fileIO = new Scanner(new FileReader(nomeFicheiro));
        while (fileIO.hasNextLine() && posicao < nAtletas) {
            String i = fileIO.nextLine();
            if(utilitarios.validacao(i, nDadosAtleta, socios, posicao)) {
                String dados[] = i.split(";");
                
                socios[posicao][0] = dados[0].trim();
                socios[posicao][1] =  dados[1].trim();
                socios[posicao][2] = dados[2].trim();
                socios[posicao][3] = dados[3].trim();
                
                utilitarios.iniciaProvas(provas, posicao);
                
                posicao++;
            }
        }
        fileIO.close();
        return posicao;
    }
    
    public static void inscricoes(String[][] socios, int provas[][], int posicao, String nomeFicheiro, int prova) throws FileNotFoundException {
        Scanner fileIO = new Scanner(new FileReader(nomeFicheiro));
        while (fileIO.hasNextLine()) {
            String i = fileIO.nextLine();
            int index = utilitarios.indexOf(socios, i.trim(), posicao);
            if(index != -1 && provas[index][prova - 1] == -1) {
                provas[index][prova -1] = 0;
            }
        }
        fileIO.close();
    }
    
    public static void inserirTempos (String[][] socios, int provas[][], int posicao, String nomeFicheiro, int prova) throws FileNotFoundException {
        Scanner fileIO = new Scanner(new FileReader(nomeFicheiro));
        while (fileIO.hasNextLine()) {
            String i = fileIO.nextLine();
            if(i.split(";").length == 2) {
                utilitarios.registaTempo(socios, provas, i, posicao, prova);
            }
        }
        fileIO.close();
    }
    
    public static int pedeProva(String socios[][], int provas[][], int posicao) {
        int prova = 0;
        
        do {
            System.out.println("insira o numero da prova");
            prova = Integer.parseInt(scan.nextLine());
        } while(prova < 0 || prova > nProvas);
        
        return prova;
    }
}
    
    
        /**********************************
                     DEBUGS    
                * Criar classe de testes
        ***********************************/
        
            //System.out.println(utilitarios.reduzirNome("José Costa Rodrigues Neto"));
            //System.out.println(utilitarios.calcIdade("8/8/1992", hoje));
            //utilitarios.listarPaginado(socios, 3, 2);
            //System.out.println(utilitarios.indexOf(socios, "443543674", 3));
            //System.out.println(utilitarios.alterarDados(socios, "443543674", 2));
            //System.out.println(utilitarios.nomeValido("Ricardo Gomes"));
            //utilitarios.mudaData(socios, "443543674", 3);
            //System.out.println(utilitarios.validaData("28/3/2016"));
