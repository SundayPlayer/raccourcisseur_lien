package Project.Service.impl;

import Project.DAO.UrlDAO;
import Project.Model.Url;
import Project.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlDAO urlDAO;

    @Override
    public long add(Url url) {
        return urlDAO.add(url);
    }

    @Override
    public Url getById(long id) {
        return urlDAO.getById(id);
    }

    @Override
    public Url getByTinyUrl(String tinyUrl) {
        return urlDAO.getByTinyUrl(tinyUrl);
    }
}
