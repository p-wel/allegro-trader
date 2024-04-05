package com.pwel.allegrotrader.api.finder.infrastructure;

import com.pwel.allegrotrader.api.finder.domain.SearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<SearchCriteria, Long> {

}
