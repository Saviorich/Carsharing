package by.epam.carsharing.controller;

import by.epam.carsharing.controller.command.CommandFactory;
import by.epam.carsharing.controller.command.CommandName;
import by.epam.carsharing.entity.News;
import by.epam.carsharing.entity.user.User;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.NewsService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import by.epam.carsharing.util.SessionAttribute;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/Upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {


    private static final Logger logger = LogManager.getLogger(FileUploadingServlet.class);
    private static final NewsService newsService = ServiceFactory.getInstance().getNewsService();
    private static final CommandFactory factory = new CommandFactory();
    private static final String IMAGE_EDITOR_PART = "image_editor";
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String IMAGES_DIRECTORY_NAME = "images";
    private static final String UPLOAD_DIR = "D:\\java_projects\\jwd_final_project\\Carsharing\\src\\main\\webapp\\"
            + IMAGES_DIRECTORY_NAME;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            Part part = request.getPart(IMAGE_EDITOR_PART);
            String filename = part.getSubmittedFileName();
            String upload_path = UPLOAD_DIR + File.separator + filename;

            String imagePath = IMAGES_DIRECTORY_NAME + File.separator + filename;
            ;
            if (Files.exists(Paths.get(upload_path + filename).toAbsolutePath())) {
                imagePath = request.getParameter(RequestParameter.IMAGE_PATH);
            }

            boolean isSuccess;
            try (InputStream inputStream = part.getInputStream()) {
                isSuccess = uploadFile(inputStream, upload_path);
            }
            if (isSuccess) {
                try {
                    String header = request.getParameter(RequestParameter.HEADER_EDITOR);
                    String content = request.getParameter(RequestParameter.CONTENT_EDITOR);

                    String command = request.getParameter(RequestParameter.COMMAND);
                    logger.log(Level.DEBUG, command);
                    if (command.equals(CommandName.ADDNEWS.toString().toLowerCase())) {
                        HttpSession session = request.getSession();
                        int userId = ((User)session.getAttribute(SessionAttribute.USER)).getId();
                        addNews(userId, header, content, imagePath);
                    } else {
                        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
                        updateNews(id, header, content, imagePath);
                    }
                } catch (ServiceException e) {
                    throw new ServletException(e);
                }
            }
        }
        response.sendRedirect(GO_TO_NEWS_PAGE);
    }

    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            int result = inputStream.read(bytes);
            if (result != -1) {
                try (FileOutputStream fops = new FileOutputStream(path)) {
                    fops.write(bytes);
                }
            }
        } catch (IOException e) {
            throw new ServletException(e);
        }
        return true;
    }

    private void updateNews(int id, String header, String content, String imagePath) throws ServiceException {
        newsService.update(id, header, content, imagePath);
    }

    private void addNews(int userId, String header, String content, String imagePath) throws ServiceException {
        News news = new News(userId, header, content, imagePath);
        newsService.add(news);
    }
}
