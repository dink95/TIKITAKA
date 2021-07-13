package com.tiki.auction;

import com.tiki.auction.domain.AuctionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuctionApplicationTests {

    @Test
    void contextLoads() {
        AuctionDTO dto = new AuctionDTO();
        dto.setMbrId("dink95");
        dto.setProdNo(1);

    }

}
