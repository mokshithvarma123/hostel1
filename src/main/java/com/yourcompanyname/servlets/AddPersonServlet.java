package com.yourcompanyname.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.yourcompanyname.model.Person;
import com.yourcompanyname.util.PersonDAO;

@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Part documentPart = request.getPart("document");
        Part photoPart = request.getPart("photo");
        
        // Specify the directory where you want to store the files
        String uploadDir = "uploads"; // Change this to your chosen directory
        String contextPath = request.getServletContext().getRealPath("");
        String uploadPath = contextPath + File.separator + uploadDir;
        File uploadDirFile = new File(uploadPath);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Process and store the document file
        String documentFileName = Paths.get(documentPart.getSubmittedFileName()).getFileName().toString();
        String documentPath = uploadPath + File.separator + documentFileName;
        documentPart.write(documentPath);

        // Process and store the photo file
        String photoFileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
        String photoPath = uploadPath + File.separator + photoFileName;
        photoPart.write(photoPath);

        // Create a Person object with the specified paths
        Person person = new Person(name, email, documentPath, photoPath);
        PersonDAO.addPerson(person);

        response.sendRedirect("personDetails.jsp");
    }
}