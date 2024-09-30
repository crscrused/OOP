import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class MyChat {
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    File chat = new File("chat.txt");
    while(true){
        try{
            if(!chat.exists()){
            System.out.println("We had to make a new file.");
            chat.createNewFile();
            }
            FileWriter writer = new FileWriter(chat, true);
            String chats = scanner.nextLine();
            if(chats.equalsIgnoreCase("exit")){break;}
            writer.write(chats + "\n");
            writer.close();
    }catch(IOException e){
            e.printStackTrace();
        } 
    }
            scanner.close();
    }
}