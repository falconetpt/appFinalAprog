
package projecto_final;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class erros {
    public static String[] erros = {
        "erro_associados.txt", "erro_inscricoes.txt", "erro_tempos.txt"
    };
    
    public static void initialize() throws FileNotFoundException {
        Formatter outputTxt = null;
        for(int i = 0; i < erros.length; i++) {
            outputTxt = new Formatter(new File(erros[i]));
            outputTxt.format("%s%n", "ERROS");
            outputTxt.close();
        }
    }
    
    public static void erroAssociados (String erro) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(erros[0], true));
        bw.write(erro + "\n");
        bw.close();
    }
    
    public static void erroInscricoes (String erro) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(erros[1], true));
        bw.write(erro + "\n");
        bw.close();
    }
    
    public static void erroTempos (String erro) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(erros[2], true));
        bw.write(erro + "\n");
        bw.close();
    }
    
}
