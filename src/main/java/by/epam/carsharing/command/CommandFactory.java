package by.epam.carsharing.command;

import by.epam.carsharing.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandFactory() {
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPage());
        commands.put(CommandName.GOTONEWSPAGE, new GoToNewsPage());
        commands.put(CommandName.GOTOREGISTERPAGE, new GoToRegisterPage());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.SIGNOUT, new SignOutCommand());
        commands.put(CommandName.GOTOCARSPAGE, new GoToCarPage());
        commands.put(CommandName.CHANGELANG, new ChangeLanguageCommand());
        commands.put(CommandName.GOTONEWSEDITPAGE, new GoToNewsEditPage());
        commands.put(CommandName.EDITNEWS, new EditNewsCommand());
    }

    public Command takeCommand(String command) {
        return commands.get(CommandName.valueOf(command.toUpperCase()));
    }
}
