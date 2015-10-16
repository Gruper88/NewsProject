package by.degtev.news.services;


import by.degtev.news.dao.IUserDetailsDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDetailsService implements IUserDetailsService {
    final static Logger LOGGER = Logger.getLogger(UserDetailsService.class);

    @Autowired
    private IUserDetailsDao userDetailsDao;

}
