package hiphadi.menu.api.controller;

import hiphadi.menu.api.ApiResponse;
import hiphadi.menu.api.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.api.service.request.AddProductRequestDto;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

    private final ProductServiceImpl productService;

    //상품 추가
    @PostMapping("/add")
    public ApiResponse<String> addProduct(@RequestBody AddProductRequestDto addProductRequestDto) {
        productService.addProduct(addProductRequestDto);
        return ApiResponse.created("상품 추가 완료");
    }

    //상품 삭제
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> addProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ApiResponse.ok("상품 삭제 완료");
    }
}
