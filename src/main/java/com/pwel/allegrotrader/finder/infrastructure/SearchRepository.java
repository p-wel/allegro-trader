package com.pwel.allegrotrader.finder.infrastructure;

import com.pwel.allegrotrader.finder.domain.SearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<SearchCriteria, Long> {

}
