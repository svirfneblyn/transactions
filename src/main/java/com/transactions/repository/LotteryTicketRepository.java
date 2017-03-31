package com.transactions.repository;

import com.transactions.entity.LotteryTicket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryTicketRepository extends CrudRepository<LotteryTicket, Long> {
   @Query(value = "select * from lottery_tickets where buyer_id is null limit 1", nativeQuery = true)
   LotteryTicket getNotSoldedTicket();
}
