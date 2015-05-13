package com.swiftcorp.portal.common.file.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.file.dto.File;

public class FileDAOImpl extends HibernateDaoSupport implements IFileDAO
{

    /**
     * 
     * @param id
     * @return
     */
    public File load(Long id) {
        return (File) getHibernateTemplate().get(File.class, id);
    }

    /**
     * 
     * @param file
     * @return
     */
    public void save(File file) {
        getHibernateTemplate().save(file);
    }

}
