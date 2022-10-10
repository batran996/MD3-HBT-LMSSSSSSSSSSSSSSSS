package rikkei.academy.service.module;

import rikkei.academy.model.Module;

import java.sql.SQLException;
import java.util.List;

public interface IModuleService {
     void insertModule(Module module);
     Module selectModule(int id);
     List<Module> selectAllModule();
     boolean deleteModule(int id) throws SQLException;
     boolean updateModule(Module moDule) throws SQLException;

}
