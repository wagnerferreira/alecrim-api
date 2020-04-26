package com.alecrim.repository;

import com.alecrim.entity.LogImportCsv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogImportRepository extends CrudRepository<LogImportCsv, Long> {

    LogImportCsv findByFile(String fileName);

}