package by.epam.carsharing.controller;

import by.epam.carsharing.controller.command.CommandFactory;
import by.epam.carsharing.exception.ServiceException;
import by.epam.carsharing.service.NewsService;
import by.epam.carsharing.service.ServiceFactory;
import by.epam.carsharing.util.RequestParameter;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final CommandFactory factory = new CommandFactory();
    private static final String IMAGE_EDITOR_PART = "image_editor";
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String IMAGES_DIRECTORY_NAME = "images";
    private static final String UPLOAD_DIR = "D:\\java_projects\\jwd_final_project\\Carsharing\\src\\main\\webapp\\"
            + IMAGES_DIRECTORY_NAME;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO: add Filter
        request.setCharacterEncoding("UTF-8");


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
                    int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
                    String header = request.getParameter(RequestParameter.HEADER_EDITOR);
                    String content = request.getParameter(RequestParameter.CONTENT_EDITOR);

                    NewsService newsService = serviceFactory.getNewsService();
                    newsService.update(id, header, content, imagePath);
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
}
