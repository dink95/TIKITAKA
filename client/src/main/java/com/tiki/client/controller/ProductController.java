package com.tiki.client.controller;

import com.tiki.client.domain.InsertProductDTO;
import com.tiki.client.domain.ProductDTO;
import com.tiki.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/create") /*상품등록 페이지*/
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/create");
        return view;
    }

    @RequestMapping("/product/create.do")  /*상품등록*/
    public ModelAndView createProduct(InsertProductDTO productDTO, MultipartHttpServletRequest multi,
                                      HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        ModelAndView view = new ModelAndView();
        int result = 0;
        try {
            result = productService.createProduct(productDTO,idCookie.getValue(),tokenCookie.getValue());
            int catNo = productDTO.getCatNo();
            List<MultipartFile> fileList = multi.getFiles("file");
//           String path = "c:/tmp/"+Integer.toString(result)+"/";
            String path = "/Users/gimmugyeong/tmp/" +Integer.toString(result)+"/";
            File dir = new File(path);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }

            int count =1;
            for (MultipartFile filePart : fileList) {

                FileOutputStream fs = new FileOutputStream(path+Integer.toString(count)+".jpg");
                fs.write(filePart.getBytes());
                fs.close();
                count++;

            }
            if (result > 0) {
                view.addObject("resultCode", 200);
                view.addObject("catNo", productDTO.getCatNo());
                view.addObject("prodPrc",productDTO.getProdPrc());
                view.addObject("prodNo",result);

            }else {
                view.addObject("resultCode", 400);
            }
        } catch (Exception e) {
            view.addObject("resultCode", 500);
        }
        view.setViewName("product/create_result");
        return view;
    }

    @GetMapping(value = "/product/update") /*상품업데이트 페이지*/
    public ModelAndView update() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/update");
        return view;
    }

    @RequestMapping("/product/update.do")  /*상품업데이트*/
    public ModelAndView updateProduct(ProductDTO productDTO,
                                      HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        ModelAndView view = new ModelAndView();
        int result = 0;
        try {
            result = productService.updateProduct(productDTO, idCookie.getValue(), tokenCookie.getValue());

            if (result > 0) {
                view.addObject("resultCode", 201);
            }else {
                view.addObject("resultCode", 400);
            }
        } catch (Exception e) {
            view.addObject("resultCode", 500);
        }
        view.setViewName("product/create_result");
        return view;
    }

    @RequestMapping("/product/delete")  /*상품삭제*/
    @ResponseBody
    public Map<String,Object>deleteProduct(@RequestParam(value = "prodNo") Integer prodNo,
                                           HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        Map<String,Object> resultMap = new HashMap<>();
        int result = 0;

        try {
            result = productService.deleteProduct(prodNo,idCookie.getValue(),tokenCookie.getValue());

            if (result > 0) {
                resultMap.put("resultCode", 200);
            }else {
                resultMap.put("resultCode", 500);
            }
        } catch (Exception e) {
            resultMap.put("resultCode", 500);
        }
        return resultMap;
    }



    @GetMapping(value = "/product/list") /*상품 리스트 페이지*/
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/list");
        return view;
    }

    @RequestMapping("/product/list.do")  /*상품 이미지 리스트 */
    @ResponseBody
    public Map<String, Object> productList(ProductDTO productDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list= productService.productList();
            resultMap.put("dataList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/product/list.query")  /*상품 검색 리스트 */
    @ResponseBody
    public Map<String,Object> producQuerytList(@RequestParam(value = "prodNm") String prodNm, @RequestParam(value = "catNo") Integer catNo) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list= productService.productQuerytList(prodNm, catNo);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/highprice/list.query")  /*상품 검색 리스트 높은 가격순*/
    @ResponseBody
    public Map<String,Object> producQuerytListHighPrice(@RequestParam(value = "prodNm") String prodNm, @RequestParam(value = "catNo") Integer catNo) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list= productService.productQuerytListHighPrice(prodNm, catNo);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/lowprice/list.query")  /*상품 검색 리스트 낮은 가격순*/
    @ResponseBody
    public Map<String,Object> producQuerytListLowPrice(@RequestParam(value = "prodNm") String prodNm, @RequestParam(value = "catNo") Integer catNo) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list= productService.productQuerytListLowPrice(prodNm, catNo);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/list.prodNm")  /*상품이름 검색 리스트 */
    @ResponseBody
    public Map<String,Object> ProdNmQueryList(@RequestParam(value = "prodNm") String prodNm) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQueryProdNmList(prodNm);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/highprice/list.prodNm")  /*상품이름 검색 리스트 높은 가격순 */
    @ResponseBody
    public Map<String,Object> ProdNmQueryListHighPrice(@RequestParam(value = "prodNm") String prodNm) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQueryProdNmListHighPrice(prodNm);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/lowprice/list.prodNm")  /*상품이름 검색 리스트 낮은 가격순 */
    @ResponseBody
    public Map<String,Object> ProdNmQueryListLowPrice(@RequestParam(value = "prodNm") String prodNm) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQueryProdNmListLowPrice(prodNm);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    @RequestMapping("/product/list.category")  /*상품 카테고리 검색 리스트 */
    @ResponseBody
    public Map<String,Object> CatQuerytList(@RequestParam(value = "catNo") Integer catNo) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQueryCatNoList(catNo);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/highprice/list.category")  /*상품 카테고리 검색 리스트 높은 가격순 */
    @ResponseBody
    public Map<String,Object> CatQuerytListHighPrice(@RequestParam(value = "catNo") Integer catNo) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQueryCatNoListHighPrice(catNo);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/lowprice/list.category")  /*상품 카테고리 검색 리스트 낮은 가격순 */
    @ResponseBody
    public Map<String,Object> CatQuerytListlowPrice(@RequestParam(value = "catNo") Integer catNo) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQueryCatNoListLowPrice(catNo);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/list.selId")  /*상품 아이디 검색 리스트 */
    @ResponseBody
    public Map<String,Object> IdQuerytList(@RequestParam(value = "selId") String selId) {

        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQuerySelIdList(selId);

            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/auc/list.selId")  /*경매상품 아이디 검색 리스트 */
    @ResponseBody
    public Map<String,Object> IdQuerytListAuc(@RequestParam(value = "selId") String selId) {

        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQuerySelIdListAuc(selId);

            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/finish/list.selId")  /*상품 아이디 검색 리스트(판매완료) */
    @ResponseBody
    public Map<String,Object> IdQuerytListFinish(@RequestParam(value = "selId") String selId) {
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= productService.productQuerySelIdListFinish(selId);
            resultMap.put("dataQueryList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    @GetMapping(value = "/product/detail") /*상품 상세 페이지*/
    public ModelAndView detail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/detail");
        return view;
    }


    @RequestMapping(value = "/product/detail.do") /*상품 상세 정보*/
    @ResponseBody
    public Map<String,Object> productDetail(@RequestParam(value = "prodNo") Integer prodNo) {

        Map<String ,Object> resultMap = new HashMap<>();
        try {
            ProductDTO productDTO = productService.productDetail(prodNo);
            resultMap.put("dataDetail", productDTO);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/product/prodfinish")  /*판매완료업데이트*/
    @ResponseBody
    public Map<String,Object> updateProdfinish(@RequestParam(value="prodNo") int prodNo,
                                               HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        HashMap<String,Object> resultMap = new HashMap<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSelId(idCookie.getValue());
        productDTO.setProdNo(prodNo);
        int result = 0;
        try {
            result = productService.updateProdfinish(productDTO,idCookie.getValue(), tokenCookie.getValue());
            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "판매완료");
            }else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "판매완료 400");
            }
        } catch (Exception e) {
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "판매완료 500");
        }
        return resultMap;
    }

}