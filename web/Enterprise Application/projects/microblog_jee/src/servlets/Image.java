package servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Image extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String chemin = request.getParameter("src");
        String nameImage = chemin.substring(chemin.lastIndexOf("/") + 1);
        String extension = nameImage.substring(nameImage.lastIndexOf(".") + 1);
        File f = new File(chemin);
        if (f.exists()) {
            BufferedImage img = ImageIO.read(f);
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            ImageIO.write(img, extension, bas);
            byte[] data = bas.toByteArray();
            response.setContentType(getServletContext().getMimeType(nameImage));
            response.setContentLength(data.length);
            response.getOutputStream().write(data);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}