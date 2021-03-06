package Project.DAO;

import Project.Model.Url;

public interface UrlDAO {

    public Long add(Url url);

    public Url getById(long id);

    public Long getCount();

    public Url getByTinyUrl(String tinyUrl);
}
