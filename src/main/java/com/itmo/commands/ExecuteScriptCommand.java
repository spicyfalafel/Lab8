package com.itmo.commands;

import com.itmo.client.MyConsole;
import com.itmo.server.Application;
import com.itmo.app.UIApp;
import com.itmo.client.User;
import com.itmo.server.ServerMain;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The type Execute script command.
 */
@NoArgsConstructor
public class ExecuteScriptCommand extends Command {
    private File fileToExecute;
    @Getter
    private String result;
    public ExecuteScriptCommand(File file){
        this.fileToExecute = file;
        StringBuilder builder = new StringBuilder();
        try {
            MyConsole console = new MyConsole(new FileInputStream(file));
            Command command = console.getFullCommandFromConsole();
            while(command!=null){
                UIApp.getClient().sendCommandToServer(command);
                String ans = UIApp.getClient().getAnswerFromServer();
                builder.append(ans).append("\n");
                command = console.getFullCommandFromStream();
            }
            result = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ExecuteScriptCommand(String[] args) {
        super(args);

    }

    @Override
    public int getNumberOfRequiredArgs() {
        return 1;
    }

    @Override
    public void clientInsertionFromConsole() {
        fileToExecute = new File(args[0]);
    }

    @Override
    public String execute(Application application, User user) {
        return ServerMain.localeClass.getString("script_done.text");
    }

    @Override
    public String getDescription() {
        return UIApp.localeClass.getString("execute_script_description.text");
    }
}
