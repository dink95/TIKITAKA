package com.tiki.product.controller;

import com.tiki.product.domain.CategoryDTO;
import com.tiki.product.domain.InsertProdDTO;
import com.tiki.product.domain.ProductDTO;
import com.tiki.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController

public class ProductController {

    @Autowired
    private ProductService productService;

    // 상위 카테고리일때 마지막 카테고리 반환하는 함수
    public int finishCatNo(int catNo) {

        int finishNo = 0;
        int[] highCatNo = {10, 20, 30, 40};
        boolean check = false;

        for(int i = 0; i < 4; i++){
            if (catNo == highCatNo[i]) {
                check = true;
                break;
            }
        }

        if(check) {
            switch(catNo) {
                case 10:
                    finishNo = 19;
                    break;
                case 20:
                    finishNo = 26;
                    break;
                case 30:
                    finishNo = 36;
                    break;
                case 40:
                    finishNo = 47;
                    break;
                default:
                    System.out.println("잘못된 접근입니다.");
            }
        }
        else {
            finishNo = catNo;
        }

        return finishNo;
    }

//    // 룸카운트 테스트
//    @GetMapping(value = "/roomcount/{prodNo}")
//    public int roomCount(@PathVariable("prodNo") int prodNo) {
//
//        return  productService.selectRoomCount(prodNo);
//    }


    // 상품 디테일 보기
    @GetMapping("/prd/{prodNo}")
    public ProductDTO selectProductByProdNo(@PathVariable("prodNo") int prodNo) {
        return productService.selectProductDetail(prodNo);
    }

    // 상품 전체 리스트 보기
    @GetMapping(value = "/prd/list")
    public List productList() {

        List<ProductDTO> productList = productService.getProductList();

        return productList;
    }

    // 검색 결과 보기
    @GetMapping(value = "/prd/result/{prodNm}/{catNo}")
    public List resultList(@PathVariable("prodNm") String prodNm,
                           @PathVariable("catNo") int catNo) {

        ProductDTO productDTO = new ProductDTO();

        int finishNo = finishCatNo(catNo);

        productDTO.setProdNm(prodNm);
        productDTO.setCatNo(catNo);
        productDTO.setNego(finishNo);

        List<ProductDTO> productList = productService.resultByNmCat(productDTO);

        return productList;
    }

    // 검색 결과 보기 높은 가격순
    @GetMapping(value = "/prd/result/highprice/{prodNm}/{catNo}")
    public List resultListHighPrice(@PathVariable("prodNm") String prodNm,
                           @PathVariable("catNo") int catNo) {

        ProductDTO productDTO = new ProductDTO();

        int finishNo = finishCatNo(catNo);

        productDTO.setProdNm(prodNm);
        productDTO.setCatNo(catNo);
        productDTO.setNego(finishNo);

        List<ProductDTO> productList = productService.resultByNmCatHighPrice(productDTO);

        return productList;
    }

    // 검색 결과 보기 낮은 가격순
    @GetMapping(value = "/prd/result/lowprice/{prodNm}/{catNo}")
    public List resultListLowPrice(@PathVariable("prodNm") String prodNm,
                           @PathVariable("catNo") int catNo) {

        ProductDTO productDTO = new ProductDTO();

        int finishNo = finishCatNo(catNo);

        productDTO.setProdNm(prodNm);
        productDTO.setCatNo(catNo);
        productDTO.setNego(finishNo);

        List<ProductDTO> productList = productService.resultByNmCatLowPrice(productDTO);

        return productList;
    }

    // 검색 결과 보기 상품 이름으로
    @GetMapping(value = "/prd/result/prodNm/{prodNm}")
    public List resultProdNameList(@PathVariable("prodNm") String prodNm) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProdNm(prodNm);
        List<ProductDTO> productList = productService.resultByProdNm(prodNm);

