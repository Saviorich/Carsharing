package by.epam.carsharing;

import by.epam.carsharing.command.Command;
import by.epam.carsharing.command.CommandFactory;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    private final CommandFactory factory = new CommandFactory();

    public Controller() {super();}

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        Command command;

        name = request.getParameter("command");
        command = factory.takeCommand(name);

        command.execute(request, response);
    }

    public void destroy() {
    }
}