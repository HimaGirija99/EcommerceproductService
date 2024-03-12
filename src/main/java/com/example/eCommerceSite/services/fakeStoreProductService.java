package com.example.eCommerceSite.services;

import com.example.eCommerceSite.clients.fakestore.Dto.FakeStoreProductDto;
import com.example.eCommerceSite.clients.fakestore.client.FakeStoreClient;
import com.example.eCommerceSite.models.Categories;
import com.example.eCommerceSite.models.Product;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Primary
@Service
public class fakeStoreProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreClient fakeStoreClient;
    @Autowired
    public fakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public List<Product> getAllProducts() {
       /*RestTemplate restTemplate = restTemplateBuilder.build();
       ResponseEntity<ProductDto[]> productDtos =
                restTemplate
                       .getForEntity("https://fakestoreapi.com/products", ProductDto[].class);*/


        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        List<Product> answer = new ArrayList<>();
        for (@Nonnull FakeStoreProductDto productDto: fakeStoreProductDtos) {
            Product product = getProduct(productDto);
            answer.add(product);
        }
        return answer;
    }


    @Override
    public Product getSingleProduct(Long productId) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> productDto =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class, productId);

        return getProduct(Objects.requireNonNull(productDto.getBody()));
    }

//    @Override
//    public Product addNewProduct(IClientProductDto productDto) {
//        RestTemplate restTemplate= restTemplateBuilder.build();
//        //restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
//        // saving the data for db
//        Product product = getProduct((FakeStoreProductDto) productDto);
//        return product;
//    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }


    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
                = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        assert fakeStoreProductDto1 != null;
        return getProduct(fakeStoreProductDto1);
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }

    private Product getProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }
}


