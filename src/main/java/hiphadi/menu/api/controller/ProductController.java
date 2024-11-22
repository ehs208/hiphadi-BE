package hiphadi.menu.api.controller;

import java.util.List;

import hiphadi.menu.api.ApiResponse;
import hiphadi.menu.api.service.ProductServiceImpl;
import hiphadi.menu.api.service.response.ProductResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.api.service.request.ProductRequest;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

    private static final int PAGE_DEFAULT_SIZE = 3;

    private final ProductServiceImpl productService;

    //상품 추가
    @PostMapping("/add")
    public ApiResponse<Void> addProduct(@RequestBody ProductRequest productRequest) {
        return ApiResponse.created(productService.createProduct(productRequest));
    }

    //상품 삭제
    @DeleteMapping("{id}")
    public ApiResponse<Void> addProduct(@PathVariable Long id) {
        return ApiResponse.ok(productService.deleteProduct(id));
    }

    //상품 수정
    @PutMapping("{id}")
    public ApiResponse<Void> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return ApiResponse.ok(productService.updateProduct(id, productRequest));
    }

    // // 상품 목록 조회 Legacy
    // @GetMapping("/list")
    // // public ApiResponse<List<ProductResponse>> getProductList(
    // //     @RequestParam(required = false) String categoryCursor,
    // //     @RequestParam(required = false) Long idCursor) {
    // //     List<ProductResponse> productListResponse = productService.findProductsByPage(categoryCursor, idCursor, PageRequest.of(0, PAGE_DEFAULT_SIZE));
    // //     return ApiResponse.ok(productListResponse);
    // // }

    @GetMapping("/list")
    public ApiResponse<List<ProductResponse>> getProductList() {
        List<ProductResponse> productListResponse = productService.getAllProducts();
        return ApiResponse.ok(productListResponse);
    }
}