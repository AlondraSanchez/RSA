package codigo;

import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSA {

    public RSA() {
         Security.addProvider(new BouncyCastleProvider());
    }
    
    public void generarLLaves(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
            keyGen.initialize(1024);
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivada = clavesRSA.getPrivate(); 
            PublicKey clavePublica = clavesRSA.getPublic();
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public byte[] cifrarLLavePublica(byte[] message, PublicKey clavePublica ){
        byte[] bufferCifrado = null;
        try {
            Cipher cifrador = Cipher.getInstance("RSA", "BC");
            try {
                cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bufferCifrado = cifrador.doFinal(message);
            } catch (IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarBytes(bufferCifrado);
            return bufferCifrado;
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bufferCifrado;
    }
    
    public void decifrarLLavePrivada(byte[] cipherText,PrivateKey clavePrivada){
        byte[] bufferPlano2 = null;
        try {
            Cipher cifrador = Cipher.getInstance("RSA", "BC");
            try {
                cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bufferPlano2 = cifrador.doFinal(cipherText);
            } catch (IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarBytes(bufferPlano2);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public byte[] cifrarLLavePrivada(byte[] plainText, PrivateKey clavePrivada){
        byte[] bufferPlano2 = null;
        try {
            Cipher cifrador = Cipher.getInstance("RSA", "BC");
            try {
                cifrador.init(Cipher.ENCRYPT_MODE, clavePrivada);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bufferPlano2 = cifrador.doFinal(plainText);
            } catch (IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarBytes(bufferPlano2);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bufferPlano2;
    }
    
    public void decifrarLLavePublica(byte[] cipherText,PublicKey clavePublica){
        byte[] bufferPlano2 = null;
        try {
            Cipher cifrador = Cipher.getInstance("RSA", "BC");
            try {
                cifrador.init(Cipher.DECRYPT_MODE, clavePublica);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bufferPlano2 = cifrador.doFinal(cipherText);
            } catch (IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarBytes(bufferPlano2);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] leerLinea(java.io.InputStream in) throws IOException {
        byte[] buffer1 = new byte[1000];
        int i = 0;
        byte c;
        c = (byte) in.read();
        while ((c != '\n') && (i < 1000)) {
            buffer1[i] = c;
            c = (byte) in.read();
            i++;
        }
        byte[] buffer2 = new byte[i];
        System.arraycopy(buffer1, 0, buffer2, 0, i);
        return (buffer2);
    }

    public void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
}
