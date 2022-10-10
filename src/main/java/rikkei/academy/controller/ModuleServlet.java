package rikkei.academy.controller;

import rikkei.academy.model.BaiDoc;
import rikkei.academy.model.Module;
import rikkei.academy.service.modul.ModuleServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/Module")
public class ModuleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ModuleServiceIMPL moduleServiceIMPL;

    public void init() {
        moduleServiceIMPL = new ModuleServiceIMPL();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "newMD":
                    FormShowList(request, response);
                    break;
                case "insertMD":
                    FormInsertModule(request, response);
                    break;
                case "deleteMD":
                    deleteModule(request, response);
                    break;
                case "editMD":
                    FormEditModule(request, response);
                    break;
                case "updateMD":
                    FormUpdateModule(request, response);
                    break;
                default:
                    listModule(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Module> modules = moduleServiceIMPL.selectAllModule();
        request.setAttribute("listModule", modules);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewmodule/module-list.jsp");
        dispatcher.forward(request, response);
    }

    private void FormShowList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewmodule/module-form.jsp");
        dispatcher.forward(request, response);
    }

    private void FormEditModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Module exModule = moduleServiceIMPL.selectModule(id);
        request.setAttribute("Module", exModule);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewmodule/module-form.jsp");
        dispatcher.forward(request, response);

    }
    private void FormInsertModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name_module");
        Module newMD =new Module(name);
        moduleServiceIMPL.insertModule(newMD);
        listModule(request, response);


    }
    private void FormUpdateModule(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name_module");
        Module list = new Module(id,name);
        moduleServiceIMPL.updateModule(list);
        listModule(request, response);
    }
    private void deleteModule(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        moduleServiceIMPL.deleteModule(id);
        response.sendRedirect("/Module");
    }
}