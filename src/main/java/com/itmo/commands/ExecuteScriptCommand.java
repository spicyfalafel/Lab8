package com.itmo.commands;

import com.itmo.app.Application;
import com.itmo.client.User;
import com.itmo.utils.LocaleClass;

/**
 * The type Execute script command.
 */
public class ExecuteScriptCommand extends Command {

    public ExecuteScriptCommand(String[] args) {
        super(args);
    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 1;
    }

    @Override
    public void clientInsertionFromConsole() {
        /*try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            System.out.println("Запуск скрипта " + args[0]);
            while ((line = reader.readLine()) != null) {
            *//*    Command c = Client.getCommandFromString(line);
                if (c != null) {
                    FieldsScanner scanner = FieldsScanner.getInstance();
                    scanner.configureScanner(new Scanner(reader));
                    c.clientInsertion();
                    scanner.configureScanner(new Scanner(System.in));
                    byte[] serializedCommand = SerializationManager.writeObject(c);
                    Client.sendOneByte();
                    Client.getSocket().getOutputStream().write(serializedCommand);
            *//*        //Client.getAns(); //TODO
                    //sendCommandToServer(command);
                    //String answer = getAnswerFromServer();
                    //setUserNameAfterRegistering(answer);
                    //checkExitCommand(command);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("капут");*/
       // } catch (ClassNotFoundException e) {
       //     e.printStackTrace();
     /*   }catch (StackOverflowError e){
            System.out.println("Стэк переполнен!");
        }
*/
    }

    @Override
    public String execute(Application application, User user) {
        return LocaleClass.getString("script_done.text");
    }

    @Override
    public String getDescription() {
        return LocaleClass.getString("execute_script_description.text");
    }
}
