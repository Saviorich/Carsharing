package by.epam.carsharing.command.impl;

import by.epam.carsharing.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.DEBUG, request.getParameter("image_editor"));
        logger.log(Level.DEBUG, request.getParameter("content_editor"));
        logger.log(Level.DEBUG, request.getParameter("header_editor"));
        logger.log(Level.DEBUG, request.getParameter("command"));
    }
}
