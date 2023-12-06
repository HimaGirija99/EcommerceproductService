package com.example.eCommerceSite.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    IProductService productService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    void getAllProducts() throws Exception {
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product());
//        products.add(new Product());
//        products.add(new Product());
//
//        when(productService.getAllProducts()).thenReturn(products);
//
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectMapper.writeValueAsString(products)));
//    }
//
//    @Test
//    void createProduct() throws Exception {
//        Product productToCreate = new Product();
//        productToCreate.setTitle("iPhone 15 Pro Max");
//        productToCreate.setImageUrl("some image");
//        productToCreate.setDescription("Best iPhone Ever");
//
//        Product expectedProduct = new Product();
//        expectedProduct.setId(1L);
//        expectedProduct.setTitle("iPhone 15 Pro Max");
//        expectedProduct.setImageUrl("some image");
//        expectedProduct.setDescription("Best iPhone Ever");
//
//        when(productService.addNewProduct(any(Product.class))).thenReturn(expectedProduct);
//
//        mockMvc.perform(
//                post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(productToCreate)))
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
//                .andExpect(jsonPath("$.student.name", is("Nikhil")))
//                .andExpect(jsonPath("$.length()", is(2)));
//
//    }
}

// {
//   student: {
//      name: ,
//      email: ,
//      age: ,
//   }
// }