        return productList;
    }

    // 검색 결과 보기 상품 이름으로 높은 가격순
    @GetMapping(value = "/prd/result/prodNm/highPrice/{prodNm}")
    public List resultProdNameListHighPrice(@PathVariable("prodNm") String prodNm) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProdNm(prodNm);
        List<ProductDTO> productList = productService.resultByProdNmHighPrice(prodNm);

        return productList;
    }

    // 검색 결과 보기 상품 이름으로 낮은 가격순
    @GetMapping(value = "/prd/result/prodNm/lowPrice/{prodNm}")
    public List resultProdNameListLowPrice(@PathVariable("prodNm") String prodNm) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProdNm(prodNm);
        List<ProductDTO> productList = productService.resultByProdNmLowPrice(prodNm);

        return productList;
    }


    // 검색 결과 보기 판매자 이름으로
    @GetMapping(value = "/prd/result/selId/{selId}")
    public List resultSelIdList(@PathVariable("selId") String selId) {
        List<ProductDTO> productList = productService.resultById(selId);

        return productList;
    }

    // 검색 결과 보기 판매자 이름으로(판매완료)
    @GetMapping(value = "/prd/result/selId/finish/{selId}")
    public List resultSelIdListFinish(@PathVariable("selId") String selId) {
        List<ProductDTO> productList = productService.resultByIdFinish(selId);

        return productList;
    }

    // 검색 결과 보기 카테코리 번호로
    @GetMapping(value = "/prd/result/catNo/{catNo}")
    public List resultList(@PathVariable("catNo") int catNo) {

        List<ProductDTO> productList;

        int finishNo = finishCatNo(catNo);

        productList = productService.resultByCatNo(catNo, finishNo);

        if(productList == null) {
            System.out.println("에러가 나왔으니 확인바람");
        }

        return productList;
    }

    // 검색 결과 보기 카테코리 번호로 높은 가격순
    @GetMapping(value = "/prd/result/catNo/highPrice/{catNo}")
    public List resultListHighPrice(@PathVariable("catNo") int catNo) {

        List<ProductDTO> productList;

        int finishNo = finishCatNo(catNo);

        productList = productService.resultByCatNoHighPrice(catNo, finishNo);

        if(productList == null) {
            System.out.println("에러가 나왔으니 확인바람");
        }

        return productList;
    }

    // 검색 결과 보기 카테코리 번호로 높은 가격순
    @GetMapping(value = "/prd/result/catNo/lowPrice/{catNo}")
    public List resultListLowPrice(@PathVariable("catNo") int catNo) {

        List<ProductDTO> productList;

        int finishNo = finishCatNo(catNo);

        productList = productService.resultByCatNoLowPrice(catNo, finishNo);

        if(productList == null) {
            System.out.println("에러가 나왔으니 확인바람");
        }

        return productList;
    }

    // 등록된 상품 삭제하기
    @DeleteMapping("/prd/{prodNo}/{selId}")
    public int deleteMember(@PathVariable("prodNo") int prodNo,
                            @PathVariable("selId") String selId ) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProdNo(prodNo);
        productDTO.setSelId(selId);
        return productService.deleteProduct(productDTO);
    }

    // 등록된 상품 업데이트
    @PatchMapping("/prd/update")
    public int updateProductRegister(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }

    // 판매완료 상품 업데이트
    @PatchMapping("/prd/prodfinish")
    public int updateProductFinish(@RequestBody ProductDTO productDTO) {
        return productService.updateProductFinish(productDTO);
    }

    // 상품 등록하기
    @PostMapping("/prd")
    public int insertProduct(@RequestBody InsertProdDTO insertProdDTO) {

        ProductDTO productDTO = new ProductDTO(insertProdDTO.getProdNm(), insertProdDTO.getProdPrc(),
                insertProdDTO.getCatNo(),insertProdDTO.getSelId(),insertProdDTO.getWay(),insertProdDTO.getNego()
        ,insertProdDTO.getProdCo(), insertProdDTO.getContent(), insertProdDTO.getImgCount(), insertProdDTO.getRoomCount());

        productService.insertProduct(productDTO);

        return  productService.selectProductNo(productDTO);
    }

}
