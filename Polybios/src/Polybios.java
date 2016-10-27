import javax.swing.JOptionPane;

public class Polybios {
    public static void main(String[] args) {
        int opc=Integer.parseInt(JOptionPane.showInputDialog("Elige una opcion:\n1.Encriptar\n2.Desencriptar"));
        char[][]matriz=new char[5][5];
        matriz[0][0]='a';
        matriz[0][1]='b';
        matriz[0][2]='c';
        matriz[0][3]='d';
        matriz[0][4]='e';
        matriz[1][0]='f';
        matriz[1][1]='g';
        matriz[1][2]='h';
        matriz[1][3]='1';
        matriz[1][4]='k';
        matriz[2][0]='l';
        matriz[2][1]='m';
        matriz[2][2]='2';
        matriz[2][3]='o';
        matriz[2][4]='p';
        matriz[3][0]='q';
        matriz[3][1]='r';
        matriz[3][2]='s';
        matriz[3][3]='t';
        matriz[3][4]='u';
        matriz[4][0]='v';
        matriz[4][1]='w';
        matriz[4][2]='x';
        matriz[4][3]='y';
        matriz[4][4]='z';
        char letras[]=new char[5];
        letras[0]='A';
        letras[1]='B';
        letras[2]='C';
        letras[3]='D';
        letras[4]='E';
        if(opc==1){
            String palabra=JOptionPane.showInputDialog("Ingresa una palabra a encriptar").toLowerCase();
            String encriptada="";
            boolean encontrada=false;
            for(int a=0;a<palabra.length();a++){
                if (palabra.charAt(a) == 'i' || palabra.charAt(a) == 'j') {
                    encriptada = encriptada + letras[1] + letras[3];
                    encontrada=true;
                }
                if (palabra.charAt(a) == 'n' || palabra.charAt(a) == 'ñ') {
                    encriptada = encriptada + letras[2] + letras[2];
                    encontrada=true;
                }
                if(encontrada==false){
                    for(int b=0;b<5;b++){
                        for(int c=0;c<5;c++){
                            if(palabra.charAt(a)==matriz[b][c]){
                                encriptada=encriptada+letras[b]+letras[c];
                            }
                        }
                    }
                }
                encontrada=false;
            }
            JOptionPane.showMessageDialog(null, "La palabra ya encriptada es: "+encriptada);
        }
        if(opc==2){
            String palabra=JOptionPane.showInputDialog("Ingresa una palabra a desencriptar").toUpperCase();
            String desencriptada="";
            int x=0;
            int y=0;
            for(int a=0;a<palabra.length();a++){ 
                for(int b=0;b<5;b++){
                    if(a%2==0){
                        if(palabra.charAt(a)==letras[b]){
                            x=b;
                        }
                    }
                    if(a%2!=0){
                        if(palabra.charAt(a)==letras[b]){
                            y=b;
                            desencriptada=desencriptada+matriz[x][y];
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "La palabra ya desencriptada es (1=i/j  2=n/ñ): "+desencriptada);
        }
    }
}
