package by.epam.carsharing.controller.command;

import by.epam.carsharing.controller.command.impl.*;
import by.epam.carsharing.controller.command.impl.car.*;
import by.epam.carsharing.controller.command.impl.comment.DeleteComment;
import by.epam.carsharing.controller.command.impl.comment.GoToCarComment;
import by.epam.carsharing.controller.command.impl.comment.LeaveComment;
import by.epam.carsharing.controller.command.impl.news.AddNewsCommand;
import by.epam.carsharing.controller.command.impl.news.DeleteNewsCommand;
import by.epam.carsharing.controller.command.impl.news.EditNewsCommand;
import by.epam.carsharing.controller.command.impl.news.GoToNewsPage;
import by.epam.carsharing.controller.command.impl.order.ChangeOrderStatusCommand;
import by.epam.carsharing.controller.command.impl.order.GoToOrderPage;
import by.epam.carsharing.controller.command.impl.order.GoToUserOrderPage;
import by.epam.carsharing.controller.command.impl.order.MakeOrderCommand;
import by.epam.carsharing.controller.command.impl.payment.GoToPaymentPage;
import by.epam.carsharing.controller.command.impl.payment.MakePayment;

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
        commands.put(CommandName.GOTOCARCOMMENTSPAGE, new GoToCarComment());
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
        commands.put(CommandName.GOTOORDERSPAGE, new GoToUserOrderPage());
        commands.put(CommandName.MAKEORDER, new MakeOrderCommand());
        commands.put(CommandName.CHANGEORDERSTATUS, new ChangeOrderStatusCommand());
        commands.put(CommandName.GOTOPAYMENTPAGE, new GoToPaymentPage());
        commands.put(CommandName.MAKEPAYMENT, new MakePayment());
        commands.put(CommandName.LEAVECOMMENT, new LeaveComment());
        commands.put(CommandName.DELETECOMMENT, new DeleteComment());
    }

    public Command takeCommand(String command) {
        return commands.get(CommandName.valueOf(command.toUpperCase()));
    }
}
