package by.epam.carsharing.controller.command;

import by.epam.carsharing.controller.command.impl.*;
import by.epam.carsharing.controller.command.impl.car.AddCarCommand;
import by.epam.carsharing.controller.command.impl.car.DeleteCarCommand;
import by.epam.carsharing.controller.command.impl.car.EditCarCommand;
import by.epam.carsharing.controller.command.impl.car.GoToCarPage;
import by.epam.carsharing.controller.command.impl.news.AddNewsCommand;
import by.epam.carsharing.controller.command.impl.news.DeleteNewsCommand;
import by.epam.carsharing.controller.command.impl.news.EditNewsCommand;
import by.epam.carsharing.controller.command.impl.news.GoToNewsPage;
import by.epam.carsharing.controller.command.impl.order.GoToOrderPage;
import by.epam.carsharing.controller.command.impl.order.GoToUsersOrdersPage;
import by.epam.carsharing.controller.command.impl.order.MakeOrderCommand;

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
        commands.put(CommandName.GOTONEWSEDITPAGE, new GoToEditPage());
        commands.put(CommandName.GOTOCAREDITPAGE, new GoToEditPage());
        commands.put(CommandName.ADDCAR, new AddCarCommand());
        commands.put(CommandName.EDITCAR, new EditCarCommand());
        commands.put(CommandName.DELETECAR, new DeleteCarCommand());
        commands.put(CommandName.ADDNEWS, new AddNewsCommand());
        commands.put(CommandName.DELETENEWS, new DeleteNewsCommand());
        commands.put(CommandName.EDITNEWS, new EditNewsCommand());
        commands.put(CommandName.GOTOORDERPAGE, new GoToOrderPage());
        commands.put(CommandName.GOTOORDERSPAGE, new GoToUsersOrdersPage());
        commands.put(CommandName.MAKEORDER, new MakeOrderCommand());
    }

    public Command takeCommand(String command) {
        return commands.get(CommandName.valueOf(command.toUpperCase()));
    }
}
