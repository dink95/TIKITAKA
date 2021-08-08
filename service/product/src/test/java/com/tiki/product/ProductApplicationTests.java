package com.tiki.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiki.product.domain.ProductDTO;
import com.tiki.product.mapper.ProductMapper;
import com.tiki.product.service.ProductService;

import com.tiki.product.domain.ChatDTO;
import com.tiki.product.mapper.ChatMapper;
import com.tiki.product.service.ChatService;

import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private ChatService chatService;

    @Test
    public void testOfselectChat() {
        int chatTotalCount = chatMapper.selectChatTotalCount();
        if(chatTotalCount > 0) {
            List<ChatDTO> chatList = chatService.selectExistChatList("test");
            if(CollectionUtils.isEmpty(chatList) == false) {
                for(ChatDTO chat : chatList) {
                    System.out.println("==============");
                    System.out.println(chat.getChatIdx());
                    System.out.println(chat.getRoomNo());
                    System.out.println(chat.getProdNo());
                    System.out.println(chat.getContent());
                    System.out.println(chat.getSendtime());
                    System.out.println("==============");
                }
            }
        }
    }

    /**@Test
    public void testOfChatInsert() {

        ChatDTO params = new ChatDTO();
        params.setProdNo(4);
        params.setRecipientId("user");
        params.setSendId("test");
        params.setContent("제발 222 좀 되라 222");

        int result = chatMapper.insertChat(params);

        System.out.println("roomNo : " + params.getRoomNo());
        System.out.println("결과는 " + result + " 입니다.");
    }*/

    /**@Test
    public void testOfupdateRoomCount() {

        ///int result = productMapper.updateView(1);

        ChatDTO params = new ChatDTO();
        params.setProdNo(1);
        params.setRecipientId("user");
        params.setSendId("asd");

        Integer result = chatService.findRoomNo(params);
        int roomNo = 0;

        if(result == null)
            System.out.println("roomNo : " + roomNo);
        else {
            roomNo = result.intValue();
            System.out.println("roomNo : " + roomNo);
        }

    }*/

    /**@Test
    public void testOfSelectDetail() {
        int productTotalCount = productMapper.selectProductTotalCount();
        if(productTotalCount > 0) {
            List<ProductDTO> productList = productService.resultByCatNo(10,19);
            if(CollectionUtils.isEmpty(productList) == false) {
                for(ProductDTO product : productList) {
                    System.out.println("==============");
                    System.out.println(product.getProdNm());
                    System.out.println(product.getSelId());
                    System.out.println(product.getProdPrc());
                    System.out.println("==============");
                }
            }
        }
    }*/

}
