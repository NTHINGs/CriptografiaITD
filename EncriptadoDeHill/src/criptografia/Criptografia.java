package criptografia;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

/**
 *
 * @author Mauricio Alejandro Martinez Pacheco
 */
public class Criptografia {
    JTextField texto=new JTextField(20);
    JTextField salida=new JTextField();
    JTextField clavetextfield=new JTextField(20);
    public Criptografia(){
        /**
         * Constructor para la interfaz gráfica
         */
        JFrame ventana=new JFrame("Criptografía: Método de Hill");
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        
        //Campo de texto para la entrada
        JPanel panel1=new JPanel();
        panel1.setLayout(new GridBagLayout());
        JLabel label1=new JLabel("Texto:");
        JLabel label2=new JLabel("Clave(4 digitos):");
        GridBagConstraints config1=new GridBagConstraints();
        config1.gridx=0;
        config1.gridy=0;
        GridBagConstraints config2=new GridBagConstraints();
        config2.gridx=1;
        config2.gridy=0;
        config2.fill = GridBagConstraints.HORIZONTAL; 
        GridBagConstraints config3=new GridBagConstraints();
        config3.gridx=0;
        config3.gridy=1;
        GridBagConstraints config4=new GridBagConstraints();
        config4.gridx=1;
        config4.gridy=1;
        panel1.add(label1,config1);
        panel1.add(texto,config2);
        panel1.add(label2,config3);
        panel1.add(clavetextfield,config4);
        panel.add(panel1,BorderLayout.NORTH);
        
        //Un sub-panel para almacenar dos botones
        JPanel botones=new JPanel();
        JButton encriptar=new JButton("ENCRIPTAR");
        encriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encriptar(texto.getText());
            }
        });

        /*JButton desencriptar=new JButton("DESENCRIPTAR");
        desencriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desencriptar(texto.getText());
            }
        });
        botones.add(desencriptar);*/
        botones.add(encriptar);
        panel.add(botones,BorderLayout.CENTER);
        
        //Campo de texto para la salida sin edicion
        salida.setEditable(false);
        panel.add(salida, BorderLayout.SOUTH);
        
        //Formato de la ventana y que se muestr
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400,150);
        ventana.setContentPane(panel);
        ventana.show();
    }
    
    //Metodo para encriptar
    public void encriptar(String entrada){
        //Declaramos el alfabeto
        char[] alfabeto={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        String encriptado="";
        
        //Convertimos la entrada a minisculas por si existen mayusculas
        entrada=entrada.toLowerCase();
        
        //Comparar si se pueden formar las parejas y en caso que no agregar una equis al final
        if(entrada.length()%2!=0){
            entrada=entrada+"x";
        }
        
        //convertir la cadena en un arreglo de caracteres
        char[] entradachar=entrada.toCharArray();
        
        //Verificar que se ingreso una clave de 4 caracteres
        if(clavetextfield.getText().length()==4){ 
            
            //Formar la matriz de la clave
            int[] matrizclave = new int[4];
            char[] clave = clavetextfield.getText().toCharArray();
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < alfabeto.length; b++) {
                    if (clave[a] == alfabeto[b]) {
                        matrizclave[a] = b;
                    }
                }
            }
            System.out.println(matrizclave[0]+"   "+matrizclave[1]);
            System.out.println(matrizclave[2]+"   "+matrizclave[3]);
            int[]vectorpar=new int[2];
            int equis=0;
            
            //Repetir por los numeros de parejas que tiene la palabra a encriptar 
            for(int x=0;x<(entrada.length()/2);x++){
                
                //Recorrer las parejas y convertir a su equivalente numerico
                for (int a = 0; a < alfabeto.length; a++) {
                    if (entradachar[equis] == alfabeto[a]) {
                        vectorpar[0] = a;
                    }
                    if(entradachar[equis+1]==alfabeto[a]){
                        vectorpar[1] = a;
                    }
                }
                System.out.println(vectorpar[0]);
                System.out.println(vectorpar[1]);
                
                //Operaciones del metodo de hill
                int letra1=((matrizclave[0]*vectorpar[0])+(matrizclave[1]*vectorpar[1]))%27;
                int letra2=((matrizclave[2]*vectorpar[0])+(matrizclave[3]*vectorpar[1]))%27;
                
                System.out.println(letra1+"     "+letra2);
                
                //Recorrer el alfabeto e ir concatenando en una variable las letras encriptadas
                for(int a=0;a<alfabeto.length;a++){
                    if(letra1==a){
                        encriptado=encriptado+alfabeto[a];
                    }
                }
                
                for(int a=0;a<alfabeto.length;a++){
                    if(letra2==a){
                        encriptado=encriptado+alfabeto[a];
                    }
                }
                
                //sumar dos para la siguiente pareja
                equis=equis+2;
            }
            //muestra el mensaje encriptado
            salida.setText(encriptado);
            
        }else{
            JOptionPane.showMessageDialog(null,"LA CLAVE NO ES DE 4 DIGITOS","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /*Desencriptar es casi imposible en este metodo ya que debe ser una clave que resulte una matriz inversa entera, pero este es el procedimiento
    //Metodo para desencriptar
    public void desencriptar(String entrada){
        //Declaramos el alfabeto
        char[] alfabeto={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        String encriptado="";
        
        //Convertimos la entrada a minisculas por si existen mayusculas
        entrada=entrada.toLowerCase();
        
        //Comparar si se pueden formar las parejas y en caso que no agregar una equis al final
        if(entrada.length()%2!=0){
            entrada=entrada+"x";
        }
        
        //convertir la cadena en un arreglo de caracteres
        char[] entradachar=entrada.toCharArray();
        
        //Verificar que se ingreso una clave de 4 caracteres
        if(clavetextfield.getText().length()==4){ 
            
            //Formar la matriz de la clave
            int[] matrizclave = new int[4];
            char[] clave = clavetextfield.getText().toCharArray();
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < alfabeto.length; b++) {
                    if (clave[a] == alfabeto[b]) {
                        matrizclave[a] = b;
                    }
                }
            }
            
            //calcular la inversa de la matriz para descifrar
            float determinante=(matrizclave[0]*matrizclave[3])-(matrizclave[1]*matrizclave[2]);
            System.out.println(determinante);
            int[]adjunta=new int[4];
            adjunta[0]=matrizclave[3];
            adjunta[1]=-matrizclave[1];
            adjunta[2]=-matrizclave[2];
            adjunta[3]=matrizclave[0];
            System.out.println(adjunta[0]+"   "+adjunta[1]);
            System.out.println(adjunta[2]+"   "+adjunta[3]);
            float matrizinversa[]=new float[4];
            matrizinversa[0]=adjunta[0]/determinante;
            matrizinversa[1]=adjunta[1]/determinante;
            matrizinversa[2]=adjunta[2]/determinante;
            matrizinversa[3]=adjunta[3]/determinante;
            
            System.out.println(matrizinversa[0]+"   "+matrizinversa[1]);
            System.out.println(matrizinversa[2]+"   "+matrizinversa[3]);
            int[]vectorpar=new int[2];
            int equis=0;
            
            //Repetir por los numeros de parejas que tiene la palabra a desencriptar 
            for(int x=0;x<(entrada.length()/2);x++){
                
                //Recorrer las parejas y convertir a su equivalente numerico
                for (int a = 0; a < alfabeto.length; a++) {
                    if (entradachar[equis] == alfabeto[a]) {
                        vectorpar[0] = a;
                    }
                    if(entradachar[equis+1]==alfabeto[a]){
                        vectorpar[1] = a;
                    }
                }
                System.out.println(vectorpar[0]);
                System.out.println(vectorpar[1]);
                
                //Operaciones del metodo de hill
                double letra1=((matrizinversa[0]*vectorpar[0])+(matrizinversa[1]*vectorpar[1]))%27;
                double letra2=((matrizinversa[2]*vectorpar[0])+(matrizinversa[3]*vectorpar[1]))%27;
                
                System.out.println(letra1+"     "+letra2);
                
                //Recorrer el alfabeto e ir concatenando en una variable las letras desencriptadas
                for(int a=0;a<alfabeto.length;a++){
                    if(letra1==a){
                        encriptado=encriptado+alfabeto[a];
                    }
                }
                
                for(int a=0;a<alfabeto.length;a++){
                    if(letra2==a){
                        encriptado=encriptado+alfabeto[a];
                    }
                }
                
                //sumar dos para la siguiente pareja
                equis=equis+2;
            }
            //muestra el mensaje encriptado
            salida.setText(encriptado);
            
        }else{
            JOptionPane.showMessageDialog(null,"LA CLAVE NO ES DE 4 DIGITOS","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }*/
    
    //Metodo principal
    public static void main(String[] args) {
        Criptografia main=new Criptografia();
    }
    
}
