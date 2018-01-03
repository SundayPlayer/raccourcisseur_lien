package Project.Service;

import Project.Model.Url;

public interface UrlService {
    public long add(Url user);

    public Url getById(long id);
}
