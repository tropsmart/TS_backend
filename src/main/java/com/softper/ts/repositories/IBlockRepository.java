package com.softper.ts.repositories;

import com.softper.ts.models.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlockRepository extends JpaRepository<Block, Integer> {
    @Query("select b from Block b where b.user.id = (:uid)")
    List<Block> findBlockedsByUserId(@Param("uid")int userId);

    @Query("select b from Block b where b.user.id = (:uid) and b.blocked.id = (:bid)")
    Block findBlockByUserAndBlockedId(@Param("uid")int userId, @Param("bid")int blockedId);
}
