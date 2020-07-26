package com.itmo.client;

import com.itmo.commands.*;
import com.itmo.exceptions.NoSuchCommandException;
import com.itmo.exceptions.WrongArgumentsNumberException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MyConsole {
    private static CommandsInvoker invoker;
    private static final BufferedReader systemIn
            = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    MyConsole(){
        registerCommands();
    }

    public Command getFullCommandFromConsole(){
        Command command = null;
        try {
            command = scanCommandFromConsole();
            command.clientInsertionFromConsole();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return command;
    }

    private boolean checkExitCommand(Command command){
        return command instanceof ExitCommand;
    }

    private Command scanCommandFromConsole() throws IOException {
        Command command = null;
        String line;
        do{
            if((line = systemIn.readLine()) != null){
                command = getCommandFromString(line);
            }
        }while(command==null);
        return command;
    }

    public static Command getCommandFromString(String command){
        String[] splitted = command.split(" ");
        String commandName = splitted[0];
        String[] arguments = new String[splitted.length - 1];
        System.arraycopy(splitted, 1, arguments, 0, splitted.length - 1);
        try {
            return invoker.validateCommand(commandName, arguments);
        } catch (WrongArgumentsNumberException | NoSuchCommandException e) {
            return null;
        }
    }

    private void registerCommands(){
        invoker = CommandsInvoker.getInstance();
        invoker.register("info", new InfoCommand( null));
        invoker.register("help", new HelpCommand(null));
        invoker.register("exit", new ExitCommand(null));
        invoker.register("clear", new ClearCommand(null));
        invoker.register("remove_by_id", new RemoveByIdCommand(null));
        invoker.register("add", new AddElementCommand());
        invoker.register("show", new ShowCommand(null));
        invoker.register("update", new UpdateByIdCommand(null));
        invoker.register("filter_starts_with_name", new FilterStartsWithNameCommand(null));
        invoker.register("add_if_max", new AddIfMaxCommand(null));
        invoker.register("add_if_min", new AddIfMinCommand(null));
        invoker.register("remove_lower", new RemoveLowerThanElementCommand(null));
        invoker.register("print_field_ascending_wingspan", new PrintFieldAscendingWingspanCommand(null));
        invoker.register("print_descending", new PrintDescendingCommand(null));
        invoker.register("execute_script", new ExecuteScriptCommand(null));
        invoker.register("login", new LoginCommand());
        invoker.register("register", new RegisterCommand());
    }
}