package rikkei.academy.service.baidoc;

import rikkei.academy.model.BaiDoc;
import rikkei.academy.model.Module;

import java.util.List;

public interface IBaiDocService {
    List<BaiDoc> findByBaiDoc(int id);
}
