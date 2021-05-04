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
        productDTO.setProdNm(prodNm);
        productDTO.setCatNo(catNo);

        List<ProductDTO> productList = productService.resultByNmCat(productDTO);

        return productList;
    }

    // 검색 결과 보기 상품 이름으로
    @GetMapping(value = "/prd/result/{prodNm}")
    public List resultProdNameList(@PathVariable("prodNm") String prodNm) {

        List<ProductDTO> productList = productService.resultByProdNm(prodNm);

        return productList;
    }

    // 검색 결과 보기 판매자 이름으로
    @GetMapping(value = "/prd/result/{selId}")
    public List resultSelIdList(@PathVariable("selId") String selId) {

        List<ProductDTO> productList = productService.resultById(selId);

        return productList;
    }

    // 검색 결과 보기 카테코리 번호로
    @GetMapping(value = "/prd/result/{catNo}")
    public List resultList(@PathVariable("catNo") int catNo) {

        List<ProductDTO> productList = new ArrayList();
        List<ProductDTO> finalList = new ArrayList();
        List<ProductDTO> tempList = null;

        int[] highCatNo = {10, 20, 30, 40};
        boolean check = true;

        for(int i = 0; i < 4; i++){
            if (catNo == highCatNo[i]) {
                check = false;
                break;
            }
        }

        if(check) {
            productList = productService.resultByCatNo(catNo);
        }
        else {
            int finishCat = 0;

            switch(catNo) {
                case 10:
                    finishCat = 19;
                    break;
                case 20:
                    finishCat = 26;
                    break;
                case 30:
                    finishCat = 36;
                    break;
                case 40:
                    finishCat = 47;
                    break;
                default:
                    System.out.println("잘못된 접근입니다.");
            }

            for(catNo = catNo + 1; catNo <= finishCat; catNo++) {
                tempList = productService.resultByCatNo(catNo);
                finalList.addAll(tempList);
            }

            productList = finalList;
        }

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

    // 상품 등록하기
    @PostMapping("/prd")
    public int insertProduct(@RequestBody InsertProdDTO insertProdDTO) {

        ProductDTO productDTO = new ProductDTO(insertProdDTO.getProdNm(), insertProdDTO.getProdPrc(),
                insertProdDTO.getCatNo(),insertProdDTO.getSelId(),insertProdDTO.getWay(),insertProdDTO.getNego()
        ,insertProdDTO.getProdCo(), insertProdDTO.getContent());

        productService.insertProduct(productDTO);
        return  productService.selectProductNo(productDTO);
    }


    // 다중 파일 업로드 "4.15" 아직 미완성
    @PostMapping("/prd/multiupload")
    public String MultifileUp(MultipartHttpServletRequest multi) {
        // 저장경로 설정
        String path = "*/product/resources/photo/";
        String fileName = ""; // 업로드 되는 파일명

        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        Iterator<String> files = multi.getFileNames();
        MultipartFile mpf = multi.getFile(files.next());

        if (mpf == null || mpf.getSize() <= 0) {
            System.out.println("파일용량 x");
            return "ajaxUpload";
        }

        List<MultipartFile> fileList = multi.getFiles("file");
        for (MultipartFile filePart : fileList) {
            fileName = filePart.getOriginalFilename(); // 원본 파일 명
            System.out.println("실제 파일 이름 : " + fileName);
            long fileSize = filePart.getSize(); // 파일 사이즈

            if (!fileName.equals("")) { //파일 쓰기
                try {
                    FileOutputStream fs = new FileOutputStream(path + fileName);
                    fs.write(filePart.getBytes());
                    fs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "ajaxUpload";
    }
}
